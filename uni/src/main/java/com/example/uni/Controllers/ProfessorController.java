package com.example.uni.Controllers;

import com.example.uni.Controllers.Models.ResponseModels;
import com.example.uni.Controllers.Models.Status;
import com.example.uni.Dto.ProfessorDto;
import com.example.uni.Models.Professor;
import com.example.uni.Services.ProfessorService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/professor" )
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    /**
     * function for add new professor
     * @param professorDto input data
     * @return professor
     */
    @PostMapping
    public ResponseEntity<Object> addProfessor(@Valid @RequestBody ProfessorDto professorDto){

        ResponseModels responseModels = new ResponseModels();
        try {
            Professor professor= new Professor(professorDto.getPersonnelNumber(),
                    professorDto.getFirstname(),professorDto.getLastname(),
                    professorDto.getNationalCode());
            responseModels.setData( professorService.addProfessor(professor,professorDto.getCollegeId()));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("professor successfully create");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for update professor
     * @param id of professor
     * @param professorDto input data
     * @return professor
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfessor(@PathVariable("id") Long id,@Valid @RequestBody ProfessorDto professorDto){

        ResponseModels responseModels = new ResponseModels();
        try {
            Professor professor= new Professor(professorDto.getPersonnelNumber(),
                    professorDto.getFirstname(),professorDto.getLastname(),
                    professorDto.getNationalCode());
            responseModels.setData( professorService.updateProfsser(professor,id,professorDto.getCollegeId()));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("professor successfully update");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }


    /**
     * function for delete professor
     * @param id of college
     * @return college
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfessor(@PathVariable("id") Long id){
        ResponseModels responseModels = new ResponseModels();
        try {
            professorService.deleteProfsser(id);
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("professor successfully delete");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }

    }

    /**
     * function for get all professor
     * @return all professor
     */
    @GetMapping
    public ResponseEntity< Object> getAllProfessor() {
        ResponseModels responseModels = new ResponseModels();
        responseModels.setStatus(Status.SUCCESS);
        responseModels.setMessage("get All professor successfully ");
        responseModels.setData(professorService.getAllProfessor());
        return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
    }


    /**
     * function for get a professor
     * @param id of professor
     * @return a professor
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity< Object> getProfessor(@PathVariable("id") Long id) {

        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(professorService.getProfessor(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get professor successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }


    }


    /**
     * function for get all lesson of professor
     * @param id of professor
     * @return all lesson of professor
     */
    @GetMapping("/{id}/lessons")
    public ResponseEntity<Object> getLessons(@PathVariable("id") Long id){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(professorService.getAllLesson(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get professor lessons successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }

    /**
     * function for get all lesson of professor
     * @param id of professor
     * @return all lesson of professor
     */
    @GetMapping("/{id}/students")
    public ResponseEntity<Object> getStudents(@PathVariable("id") Long id){
        ResponseModels responseModels = new ResponseModels();
        try {
            responseModels.setData(professorService.getAllStudent(id));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("get professor lessons successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }



    /**
     * function for add lesson of professor
     * @param professorId of professor
     * @return all lesson of professor
     */
    @GetMapping("/{professorId}/addLesson")
    public ResponseEntity<Object> addLesson(@PathVariable("professorId") Long professorId ,@RequestBody String json){

        ResponseModels responseModels = new ResponseModels();
        try {
            JsonObject data = new Gson().fromJson(json, JsonObject.class);
            Long lessonId = data.get("lessonId").getAsLong();
            responseModels.setData(professorService.addLesson(professorId,lessonId));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("add lesson of professor successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }

    /**
     * function for delete lesson of professor
     * @param professorId of professor
     * @return all lesson of professor
     */
    @GetMapping("/{professorId}/deleteLesson")
    public ResponseEntity<Object> deleteLesson(@PathVariable("professorId") Long professorId, @RequestBody String json){
        ResponseModels responseModels = new ResponseModels();
        try {
            JsonObject data = new Gson().fromJson(json, JsonObject.class);
            Long lessonId = data.get("lessonId").getAsLong();
            responseModels.setData(professorService.deleteLesson(professorId,lessonId));
            responseModels.setStatus(Status.SUCCESS);
            responseModels.setMessage("delete lesson of professor successfully");
            return ResponseEntity.status(HttpStatus.FOUND).body(responseModels);
        } catch (Exception e) {
            responseModels.setStatus(Status.FAILED);
            responseModels.setMessage("Error "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModels);
        }
    }
}
