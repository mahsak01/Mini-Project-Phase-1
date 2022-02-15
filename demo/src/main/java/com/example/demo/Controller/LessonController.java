package com.example.demo.Controller;


import com.example.demo.Models.Lesson;
import com.example.demo.Models.Uni;
import com.example.demo.accessingdatamysql.LessonRepository;
import com.example.demo.accessingdatamysql.UniRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;


    /**
     * function for add new lesson
     * @param lesson input data
     * @param error for error
     * @return result
     */
    @PostMapping("/lesson")
    public String addNewLesson(@RequestBody @Valid Lesson lesson, Errors error) {
        if (lessonRepository.findByNameAndTeacherIdAndUniId(lesson.getName() , lesson.getTeacherId() , lesson.getUniId()) != null){
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The lesson Exist\"\n" +
                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        lessonRepository.save(new Lesson(lesson.getName(), lesson.getUnit(), lesson.getTeacherId() , lesson.getUniId()));

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\":\"success Create New lesson\"\n" +

                "}";
    }


    /**
     * function for update lesson
     * @param id id lesson
     * @param lesson input data
     * @param error for error
     * @return result
     */
    @PutMapping("/lesson/{id}")
    public String updateLesson(@PathVariable("id") Long id,@RequestBody @Valid Lesson lesson,  Errors error) {
        if (lessonRepository.findByNameAndTeacherIdAndUniId(lesson.getName(),lesson.getTeacherId() , lesson.getUniId())!=null)
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The lesson with this information exist\"\n" +
                    "}";
        lesson.setId(id);
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }

        lessonRepository.save(lesson);

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\": \"success Update lesson\"\n" +

                "}";
    }

    /**
     * function for delete lesson
     * @param json input data
     * @param error for error
     * @return result
     */
    @DeleteMapping("/lesson")
    public String deleteLesson(@Valid @RequestBody String json,  Errors error) {
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        String lessonName = data.get("name").getAsString();
        Long teacherId = data.get("teacherId").getAsLong();
        Long uniId = data.get("uniId").getAsLong();

        Lesson lesson = lessonRepository.findByNameAndTeacherIdAndUniId(lessonName,teacherId,uniId);
        if (lesson == null) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"Not Find lesson\"\n" +

                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        lessonRepository.deleteById(lesson.getId());

        return "{\n" +
                "    \"status\": \"success\"\n" +
                "    \"message\": \"success delete lesson\"\n" +

                "}";
    }


    /**
     * function for get all lesson
     * @return all lesson
     */
    @GetMapping(path = "/lesson")
    public Iterable<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

    /**
     * function for get a lesson
     * @param id of lesson
     * @return a lesson
     */
    @GetMapping(path = "/lesson/{id}")
    public Optional<Lesson> getLesson(@PathVariable("id") Long id) {
        return lessonRepository.findById(id);
    }

}
