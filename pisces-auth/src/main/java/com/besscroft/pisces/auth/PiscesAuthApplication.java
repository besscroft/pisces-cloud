package com.besscroft.pisces.auth;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.besscroft.pisces.auth")
@MapperScan("com.besscroft.pisces.auth.mapper")
public class PiscesAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiscesAuthApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  Pisces 认证中心启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
