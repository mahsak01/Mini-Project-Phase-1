package com.example.uni.Controllers;

import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.StudentDto;
import com.example.uni.Models.College;
import com.example.uni.Models.Student;
import com.example.uni.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * function for add new studnet
     * @param studentDto input data
     * @return student
     */
    @PostMapping
    public ResponseEntity<Object> addCollege(@Valid @RequestBody StudentDto studentDto){

        ResponseModels responseModels = new ResponseModels();
        try {
            Student student= new Student(studentDto.getPersonnelNumber(),
                    studentDto.getFirstname(),studentDto.getLastname(),
                    studentDto.getNationalCode());
            responseModels.setData( studentService.addStudent(student,studentDto.getCollegeId()));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("student successfully create");
             return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for update studnet
     * @param id of student
     * @param studentDto input data
     * @return student
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCollege(@PathVariable("id") Long id,@Valid @RequestBody StudentDto studentDto){

        ResponseModels responseModels = new ResponseModels();
        try {
            Student student= new Student(studentDto.getPersonnelNumber(),
                    studentDto.getFirstname(),studentDto.getLastname(),
                    studentDto.getNationalCode());
            responseModels.setData( studentService.updateStudent(student,id,studentDto.getCollegeId()));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("student successfully update");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for delete student
     * @param id of college
     * @return college
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") Long id){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentService.deleteStudent(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("student successfully delete");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }


}
