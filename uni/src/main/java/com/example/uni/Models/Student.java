package com.example.uni.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Student")
public class Student {

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

    @NotEmpty(message = "Please fill personnel Number")
    private String personnelNumber;

    @NotEmpty(message = "Please fill firstname")
    @Size(min = 3, max = 255, message = "Please Enter firstname  between 3-255 character")
    private String firstname;

    @NotEmpty(message = "Please fill lastname")
    @Size(min = 3, max = 255, message = "Please Enter lastname between 3-255 character")
    private String lastname;

    @NotEmpty(message = "Please fill national Code")
    @Size(min = 10, max = 10, message = "Please Enter national Code name 10 character")
    private String nationalCode;

    @OneToMany(mappedBy = "student")
    private Set<StudentLesson> studentLessons;

    @ManyToOne(targetEntity = College.class)
    @JoinColumn
    private College college;

    public Student() {
    }

    public Student(String personnelNumber, String firstname, String lastname, String nationalCode) {
        this.personnelNumber = personnelNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Set<StudentLesson> getStudentLessons() {
        return studentLessons;
    }

    public void setStudentLessons(Set<StudentLesson> studentLessons) {
        this.studentLessons = studentLessons;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
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
