package com.lr.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * headers
 * headers exchange主要通过发送的request message中的header进行匹配，其中匹配规则（x-match）又分为all和any，
 * all代表必须所有的键值对匹配，any代表只要有一个键值对匹配即可。headers exchange的默认匹配规则（x-match）是any。
 */
@Configuration
public class RabbitHeaderConfig {
    public static final String HEADERNAME="apple-header";
    @Bean
    Queue queueName() {
        return new  Queue("name-queue");
    }

    @Bean
    Queue queueAge() {
        return new Queue("age-queue");
    }

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERNAME,true,false);
    }

    @Bean
    Binding bindingName() {
        Map<String,Object> map=new HashMap<>();
        map.put("name","apple");
        //消息的header只要有一个key-value匹配map，就把消路由到对应queue,whereAll，表示key-value都要匹配
        return BindingBuilder.bind(queueName()).to(headersExchange()).whereAny(map).match();
    }
    @Bean
    Binding bindingAge() {
       // 只要有一个一个字段匹配，不管value就把消路由到对应queue
        return BindingBuilder.bind(queueAge()).to(headersExchange()).where("age").exists();
    }






}
