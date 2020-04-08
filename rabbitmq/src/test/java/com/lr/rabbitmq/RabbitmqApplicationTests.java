package com.lr.rabbitmq;

import com.lr.rabbitmq.config.RabbitFanoutConfig;
import com.lr.rabbitmq.config.RabbitHeaderConfig;
import com.lr.rabbitmq.config.RabbitTopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        //消息到达Exchange后，会被转发到与该消息rotingKey相同的Queue上
        rabbitTemplate.convertAndSend("hello.apple","aaaaaaaaaaaaaaaa");
    }

    @Test
    void testFanout() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTNAME,null,"fanout");
    }

    @Test
    void testTopic() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"xiaomi.apple","杂粮");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"huawei.aaa","蛤为");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"apple.phone","apple");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"huawei.phone","huawei-apple");
    }

    @Test
    public void testHeader() {
        Message nameMsg = MessageBuilder.withBody("hello apple !".getBytes()).setHeader("name","apple").build();
        Message ageMsg = MessageBuilder.withBody("hello 99 !".getBytes()).setHeader("age","99").build();
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, ageMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, nameMsg);
    }

}
