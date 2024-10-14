package com.voco_task.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Like extends BaseEntity{
    private String likeId;
    private String restaurantId;
    private Long like;
    private String userId; //beğenen kişinin id'si
    private String username; //beğenen kişinin adı
}
