package com.example.springexample.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotification {
	
	@Autowired
    public JavaMailSender emailSender;
 
    public void sendEmailNotification(String userEmail, String body) throws MailException {
 
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(userEmail); 
        message.setFrom("ashishk.mca.du.2015@gmail.com");
        message.setSubject("ticket"); 
        message.setText(body);
        
        emailSender.send(message);

    } 

}
