package com.example.uni.Services.Core;

import com.example.uni.Dto.PersonDto;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Professor;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CoreProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CoreCollegeService coreCollegeService;

    @Autowired
    private CoreLessonService coreLessonService;


    /**
     * function for add new professor
     *
     * @param professor input data
     * @return professor
     */
    public Professor addProfessor(Professor professor,Long collgeId) throws Exception {

        if (searchByNationalCode(professor.getNationalCode()) != null)
            throw new Exception("The professor With This national code Exist");
        else if (searchByPersonnelNumber(professor.getPersonnelNumber()) != null)
            throw new Exception("The professor With This Personnel Number  Exist");


        coreCollegeService.getCollege(collgeId).addProfessor(professor);
        professorRepository.save(professor);

        return professor;
    }

    /**
     * function for update professor
     *
     * @param professor   input data
     * @param professorId of professor
     * @return professor
     */
    public Professor updateProfsser(Professor professor,Long collegeId, Long professorId) throws Exception {

        //todo
        Professor newProfessor = getProfessor(professorId);

        if (searchByNationalCode(professor.getNationalCode()) != null)
            if (!searchByNationalCode(professor.getNationalCode()).getId().equals(professorId))
                throw new Exception("The professor With This national code Exist");
        if (searchByPersonnelNumber(professor.getPersonnelNumber()) != null)
            if (!searchByPersonnelNumber(professor.getPersonnelNumber()).getId().equals(professorId))
                throw new Exception("The professor With This Personnel Number  Exist");

        newProfessor.getCollege().deleteProfessor(newProfessor);

        professor.setId(professorId);

        coreCollegeService.getCollege(collegeId).addProfessor(professor);
        professorRepository.save(professor);

        return professor;
    }

    /**
     * function for delete professor
     *
     * @param professorId input data
     * @return professor
     */
    public void deleteProfsser(Long professorId) throws Exception {

        Professor professor = getProfessor(professorId);

        professor.getCollege().deleteProfessor(professor);

        professorRepository.deleteById(professorId);

    }

    /**
     * function for search professor with national code
     *
     * @param nationalCode of professor
     * @return professor
     */

    public Professor searchByNationalCode(String nationalCode) {
        return professorRepository.findByNationalCode(nationalCode);
    }

    /**
     * function for search professor with personnel Number
     *
     * @param personnelNumber of professor
     * @return professor
     */
    public Professor searchByPersonnelNumber(String personnelNumber) {
        return professorRepository.findByPersonnelNumber(personnelNumber);
    }

    /**
     * function for get all professor
     *
     * @return all professor
     */
    public List<Professor> getAllProfessor() {
        return professorRepository.findAll();
    }

    /**
     * function for get professor
     *
     * @param professorId input data
     * @return professor
     */
    public Professor getProfessor(Long professorId) throws Exception {
        Optional<Professor> professor = professorRepository.findById(professorId);
        if (professor.isEmpty()) throw new Exception("The professor With This id not Exist");
        return professor.get();
    }

    /**
     * function for get all lesson of college
     *
     * @param professorId of professor
     * @return all lesson
     */
    public Set<Lesson> getAllLesson(Long professorId) throws Exception {

        return getProfessor(professorId).getLessons();
    }

    /**
     * function for get all student of professor
     *
     * @param professorId of professor
     * @return list of student
     */
    public List<Student> getAllStudent(Long professorId) throws Exception {
        List<Student> students = new ArrayList<>();
        for (Lesson lesson: getProfessor(professorId).getLessons()){
            for (StudentLesson studentLesson : lesson.getStudentLessons())
                students.add(studentLesson.getStudent());


        }
        return students;
    }

    /**
     * function for add lesson of professor
     *
     * @param professorId input data
     * @param lessonId    input data
     * @return professor
     */

    public Professor addLesson(Long professorId, Long lessonId) throws Exception {
        Professor professor = getProfessor(professorId);
        Lesson lesson = coreLessonService.getLesson(lessonId);
        professor.addLesson(lesson);
        lesson.addProfessor(professor);
        professorRepository.save(professor);
        return professor;
    }


    /**
     * function for delete lesson of professor
     *
     * @param professorId input data
     * @param lessonId    input data
     * @return professor
     */
    public Professor deleteLesson(Long professorId, Long lessonId) throws Exception {
        Professor professor = getProfessor(professorId);
        Lesson lesson = coreLessonService.getLesson(lessonId);
        professor.deleteLesson(lesson);
        lesson.deleteProfessor(professor);
        professorRepository.save(professor);
        return professor;
    }







}
