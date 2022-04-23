package com.example.uni.Services.Crud;

import com.example.uni.Dto.LessonDto;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Professor;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.LessonRepository;
import com.example.uni.Services.Core.CoreCollegeService;
import com.example.uni.Services.Core.CoreLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrudLessonService {

    @Autowired
    private CoreLessonService coreLessonService;





    /**
     * function for add new lesson
     *
     * @param lessonDto input data
     * @return lesson
     */
    public Lesson addLesson(LessonDto lessonDto) throws Exception {
        Lesson lesson = new Lesson(lessonDto.getLessonName(),lessonDto.getUnit());
        return coreLessonService.addLesson(lesson,lessonDto.getCollegeId());
    }

    /**
     * function for update lesson
     *
     * @param lessonDto input data
     * @param lessonId  of lesson
     * @return lesson
     */
    public Lesson updateLesson(LessonDto lessonDto, Long lessonId) throws Exception {
        Lesson lesson = new Lesson(lessonDto.getLessonName(),lessonDto.getUnit());

        return coreLessonService.updateLesson(lesson,lessonDto.getCollegeId(),lessonId);
    }


    /**
     * function for delete lesson
     *
     * @param lessonId of lesson
     */
    public void deleteLesson(Long lessonId) throws Exception {

        coreLessonService.deleteLesson(lessonId);


    }



    /**
     * function for get lesson with id
     *
     * @param lessonId of lesson
     * @return lesson
     */
    public Lesson getLesson(Long lessonId) throws Exception {
        return coreLessonService.getLesson(lessonId);

    }

    /**
     * function for get all lesson
     *
     * @return all lesson
     */
    public List<Lesson> getAllLesson() {
        return coreLessonService.getAllLesson();
    }


    /**
     * function for get all professor of lesson
     *
     * @param lessonId of lesson
     * @return all professor of lesson
     */
    public Set<Professor> getAllProfessor(Long lessonId) throws Exception {
        return coreLessonService.getAllProfessor(lessonId);
    }

    /**
     * function for get all student of lesson
     *
     * @param lessonId of lesson
     * @return all student of lesson
     */
    public List<Student> getAllStudent(Long lessonId) throws Exception {
        return coreLessonService.getAllStudent(lessonId);

    }





}
