package com.besscroft.pisces.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class PiscesGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiscesGatewayApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  Pisces 网关启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webBuilder(){
        return WebClient.builder();
    }

}
