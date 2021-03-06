package com.qiaohx.util.constant;

/**
 * 消息队列的常量统一配置
 */
public class MQConstant {

    // 交换机名称
    public final static String EXCHANGE_AMQ_DIRECT = "amq.direct";
    // 绑定的值
    public static final String ROUTING_KEY_TEST_ROUTING_KEY = "test.bind.key";
    // 队列名称
    public final static String QUEUE_TEST_QUEUE = "test.queue";


    // 开通博客路由
    public static final String ROUTING_KEY_BLOG_OPEN_ROUTING_KEY = "blog.open";
    // 队列名称
    public final static String QUEUE_BLOG_OPEN_QUEUE = "queue.blog.open";
}
