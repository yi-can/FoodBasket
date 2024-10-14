package com.voco_task.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    //AUTHDAN USER'A
    private String queueUser = "queueUser";
    @Bean
    Queue queueUser(){
        return new Queue(queueUser);
    }

    //USERDAN AUTH'A UPDATE
    private String exchangeUser = "exchangeUser";
    private String queueAuth = "queueAuth";
    private String updateAuthBindingKey = "createRestaurantBindingKey";

    @Bean
    Queue queueAuth(){
        return new Queue(queueAuth);
    }
    @Bean
    DirectExchange exchangeUser(){
        return new DirectExchange(exchangeUser);
    }
    @Bean
    public Binding restaurantBindingKey(final Queue queueAuth, final DirectExchange exchangeUser){
        return BindingBuilder.bind(queueAuth).to(exchangeUser).with(updateAuthBindingKey);
    }

    //USERDAN AUTH'A CONTROL
    private String queueAuthControl = "queueAuthControl";
    private String controlAuthBindingKey = "controlRestaurantBindingKey";
    @Bean
    Queue queueAuthControl(){
        return new Queue(queueAuthControl);
    }
    @Bean
    public Binding restaurantBindingKeyControl(final Queue queueAuthControl, final DirectExchange exchangeUser){
        return BindingBuilder.bind(queueAuthControl).to(exchangeUser).with(controlAuthBindingKey);
    }
    //USERDAN AUTH'A DELETE
    private String queueAuthDelete = "queueAuthDelete";
    private String deleteAuthBindingKey = "deleteRestaurantBindingKey";
    @Bean
    Queue queueAuthDelete(){
        return new Queue(queueAuthDelete);
    }
    @Bean
    public Binding restaurantBindingKeyDelete(final Queue queueAuthDelete, final DirectExchange exchangeUser){
        return BindingBuilder.bind(queueAuthDelete).to(exchangeUser).with(deleteAuthBindingKey);
    }
}


