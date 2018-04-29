package org.collaborative.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.collaborative.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class EmailServiceHelper {

	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	public void sendVerificationEmail(User user){
		SimpleMailMessage message = new SimpleMailMessage(); 
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("user", user.getFirstName() + " " + user.getLastName());
		data.put("url", user.getUrl());
		String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "emailTemplates/verifyEmail.vm", data);
        message.setTo(user.getEmail()); 
        message.setSubject("Verify Email"); 
        message.setText(body);
        emailSender.send(message);
	}
}
