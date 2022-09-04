package com.besscroft.pisces.framework.common.constant;

/**
 * @Description 消息枚举
 * @Author Bess Croft
 * @Date 2022/9/4 11:30
 */
public interface AMQPConstant {

    /** 消息 */
    String MESSAGE_QUEUE = "pieces-queue-message";

    /** 短信 */
    String SMS_QUEUE = "pisces-queue-sms";

    /** 邮件 */
    String EMAIL_QUEUE = "pisces-queue-email";

    /** server 酱 */
    String SERVER_CHAN_QUEUE = "pisces-queue-server-chan";

    /** Bark */
    String BARK_QUEUE = "pisces-queue-bark";

    /** Telegram */
    String TG_QUEUE = "pisces-queue-tg";

    /** 企业微信 */
    String WORK_WEIXIN_QUEUE = "pisces-queue-work-weixin";

}
