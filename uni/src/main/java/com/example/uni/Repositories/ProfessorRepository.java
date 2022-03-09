package com.example.uni.Repositories;

import com.example.uni.Models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ProfessorRepository extends JpaRepository<Professor , Long> {
}
