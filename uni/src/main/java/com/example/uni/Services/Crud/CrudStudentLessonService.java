package com.example.uni.Services.Crud;

import com.example.uni.Dto.StudentLessonDto;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Professor;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.StudentLessonRepository;
import com.example.uni.Services.Core.CoreStudentLessonService;
import com.example.uni.Services.Core.CoreStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudStudentLessonService {

    @Autowired
    private CoreStudentLessonService coreStudentLessonService;


    /**
     * function for add student lesson
     *
     * @param studentLessonDto input data
     * @return StudentLesson
     */
    public StudentLesson addStudentLesson(StudentLessonDto studentLessonDto) throws Exception {

        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setGrade(studentLessonDto.getGrade());
        studentLesson.setTerm(studentLessonDto.getTerm());


        return coreStudentLessonService.addStudentLesson(studentLesson, studentLessonDto.getStudentId(),
                studentLessonDto.getLessonId(), studentLessonDto.getProfessorId());

    }


    /**
     * function for delete student lesson
     *
     * @param studentLessonDto input data
     */
    public void deleteStudentLesson(StudentLessonDto studentLessonDto) throws Exception {

        coreStudentLessonService.deleteStudentLesson(studentLessonDto.getStudentId(), studentLessonDto.getLessonId(), studentLessonDto.getTerm());

    }


    /**
     * function for set grade of student
     *
     * @param studentLessonDto input data
     * @return student lesson
     */
    public StudentLesson setGrade(StudentLessonDto studentLessonDto) throws Exception {
        return coreStudentLessonService.setGrade(studentLessonDto.getStudentId(), studentLessonDto.getLessonId(), studentLessonDto.getTerm(), studentLessonDto.getGrade());
    }


    /**
     * function for get avg of lesson
     *
     * @param lessonId of lesson
     * @return avg
     */
    public float getAvgOfLesson(Long lessonId) throws Exception {
        return coreStudentLessonService.getAvgOfLesson(lessonId);

    }

    /**
     * function for get avg of all lesson of professor
     *
     * @param professorId of professor
     * @return avg
     */
    public float getAvgOfProfessor(Long professorId) throws Exception {
        return getAvgOfProfessor(professorId);
    }
}
