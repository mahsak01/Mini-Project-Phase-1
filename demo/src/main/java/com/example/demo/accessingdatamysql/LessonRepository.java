package com.example.demo.accessingdatamysql;

import com.example.demo.Models.Lesson;
import org.springframework.data.repository.CrudRepository;


public interface LessonRepository extends CrudRepository<Lesson, Long> {

    Lesson findByNameAndTeacherIdAndUniId(String name , Long teacherId , Long uniId);


}