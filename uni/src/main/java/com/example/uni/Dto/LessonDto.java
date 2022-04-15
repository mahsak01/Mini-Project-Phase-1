package com.example.uni.Dto;


import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class LessonDto {


    @NotNull
    @Size(min = 1 ,max = 255)
    private String lessonName;

    @NotNull
    @Min(1)
    private int unit;

    @NotNull(message = "Please fill college Id")
    private Long collegeId;

}
