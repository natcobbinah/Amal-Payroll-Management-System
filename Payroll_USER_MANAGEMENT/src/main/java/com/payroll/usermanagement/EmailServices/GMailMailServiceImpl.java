package com.payroll.usermanagement.EmailServices;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class GMailMailServiceImpl implements GMailMailService {

	@Autowired
	JavaMailSender mailSender;
	
	@Override
	public void sendEmail(Mail mail) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(),"Amalpayroll_managementSystem"));
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent());
			
			mailSender.send(mimeMessageHelper.getMimeMessage());
		}catch(MessagingException e) {
			System.out.println(e);
		}catch(UnsupportedEncodingException e) {
			System.out.println(e);
		}

	}

}













