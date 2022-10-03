package com.besscroft.pisces.admin.sender.impl;

import com.besscroft.pisces.admin.sender.MessageSender;
import com.besscroft.pisces.framework.common.constant.AMQPConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/9/4 11:27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {

    private final AmqpTemplate rabbitTemplate;

    @Override
    @Async
    public void send(String message) {
        rabbitTemplate.convertAndSend(AMQPConstant.MESSAGE_QUEUE, message);
    }

    @Override
    @Async
    public void sendSMS(String message) {
        rabbitTemplate.convertAndSend(AMQPConstant.SMS_QUEUE, message);
    }

    @Override
    @Async
    public void sendEmail(String email) {
        rabbitTemplate.convertAndSend(AMQPConstant.EMAIL_QUEUE, email);
    }

    @Override
    @Async
    public void sendServerChan(String message) {
        rabbitTemplate.convertAndSend(AMQPConstant.SERVER_CHAN_QUEUE, message);
    }

    @Override
    @Async
    public void sendBark(String message) {
        rabbitTemplate.convertAndSend(AMQPConstant.BARK_QUEUE, message);
    }

    @Override
    @Async
    public void sendTG(String message) {
        rabbitTemplate.convertAndSend(AMQPConstant.TG_QUEUE, message);
    }

    @Override
    @Async
    public void sendWorkWeixin(String message) {
        rabbitTemplate.convertAndSend(AMQPConstant.WORK_WEIXIN_QUEUE, message);
    }

}
