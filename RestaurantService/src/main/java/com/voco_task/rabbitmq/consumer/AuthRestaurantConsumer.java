package com.voco_task.rabbitmq.consumer;

import com.voco_task.rabbitmq.model.AuthRestaurantModel;
import com.voco_task.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthRestaurantConsumer {
    private final RestaurantService restaurantService;

    @RabbitListener(queues = "queueRestaurant")
    public void createUser(AuthRestaurantModel model){
        restaurantService.createRestaurant(model);
    }
}
