package com.slabko.task1.postservice.validators;

import com.slabko.task1.postservice.exceptions.RequestedDataAlreadyExistsException;
import com.slabko.task1.postservice.exceptions.RequestedDataNotFoundException;
import com.slabko.task1.postservice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PostValidator {

    @Autowired
    private PostRepository postRepository;

    public void validatePost(String text) {
        if (!StringUtils.hasText(text)) {
            throw new RequestedDataAlreadyExistsException("Validation error or request body is an invalid");
        }
    }

    public void validatePostExists(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RequestedDataNotFoundException("Post with requested: " + id + " not exists");
        }
    }
}
