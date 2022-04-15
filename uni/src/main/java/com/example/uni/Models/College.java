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

    public College(String collegeName) {
        this.collegeName = collegeName;
    }

    public College() {
    }



    public void addProfessor(Professor professor) throws Exception {
        Models.setField(professor,"college" , this);
        professors.add(professor);
    }

    public void deleteProfessor(Professor professor) throws Exception {
        Models.setField(professor,"college" , null);
        professors.remove(professor);
    }

    public void addStudent(Student student) throws Exception {

        Models.setField(student,"college" , this);
        students.add(student);

    }
    public void deleteStudent(Student student) throws Exception {
        students.remove(student);
        Models.setField(student,"college" , null);
    }


    public void addLesson(Lesson lesson) throws Exception {
        Models.setField(lesson,"college" , this);
        lessons.add(lesson);
    }

    public void deleteLesson(Lesson lesson) throws Exception {
        Models.setField(lesson,"college" , null);
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
