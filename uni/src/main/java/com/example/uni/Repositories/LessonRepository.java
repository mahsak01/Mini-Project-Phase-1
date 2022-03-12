package com.example.uni.Repositories;

import com.example.uni.Models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  LessonRepository extends JpaRepository<Lesson , Long> {

    Lesson findByLessonNameAndCollege_Id(String lessonName , Long collegeId);
}
