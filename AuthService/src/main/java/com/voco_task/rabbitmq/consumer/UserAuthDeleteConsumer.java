package com.voco_task.rabbitmq.consumer;

import com.voco_task.rabbitmq.model.EmailControlModel;
import com.voco_task.rabbitmq.model.UserAuthUpdateModel;
import com.voco_task.repository.IAuthRepository;
import com.voco_task.repository.entity.Auth;
import com.voco_task.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserAuthDeleteConsumer {
    private final AuthService authService;
    @RabbitListener(queues = "queueAuthDelete")
    public void authDelete(UserAuthUpdateModel model){
        authService.deleteAuth(model);
    }

}
