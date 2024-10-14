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
public class Comment extends BaseEntity{
    @Id
    private String commentId;
    private String restaurantId;
    private String restaurantName;
    private String comment;
    private String userId; //yorum yapan kişinin id'si
    private String userName; //yorum yapan kişinin adı
    //command de argo kelimeleri filtreden geçirelim
}
