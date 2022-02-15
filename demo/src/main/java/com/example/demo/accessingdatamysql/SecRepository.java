package com.example.demo.accessingdatamysql;

import com.example.demo.Models.Sec;
import com.example.demo.Models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface SecRepository extends CrudRepository<Sec, Long> {

    Iterable<Sec> findByLessonIdAndTermAndTeacherId(Long lessonId , int term , Long teacherId);

    Sec findByLessonIdAndTermAndStudentIdAndSecId(Long lessonId , int term , Long studentId, Long secId);

    @Query(value = "SELECT avg(score) FROM Sec where lessonId=?1 and term=?2 and teacherId=?3")
     float avgOfLesson(Long lessonId , int term , Long teacherId);

    Iterable<Sec> findByStudentIdAndTerm(Long studentId , int term );
}