package com.besscroft.pisces.gateway.authorization;

import com.besscroft.pisces.framework.common.constant.AuthConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * @Description 认证、鉴权管理
 * @Url https://github.com/spring-projects/spring-security/pull/9630
 * @Author Bess Croft
 * @Date 2022/2/3 20:24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();

        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            log.info("预检请求放行.[path={}]", path);
            return Mono.just(new AuthorizationDecision(true));
        }

        // todo 白名单
        List<String> whiteUrlList = new ArrayList<>();
        whiteUrlList.add("/pisces-admin/user/login");
        whiteUrlList.add("/pisces-auth/publicKey/get");
        for (String whiteUrl : whiteUrlList) {
            if (pathMatcher.match(whiteUrl, path)) {
                log.info("白名单路径匹配成功，放行[path={}]", path);
                return Mono.just(new AuthorizationDecision(true));
            }
        }

        // token 为空拒绝访问
        String token = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            log.error("token 为空拒绝访问.[path={}]", path);
            return Mono.just(new AuthorizationDecision(false));
        }

        // 从 Redis 获取资源角色关系列表（即角色能访问的对应接口）
        Map<Object, Object> roleResourceMap = redisTemplate.opsForHash().entries(AuthConstants.PERMISSION_RULES_KEY);
        // todo 权限关系列表为空时，做一些处理

        Iterator<Object> iterator = roleResourceMap.keySet().iterator();

        // 接口需要的角色权限集合 authorities 统计
        // 多角色改造支持，本项目使用的是 RBAC 方案，在复杂场景下，应使用更细粒度的 ABAC 控制方案：https://en.wikipedia.org/wiki/Attribute-based_access_control
        Set<String> authorities = new HashSet<>();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, path)) {
                String value = String.valueOf(roleResourceMap.get(pattern))
                        .replaceAll("^\\[|]$", "").replaceAll(" ", "");
                List<String> list = new ArrayList<>(Arrays.asList(value.split(",")));
                authorities.addAll(list);
            }
        }

        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleId -> {
                    // roleId 是请求用户的角色(格式:ROLE_{roleId})，authorities 是请求资源所需要角色的集合
                    log.info("访问路径[path={}]", path);
                    log.info("用户角色信息[roleId={}]", roleId);
                    log.info("资源需要权限[authorities={}]", authorities);
                    return authorities.contains(roleId);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}
