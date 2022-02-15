package com.example.demo.Controller;


import com.example.demo.Models.Lesson;
import com.example.demo.Models.Sec;
import com.example.demo.Models.Uni;
import com.example.demo.accessingdatamysql.LessonRepository;
import com.example.demo.accessingdatamysql.SecRepository;
import com.example.demo.accessingdatamysql.StudentRepository;
import com.example.demo.accessingdatamysql.UniRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class SecController {

    @Autowired
    private SecRepository secRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LessonRepository lessonRepository;


    /**
     * function for add new sec
     * @param sec input data
     * @param error for error
     * @return result
     */
    @PostMapping("/sec")
    public String addNewSec(@RequestBody @Valid Sec sec, Errors error) {
        if (secRepository.findByLessonIdAndTermAndStudentIdAndSecId(sec.getLessonId(),sec.getTerm(),sec.getStudentId(),sec.getSecId())!= null){
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The sec With This information Exist\"\n" +
                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        secRepository.save(new Sec(sec.getSecId() ,sec.getLessonId() ,sec.getStudentId(), sec.getTeacherId(),sec.getScore(),sec.getTerm()));

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\":\"success Create New sec\"\n" +

                "}";
    }


    /**
     * function for update sec
     * @param id id uni
     * @param sec input data
     * @param error for error
     * @return result
     */
    @PutMapping("/sec/{id}")
    public String updateSec(@PathVariable("id") Long id,@RequestBody @Valid Sec sec,  Errors error) {
        if (secRepository.findByLessonIdAndTermAndStudentIdAndSecId(sec.getLessonId(),sec.getTerm(),sec.getStudentId(),sec.getSecId())!= null)
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The sec with this information exist\"\n" +
                    "}";
        sec.setId(id);
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }

        secRepository.save(sec);

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\": \"success Update sec\"\n" +

                "}";
    }

    /**
     * function for delete sec
     * @param json input data
     * @param error for error
     * @return result
     */
    @DeleteMapping("/sec")
    public String deleteSec(@Valid @RequestBody String json,  Errors error) {
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        Long secId = data.get("secId").getAsLong();
        Long lessonId = data.get("lessonId").getAsLong();
        int term = data.get("term").getAsInt();
        Long studentId = data.get("studentId").getAsLong();
        Sec sec = secRepository.findByLessonIdAndTermAndStudentIdAndSecId(lessonId,term,studentId,secId);
        if (sec == null) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"Not Find sec With this information\"\n" +

                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        secRepository.deleteById(sec.getId());

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\": \"success delete sec\"\n" +

                "}";

    }



    /**
     * function for get all sec
     * @return all sec
     */
    @GetMapping(path = "/sec")
    public Iterable<Sec> getAllSec() {
        return secRepository.findAll();
    }

    /**
     * function for get a sec
     * @param id of sec
     * @return a sec
     */
    @GetMapping(path = "/sec/{id}")
    public Optional<Sec> getUni(@PathVariable("id") Long id) {
        return secRepository.findById(id);
    }

    @GetMapping(path = "/sec/getAvgOfLesson")
    public String getAvgOfLesson(@Valid @RequestBody String json,  Errors error){
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        Long lessonId = data.get("lessonId").getAsLong();
        int term = data.get("term").getAsInt();
        Long teacherId = data.get("teacherId").getAsLong();
        if (secRepository.findByLessonIdAndTermAndTeacherId(lessonId,term,teacherId) == null) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"Not Find sec With this information\"\n" +

                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        double avg=secRepository.avgOfLesson(lessonId,term,teacherId);
        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"avg\": \""+avg+"\"\n" +

                "}";
    }


    @GetMapping(path = "/sec/getAvgOfStudent")
    public String getAvgOfStudent(@Valid @RequestBody String json,  Errors error){
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        Long studentId = data.get("studentId").getAsLong();
        int term = data.get("term").getAsInt();
        if (studentRepository.findById(studentId) == null) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"Not Find student With this information\"\n" +

                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        Iterable<Sec> secs=secRepository.findByStudentIdAndTerm(studentId,term);
        ArrayList<Long> ids=new ArrayList<>();
        for (Sec sec : secs)
            ids.add(sec.getLessonId());
        Iterable<Lesson> lessons = lessonRepository.findAllById(ids);

        float avg=0;
        int sumUnit=0;
        for (Sec sec : secs)
              for (Lesson lesson:lessons)
                  if (sec.getLessonId()==lesson.getId()){
                      avg=sec.getScore()*lesson.getUnit();
                      sumUnit+=lesson.getUnit();
                      break;
                  }

        avg/=sumUnit;
        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"avg\": \""+avg+"\"\n" +

                "}";
    }


}
