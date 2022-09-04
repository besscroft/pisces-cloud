package com.besscroft.pisces.amqp.listener;

import com.besscroft.pisces.amqp.config.BarkConfiguration;
import com.besscroft.pisces.amqp.service.PushService;
import com.besscroft.pisces.framework.common.constant.AMQPConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description 消息接收监听器
 * @Author Bess Croft
 * @Date 2022/9/3 15:10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageReceiverListener {

    private final PushService pushService;

    @RabbitHandler
    @RabbitListener(queues = AMQPConstant.MESSAGE_QUEUE)
    public void processMessage(String message) {
        log.info("Message Receiver:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = AMQPConstant.SMS_QUEUE)
    public void processSms(String message) {
        log.info("Sms Receiver:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = AMQPConstant.EMAIL_QUEUE)
    public void processEmail(String message) {
        log.info("Email Receiver:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = AMQPConstant.SERVER_CHAN_QUEUE)
    public void processServerChan(String message) {
        log.info("ServerChan Receiver:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = AMQPConstant.BARK_QUEUE)
    public void processBark(String message) {
        pushService.pushBark(BarkConfiguration.sendKey, message);
        log.info("Bark Receiver:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = AMQPConstant.TG_QUEUE)
    public void processTG(String message) {
        log.info("TG Receiver:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = AMQPConstant.WORK_WEIXIN_QUEUE)
    public void processWorkWeixin(String message) {
        log.info("WorkWeixin Receiver:{}", message);
    }

}
