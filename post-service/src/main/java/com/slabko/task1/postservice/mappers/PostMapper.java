package com.slabko.task1.postservice.mappers;

import com.slabko.task1.postservice.dtos.PostDTO;
import com.slabko.task1.postservice.entities.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostEntity toEntity(PostDTO postDTO);

    PostDTO toDto(PostEntity postEntity);

}
