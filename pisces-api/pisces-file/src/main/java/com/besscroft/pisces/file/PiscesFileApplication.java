package com.besscroft.pisces.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableDiscoveryClient
@EnableConfigurationProperties
@ComponentScan("com.besscroft.pisces")
@SpringBootApplication(scanBasePackages = "com.besscroft.pisces")
public class PiscesFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiscesFileApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  Pisces 分布式文件中心启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
