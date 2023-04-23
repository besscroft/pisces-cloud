package com.besscroft.pisces.amqp.config;

import com.besscroft.pisces.framework.common.constant.AMQPConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description rabbitmq 配置
 * @Author Bess Croft
 * @Date 2022/9/3 23:54
 */
@Configuration
@RequiredArgsConstructor
public class RabbitQueueConfiguration {

    /**
     * 消息队列
     * @return Queue
     */
    @Bean
    public Queue directMessageQueue() {
        return new Queue(AMQPConstant.MESSAGE_QUEUE);
    }

    /**
     * 短信队列
     * @return Queue
     */
    @Bean
    public Queue directSMSQueue() {
        return new Queue(AMQPConstant.SMS_QUEUE);
    }

    /**
     * 邮件队列
     * @return Queue
     */
    @Bean
    public Queue directEmailQueue() {
        return new Queue(AMQPConstant.EMAIL_QUEUE);
    }

    /**
     * server 酱队列
     * @return Queue
     */
    @Bean
    public Queue directServerChanQueue() {
        return new Queue(AMQPConstant.SERVER_CHAN_QUEUE);
    }

    /**
     * Bark 队列
     * @return Queue
     */
    @Bean
    public Queue directBarkQueue() {
        return new Queue(AMQPConstant.BARK_QUEUE);
    }

    /**
     * Telegram 队列
     * @return Queue
     */
    @Bean
    public Queue directTGQueue() {
        return new Queue(AMQPConstant.TG_QUEUE);
    }

    /**
     * 企业微信队列
     * @return Queue
     */
    @Bean
    public Queue directWorkWeixinQueue() {
        return new Queue(AMQPConstant.WORK_WEIXIN_QUEUE);
    }

}
