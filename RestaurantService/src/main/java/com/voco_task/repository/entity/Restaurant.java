package com.voco_task.repository.entity;


//import com.voco_task.repository.enums.EKitchen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Restaurant extends BaseEntity{
    @Id
    private String restaurantId;
    private String name;
    private String avatar;
    private List<String> phone;//birden fazla restaurant şubesi olabileceği düşünülmüştür!!!
    private List<String> address;
    private List<Double> latitude;
    private List<Double> longitude;
    private List<String> foodId;//exxel e ile veri yükleyelim sonrasında
    private List<String> commentId;
    private List<String> likeId;
    private List<String> orders;
    private Float RestaurantScore;
//    private List<EKitchen> kitchens;
}
