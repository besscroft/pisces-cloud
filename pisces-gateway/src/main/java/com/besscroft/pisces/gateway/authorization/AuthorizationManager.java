package com.besscroft.pisces.gateway.authorization;

import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import com.besscroft.pisces.framework.common.dto.WhiteDictDto;
import com.besscroft.pisces.gateway.retry.AuthRetry;
import com.besscroft.pisces.gateway.retry.WhiteRetry;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

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
    private final AuthRetry authRetry;
    private final WhiteRetry whiteRetry;

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

        // 登录放行策略
        if (pathMatcher.match("/pisces-admin/user/login", path)) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 白名单
        List<WhiteDictDto> whiteDictDtoList = (List<WhiteDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.WHITE);
        if (CollectionUtils.isEmpty(whiteDictDtoList)) {
            boolean b = whiteRetry.retryWhiteTask();
            log.info("异步远程载入白名单缓存:{}", b);
        }
        whiteDictDtoList = (List<WhiteDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.WHITE);
        List<String> whiteUrlList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(whiteDictDtoList)) {
            whiteUrlList = whiteDictDtoList.stream().map(WhiteDictDto::getPath).collect(Collectors.toList());
        }
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

        // 从 Redis 获取资源角色列表（即访问接口所需要的角色）
        List<String> roleResourceList = (List<String>) redisTemplate.opsForHash().get(AuthConstants.PERMISSION_RULES_KEY, path);
        // 权限关系列表为空时，做一些处理
        if (CollectionUtils.isEmpty(roleResourceList)) {
            Map<Object, Object> roleResourceMap = redisTemplate.opsForHash().entries(AuthConstants.PERMISSION_RULES_KEY);
            if (CollectionUtils.isEmpty(roleResourceMap)) {
                boolean b = authRetry.retryAuthTask();
                log.info("异步远程载入权限缓存:{}", b);
                if (pathMatcher.match("/pisces-admin/user/info", path)) {
                    return Mono.just(new AuthorizationDecision(true));
                }
                roleResourceList = (List<String>) redisTemplate.opsForHash().get(AuthConstants.PERMISSION_RULES_KEY, path);
            } else {
                // 如果是 restful 风格，只能模式匹配
                for (Object o : roleResourceMap.keySet()) {
                    String pattern = (String) o;
                    if (pathMatcher.match(pattern, path)) {
                        List<String> list = (List<String>) roleResourceMap.get(pattern);
                        if (CollectionUtils.isEmpty(roleResourceList)) {
                            roleResourceList = new ArrayList<>(list);
                            break;
                        }
                        roleResourceList.addAll(list);
                    }
                }
            }
        }

        // 接口需要的角色权限集合 authorities 统计
        // 多角色改造支持，本项目使用的是 RBAC 方案，在复杂场景下，应使用更细粒度的 ABAC 控制方案：https://en.wikipedia.org/wiki/Attribute-based_access_control
        Set<String> authorities = new HashSet<>(roleResourceList);

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
