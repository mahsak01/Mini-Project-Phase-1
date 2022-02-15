package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=3, max=255)
    private String firstname;

    @NotNull
    @Size(min=3, max=255)
    private String lastname;

    @NotNull
    @Size(min=10, max=10)
    private  String nationalCode;

    @NotNull
    private Long uniId;

    protected Student() {}


    public Student( String firstName, String lastName, String nationalCode, Long uniId) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.nationalCode = nationalCode;
        this.uniId = uniId;
    }

    @Override
    public String toString() {
        return String.format(
                "Student[id=%d, firstName='%s', lastName='%s', nationalCode='%s', uniId=%d]",
                id, firstname, lastname,nationalCode,uniId);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Long getUniId() {
        return uniId;
    }

    public void setUniId(Long uniId) {
        this.uniId = uniId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
