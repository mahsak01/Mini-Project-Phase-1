package com.example.uni.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @SequenceGenerator(
            name = "professor_sequence",
            sequenceName = "professor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "professor_sequence"
    )
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long personnelNumber;

    @NotNull
    @Size(min=3, max=255)
    private String firstname;

    @NotNull
    @Size(min=3, max=255)
    private String lastname;

    @NotNull
    @Column(unique = true)
    @Size(min=10, max=10)
    private String nationalCode;

    @ManyToMany(mappedBy = "professors")
    @JsonIgnore
    private Set<Lesson> lessons ;

    public Professor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(Long personnelNumber) {
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

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", personnelNumber=" + personnelNumber +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", lessons=" + lessons +
                '}';
    }
}
