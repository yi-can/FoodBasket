package com.voco_task.rabbitmq.consumer;

import com.voco_task.exceptions.AuthServiceException;
import com.voco_task.exceptions.ErrorType;
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
public class EmailControlConsumer {
    private final IAuthRepository authRepository;
    @RabbitListener(queues = "queueAuthControl")
    public Object emailControl(EmailControlModel model){
        Optional<Auth> authOptional = authRepository.findByEmail(model.getEmail());
        System.out.println("Consumer kısmı " + authOptional.get().getEmail());
        if (authOptional.isEmpty()){
            return true;
        }
        return false;
    }
}
