package com.slabko.task1.userservice.repositories;

import com.slabko.task1.userservice.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
