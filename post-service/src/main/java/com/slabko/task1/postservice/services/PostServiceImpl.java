package com.slabko.task1.postservice.services;

import com.slabko.task1.postservice.entities.PostEntity;
import com.slabko.task1.postservice.exceptions.RequestedDataNotFoundException;
import com.slabko.task1.postservice.mappers.PostMapper;
import com.slabko.task1.postservice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;

    @Override
    public PostEntity getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> {
            throw new RequestedDataNotFoundException("Post with id: " + id + " not found");
        });
    }
}
