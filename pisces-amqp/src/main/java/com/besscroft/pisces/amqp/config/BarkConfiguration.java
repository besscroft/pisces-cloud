package com.besscroft.pisces.amqp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Bark 配置
 * @Author Bess Croft
 * @Date 2022/9/4 12:28
 */
@Configuration
public class BarkConfiguration {

    /** sendKey */
    public static String sendKey;

    /** 推送地址 */
    public static String pushUrl;

    @Value("${pisces.amqp.channel.bark.send-key}")
    public void setSendKey(String sendKey) {
        BarkConfiguration.sendKey = sendKey;
    }

    @Value("${pisces.amqp.channel.bark.push-url}")
    public void setPushUrl(String pushUrl) {
        BarkConfiguration.pushUrl = pushUrl;
    }
}
