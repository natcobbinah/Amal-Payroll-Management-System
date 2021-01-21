package com.payroll.usermanagement.EmailServices;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(String toEmail,
			String mailSubject, String message) {
		
		var mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom("amalpayrollsystem@gmail.com");
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(mailSubject);
		mailMessage.setText(message);
		
		javaMailSender.send(mailMessage);
	}
	
	
}
