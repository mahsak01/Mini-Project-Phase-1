package com.example.uni.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Professor> professors= new HashSet<>();

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Student> students= new HashSet<>();

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
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

    public void addProfessor(Professor professor) {
        professor.setCollege(this);
        professors.add(professor);
    }

    public void deleteProfessor(Professor professor) {
        professor.setCollege(null);
        professors.remove(professor);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {

        student.setCollege(this);
        students.add(student);

    }
    public void deleteStudent(Student student) {
        students.remove(student);
        student.setCollege(null);
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        lesson.setCollege(this);
        lessons.add(lesson);
    }

    public void deleteLesson(Lesson lesson) {
        lesson.setCollege(this);
        lessons.add(lesson);
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
