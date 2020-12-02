package com.serviceApp.utility.mailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class JMS {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Environment environment;

	// function for sending mail
	public void registrationMail(String emailId, String password) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(environment.getProperty("EMAIL_ID"));
		message.setTo(emailId);
		message.setSubject("serviceapp registeration");
		message.setText("Your Registration for ServiceApp is Successful. Your credentials are \n Email :"+ emailId +" \n Password : " + password);
		mailSender.send(message);
	}
	
	public void sendMail(String emailId,String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(environment.getProperty("EMAIL_ID"));
		message.setTo(emailId);
		message.setSubject(subject);
		message.setText(msg);
		
		mailSender.send(message);
		
	}
}
