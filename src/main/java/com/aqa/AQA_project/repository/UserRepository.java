package com.aqa.AQA_project.repository;

import com.aqa.AQA_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}