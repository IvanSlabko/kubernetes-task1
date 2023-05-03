package com.slabko.task1.userservice.services;

import com.slabko.task1.userservice.entities.UserEntity;
import com.slabko.task1.userservice.exceptions.RequestedDataNotFoundException;
import com.slabko.task1.userservice.mappers.UserMapper;
import com.slabko.task1.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new RequestedDataNotFoundException("User with id: " + id + " not found");
        });
    }
}
