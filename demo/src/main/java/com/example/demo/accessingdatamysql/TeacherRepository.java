package com.example.demo.accessingdatamysql;

import com.example.demo.Models.Student;
import com.example.demo.Models.Teacher;
import org.springframework.data.repository.CrudRepository;


public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Teacher findByNationalCode(String nationalCode);
}