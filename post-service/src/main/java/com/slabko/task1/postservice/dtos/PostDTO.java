package com.slabko.task1.postservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private Long authorId;
    private String text;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate postedAt;

}
