package com.slabko.task1.postservice.controllers;

import com.slabko.task1.postservice.dtos.PostDTO;
import com.slabko.task1.postservice.dtos.UserDTO;
import com.slabko.task1.postservice.entities.PostEntity;
import com.slabko.task1.postservice.mappers.PostMapper;
import com.slabko.task1.postservice.repositories.PostRepository;
import com.slabko.task1.postservice.services.PostService;
import com.slabko.task1.postservice.validators.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostValidator postValidator;
    @Autowired
    private PostService postService;

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, postService!";
    }

    @GetMapping("/posts/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        return postMapper.toDto(postService.getPostById(id));
    }

    @PostMapping("/posts")
    public PostDTO addPost(@RequestBody PostDTO postDTO) {
        postValidator.validatePost(postDTO.getText());
        PostEntity postEntity = postRepository.save(postMapper.toEntity(postDTO));
        int postsCount = postRepository.countByAuthorId(postDTO.getAuthorId());
        UserDTO userDTO = new UserDTO();
        userDTO.setAmountOfPosts(postsCount);
        WebClient.builder().build()
            .put()
            .uri("http://user-service:8080/users/posts/" + postDTO.getAuthorId())
            .body(BodyInserters.fromValue(userDTO))
            .retrieve()
            .bodyToMono(Void.class)
            .subscribe();
        return postMapper.toDto(postEntity);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePostById(@PathVariable Long id) {
        postValidator.validatePostExists(id);
        postRepository.deleteById(id);

    }

    @PutMapping("/posts/{id}")
    public void updatePostById(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostEntity post = postService.getPostById(id);
        post.setText(postDTO.getText());
        postRepository.save(postMapper.toEntity(postDTO));
        int postsCount = postRepository.countByAuthorId(postDTO.getAuthorId());
        UserDTO userDTO = new UserDTO();
        userDTO.setAmountOfPosts(postsCount);
        WebClient.builder().build().put()
            .uri("http://user-service:8080/users/posts/" + postDTO.getAuthorId())
            .body(BodyInserters.fromValue(userDTO))
            .retrieve()
            .bodyToMono(Void.class)
            .subscribe();
    }

}
