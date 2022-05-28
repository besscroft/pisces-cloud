package com.besscroft.pisces.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.besscroft.pisces.gateway.handler.SentinelExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Sentinel Gateway 适配模块配置
 * @Author Bess Croft
 * @Date 2022/5/28 12:16
 */
@Configuration
public class SentinelGatewayConfiguration {

    @Bean
    public BlockRequestHandler blockRequestHandler() {
        return new SentinelExceptionHandler();
    }

}
