package com.slabko.task1.userservice.validators;

import com.slabko.task1.userservice.exceptions.RequestedDataAlreadyExistsException;
import com.slabko.task1.userservice.exceptions.RequestedDataNotFoundException;
import com.slabko.task1.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validateUsername(String username) {
        userRepository.findByUsername(username)
            .ifPresent(user -> {
                throw new RequestedDataAlreadyExistsException("Requested: " + username + " already exists");
            });
    }

    public void validateUserExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RequestedDataNotFoundException("User with requested: " + id + " not exists");
        }
    }
}
