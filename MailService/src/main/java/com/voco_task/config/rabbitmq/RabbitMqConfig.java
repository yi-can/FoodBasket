package com.voco_task.config.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    private String queueMail = "queueMail";
    @Bean
    Queue queueMail(){
        return new Queue(queueMail);
    }

}


