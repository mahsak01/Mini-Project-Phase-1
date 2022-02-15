package com.example.demo.Controller;


import com.example.demo.Models.Student;
import com.example.demo.accessingdatamysql.StudentRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;


    /**
     * function for add new student
     * @param student input data
     * @param error for error
     * @return result
     */
    @PostMapping("/student")
    public String addNewStudent(@RequestBody @Valid Student student,  Errors error) {
        if (studentRepository.findByNationalCode(student.getNationalCode()) != null){
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The Student With This nationalCode Exist\"\n" +
                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        studentRepository.save(new Student(student.getFirstname(), student.getLastname(), student.getNationalCode(), student.getUniId()));

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\":\"success Create New Student\"\n" +

                "}";
    }


    /**
     * function for update student
     * @param id id student
     * @param student input data
     * @param error for error
     * @return result
     */
    @PutMapping("/student/{id}")
    public String updateStudent(@PathVariable("id") Long id,@RequestBody @Valid Student student,  Errors error) {
        if (studentRepository.findByNationalCode(student.getNationalCode()) != null)
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The student with this national code exist\"\n" +
                    "}";

        student.setId(id);
        if (error.hasErrors())
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";

        studentRepository.save(student);

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\": \"success Update Student\"\n" +

                "}";
    }

    /**
     * function for delete student
     * @param json input data
     * @param error for error
     * @return result
     */
    @DeleteMapping("/student")
    public String deleteStudent(@Valid @RequestBody String json,  Errors error) {
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        String nationalCode = data.get("nationalCode").getAsString();
        Student student = studentRepository.findByNationalCode(nationalCode);
        if (student == null) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"Not Find Student With this national code\"\n" +

                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        studentRepository.deleteById(student.getId());

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\": \"success delete student\"\n" +

                "}";
    }


    /**
     * function for get all student
     * @return all student
     */
    @GetMapping(path = "/student")
    public Iterable<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    /**
     * function for get a student
     * @param id of student
     * @return a student
     */
    @GetMapping(path = "/student/{id}")
    public Optional<Student> getStudent(@PathVariable("id") Long id) {
        return studentRepository.findById(id);
    }

}
