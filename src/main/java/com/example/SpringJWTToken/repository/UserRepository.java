package com.example.SpringJWTToken.repository;

import com.example.SpringJWTToken.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
