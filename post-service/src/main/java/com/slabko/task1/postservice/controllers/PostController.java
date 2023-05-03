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
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, k8s!";
    }

    @GetMapping("/posts/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        return postMapper.toDto(postService.getPostById(id));
    }

    @PostMapping("/posts")
    public PostDTO addPost(@RequestBody PostDTO postDTO) {
        postValidator.validatePost(postDTO.getText());
        UserDTO userDTO = new UserDTO();
        userDTO.setAmountOfPosts(1);
        restTemplate.put("http://localhost:8080/users/posts/" + postDTO.getAuthorId(), userDTO);
        return postMapper.toDto(postRepository.save(postMapper.toEntity(postDTO)));
    }

    @DeleteMapping("/posts/{id}")
    public void deletePostById(@PathVariable Long id) {
        postValidator.validatePostExists(id);
        postRepository.deleteById(id);

    }

    @PutMapping("/posts/{id}")
    public void updatePostById(@PathVariable Long id, @RequestBody PostDTO postToUpdate) {
        PostEntity post = postService.getPostById(id);
        post.setText(postToUpdate.getText());
        postRepository.save(post);
        UserDTO userDTO = new UserDTO();
        userDTO.setAmountOfPosts(1);
        restTemplate.put("http://localhost:8080/users/posts/" + post.getAuthorId(), userDTO);
    }

}
