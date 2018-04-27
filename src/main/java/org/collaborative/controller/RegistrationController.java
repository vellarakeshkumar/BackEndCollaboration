package org.collaborative.controller;
import org.collaborative.model.*;
import org.collaborative.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

   
  @Autowired
   private UserService userService;
    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
	
		
	 if (!userService.isEmailValid(user.getEmail()))
	 {
			Error error = new Error(user.getEmail()+"...Email address already exists,, please enter different email");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
	}

		boolean result = userService.saveUser(user);
		if (result) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			Error error = new Error("unable to register user details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
