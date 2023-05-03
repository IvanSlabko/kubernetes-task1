package com.slabko.task1.postservice.services;

import com.slabko.task1.postservice.entities.PostEntity;

public interface PostService {

    PostEntity getPostById(Long id);
}
