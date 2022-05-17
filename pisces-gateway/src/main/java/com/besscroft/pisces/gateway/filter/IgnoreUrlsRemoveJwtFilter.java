package com.besscroft.pisces.gateway.filter;

import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.besscroft.pisces.framework.common.constant.SystemDictConstants;
import com.besscroft.pisces.framework.common.dto.WhiteDictDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 白名单 JWT 请求头过滤器
 * @Author Bess Croft
 * @Date 2022/2/3 22:40
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    private final WebClient.Builder webBuilder;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 白名单路径移除 JWT 请求头
        // 退出登录不移除请求头
        if (pathMatcher.match("/pisces-admin/user/logout", uri.getPath())) {
            return webFilterChain.filter(serverWebExchange);
        }
        // 白名单
        List<WhiteDictDto> whiteDictDtoList = (List<WhiteDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.WHITE);
        if (CollectionUtils.isEmpty(whiteDictDtoList)) {
            Object result = webBuilder.build().get()
                    .uri("lb://pisces-admin/white/getWhiteDict")
                    .retrieve()
                    .bodyToMono(Object.class).subscribe();
        }
        whiteDictDtoList = (List<WhiteDictDto>) redisTemplate.opsForValue().get(SystemDictConstants.WHITE);
        if (!CollectionUtils.isEmpty(whiteDictDtoList)) {
            List<String> urls = whiteDictDtoList.stream().map(WhiteDictDto::getPath).collect(Collectors.toList());
            for (String url: urls) {
                if (pathMatcher.match(url, uri.getPath())) {
                    request = serverWebExchange.getRequest().mutate().header(AuthConstants.JWT_TOKEN_HEADER, "").build();
                    serverWebExchange = serverWebExchange.mutate().request(request).build();
                    return webFilterChain.filter(serverWebExchange);
                }
            }
        }
        return webFilterChain.filter(serverWebExchange);
    }

}
