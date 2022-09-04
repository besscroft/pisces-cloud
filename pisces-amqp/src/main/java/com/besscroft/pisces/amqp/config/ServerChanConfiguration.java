package com.besscroft.pisces.amqp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Server 酱配置
 * @Author Bess Croft
 * @Date 2022/9/4 12:25
 */
@Configuration
public class ServerChanConfiguration {

    /** sendKey */
    public static String sendKey;

    /** 推送地址 */
    public static String pushUrl;

    @Value("${pisces.amqp.channel.server-chan.send-key}")
    public void setSendKey(String sendKey) {
        ServerChanConfiguration.sendKey = sendKey;
    }

    @Value("${pisces.amqp.channel.server-chan.push-url}")
    public void setPushUrl(String pushUrl) {
        ServerChanConfiguration.pushUrl = pushUrl;
    }
}
