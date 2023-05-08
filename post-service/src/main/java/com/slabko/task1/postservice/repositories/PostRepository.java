package com.slabko.task1.postservice.repositories;

import com.slabko.task1.postservice.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    int countByAuthorId(Long authorId);

}
