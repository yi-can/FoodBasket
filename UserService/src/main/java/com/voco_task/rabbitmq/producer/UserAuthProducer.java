package com.voco_task.rabbitmq.producer;

import com.voco_task.rabbitmq.model.EmailControlModel;
import com.voco_task.rabbitmq.model.UserAuthUpdateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthProducer {
    private String exchangeUser = "exchangeUser";
    private String updateAuthBindingKey = "createRestaurantBindingKey";
    private String controlAuthBindingKey = "controlRestaurantBindingKey";
    private String deleteAuthBindingKey = "deleteRestaurantBindingKey";

    private final RabbitTemplate rabbitTemplate;
    public void updateAuth(UserAuthUpdateModel model){
        rabbitTemplate.convertAndSend(exchangeUser,updateAuthBindingKey,model);
    }

    public Object userControl(EmailControlModel model){
        return rabbitTemplate.convertSendAndReceive(exchangeUser, controlAuthBindingKey, model);
    }

    public void userDelete(UserAuthUpdateModel model){
        rabbitTemplate.convertAndSend(exchangeUser, deleteAuthBindingKey, model);
    }





}
