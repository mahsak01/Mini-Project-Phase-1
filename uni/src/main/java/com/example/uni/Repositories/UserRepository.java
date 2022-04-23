package com.example.uni.Repositories;

import com.example.uni.Dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDto,Long> {

    UserDto findByUsername(String username);
}
