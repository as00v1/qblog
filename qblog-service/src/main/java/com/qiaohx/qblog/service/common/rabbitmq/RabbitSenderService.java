package com.qiaohx.qblog.service.common.rabbitmq;

import com.qiaohx.util.constant.MQConstant;
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
    public void send(String message) {
        amqpTemplate.convertAndSend(MQConstant.EXCHANGE_AMQ_DIRECT, MQConstant.ROUTING_KEY_TEST_ROUTING_KEY, message);
    }

}
