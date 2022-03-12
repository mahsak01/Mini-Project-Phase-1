package com.example.uni.Dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Component
public class StudentLessonDto {

    @NotNull(message = "Please fill term")
    @Min(value = 1 , message = "term start 1")
    private int term;

    @NotNull(message = "Please fill college Id")
    private Long collegeId;

    @NotNull(message = "Please fill student Id")
    private Long studentId;

    @NotNull(message = "Please fill professor Id")
    private Long professorId;
}
