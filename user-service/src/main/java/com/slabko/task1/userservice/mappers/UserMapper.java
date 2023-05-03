package com.slabko.task1.userservice.mappers;

import com.slabko.task1.userservice.dtos.UserDTO;
import com.slabko.task1.userservice.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDTO userDTO);

    UserDTO toDto(UserEntity userEntity);

}
