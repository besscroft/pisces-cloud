package com.besscroft.pisces.admin.sender;

/**
 * @Description 消息发送服务
 * @Author Bess Croft
 * @Date 2022/9/4 11:26
 */
public interface MessageSender {

    /**
     * 发送消息
     * @param message 消息内容
     */
    void send(String message);

    /**
     * 发送短信
     * @param message 短信内容
     */
    void sendSMS(String message);

    /**
     * 发送邮件
     * @param email 邮件
     */
    void sendEmail(String email);

    /**
     * 发送消息到 Server 酱
     * @param message 消息内容/
     */
    void sendServerChan(String message);

    /**
     * 发送消息到 Bark
     * @param message 消息内容
     */
    void sendBark(String message);

    /**
     * 发送消息到 tg
     * @param message 消息内容
     */
    void sendTG(String message);

    /**
     * 发送消息到企业微信
     * @param message 消息内容
     */
    void sendWorkWeixin(String message);

}
