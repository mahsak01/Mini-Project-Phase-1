package com.example.uni.Services;

import com.example.uni.Dto.StudentLessonDto;
import com.example.uni.Models.*;
import com.example.uni.Repositories.StudentLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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


        if (searchByLessonAndStudentAndTerm((Long) Models.getField(studentLessonDto,"lessonId")
                ,(Long) Models.getField(studentLessonDto,"studentId"),
                (int) Models.getField(studentLessonDto,"term")) != null)
            throw new Exception("The student took this lesson this term");

        Student student = studentService.getStudent((Long) Models.getField(studentLessonDto,"studentId"));
        Lesson lesson = lessonService.getLesson((Long) Models.getField(studentLessonDto,"lessonId"));
        Professor professor=professorService.getProfessor((Long) Models.getField(studentLessonDto,"professorId"));
        StudentLesson studentLesson = new StudentLesson((float) Models.getField(studentLessonDto,"grade") ,
                (int) Models.getField(studentLessonDto,"term"),
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

        if (searchByLessonAndStudentAndTerm((Long) Models.getField(studentLessonDto,"lessonId")
                ,(Long) Models.getField(studentLessonDto,"studentId"),
                (int) Models.getField(studentLessonDto,"term")) != null)
            if (!Models.getField(searchByLessonAndStudentAndTerm((Long) Models.getField(studentLessonDto,"lessonId")
                    ,(Long) Models.getField(studentLessonDto,"studentId"),
                    (int) Models.getField(studentLessonDto,"term")),
                    "id").equals(studentLessonId) )
                throw new Exception("The student took this lesson this term");

        Student student = studentService.getStudent((Long) Models.getField(studentLessonDto,"studentId"));
        Lesson lesson = lessonService.getLesson((Long) Models.getField(studentLessonDto,"lessonId"));
        Professor professor=professorService.getProfessor((Long) Models.getField(studentLessonDto,"professorId"));

        ((Student) Models.getField(studentLesson,"student")).deleteStudentLesson(studentLesson);
        ((Lesson) Models.getField(studentLesson,"lesson")).deleteStudentLesson(studentLesson);

        studentLesson = new StudentLesson((float) Models.getField(studentLessonDto,"grade") ,
                (int) Models.getField(studentLessonDto,"term"),
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

        StudentLesson studentLesson = searchByLessonAndStudentAndTerm((Long) Models.getField(studentLessonDto,"lessonId"),
                (Long) Models.getField(studentLessonDto,"studentId"),
                (int) Models.getField(studentLessonDto,"term"));

        ((Lesson) Models.getField(studentLesson,"lesson")).deleteStudentLesson(studentLesson);
        ((Student) Models.getField(studentLesson,"studnet")).deleteStudentLesson(studentLesson);

        studentLessonRepository.deleteById((Long) Models.getField(studentLesson,"id"));

    }


        /**
     * function for set grade of student
     * @param studentLessonDto input data
     * @return student lesson
     */
    public StudentLesson setGrade(StudentLessonDto studentLessonDto) throws Exception {

               StudentLesson studentLesson = searchByLessonAndStudentAndTerm((Long) Models.getField(studentLessonDto,"lessonId")
                       ,(Long) Models.getField(studentLessonDto,"studentId"),
                       (int) Models.getField(studentLessonDto,"term"));
               if (studentLesson!=null){
                   Models.setField(studentLesson,"grade",(float) Models.getField(studentLessonDto,"grade"));
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


        searchByLessonId((Long) Models.getField(lessonService.getLesson(lessonId),"id")).forEach(
                (temp)->{

                    try {
                        avg[0] +=(float)Models.getField(temp,"grade") * (int)Models.getField(Models.getField(temp,"lesson"),"unit");
                        unit[0] +=(int)Models.getField(Models.getField(temp,"lesson"),"unit");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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

        ((Set<Lesson>)Models.getField(professorService.getProfessor(professorId),"lessons")).forEach((temp) -> {
            try {
                int tempUnit = (int) Models.getField(lessonService.getLesson((Long) Models.getField(temp,"id")),"unit");
                avg[0] += getAvgOfLesson((Long) Models.getField(temp,"id")) * tempUnit;
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
