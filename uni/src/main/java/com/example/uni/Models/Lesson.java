package com.example.uni.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @SequenceGenerator(
            name = "lesson_sequence",
            sequenceName = "lesson_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lesson_sequence"
    )
    private Long id;

    @NotNull
    @Size(min = 1 ,max = 255)
    private String lessonName;

    @NotNull
    @Min(1)
    private int unit;

    @ManyToMany
    @JoinTable(
            name = "lesson_professor",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    @JsonIgnore
    private Set<Professor> professors ;

    @OneToMany(mappedBy = "lesson")
    @JsonIgnore
    private Set<StudentLesson> studentLessons ;


    public Lesson() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", unit=" + unit +
                ", professors=" + professors +
                '}';
    }
}
