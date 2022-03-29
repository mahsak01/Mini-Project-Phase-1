package com.example.uni.Services;

import com.example.uni.Dto.ProfessorDto;
import com.example.uni.Dto.StudentLessonDto;
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
import java.util.stream.Collectors;

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
    public Professor addProfessor(ProfessorDto professorDto) throws Exception {
        if (searchByNationalCode(professorDto.getNationalCode()) != null)
            throw new Exception("The professor With This national code Exist");
        else if (searchByPersonnelNumber(professorDto.getPersonnelNumber()) != null)
            throw new Exception("The professor With This Personnel Number  Exist");

        Professor professor= new Professor(professorDto.getPersonnelNumber(),
                professorDto.getFirstname(),professorDto.getLastname(),
                professorDto.getNationalCode());

        collegeService.getCollege(professorDto.getCollegeId()).addProfessor(professor);
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
    public Professor updateProfsser(ProfessorDto professorDto, Long professorId) throws Exception {

        //todo
        Professor professor = getProfessor(professorId);

        if (searchByNationalCode(professorDto.getNationalCode()) != null)
            if (!searchByNationalCode(professorDto.getNationalCode()).getId().equals(professorId))
                throw new Exception("The professor With This national code Exist");
        if (searchByPersonnelNumber(professorDto.getPersonnelNumber()) != null)
            if (!searchByPersonnelNumber(professorDto.getPersonnelNumber()).getId().equals(professorId))
                throw new Exception("The professor With This Personnel Number  Exist");

        professor.getCollege().deleteProfessor(professor);

        professor = new Professor(professorDto.getPersonnelNumber(),
                professorDto.getFirstname(), professorDto.getLastname(),
                professorDto.getNationalCode());
        professor.setId(professorId);

        collegeService.getCollege(professorDto.getCollegeId()).addProfessor(professor);
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
