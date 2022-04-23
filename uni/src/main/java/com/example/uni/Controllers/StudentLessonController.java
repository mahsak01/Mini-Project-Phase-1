package com.example.uni.Controllers;

import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.StudentLessonDto;
import com.example.uni.Services.Crud.CrudStudentLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/")
public class StudentLessonController {

    @Autowired
    private CrudStudentLessonService crudStudentLessonService;


    /**
     * function for add lesson of Student
     *
     * @param studentLessonDto input data
     * @return student lesson
     */
    @PostMapping("students/lesson")
    public ResponseEntity<Object> addLesson(@Valid @RequestBody StudentLessonDto studentLessonDto) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentLessonService.addStudentLesson(studentLessonDto));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("add lesson of student successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for delete lesson of Student
     *
     * @param studentLessonDto of student
     * @return
     */
    @DeleteMapping("students/lesson")
    public ResponseEntity<Object> deleteLesson(@Valid @RequestBody StudentLessonDto studentLessonDto) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        crudStudentLessonService.deleteStudentLesson(studentLessonDto);
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("delete lesson of student successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for delete lesson of professor
     *
     * @param professorId of professor
     * @return all lesson of professor
     */
    @GetMapping("professors/{professorId}/avg")
    public ResponseEntity<Object> getAvg(@PathVariable("professorId") Long professorId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentLessonService.getAvgOfProfessor(professorId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("delete lesson of professor successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for set grade of student
     *
     * @param studentLessonDto input data
     * @return student lesson
     */
    @PostMapping("professors/addGrade")
    public ResponseEntity<Object> addGrade(@Valid @RequestBody StudentLessonDto studentLessonDto) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentLessonService.setGrade(studentLessonDto));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("change grade successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get avg of lesson
     *
     * @param lessonId of lesson
     * @return avg of lesson
     */
    @GetMapping("lessons/{lessonId}/avgOfLesson")
    public ResponseEntity<Object> getAvgOfLesson(@PathVariable("lessonId") Long lessonId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentLessonService.getAvgOfLesson(lessonId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get avg of lesson successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for get avg of professor
     *
     * @param professorId of professor
     * @return avg of professor
     */
    @GetMapping("professors/{professorId}/avgOfProfessor")
    public ResponseEntity<Object> getAvgOfProfessor(@PathVariable("professorId") Long professorId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudStudentLessonService.getAvgOfProfessor(professorId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get avg students of professor successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }
}
