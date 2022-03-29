package com.example.uni.Services;

import com.example.uni.Dto.StudentLessonDto;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Professor;
import com.example.uni.Models.Student;
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
     * @param studentLessonDto input data
     * @return StudentLesson
     */
    public StudentLesson addStudentLesson(StudentLessonDto studentLessonDto) throws Exception {

        if (searchByLessonAndStudentAndTerm(studentLessonDto.getLessonId() ,studentLessonDto.getStudentId(),studentLessonDto.getTerm()) != null)
            throw new Exception("The student took this lesson this term");

        Student student = studentService.getStudent(studentLessonDto.getStudentId());
        Lesson lesson = lessonService.getLesson(studentLessonDto.getLessonId());
        Professor professor=professorService.getProfessor(studentLessonDto.getProfessorId());
        StudentLesson studentLesson = new StudentLesson(studentLessonDto.getGrade() , studentLessonDto.getTerm(),
                lesson,professor,student);
        studentLessonRepository.save(studentLesson);
        lesson.addStudentLesson(studentLesson);
        student.addStudentLesson(studentLesson);
        return studentLesson;

    }

    /**
     * function for update student lesson
     * @param studentLessonDto input data
     * @param studentLessonId input data
     * @return StudentLesson
     */
    public StudentLesson updateStudentLesson(StudentLessonDto studentLessonDto, Long studentLessonId) throws Exception {

        StudentLesson studentLesson = getStudentLesson(studentLessonId);

        if (searchByLessonAndStudentAndTerm(studentLessonDto.getLessonId() ,studentLessonDto.getStudentId(),studentLessonDto.getTerm()) != null)
            if (!searchByLessonAndStudentAndTerm(studentLessonDto.getLessonId() ,studentLessonDto.getStudentId(),studentLessonDto.getTerm()).getId().equals(studentLessonId) )
                throw new Exception("The student took this lesson this term");

        Student student = studentService.getStudent(studentLessonDto.getStudentId());
        Lesson lesson = lessonService.getLesson(studentLessonDto.getLessonId());
        Professor professor=professorService.getProfessor(studentLessonDto.getProfessorId());

        studentLesson.getStudent().deleteStudentLesson(studentLesson);
        studentLesson.getLesson().deleteStudentLesson(studentLesson);

        studentLesson = new StudentLesson(studentLessonDto.getGrade() , studentLessonDto.getTerm(),
                lesson,professor,student);

        studentLessonRepository.save(studentLesson);
        lesson.addStudentLesson(studentLesson);
        student.addStudentLesson(studentLesson);

        return studentLesson;

    }


    /**
     * function for delete student lesson
     * @param studentLessonDto input data
     */
    public void deleteStudentLesson(StudentLessonDto studentLessonDto) throws Exception {

        StudentLesson studentLesson = searchByLessonAndStudentAndTerm(studentLessonDto.getLessonId(),
                studentLessonDto.getStudentId(), studentLessonDto.getTerm());
        studentLesson.getLesson().deleteStudentLesson(studentLesson);
        studentLesson.getStudent().deleteStudentLesson(studentLesson);
        studentLessonRepository.deleteById(studentLesson.getId());

    }


        /**
     * function for set grade of student
     * @param studentLessonDto input data
     * @return student lesson
     */
    public StudentLesson setGrade(StudentLessonDto studentLessonDto) throws Exception {

               StudentLesson studentLesson = searchByLessonAndStudentAndTerm(studentLessonDto.getLessonId(),studentLessonDto.getStudentId(),studentLessonDto.getTerm());
               if (studentLesson!=null){
                   studentLesson.setGrade(studentLessonDto.getGrade());
                   studentLessonRepository.save(studentLesson);
                   return studentLesson;
               }


        throw  new Exception("The lesson and professor and student With This id not Exist");
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


    /**
     * function for get avg of lesson
     *
     * @param lessonId of lesson
     * @return avg
     */
    public float getAvgOfLesson(Long lessonId) throws Exception {
        final float[] avg = {0};
        final int[] unit = {0};

        searchByLessonId(lessonService.getLesson(lessonId).getId()).forEach(
                (temp)->{
                    avg[0] +=temp.getGrade() * temp.getLesson().getUnit();
                    unit[0] +=temp.getLesson().getUnit();
                }
        );
        if (unit[0] != 0)
            avg[0] /= unit[0];
        return avg[0];

    }

    /**
     * function for get avg of all lesson of professor
     *
     * @param professorId of professor
     * @return avg
     */
    public float getAvgOfProfessor(Long professorId) throws Exception {
        final float[] avg = {0};
        final int[] unit = {0};
        final String[] errors = {""};

        professorService.getProfessor(professorId).getLessons().forEach((temp) -> {
            try {
                int tempUnit = lessonService.getLesson(temp.getId()).getUnit();
                avg[0] += getAvgOfLesson(temp.getId()) * tempUnit;
                unit[0] += tempUnit;
            } catch (Exception e) {
                errors[0] += e.getMessage() + "\n";
            }
        });
        if (!errors[0].isEmpty()) throw new Exception(errors[0]);
        if (unit[0]!=0)
            avg[0]/=unit[0];
        return avg[0];
    }
}
