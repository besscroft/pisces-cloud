package com.besscroft.pisces.gateway.filter;

import com.besscroft.pisces.framework.common.constant.AuthConstants;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 白名单 JWT 请求头过滤器
 * @Author Bess Croft
 * @Date 2022/2/3 22:40
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 白名单路径移除 JWT 请求头
        // todo 白名单
        List<String> urls = new ArrayList<>();
        for (String url: urls) {
            // 退出登录不移除请求头
            if (pathMatcher.match("/pisces-admin/user/logout", uri.getPath())) {
                return webFilterChain.filter(serverWebExchange);
            }
            if (pathMatcher.match(url, uri.getPath())) {
                request = serverWebExchange.getRequest().mutate().header(AuthConstants.JWT_TOKEN_HEADER, "").build();
                serverWebExchange = serverWebExchange.mutate().request(request).build();
                return webFilterChain.filter(serverWebExchange);
            }
        }
        return webFilterChain.filter(serverWebExchange);
    }

}
