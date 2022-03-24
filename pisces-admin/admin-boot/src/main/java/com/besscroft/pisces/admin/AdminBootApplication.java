package com.besscroft.pisces.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.besscroft.pisces")
@MapperScan("com.besscroft.pisces.admin.mapper")
public class AdminBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  Pisces Cloud 业务系统启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
