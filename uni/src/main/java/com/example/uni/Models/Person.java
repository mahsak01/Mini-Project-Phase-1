package com.example.uni.Models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    @NotEmpty(message = "Please fill personnel Number") String personnelNumber;

    @NotEmpty(message = "Please fill firstname")
    @Size(min = 3, max = 255, message = "Please Enter firstname  between 3-255 character") String firstname;

    @NotEmpty(message = "Please fill lastname")
    @Size(min = 3, max = 255, message = "Please Enter lastname between 3-255 character") String lastname;

    @NotEmpty(message = "Please fill national Code")
    @Size(min = 10, max = 10, message = "Please Enter national Code name 10 character") String nationalCode;
}
