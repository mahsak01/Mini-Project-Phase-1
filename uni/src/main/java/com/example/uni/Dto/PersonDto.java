package com.example.uni.Dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class PersonDto {

    @NotEmpty(message = "Please fill personnel Number") String personnelNumber;

    @NotEmpty(message = "Please fill firstname")
    @Size(min = 3, max = 255, message = "Please Enter firstname  between 3-255 character") String firstname;

    @NotEmpty(message = "Please fill lastname")
    @Size(min = 3, max = 255, message = "Please Enter lastname between 3-255 character") String lastname;

    @NotEmpty(message = "Please fill national Code")
    @Size(min = 10, max = 10, message = "Please Enter national Code name 10 character") String nationalCode;

    @NotNull(message = "Please fill college Id")
     Long collegeId;

    public PersonDto() {
    }

    public PersonDto(String personnelNumber, String firstname, String lastname, String nationalCode, Long collegeId) {
        this.personnelNumber = personnelNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
        this.collegeId = collegeId;
    }
}
