package com.harshit.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(String recievingEmail, String subject, String emailBody) throws MailException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(recievingEmail);
		mail.setFrom("harshitkishor1@gmail.com");
		mail.setSubject(subject);
		mail.setText(emailBody);

		javaMailSender.send(mail);
	}
}