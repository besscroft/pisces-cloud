package com.besscroft.pisces.amqp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description tg 配置
 * @Author Bess Croft
 * @Date 2022/9/4 12:29
 */
@Configuration
public class TgConfiguration {

    /** token */
    private static String token;

    /** chatId */
    private static String chatId;

    @Value("${pisces.amqp.channel.tg.token}")
    public void setToken(String token) {
        TgConfiguration.token = token;
    }

    @Value("${pisces.amqp.channel.tg.chat-id}")
    public void setChatId(String chatId) {
        TgConfiguration.chatId = chatId;
    }

}
