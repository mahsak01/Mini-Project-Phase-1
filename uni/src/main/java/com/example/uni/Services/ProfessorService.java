package com.example.uni.Services;

import com.example.uni.Dto.PersonDto;
import com.example.uni.Models.*;
import com.example.uni.Repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private LessonService lessonService;


    /**
     * function for add new professor
     *
     * @param professorDto input data
     * @return professor
     */
    public Professor addProfessor(PersonDto professorDto) throws Exception {

        if (searchByNationalCode((String) Models.getField(professorDto,"nationalCode")) != null)
            throw new Exception("The professor With This national code Exist");
        else if (searchByPersonnelNumber((String) Models.getField(professorDto,"personnelNumber")) != null)
            throw new Exception("The professor With This Personnel Number  Exist");

        Professor professor= new Professor((String) Models.getField(professorDto,"personnelNumber"),
                (String) Models.getField(professorDto,"firstname"),
                (String) Models.getField(professorDto,"lastname"),
                (String) Models.getField(professorDto,"nationalCode"));

        collegeService.getCollege((Long) Models.getField(professorDto,"collegeId")).addProfessor(professor);
        professorRepository.save(professor);

        return professor;
    }

    /**
     * function for update professor
     *
     * @param professorDto   input data
     * @param professorId of professor
     * @return professor
     */
    public Professor updateProfsser(PersonDto professorDto, Long professorId) throws Exception {

        //todo
        Professor professor = getProfessor(professorId);

        if (searchByNationalCode((String) Models.getField(professorDto,"nationalCode")) != null)
            if (!Models.getField(searchByNationalCode((String) Models.getField(professorDto,"nationalCode")),"id").equals(professorId))
                throw new Exception("The professor With This national code Exist");
        if (searchByPersonnelNumber((String) Models.getField(professorDto,"personnelNumber")) != null)
            if (!Models.getField(searchByPersonnelNumber((String) Models.getField(professorDto,"personnelNumber")),"id").equals(professorId))
                throw new Exception("The professor With This Personnel Number  Exist");

        ((College)Models.getField(professor,"college")).deleteProfessor(professor);

        professor = new Professor((String) Models.getField(professorDto,"personnelNumber"),
                (String) Models.getField(professorDto,"firstname"),
                (String) Models.getField(professorDto,"lastname"),
                (String) Models.getField(professorDto,"nationalCode"));
        Models.setField(professor,"id",professorId);

        collegeService.getCollege((Long) Models.getField(professorDto,"collegeId")).addProfessor(professor);
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

        ((College) Models.getField(professor,"college")).deleteProfessor(professor);

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

        return (Set<Lesson>) Models.getField(getProfessor(professorId),"lessons");
    }

    /**
     * function for get all student of professor
     *
     * @param professorId of professor
     * @return list of student
     */
    public List<Student> getAllStudent(Long professorId) throws Exception {
        List<Student> students = new ArrayList<>();
        for (Lesson lesson: (Set<Lesson>) Models.getField(getProfessor(professorId),"lessons")){
            for (StudentLesson studentLesson : (Set<StudentLesson>) Models.getField(lesson,"studentLessons"))
                students.add((Student) Models.getField(studentLesson,"student"));


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
        Lesson lesson = lessonService.getLesson(lessonId);
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
        Lesson lesson = lessonService.getLesson(lessonId);
        professor.deleteLesson(lesson);
        lesson.deleteProfessor(professor);
        professorRepository.save(professor);
        return professor;
    }







}
