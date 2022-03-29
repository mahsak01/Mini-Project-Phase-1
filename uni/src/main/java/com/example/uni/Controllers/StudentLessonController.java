package com.example.uni.Controllers;

import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.StudentLessonDto;
import com.example.uni.Services.StudentLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/")
public class StudentLessonController {

    @Autowired
    private StudentLessonService studentLessonService;


    /**
     * function for add lesson of Student
     * @param studentLessonDto input data
     * @return student lesson
     */
    @PostMapping("student/lesson")
    public ResponseEntity<Object> addLesson(@Valid @RequestBody StudentLessonDto studentLessonDto) {
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentLessonService.addStudentLesson(studentLessonDto));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("add lesson of student successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }

    /**
     * function for delete lesson of Student
     *
     * @param studentLessonDto of student
     * @return
     */
    @DeleteMapping("student/lesson")
    public ResponseEntity<Object> deleteLesson( @Valid @RequestBody StudentLessonDto studentLessonDto) {
        ResponseModels responseModels = new ResponseModels();
        try {
            studentLessonService.deleteStudentLesson(studentLessonDto);
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("delete lesson of student successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }


    /**
     * function for delete lesson of professor
     * @param professorId of professor
     * @return all lesson of professor
     */
    @GetMapping("professor/{professorId}/avg")
    public ResponseEntity<Object> getAvg(@PathVariable("professorId") Long professorId){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentLessonService.getAvgOfProfessor(professorId));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("delete lesson of professor successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }



    /**
     * function for set grade of student
     * @param studentLessonDto input data
     * @return student lesson
     */
    @PostMapping("professor/addGrade")
    public ResponseEntity<Object> addGrade(@Valid @RequestBody StudentLessonDto studentLessonDto){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentLessonService.setGrade(studentLessonDto));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("change grade successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }



    /**
     * function for get avg of lesson
     *
     * @param lessonId of lesson
     * @return avg of lesson
     */
    @GetMapping("lesson/{lessonId}/avgOfLesson")
    public ResponseEntity<Object> getAvgOfLesson(@PathVariable("lessonId") Long lessonId) {
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentLessonService.getAvgOfLesson(lessonId));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get avg of lesson successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }

    /**
     * function for get avg of professor
     *
     * @param professorId of professor
     * @return avg of professor
     */
    @GetMapping("professor/{professorId}/avgOfProfessor")
    public ResponseEntity<Object> getAvgOfProfessor(@PathVariable("professorId") Long professorId) {
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(studentLessonService.getAvgOfProfessor(professorId));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get avg students of professor successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }
}
