package com.example.uni.Repositories;

import com.example.uni.Models.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College , Long> {

    College findByCollegeName(String collegeName);
}
