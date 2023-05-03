package com.slabko.task1.userservice.dtos;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private int amountOfPosts;

}
