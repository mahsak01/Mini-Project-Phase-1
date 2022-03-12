package com.example.uni.Dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Component
public class ProfessorDto {

    @NotNull
    @Column(unique = true)
    private String personnelNumber;

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

    @NotNull(message = "Please fill college Id")
    private Long collegeId;

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

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }
}
