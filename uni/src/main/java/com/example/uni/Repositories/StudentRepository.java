package com.example.uni.Repositories;

import com.example.uni.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student , Long> {

        Student findByNationalCode(String nationalCode);
        Student findByPersonnelNumber(String personnelNumber);

}
