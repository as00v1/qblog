package com.qiaohx.qblog.qblogweb;

import com.qiaohx.qblog.service.common.rabbitmq.RabbitSenderService;
import com.qiaohx.util.constant.MQConstant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitTest extends QblogWebApplicationTests {

    @Autowired
    private RabbitSenderService rabbitKit;

    @Test
    public void receive(){
//        String testContent = "send msg via spring boot";
//        rabbitKit.send(testContent);
        while(true){

        }
    }

//    @Test
//    public void send(){
//        for (int i = 0; i < 3; i++) {
//            String testContent = "send msg via spring boot test " + i;
//            rabbitKit.send(MQConstant.EXCHANGE_AMQ_DIRECT,MQConstant.ROUTING_KEY_TEST_ROUTING_KEY, testContent);
//        }
//        try {
//            Thread.sleep(10 * 1000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}
