package com.example.uni.Services;

import com.example.uni.Dto.LessonDto;
import com.example.uni.Models.*;
import com.example.uni.Repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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




    /**
     * function for add new lesson
     *
     * @param lessonDto input data
     * @return lesson
     */
    public Lesson addLesson(LessonDto lessonDto) throws Exception {

        if (searchByLessonNameAndCollegeId((String) Models.getField(lessonDto,"lessonName")
                , (Long) Models.getField(lessonDto,"collegeId")) != null)
            throw new Exception("The lesson With This name in this college Exist");
        Lesson lesson = new Lesson((String) Models.getField(lessonDto,"lessonName"),
                (int) Models.getField(lessonDto,"unit"));
        collegeService.getCollege((Long) Models.getField(lessonDto,"collegeId"))
                .addLesson(lesson);
        lessonRepository.save(lesson);

        return lesson;
    }

    /**
     * function for update lesson
     *
     * @param lessonDto input data
     * @param lessonId  of lesson
     * @return lesson
     */
    public Lesson updateLesson(LessonDto lessonDto, Long lessonId) throws Exception {
        Lesson lesson = getLesson(lessonId);
        if (searchByLessonNameAndCollegeId((String) Models.getField(lessonDto,"lessonName"), (Long) Models.getField(lessonDto,"collegeId")) != null)
            throw new Exception("The lesson With This name in this college Exist");
        collegeService.getCollege((Long) Models.getField(lessonDto,"collegeId")).deleteLesson(lesson);
        Models.setField(lesson,"lessonName",(String) Models.getField(lessonDto,"lessonName"));
        Models.setField(lesson,"unit", (int) Models.getField(lessonDto,"unit"));
        Models.setField(lesson,"id",lessonId);

        collegeService.getCollege((Long) Models.getField(lessonDto,"collegeId")).addLesson(lesson);
        lessonRepository.save(lesson);

        return lesson;
    }


    /**
     * function for delete lesson
     *
     * @param lessonId of lesson
     */
    public void deleteLesson(Long lessonId) throws Exception {

        Lesson lesson = getLesson(lessonId);

        lessonRepository.delete(lesson);



        ((College)Models.getField(lesson,"college")).deleteLesson(lesson);


    }


    /**
     * function for search lesson by name and college id
     *
     * @param lessonName input data
     * @param collegeId  input data
     * @return lesson
     */
    public Lesson searchByLessonNameAndCollegeId(String lessonName, Long collegeId) {
        return lessonRepository.findByLessonNameAndCollege_Id(lessonName, collegeId);
    }

    /**
     * function for get lesson with id
     *
     * @param lessonId of lesson
     * @return lesson
     */
    public Lesson getLesson(Long lessonId) throws Exception {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if (lesson.isEmpty())
            throw new Exception("The lesson With This id not Exist");

        return lesson.get();

    }

    /**
     * function for get all lesson
     *
     * @return all lesson
     */
    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }


    /**
     * function for get all professor of lesson
     *
     * @param lessonId of lesson
     * @return all professor of lesson
     */
    public Set<Professor> getAllProfessor(Long lessonId) throws Exception {
        return (Set<Professor>) Models.getField(getLesson(lessonId),"professors");
    }

    /**
     * function for get all student of lesson
     *
     * @param lessonId of lesson
     * @return all student of lesson
     */
    public List<Student> getAllStudent(Long lessonId) throws Exception {
        List<Student> students = new ArrayList<>();

        for (StudentLesson studentLesson:
                (Set<StudentLesson>)Models.getField(getLesson(lessonId),"studentLessons"))
          students.add((Student) Models.getField(studentLesson,"student"));

        return students;

    }





}
