package com.example.uni.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


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


    @Size(min = 0 , max = 20 ,message = "grade between 0 - 20 ")
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
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
