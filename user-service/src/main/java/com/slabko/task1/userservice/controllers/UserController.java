package com.slabko.task1.userservice.controllers;

import com.slabko.task1.userservice.dtos.UserDTO;
import com.slabko.task1.userservice.entities.UserEntity;
import com.slabko.task1.userservice.mappers.UserMapper;
import com.slabko.task1.userservice.repositories.UserRepository;
import com.slabko.task1.userservice.services.UserService;
import com.slabko.task1.userservice.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, userService!";
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }

    @PostMapping("/users")
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        userValidator.validateUsername(userDTO.getUsername());
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userValidator.validateUserExists(id);
        userRepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    public void updateUserById(@PathVariable Long id, @RequestBody UserDTO userToUpdate) {
        UserEntity user = userService.getUserById(id);
        user.setUsername(userToUpdate.getUsername());
        userRepository.save(user);
    }

    @PutMapping("/users/posts/{id}")
    public void updateUserPostAmountById(@PathVariable Long id, @RequestBody UserDTO userToUpdate) {
        UserEntity user = userService.getUserById(id);
        user.setAmountOfPosts(userToUpdate.getAmountOfPosts());
        userRepository.save(user);
    }

}
