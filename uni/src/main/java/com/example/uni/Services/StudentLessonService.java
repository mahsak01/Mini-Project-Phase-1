package com.example.uni.Services;

import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.StudentLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentLessonService {

    @Autowired
    private StudentLessonRepository studentLessonRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private LessonService lessonService;


    /**
     * function for add student lesson
     *
     * @param studentLesson input data
     * @param lessonId      input data
     * @param studentId     input data
     * @param professorId   input data
     * @return StudentLesson
     */
    public StudentLesson addStudentLesson(StudentLesson studentLesson,
                                          Long lessonId, Long studentId, Long professorId) throws Exception {

        if (searchByTermAndAndLessonId(studentLesson.getTerm(), lessonId) != null)
            throw new Exception("The student took this lesson this term");

        studentLessonRepository.save(studentLesson);
        studentLesson.setProfessor(professorService.getProfessor(professorId));
        studentLesson.setLesson(lessonService.getLesson(lessonId));
        studentService.addLesson(studentLesson, studentId);
        studentLessonRepository.save(studentLesson);
        return studentLesson;

    }

    /**
     * function for update student lesson
     *
     * @param studentLesson input data
     * @param lessonId      input data
     * @param studentId     input data
     * @param professorId   input data
     * @return StudentLesson
     */
    public StudentLesson updateStudentLesson(StudentLesson studentLesson, Long studentLessonId,
                                             Long lessonId, Long studentId, Long professorId) throws Exception {


        studentLesson.setId(studentLessonId);
        studentLesson.setProfessor(null);
        studentLesson.setLesson(null);
        studentService.addLesson(studentLesson, studentId);
        studentLessonRepository.delete(studentLesson);
        return studentLesson;

    }


    public void deleteStudentLesson(Long id) throws Exception {

        StudentLesson studentLesson = getStudentLesson(id);
        studentService.deleteLesson(studentLesson, studentLesson.getStudent().getId());
        studentLessonRepository.deleteById(id);

    }

    /**
     * function for search student lesson with term and lesson id
     *
     * @param term     input data
     * @param lessonId input data
     * @return StudentLesson
     */
    public StudentLesson searchByTermAndAndLessonId(int term, Long lessonId) {
        return studentLessonRepository.findByTermAndAndLesson_Id(term, lessonId);
    }


    /**
     * function for get student lesson
     * @param id of student lesson
     * @return student lesson
     */
    public StudentLesson getStudentLesson(Long id) throws Exception {
        Optional<StudentLesson> studentLesson = studentLessonRepository.findById(id);
        if (studentLesson.isEmpty())
            throw new Exception("The studentLesson With This id not Exist");
        return studentLesson.get();
    }


    /**
     * function for get all student lesson
     * @return all student lesson
     */
    public List<StudentLesson> getAllStudentLesson(){
        return studentLessonRepository.findAll();
    }


    /**
     * function for search studentLesson with lesson id and student id and term
     * @param lessonId input data
     * @param studentId input data
     * @param term input data
     * @return student lesson
     */
    public StudentLesson searchByLessonAndStudentAndTerm(Long lessonId , Long studentId , int term)throws Exception{
        return studentLessonRepository.findByStudentAndLessonAndTerm(studentService.getStudent(studentId)
        ,lessonService.getLesson(lessonId),term);
    }

    /**
     * function for search student lesson with lessonId
     * @param id of lesson
     * @return list of student lesson
     */
    public List<StudentLesson> searchByLessonId(Long id){

        return studentLessonRepository.findByLesson_Id(id);

    }


}
