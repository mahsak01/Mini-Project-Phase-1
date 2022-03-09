package com.example.uni.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "College")
public class College {

    @Id
    @SequenceGenerator(
            name = "college_sequence",
            sequenceName = "college_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "college_sequence"
    )
    private Long id;

    @NotEmpty(message = "Please fill college name")
    @Size(min = 4 , max = 20 , message = "Please Enter college name between 4-20 character")
    private String collegeName;

    @OneToMany
    @JsonIgnore
    private Set<Professor> professors= new HashSet<>();

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Student> students= new HashSet<>();

    @OneToMany
    @JsonIgnore
    private Set<Lesson> lessons= new HashSet<>();

    public College() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", collegeName='" + collegeName + '\'' +
                ", professors=" + professors +
                ", students=" + students +
                ", lessons=" + lessons +
                '}';
    }
}
