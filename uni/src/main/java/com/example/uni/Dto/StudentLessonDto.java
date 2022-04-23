package com.example.uni.Dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class StudentLessonDto {

    @NotNull(message = "Please fill term")
    @Min(value = 1 , message = "term start 1")
    private int term;

    @NotNull(message = "Please fill lessob Id")
    private Long lessonId;

    @NotNull(message = "Please fill student Id")
    private Long studentId;

    @NotNull(message = "Please fill professor Id")
    private Long professorId;

    @Min(value = 0 , message = "grade between 0 - 20 ")
    @Max(value = 20 ,message = "grade between 0 - 20 ")
    private float grade=0;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
