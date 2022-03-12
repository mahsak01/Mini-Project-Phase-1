package com.example.uni.Services;

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

    @Autowired
    private StudentLessonService studentLessonService;

    /**
     * function for add new professor
     *
     * @param professor input data
     * @return professor
     */
    public Professor addProfessor(Professor professor, Long id) throws Exception {
        if (searchByNationalCode(professor.getNationalCode()) != null)
            throw new Exception("The professor With This national code Exist");
        else if (searchByPersonnelNumber(professor.getPersonnelNumber()) != null)
            throw new Exception("The professor With This Personnel Number  Exist");
        professorRepository.save(professor);
        return collegeService.addProfessor(professor, id);
    }

    /**
     * function for update professor
     *
     * @param professor   input data
     * @param professorId of professor
     * @param collegeId   of college
     * @return professor
     */
    public Professor updateProfsser(Professor professor, Long professorId, Long collegeId) throws Exception {
        professor.setId(professorId);
        collegeService.deleteProfessor(professor, getProfessor(professorId).getCollege().getId());
        professorRepository.save(professor);
        return collegeService.addProfessor(professor, collegeId);
    }

    /**
     * function for delete professor
     *
     * @param id input data
     * @return professor
     */
    public void deleteProfsser(Long id) throws Exception {

        Professor professor = getProfessor(id);

        collegeService.deleteProfessor(professor, professor.getCollege().getId());

        professorRepository.deleteById(id);

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
     * @param id input data
     * @return professor
     */
    public Professor getProfessor(Long id) throws Exception {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isEmpty()) throw new Exception("The professor With This id not Exist");
        return professor.get();
    }

    /**
     * function for get all lesson of college
     *
     * @param id of professor
     * @return all lesson
     */
    public Set<Lesson> getAllLesson(Long id) throws Exception {

        return getProfessor(id).getLessons();
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
        professor.getLessons().add(lessonService.addProfessor(lessonId, professor));
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
        professor.getLessons().remove(lessonService.deleteProfessor(lessonId, professor));
        professorRepository.save(professor);
        return professor;
    }

    /**
     * function for get all student of professor
     *
     * @param id of professor
     * @return list of student
     */
    public List<Student> getAllStudent(Long id) throws Exception {
        List<Student> students = new ArrayList<>();
        getProfessor(id).getLessons().stream().map(Lesson::getStudentLessons).forEach((temp) -> students.add(((StudentLesson) temp).getStudent()));
        return students;
    }


    /**
     * function for get avg of all lesson of professor
     *
     * @param id of professor
     * @return avg
     */
    public float getAvg(Long id) throws Exception {
        final float[] avg = {0};
        final int[] unit = {0};
        final String[] errors = {""};

        getProfessor(id).getLessons().forEach((temp) -> {
            try {
                int tempUnit = lessonService.getLesson(temp.getId()).getUnit();
                avg[0] += lessonService.getAvg(temp.getId()) * tempUnit;
                unit[0] += tempUnit;
            } catch (Exception e) {
                errors[0] += e.getMessage() + "\n";
            }
        });
        if (!errors[0].isEmpty()) throw new Exception(errors[0]);
        avg[0] /= unit[0];
        return avg[0];
    }




    public StudentLesson setGrade(Long professorId ,Long lessonId, Long studentId , float grade , int term) throws Exception {
        for (Lesson lesson: getProfessor(professorId).getLessons()){
            if (lesson.getId().equals(lessonId)){
                StudentLesson studentLesson = studentLessonService.searchByLessonAndStudentAndTerm(lessonId,studentId,term);
                studentLesson.setGrade(grade);
                studentLessonService.updateStudentLesson(studentLesson,studentLesson.getId(),lessonId,studentId,professorId);
                return studentLesson;
            }
        }
        throw  new Exception("The lesson and professor and student With This id not Exist");
    }


}
