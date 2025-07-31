package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;
    private UserProducer producer;

    public UserService(UserRepository repository, UserProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    // This annotation assures Atomicity
    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = repository.save(userModel);
        producer.publishMessageEmail(userModel);
        return userModel;
    }

}