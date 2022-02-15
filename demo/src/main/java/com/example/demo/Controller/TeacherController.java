package com.example.demo.Controller;


import com.example.demo.Models.Sec;
import com.example.demo.Models.Student;
import com.example.demo.Models.Teacher;
import com.example.demo.accessingdatamysql.SecRepository;
import com.example.demo.accessingdatamysql.StudentRepository;
import com.example.demo.accessingdatamysql.TeacherRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SecRepository secRepository;


    /**
     * function for add new teacher
     * @param teacher input data
     * @param error for error
     * @return result
     */
    @PostMapping("/teacher")
    public String addNewTeacher(@RequestBody @Valid Teacher teacher, Errors error) {
        if (teacherRepository.findByNationalCode(teacher.getNationalCode())!=null)
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The teacher With This nationalCode Exist\"\n" +
                    "}";
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        teacherRepository.save(new Teacher(teacher.getFirstname(), teacher.getLastname(), teacher.getNationalCode(), teacher.getUniId()));

        return "{\n" +
                "    \"status\": success\n" +
                "    \"message\":success Create New Teacher\n" +

                "}";
    }

    /**
     * function for update teacher
     * @param id of teacher
     * @param teacher input data
     * @param error for error
     * @return result
     */
    @PutMapping("/teacher/{id}")
    public String updateTeacher(@PathVariable("id") Long id,@RequestBody @Valid Teacher teacher,  Errors error) {
        if (teacherRepository.findByNationalCode(teacher.getNationalCode())!=null)
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"The teacher with this national code exist\"\n" +
                    "}";
        teacher.setId(id);
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }

        teacherRepository.save(teacher);

        return "{\n" +
                "    \"status\": success\n" +
                "    \"message\":success Update Teacher\n" +

                "}";
    }

    /**
     * function for delete teacher
     * @param json input data
     * @param error for error
     * @return result
     */
    @DeleteMapping("/teacher")
    public String deleteTeacher(@Valid @RequestBody String json,  Errors error) {
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        String nationalCode = data.get("nationalCode").getAsString();
        Teacher teacher = teacherRepository.findByNationalCode(nationalCode);
        if (teacher == null) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \"Not Find Teacher With this national code\"\n" +

                    "}";
        }
        if (error.hasErrors()) {
            return "{\n" +
                    "    \"status\":  \"error\"\n" +
                    "    \"Errors\":  \""+error.getFieldError().getField()+":"+error.getAllErrors().get(0).getDefaultMessage()+"\"\n" +
                    "}";
        }
        teacherRepository.deleteById(teacher.getId());

        return "{\n" +
                "    \"status\": success\n" +
                "    \"message\":success Delete Teacher\n" +

                "}";
    }

    /**
     * function for get all teacher
     * @return all teacher
     */
    @GetMapping(path = "/teacher")
    public Iterable<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }


    /**
     * function for get a teacher
     * @param id input data
     * @return a teacher
     */
    @GetMapping(path = "/teacher/{id}")
    public Optional<Teacher> getTeacher(@PathVariable("id") Long id) {
        return teacherRepository.findById(id);
    }

    /**
     * function for get student with ( lesson id and teacher id and term )
     * @param json input data
     * @return all student
     */
    @GetMapping(path = "/teacher/students")
    public Iterable<Student> getStudentOfTeacher(@Valid @RequestBody String json) {
        JsonObject data = new Gson().fromJson(json, JsonObject.class);
        Long teacherId = data.get("teacherId").getAsLong();
        Long lessonId = data.get("lessonId").getAsLong();
        int term = data.get("term").getAsInt();

        Iterable<Sec> secs= secRepository.findByLessonIdAndTermAndTeacherId(lessonId,term,teacherId);

        ArrayList<Long> ids= new ArrayList<>();
        for (Sec sec: secs)
            ids.add(sec.getStudentId());


        return studentRepository.findAllById(ids);
    }




}
