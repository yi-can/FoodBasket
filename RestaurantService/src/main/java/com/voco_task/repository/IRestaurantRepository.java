package com.voco_task.repository;

import com.voco_task.repository.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantRepository extends MongoRepository<Restaurant, String> {
}
