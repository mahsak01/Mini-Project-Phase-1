package com.example.uni.Models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "StudentLesson")
public class StudentLesson {

    @Id
    @SequenceGenerator(
            name = "studentLesson_sequence",
            sequenceName = "studentLesson_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "studentLesson_sequence"
    )
    private Long id;


    @Min(value = 0 , message = "grade between 0 - 20 ")
    @Max(value = 20 ,message = "grade between 0 - 20 ")
    private float grade=0;

    @NotNull(message = "Please fill term")
    @Min(value = 1 , message = "term start 1")
    private int term;


    @ManyToOne
    @JoinColumn()
    private Lesson lesson;

    @ManyToOne
    @JoinColumn()
    private Professor professor;

    @ManyToOne
    @JoinColumn()
    private Student student;

    public StudentLesson() {
    }

    public StudentLesson(float grade, int term, Lesson lesson, Professor professor, Student student) {
        this.grade = grade;
        this.term = term;
        this.lesson = lesson;
        this.professor = professor;
        this.student = student;
    }


    @Override
    public String toString() {
        return "StudentLesson{" +
                "id=" + id +
                ", grade=" + grade +
                ", term=" + term +
                ", lesson=" + lesson +
                ", student=" + student +
                '}';
    }
}
