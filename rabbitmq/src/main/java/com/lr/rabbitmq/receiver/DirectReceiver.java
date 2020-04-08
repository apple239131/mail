package com.lr.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
    @RabbitListener(queues = "hello.apple")
    public void handler1(String msg) {
        System.out.println("收到handler1--------> "+msg);
    }

}
