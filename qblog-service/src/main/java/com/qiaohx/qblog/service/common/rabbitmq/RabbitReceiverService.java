package com.qiaohx.qblog.service.common.rabbitmq;

import com.qiaohx.util.constant.MQConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * MQ消费者
 */
@Component
public class RabbitReceiverService {

    /**
     * === 在RabbitMQ上创建queue,exchange,binding 方法二：直接在@RabbitListener声明 begin ===
     * 接收
     * @param content
     */
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            bindings = @QueueBinding(
                    value = @Queue(value = MQConstant.QUEUE_TEST_QUEUE, durable = "true", autoDelete="false"),
                    exchange = @Exchange(value = MQConstant.EXCHANGE_AMQ_DIRECT, type = ExchangeTypes.DIRECT),
                    key = MQConstant.ROUTING_KEY_TEST_ROUTING_KEY)
    )
    public void receive(String content) throws Exception{
        // ...
        System.out.println("[ReceiveMsg-1] receive msg: " + content);
        throw new Exception("假设出错");
    }

    /**
     * durable 消息持久化
     * autoDelete 自动删除 配置为true，则消费完毕队列就会被删除  false时不会删除此队列
     * @param content
     */
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            bindings = @QueueBinding(
                    value = @Queue(value = MQConstant.QUEUE_TEST_QUEUE, durable = "true", autoDelete="false"),
                    exchange = @Exchange(value = MQConstant.EXCHANGE_AMQ_DIRECT, type = ExchangeTypes.DIRECT),
                    key = MQConstant.ROUTING_KEY_TEST_ROUTING_KEY)
    )
    public void receive2(String content) {
        // ...
        System.out.println("[ReceiveMsg-2] receive msg: " + content);
    }
}
