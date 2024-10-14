package com.voco_task.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Food extends BaseEntity{
    @Id
    private String foodId;
    private String restaurantId;
    private String name;
    private String contents;
    private String ingredients;
    private String picture;

}
