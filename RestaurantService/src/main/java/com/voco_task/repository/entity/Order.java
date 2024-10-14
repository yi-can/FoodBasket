package com.voco_task.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order extends BaseEntity{
    @Id
    private String orderId;
    private String restaurantId;
    private List<String> foodId;//sipariş verilen yemekler
    private List<String> userId;//sipariş veren kişiler
    private LocalTime time;

}
