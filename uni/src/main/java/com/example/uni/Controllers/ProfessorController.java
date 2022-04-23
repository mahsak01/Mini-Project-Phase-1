package com.example.uni.Controllers;

import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.PersonDto;
import com.example.uni.Services.Crud.CrudProfessorService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {

    @Autowired
    private CrudProfessorService crudProfessorService;

    /**
     * function for add new professor
     *
     * @param professorDto input data
     * @return professor
     */
    @PostMapping
    public ResponseEntity<Object> addProfessor(@Valid @RequestBody PersonDto professorDto) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudProfessorService.addProfessor(professorDto));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("professor successfully create");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for update professor
     *
     * @param professorId  of professor
     * @param professorDto input data
     * @return professor
     */
    @PutMapping("/{professorId}")
    public ResponseEntity<Object> updateProfessor(@PathVariable("professorId") Long professorId,
                                                  @Valid @RequestBody PersonDto professorDto) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudProfessorService.updateProfsser(professorDto, professorId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("professor successfully update");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for delete professor
     *
     * @param professorId of college
     * @return college
     */
    @DeleteMapping("/{professorId}")
    public ResponseEntity<Object> deleteProfessor(@PathVariable("professorId") Long professorId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        crudProfessorService.deleteProfsser(professorId);
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("professor successfully delete");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for get all professor
     *
     * @return all professor
     */
    @GetMapping
    public ResponseEntity<Object> getAllProfessor() throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All professor successfully ");
        responseModels.setData(crudProfessorService.getAllProfessor());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get a professor
     *
     * @param professorId of professor
     * @return a professor
     */
    @GetMapping(path = "/{professorId}")
    public ResponseEntity<Object> getProfessor(@PathVariable("professorId") Long professorId) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudProfessorService.getProfessor(professorId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get professor successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get all lesson of professor
     *
     * @param professorId of professor
     * @return all lesson of professor
     */
    @GetMapping("/{professorId}/lessons")
    public ResponseEntity<Object> getLessons(@PathVariable("professorId") Long professorId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudProfessorService.getAllLesson(professorId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get professor lessons successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for get all lesson of professor
     *
     * @param professorId of professor
     * @return all lesson of professor
     */
    @GetMapping("/{professorId}/students")
    public ResponseEntity<Object> getStudents(@PathVariable("professorId") Long professorId) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setData(crudProfessorService.getAllStudent(professorId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get professor students successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for add lesson of professor
     *
     * @param professorId of professor
     * @return all lesson of professor
     */
    @PostMapping(value = "/{professorId}/lesson")
    public ResponseEntity<Object> addLesson(@PathVariable("professorId") Long professorId,
                                            @Valid @RequestBody String json) throws Exception {

        ResponseModels responseModels = new ResponseModels();
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        Long lessonId = data.get("lessonId").getAsLong();
        responseModels.setData(crudProfessorService.addLesson(professorId, lessonId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("add lesson of professor successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }

    /**
     * function for delete lesson of professor
     *
     * @param professorId of professor
     * @return all lesson of professor
     */
    @DeleteMapping("/{professorId}/lesson")
    public ResponseEntity<Object> deleteLesson(@PathVariable("professorId") Long professorId,
                                               @RequestBody String json) throws Exception {
        ResponseModels responseModels = new ResponseModels();
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        Long lessonId = data.get("lessonId").getAsLong();
        responseModels.setData(crudProfessorService.deleteLesson(professorId, lessonId));
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("delete lesson of professor successfully");
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);

    }


}
