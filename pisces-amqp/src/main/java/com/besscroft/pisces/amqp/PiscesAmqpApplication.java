package com.besscroft.pisces.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PiscesAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiscesAmqpApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  Pisces Cloud 消息中心启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
