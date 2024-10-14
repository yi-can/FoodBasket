package com.voco_task.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthRestaurantModel implements Serializable {
    private String name;
    private String avatar;
    private List<String> phone;
    private List<String> address;
}
