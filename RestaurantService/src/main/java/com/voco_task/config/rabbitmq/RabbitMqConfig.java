package com.voco_task.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //AUTHDAN RESTAURANT'A Restaurant
    private String queueRestaurant = "queueRestaurant";
    @Bean
    Queue queueUser(){
        return new Queue(queueRestaurant);
    }

}


