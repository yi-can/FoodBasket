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
    private String exchangeAuth = "exchangeAuth";
    private String queueRestaurant = "queueRestaurant";
    private String createRestaurantBindingKey = "createRestaurantBindingKey";

    @Bean
    Queue queueRestaurant(){
        return new Queue(queueRestaurant);
    }
    @Bean
    DirectExchange exchangeAuth(){
        return new DirectExchange(exchangeAuth);
    }
    @Bean
    public Binding restaurantBindingKey(final Queue queueRestaurant, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueRestaurant).to(exchangeAuth).with(createRestaurantBindingKey);
    }

    //AUTHDAN MAİL SERVİCE PASSWORD MAİLİ
    private String queueMailPassword = "queueMailPassword";
    private String passwordMailBindingKey = "passwordMailBindingKey";
    @Bean
    Queue queueMailPassword(){
        return new Queue(queueMailPassword);
    }
    @Bean
    public Binding mailPasswordBindingKey(final Queue queueMailPassword, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueMailPassword).to(exchangeAuth).with(passwordMailBindingKey);
    }

    //AUTHDAN MAİL SERVİCE AKTİVASYON MAİLİ
    private String queueMail = "queueMail";
    private String createMailBindingKey = "createMailBindingKey";
    @Bean
    Queue queueMail(){
        return new Queue(queueMail);
    }
    @Bean
    public Binding mailBindingKey(final Queue queueMail, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueMail).to(exchangeAuth).with(createMailBindingKey);
    }

    //AUTHDAN USER'A
    private String queueUser = "queueUser";
    private String createUserBindingKey = "createUserBindingKey";

    @Bean
    Queue queueUser(){
        return new Queue(queueUser);
    }
    @Bean
    public Binding userBindingKey(final Queue queueUser, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueUser).to(exchangeAuth).with(createUserBindingKey);
    }
    //USERDAN AUTH'A UPDATE
    private String queueAuth = "queueAuth";
    @Bean
    Queue queueAuth(){
        return new Queue(queueAuth);
    }

    //USERDAN AUTH'A CONTROL
    private String queueAuthControl = "queueAuthControl";
    @Bean
    Queue queueAuthControl(){
        return new Queue(queueAuthControl);
    }

    //USERDAN AUTH'A DELETE
    private String queueAuthDelete = "queueAuthDelete";
    @Bean
    Queue queueAuthDelete(){
        return new Queue(queueAuthDelete);
    }



}


