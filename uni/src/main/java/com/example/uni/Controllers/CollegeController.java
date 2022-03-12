package com.example.uni.Controllers;

import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Models.College;
import com.example.uni.Services.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;


    /**
     * function for add new college
     * @param college input data
     * @return college
     */
    @PostMapping
    public ResponseEntity<Object> addCollege(@Valid @RequestBody  College college){

        ResponseModels responseModels = new ResponseModels();
        try {
            collegeService.addCollege(college);
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("college successfully create");
            responseModels.setData(college);
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }


    /**
     * function for update college
     * @param id of college
     * @param college input data
     * @return college
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCollege(@PathVariable("id") Long id,@Valid @RequestBody  College college ){
        ResponseModels responseModels = new ResponseModels();
        try {
            collegeService.updateCollege(college,id);
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("college successfully update");
            responseModels.setData(college);
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }


    /**
     * function for delete college
     * @param id of college
     * @return college
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCollege(@PathVariable("id") Long id){
        ResponseModels responseModels = new ResponseModels();
        try {
            collegeService.deleteCollege(id);
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("college successfully delete");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }


    /**
     * function for get all college
     * @return all college
     */
    @GetMapping
    public ResponseEntity< Object> getAllCollege() {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All College successfully ");
        responseModels.setData(collegeService.getAllCollege());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get a uni
     * @param id of uni
     * @return a uni
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity< Object> getCollege(@PathVariable("id") Long id) {

        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(collegeService.getCollege(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get college successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }


    /**
     * function for get all lesson
     * @param id od college
     * @return all lesson
     */

    @GetMapping(path = "/{id}/lessons")
    public ResponseEntity<Object> getAllLesson(@PathVariable("id") Long id , @Autowired LinkedHashMap<String ,Object > map) {
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(collegeService.getAllLessonOfCollege(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get All Lesson of college successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }

    /**
     * function for get all professor
     * @param id od college
     * @return all professor
     */

    @GetMapping(path = "/{id}/professors")
    public ResponseEntity<Object> getAllProfessor(@PathVariable("id") Long id , @Autowired LinkedHashMap<String ,Object > map){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(collegeService.getAllProfessorOfCollege(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get All Professor of college successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }

    /**
     * function for get all student
     * @param id od college
     * @return all student
     */

    @GetMapping(path = "/{id}/students")
    public ResponseEntity<Object> getAllStudent(@PathVariable("id") Long id , @Autowired LinkedHashMap<String ,Object > map){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(collegeService.getAllStudentOfCollege(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get All Student of college successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }


}
