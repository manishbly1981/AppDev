package com.student.spring.security.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.spring.security.api.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	List<User> findByEmail(String email);
}
