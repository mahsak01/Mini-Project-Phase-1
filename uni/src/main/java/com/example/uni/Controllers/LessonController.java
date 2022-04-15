package com.example.uni.Controllers;


import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.LessonDto;
import com.example.uni.Services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    /**
     * function for add new lesson
     *
     * @param lessonDto input data
     * @return lesson
     */
    @PostMapping
    public ResponseEntity<Object> addLesson(@Valid @RequestBody LessonDto lessonDto) throws Exception {

        ResponseModels responseModels = new ResponseModels();

        responseModels.setData(lessonService.addLesson(lessonDto));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("lesson successfully create");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for update lesson
     *
     * @param lessonId  of lesson
     * @param lessonDto input data
     * @return lesson
     */
    @PutMapping("/{lessonId}")
    public ResponseEntity<Object> updateLesson(@PathVariable("lessonId") Long lessonId,
                                               @Valid @RequestBody LessonDto lessonDto) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(lessonService.updateLesson(lessonDto, lessonId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("lesson successfully update");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);

    }

    /**
     * function for delete lesson
     *
     * @param lessonId of lesson
     * @return all lesson of professor
     */
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Object> deleteLesson(@PathVariable("lessonId") Long lessonId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        lessonService.deleteLesson(lessonId);
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("delete lesson successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for get all lesson
     *
     * @return all lesson
     */
    @GetMapping
    public ResponseEntity<Object> getAllLesson() throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All lesson successfully ");
        responseModels.setData(lessonService.getAllLesson());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get a lesson
     *
     * @param lessonId of lesson
     * @return a lesson
     */
    @GetMapping(path = "/{lessonId}")
    public ResponseEntity<Object> getLesson(@PathVariable("lessonId") Long lessonId) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(lessonService.getLesson(lessonId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get lesson successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);

    }

    /**
     * function for get all professor of lesson
     *
     * @param lessonId of lesson
     * @return all professor of lesson
     */
    @GetMapping("/{lessonId}/professors")
    public ResponseEntity<Object> getProfessors(@PathVariable("lessonId") Long lessonId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(lessonService.getAllProfessor(lessonId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get lesson professors successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for get all student of lesson
     *
     * @param lessonId of lesson
     * @return all student of lesson
     */
    @GetMapping("/{lessonId}/students")
    public ResponseEntity<Object> getStudents(@PathVariable("lessonId") Long lessonId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(lessonService.getAllStudent(lessonId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get lesson professors successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


}
