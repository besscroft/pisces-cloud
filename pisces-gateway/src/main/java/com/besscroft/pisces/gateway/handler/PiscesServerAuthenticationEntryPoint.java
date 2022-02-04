package com.besscroft.pisces.gateway.handler;

import cn.hutool.json.JSONUtil;
import com.besscroft.pisces.result.AjaxResult;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @Description 未认证处理器
 * @Author Bess Croft
 * @Date 2022/2/3 22:24
 */
public class PiscesServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint  {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        return Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response.setStatusCode(HttpStatus.OK);
                    response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    response.getHeaders().set("Access-Control-Allow-Origin", "*");
                    response.getHeaders().set("Cache-Control", "no-cache");
                    String body = JSONUtil.toJsonStr(AjaxResult.error(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase()));
                    DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
                    return response.writeWith(Mono.just(buffer))
                            .doOnError(error -> DataBufferUtils.release(buffer));
                });
    }

}
