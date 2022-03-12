package com.example.uni.Controllers;


import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.LessonDto;
import com.example.uni.Models.Lesson;
import com.example.uni.Services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/lesson" )
public class LessonController {

    @Autowired
    private LessonService lessonService;

    /**
     * function for add new lesson
     * @param lessonDto input data
     * @return lesson
     */
    @PostMapping
    public ResponseEntity<Object> addLesson(@Valid @RequestBody LessonDto lessonDto){

        ResponseModels responseModels = new ResponseModels();
        try {
            Lesson lesson= new Lesson(lessonDto.getLessonName() , lessonDto.getUnit());
            responseModels.setData( lessonService.addLesson(lesson,lessonDto.getCollegeId()));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("lesson successfully create");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for update lesson
     * @param id of lesson
     * @param lessonDto input data
     * @return lesson
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfessor(@PathVariable("id") Long id,@Valid @RequestBody LessonDto lessonDto){

        ResponseModels responseModels = new ResponseModels();
        try {
            Lesson lesson= new Lesson(lessonDto.getLessonName() , lessonDto.getUnit());
            responseModels.setData( lessonService.updateLesson(lesson,id,lessonDto.getCollegeId()));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("lesson successfully update");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for get all lesson
     * @return all lesson
     */
    @GetMapping
    public ResponseEntity< Object> getAllLesson() {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All lesson successfully ");
        responseModels.setData(lessonService.getAllLesson());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get a lesson
     * @param id of lesson
     * @return a lesson
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity< Object> getLesson(@PathVariable("id") Long id) {

        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(lessonService.getLesson(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get lesson successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for get all professor of lesson
     * @param id of lesson
     * @return all professor of lesson
     */
    @GetMapping("/{id}/professors")
    public ResponseEntity<Object> getProfessors(@PathVariable("id") Long id){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(lessonService.getAllProfessor(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get lesson professors successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }

    /**
     * function for get all student of lesson
     * @param id of lesson
     * @return all student of lesson
     */
    @GetMapping("/{id}/students")
    public ResponseEntity<Object> getStudents(@PathVariable("id") Long id){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(lessonService.getAllStudent(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get lesson professors successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }



}
