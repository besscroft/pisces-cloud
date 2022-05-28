package com.besscroft.pisces.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.besscroft.pisces.framework.common.result.AjaxResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/5/28 16:06
 */
public class SentinelExceptionHandler implements BlockRequestHandler {

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
        return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(AjaxResult.error(
                        HttpStatus.TOO_MANY_REQUESTS.value(),
                        getMessage(throwable)
                )));
    }

    private String getMessage(Throwable throwable) {
        if (throwable instanceof FlowException) {
            return "您的访问过于频繁，请稍后重试";
        } else if (throwable instanceof DegradeException) {
            return "调用服务响应异常,已进行降级";
        } else if (throwable instanceof ParamFlowException) {
            return "您对热点参数访问过于频繁，请稍后重试";
        } else if (throwable instanceof SystemBlockException) {
            return "已触碰系统负载保护，请稍后重试";
        } else if (throwable instanceof AuthorityException) {
            return "授权规则检测不同，请检查访问参数";
        } else {
            return "非法访问，请稍后重试";
        }
    }

}
