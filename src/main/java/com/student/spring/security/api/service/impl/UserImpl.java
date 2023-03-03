package com.student.spring.security.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.spring.security.api.model.User;
import com.student.spring.security.api.repository.UserRepository;
import com.student.spring.security.api.service.AdminService;

@Service
public class UserImpl implements AdminService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EMailService eMailService; 
	
	@Override
	public String saveUser(User user) {
		String authCode="1234";
		if (user.getStatus()==null)
			user.setStatus("InActive");
		if (user.getAuthenticationCode()==null)
			user.setAuthenticationCode(authCode);
		
		userRepository.save(user);
		if (sendMailOnRegistration(user.getEmail()))
			return "User registered successfully";
		else
			return "User not registered";
	}


	@Override
	public String login(String emailId, String password, String authenticationCode) {
		
		List<User> userList=userRepository.findByEmail(emailId);
		if (userList.size()>0) {
			User user= userList.get(0);
			if (user.getEmail().equalsIgnoreCase(emailId) && user.getPassword().equals(password) && user.getAuthenticationCode().equals(authenticationCode)) {
				user.setStatus("Active");
				user.setAuthenticationCode("");
				userRepository.save(user);
				return "User Login successfully";
			}
			else
				return "Check your email id/Password/Authentication Code";
		}
		else
			return "Email id not registered";
	}

	@Override
	public String login(String emailId, String password) {
		List<User> userList=userRepository.findByEmail(emailId);
		if (userList.size()>0) {
			User user= userList.get(0);
			if (user.getEmail().equalsIgnoreCase(emailId) && user.getPassword().equals(password) && user.getStatus().equalsIgnoreCase("Active"))
				return "User Login successfully";
			else
				return "Check your email id/Password";
		}
		else
			return "Email id not registered";
	}

	
	@Override
	public List<User> getUserList() {
		return userRepository.findAll();
	}

	
	/***********************************************************/
	public boolean sendMailOnRegistration(String toMail){
		boolean bMailSend=false;
		String authCode="";
		String firstName="";
		List<User> userList= userRepository.findAll();
		for(User user: userList) {
			if(user.getEmail().equalsIgnoreCase(toMail)) {
				authCode= user.getAuthenticationCode();
				firstName= user.getFirstName();
				String Subject= "User Registration Information";
				String body= "Hello " + firstName + ",\nWelcome onboard!\n\nYou need to login, using code "+ authCode+ " to activate your account";
//				eMailService.sendEmail(toMail, Subject, body);
				bMailSend= true;
			}
		}
		return bMailSend;
	}
}