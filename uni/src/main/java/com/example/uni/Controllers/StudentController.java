package com.example.uni.Controllers;

import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.PersonDto;
import com.example.uni.Services.Crud.CrudStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private CrudStudentService crudStudentService;

    /**
     * function for add new student
     *
     * @param studentDto input data
     * @return student
     */
    @PostMapping
    public ResponseEntity<Object> addStudent(@Valid @RequestBody PersonDto studentDto) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentService.addStudent(studentDto));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("student successfully create");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);

    }

    /**
     * function for update student
     *
     * @param studentId  of student
     * @param studentDto input data
     * @return student
     */
    @PutMapping("/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable("studentId") Long studentId,
                                                @Valid @RequestBody PersonDto studentDto) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentService.updateStudent(studentDto, studentId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("student successfully update");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for delete student
     *
     * @param studentId of college
     * @return college
     */
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Long studentId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        crudStudentService.deleteStudent(studentId);
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("student successfully delete");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for get all student
     *
     * @return all student
     */
    @GetMapping
    public ResponseEntity<Object> getAllStudent() throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All student successfully ");
        responseModels.setData(crudStudentService.getAllStudent());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get a student
     *
     * @param studentId of student
     * @return a student
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<Object> getStudent(@PathVariable("studentId") Long studentId) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentService.getStudent(studentId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get student successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get all lesson of Student
     *
     * @param id of student
     * @return all lesson of Student
     */
    @GetMapping("/{id}/lessons")
    public ResponseEntity<Object> getStudentLesson(@PathVariable("id") Long id) throws Exception {
        ResponseModels responseModels = new ResponseModels();

        responseModels.setData(crudStudentService.getAllStudentLesson(id));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get student lessons successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get avg of Student
     *
     * @param id of student
     * @return avg of Student
     */
    @GetMapping("/{id}/avg")
    public ResponseEntity<Object> getAvg(@PathVariable("id") Long id) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentService.getAvg(id));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get avg student successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

}
