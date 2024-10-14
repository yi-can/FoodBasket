package com.voco_task.rabbitmq.producer;


import com.voco_task.rabbitmq.model.AuthRestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRestaurantProducer {

    private String exchangeAuth = "exchangeAuth";
    private String createRestaurantBindingKey = "createRestaurantBindingKey";

    private final RabbitTemplate rabbitTemplate;
    public void createRestaurant(AuthRestaurantModel model){
        rabbitTemplate.convertAndSend(exchangeAuth,createRestaurantBindingKey,model);
    }
}

