package com.example.uni.Controllers;

import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.StudentDto;
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
     * function for add new student
     *
     * @param studentDto input data
     * @return student
     */
    @PostMapping
    public ResponseEntity<Object> addStudent(@Valid @RequestBody StudentDto studentDto) {

        ResponseModels responseModels = new ResponseModels();
        try {

            responseModels.setData(studentService.addStudent(studentDto));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("student successfully create");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for update student
     *
     * @param studentId         of student
     * @param studentDto input data
     * @return student
     */
    @PutMapping("/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable("studentId") Long studentId, @Valid @RequestBody StudentDto studentDto) {

        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentService.updateStudent( studentDto,studentId ));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("student successfully update");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for delete student
     *
     * @param studentId of college
     * @return college
     */
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Long studentId) {
        ResponseModels responseModels = new ResponseModels();
        try {
            studentService.deleteStudent(studentId);
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("student successfully delete");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for get all student
     *
     * @return all student
     */
    @GetMapping
    public ResponseEntity<Object> getAllStudent() {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All student successfully ");
        responseModels.setData(studentService.getAllStudent());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get a student
     *
     * @param id of student
     * @return a student
     */
    @GetMapping( "/{studentId}")
    public ResponseEntity<Object> getStudent(@PathVariable("studentId") Long studentId) {

        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentService.getStudent(studentId));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get student successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }


    /**
     * function for get all lesson of Student
     *
     * @param id of student
     * @return all lesson of Student
     */
    @GetMapping("/{id}/lessons")
    public ResponseEntity<Object> getStudentLesson(@PathVariable("id") Long id) {
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentService.getAllStudentLesson(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get student lessons successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }




    /**
     * function for get avg of Student
     *
     * @param id of student
     * @return avg of Student
     */
    @GetMapping("/{id}/avg")
    public ResponseEntity<Object> getAvg(@PathVariable("id") Long id) {
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentService.getAvg(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get avg student successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }

}
