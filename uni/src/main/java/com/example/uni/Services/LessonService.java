package com.example.uni.Services;
import com.example.uni.Models.Lesson;
import com.example.uni.Models.Professor;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import com.example.uni.Repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentLessonService studentLessonService;

    @Autowired
    private StudentService studentService;


    /**
     * function for add new lesson
     * @param lesson input data
     * @return lesson
     */
    public Lesson addLesson(Lesson lesson, Long collegeId)throws Exception {
        if (searchByLessonNameAndCollegeId(lesson.getLessonName(), collegeId) != null)
            throw new Exception("The lesson With This name in this college Exist");
        lessonRepository.save(lesson);
        return collegeService.addLesson(lesson,collegeId);
    }

    /**
     * function for update lesson
     * @param lesson input data
     * @param lessonId      of lesson
     * @param collegeId     of college
     * @return lesson
     */
    public Lesson updateLesson(Lesson lesson , Long lessonId , Long collegeId)throws Exception{
        lesson.setId(lessonId);
        collegeService.deleteLesson(lesson,getLesson(lessonId).getCollege().getId());
        lessonRepository.save(lesson);
        return collegeService.addLesson(lesson,collegeId);
    }


    /**
     * function for delete lesson
     * @param id of lesson
     */
    public void  deleteLesson(Long id)throws Exception{
        final String[] errors = {""};
        Lesson lesson = getLesson(id);
        collegeService.deleteLesson(lesson,lesson.getCollege().getId());
        lesson.getProfessors().forEach((temp)-> {
            try {
                professorService.deleteLesson(temp.getId(),id);
            } catch (Exception e) {
                errors[0] = errors[0] + (e.getMessage() + "/n");
            }
        });
        studentLessonService.getAllStudentLesson().stream().filter
                ((temp)-> temp.getLesson().getId().equals(id)).forEach((temp)-> {
            try {
                studentLessonService.deleteStudentLesson(temp.getId());
            } catch (Exception e) {
                errors[0] = errors[0] + (e.getMessage() + "/n");
            }
        });

        if (!errors[0].isEmpty())
            throw new Exception(errors[0]);

    }


    /**
     * function for add professor to lesson
     * @param lessonId input data
     * @param professor input data
     * @return lesson
     */
    public Lesson addProfessor(Long lessonId , Professor professor)throws Exception{
        Lesson lesson= getLesson(lessonId);
        lesson.getProfessors().add(professor);
        professor.getLessons().add(lesson);
        lessonRepository.save(lesson);
        return lesson;
    }

    /**
     * function for delete professor to lesson
     * @param lessonId input data
     * @param professor input data
     * @return lesson
     */
    public Lesson deleteProfessor(Long lessonId , Professor professor)throws Exception{
        Lesson lesson= getLesson(lessonId);
        lesson.getProfessors().remove(professor);
        lessonRepository.save(lesson);
        return lesson;
    }


    /**
     * function for search lesson by name and college id
     * @param lessonName input data
     * @param collegeId input data
     * @return lesson
     */
    public Lesson searchByLessonNameAndCollegeId(String lessonName , Long collegeId){
        return lessonRepository.findByLessonNameAndCollege_Id(lessonName,collegeId);
    }

    /**
     * function for get lesson with id
     * @param id of lesson
     * @return lesson
     */
    public Lesson getLesson(Long id)throws Exception{
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (lesson.isEmpty())
            throw new Exception("The lesson With This id not Exist");

        return lesson.get();

    }

    /**
     * function for get all lesson
     * @return all lesson
     */
    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }


    /**
     * function for get all professor of lesson
     * @param id of lesson
     * @return all professor of lesson
     */
    public Set<Professor> getAllProfessor(Long id)throws Exception{
        return getLesson(id).getProfessors();
    }

    /**
     * function for get all student of lesson
     * @param id of lesson
     * @return all student of lesson
     */
    public Set<Student> getAllStudent(Long id)throws Exception{
        return getLesson(id).getStudentLessons().stream().map(StudentLesson::getStudent).collect(Collectors.toSet());

    }


    /**
     * function for get avg of lesson
     * @param id of lesson
     * @return avg
     */
    public float getAvg(Long id)throws Exception{
        final float[] avg = {0};
        final int[] unit = {0};

        studentLessonService.searchByLessonId(getLesson(id).getId()).forEach(
                (temp)->{
                    avg[0] +=temp.getGrade() * temp.getLesson().getUnit();
                    unit[0] +=temp.getLesson().getUnit();
                }
        );
        avg[0]/=unit[0];
        return avg[0];

    }



}
