package com.student.spring.security.api.service;

import java.util.List;

import com.student.spring.security.api.model.User;

public interface AdminService {
	String saveUser(User user);
	List<User> getUserList();

	String login(String emailId, String password, String verificationCode);
	String login(String emailId, String password);
}
