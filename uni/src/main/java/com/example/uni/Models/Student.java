package com.example.uni.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Student")
public class Student extends Person {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;


    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<StudentLesson> studentLessons;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    protected College college;

    public Student() {
    }

    public Student(String personnelNumber, String firstname, String lastname, String nationalCode) {
        this.personnelNumber = personnelNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
    }



    public void addStudentLesson(StudentLesson studentLesson) {
        studentLessons.add(studentLesson);
    }

    public void deleteStudentLesson(StudentLesson studentLesson) {
        studentLessons.remove(studentLesson);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", personnelNumber=" + personnelNumber +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", college='" + college + '\'' +
                ", studentLessons=" + studentLessons +
                '}';
    }
}
