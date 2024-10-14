package com.voco_task.repository;

import com.voco_task.repository.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepository extends MongoRepository<Food, String> {
}
