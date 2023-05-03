package com.slabko.task1.postservice.repositories;

import com.slabko.task1.postservice.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    Optional<PostEntity> findByUsername(String username);

}
