package com.example.uni.Repositories;

import com.example.uni.Models.Lesson;
import com.example.uni.Models.Student;
import com.example.uni.Models.StudentLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentLessonRepository extends JpaRepository<StudentLesson ,Long> {

    StudentLesson findByTermAndAndLesson_Id(int term , Long lessonId);

    List<StudentLesson> findByLesson_Id(Long lessonId);

    StudentLesson findByStudentAndLessonAndTerm(Student student , Lesson lesson ,int term);
}
