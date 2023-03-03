package com.student.spring.security.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.spring.security.api.model.User;
import com.student.spring.security.api.repository.UserRepository;
import com.student.spring.security.api.service.AdminService;
import com.student.spring.security.api.service.impl.EMailService;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("/api")
public class AdminController {
	private AdminService adminService;
	
	
	public AdminController(AdminService adminService) {
		super();
		this.adminService= adminService;
	}


	@PostMapping("register")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		return new ResponseEntity<String>(adminService.saveUser(user),HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return adminService.getUserList();
	}
//	public String addUser(@RequestBody User user) {
//		adminService.saveUser(user);
//		return "User Added Successfully";
//	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam String emailId, @RequestParam String password, @RequestParam(defaultValue="",required=false) String authenticationCode){
		if (authenticationCode==null||authenticationCode=="")
			return new ResponseEntity<String>(adminService.login(emailId, password), HttpStatus.OK);
		else
			return new ResponseEntity<String>(adminService.login(emailId, password, authenticationCode), HttpStatus.OK);
		
	}
}
