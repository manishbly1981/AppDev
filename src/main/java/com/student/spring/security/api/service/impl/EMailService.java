package com.student.spring.security.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
//@PropertySource("file:application.properties")
public class EMailService {
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String gFromMailId;
	
	public void sendEmail(String toMail, String subject, String body) {
		SimpleMailMessage msg= new SimpleMailMessage();
        msg.setFrom(gFromMailId);
        msg.setTo(toMail);
        msg.setSubject(subject);
        msg.setText(body);
        javaMailSender.send(msg);
        System.out.println("Mail send...");
	}
}
