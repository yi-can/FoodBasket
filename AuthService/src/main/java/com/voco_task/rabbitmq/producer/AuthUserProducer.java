package com.voco_task.rabbitmq.producer;

import com.voco_task.rabbitmq.model.AuthUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserProducer {
    private String exchangeAuth = "exchangeAuth";
    private String createUserBindingKey = "createUserBindingKey";
    private final RabbitTemplate rabbitTemplate;
    public void createUser(AuthUserModel model){
        rabbitTemplate.convertAndSend(exchangeAuth,createUserBindingKey,model);
    }

}
