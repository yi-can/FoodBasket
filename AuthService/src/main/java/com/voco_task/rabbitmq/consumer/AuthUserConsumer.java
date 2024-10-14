package com.voco_task.rabbitmq.consumer;

import com.voco_task.rabbitmq.model.UserAuthUpdateModel;
import com.voco_task.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserConsumer {

    private final AuthService authService;
    @RabbitListener(queues = "queueAuth")
    public void createUser(UserAuthUpdateModel model){
        authService.updateAuth(model);
    }

}
