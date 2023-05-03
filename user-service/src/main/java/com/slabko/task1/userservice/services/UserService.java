package com.slabko.task1.userservice.services;

import com.slabko.task1.userservice.entities.UserEntity;

public interface UserService {

    UserEntity getUserById(Long id);
}
