package com.besscroft.pisces.gateway.filter;

import com.besscroft.pisces.framework.common.constant.AuthConstants;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * @Description 全局 token 过滤器
 * @Author Bess Croft
 * @Date 2022/3/4 22:33
 */
@Slf4j
@Component
public class AuthGlobalFilter implements WebFilter, Ordered {

    /**
     * 过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        log.info("请求进来了.[token={}]", token);
        if (StringUtils.isBlank(token)) {
            return chain.filter(exchange);
        }
        try {
            String repToken = token.replace(AuthConstants.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(repToken);
            String user = jwsObject.getPayload().toString();
            log.info("token中获取的[user={}]", user);
            ServerHttpRequest request = exchange.getRequest().mutate().header(AuthConstants.USER_TOKEN_HEADER, user).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return chain.filter(exchange);
    }

    /**
     * 过滤器执行顺序，数值越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
