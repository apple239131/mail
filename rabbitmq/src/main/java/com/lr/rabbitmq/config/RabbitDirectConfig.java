package com.lr.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct--路由模式
 * 任何发送到Direct Exchange的消息都会被转发到RouteKey指定的Queue。
 * 这种模式下不需要将Exchange进行任何绑定(binding)操作。
 * 消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
 * 如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。
 * 消息到达Exchange后，会被转发到与该消息rotingKey相同的Queue上
 */
@Configuration
public class RabbitDirectConfig {
    public static final String DIRECTNAME="apple-2391";

    @Bean
    Queue queue() {
        return new Queue("hello.apple");
    }


    @Bean
    DirectExchange directExchange() {
        //durable:重启之后是否依然有效，autoDelete:长期不用是否自动删除
        return new DirectExchange(DIRECTNAME,true,false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
    }
}
