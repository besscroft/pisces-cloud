package com.besscroft.pisces.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class PiscesMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiscesMonitorApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  Pisces 监控 UI 启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
