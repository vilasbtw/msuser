package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // This annotation assures Atomicity
    @Transactional
    public UserModel save(UserModel userModel) {
        return repository.save(userModel);
    }

}