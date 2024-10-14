package com.voco_task.repository;

import com.voco_task.repository.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IAuthRepository extends MongoRepository<Auth, String> {

    Optional<Auth> findByEmailAndPassword(String email, String password);
    Optional<Auth> findByEmail(String email);
    Optional<Auth> findByAuthId(String authId);
}
