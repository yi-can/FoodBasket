package com.voco_task.rabbitmq.consumer;


import com.voco_task.rabbitmq.model.AuthUserModel;
import com.voco_task.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserConsumer {
    private final UserService userService;

    @RabbitListener(queues = "queueUser")
    public void createUser(AuthUserModel model){
        userService.saveUser(model);
    }


}
