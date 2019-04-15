package com.qiaohx.qblog.service.common.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MQ生产者
 */
@Component
public class RabbitSenderService {

    // 此接口的默认实现是RabbitTemplate，目前只有一个实现.
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     *
     * @param message 消息体
     */
    public void send(String exchangeName, String routingKey, String message) {
        amqpTemplate.convertAndSend(exchangeName, routingKey, message);
    }

}
