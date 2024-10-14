package com.voco_task.repository;

import com.voco_task.repository.entity.CreditCard;
import com.voco_task.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditCardRepository extends MongoRepository<CreditCard, String> {
}
