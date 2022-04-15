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


@RestController
@RequestMapping("/api/v1/colleges")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;


    /**
     * function for add new college
     *
     * @param college input data
     * @return college
     */
    @PostMapping
    public ResponseEntity<Object> addCollege(@Valid @RequestBody College college) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        collegeService.addCollege(college);
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("college successfully create");
        responseModels.setData(college);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for update college
     *
     * @param collegeId of college
     * @param college   input data
     * @return college
     */
    @PutMapping("/{collegeId}")
    public ResponseEntity<Object> updateCollege(@PathVariable("collegeId") Long collegeId,
                                                @Valid @RequestBody College college) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        collegeService.updateCollege(college, collegeId);
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("college successfully update");
        responseModels.setData(college);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for delete college
     *
     * @param collegeId of college
     * @return college
     */
    @DeleteMapping("/{collegeId}")
    public ResponseEntity<Object> deleteCollege(@PathVariable("collegeId") Long collegeId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        collegeService.deleteCollege(collegeId);
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("college successfully delete");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get all college
     *
     * @return all college
     */
    @GetMapping
    public ResponseEntity<Object> getAllCollege() throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All College successfully ");
        responseModels.setData(collegeService.getAllCollege());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get a uni
     *
     * @param collegeId of uni
     * @return a uni
     */
    @GetMapping(path = "/{collegeId}")
    public ResponseEntity<Object> getCollege(@PathVariable("collegeId") Long collegeId) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(collegeService.getCollege(collegeId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get college successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get all lesson
     *
     * @param collegeId of college
     * @return all lesson
     */

    @GetMapping(path = "/{collegeId}/lessons")
    public ResponseEntity<Object> getAllLesson(@PathVariable("collegeId") Long collegeId,
                                               @Autowired LinkedHashMap<String, Object> map) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(collegeService.getAllLessonOfCollege(collegeId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All Lesson of college successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for get all professor
     *
     * @param collegeId od college
     * @return all professor
     */

    @GetMapping(path = "/{collegeId}/professors")
    public ResponseEntity<Object> getAllProfessor(@PathVariable("collegeId") Long collegeId,
                                                  @Autowired LinkedHashMap<String, Object> map) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(collegeService.getAllProfessorOfCollege(collegeId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All Professor of college successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for get all student
     *
     * @param collegeId od college
     * @return all student
     */

    @GetMapping(path = "/{collegeId}/students")
    public ResponseEntity<Object> getAllStudent(@PathVariable("collegeId") Long collegeId,
                                                @Autowired LinkedHashMap<String, Object> map) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(collegeService.getAllStudentOfCollege(collegeId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All Student of college successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


}
