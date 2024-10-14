package com.voco_task.rabbitmq.producer;

import com.voco_task.rabbitmq.model.AuthMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthMailProducer {

    private String exchangeAuth = "exchangeAuth";
    private String createMailBindingKey = "createMailBindingKey";
    private String passwordMailBindingKey = "passwordMailBindingKey";

    private final RabbitTemplate rabbitTemplate;

    public void sendMail(AuthMailModel model){
        rabbitTemplate.convertAndSend(exchangeAuth,createMailBindingKey,model);
    }

    public void sendMailPassword(AuthMailModel model){
        rabbitTemplate.convertAndSend(exchangeAuth,passwordMailBindingKey,model);
    }
}
