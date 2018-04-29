package org.collaborative.controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.collaborative.model.*;
import org.collaborative.service.EmailServiceHelper;
import org.collaborative.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.collaborative.util.Util;


@Controller
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

   
  @Autowired
   private UserService userService;
    @Autowired
    private EmailServiceHelper emailServiceHelper;
    

    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
	
		
	 if (!userService.isEmailValid(user.getEmail()))
	 {
			Error error = new Error(user.getEmail()+"...Email address already exists,, please enter different email");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	 	user.setSecurityKey(Util.getSecurityKey());
	 	System.out.println(user.getSecurityKey());
		boolean result = userService.saveUser(user);
		if (result) {
			
		
			// generate token
			// insert token into database
			// send email
			
			String token=Util.generateToken(user);
			String url = "http://localhost:8180/BackEndCollaboration/verifyEmail?li=" + token;
			user.setUrl(url);
			emailServiceHelper.sendVerificationEmail(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			Error error = new Error("unable to register user details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @RequestMapping(value = "verifyEmail", method = RequestMethod.GET)
    public ResponseEntity<?> verifyEmail(@RequestParam("li") String token ){
    	// decrytp
    	// using ; you should separte token and username
    	List<String> response = new ArrayList<String>();
    	User user = Util.getUserDetailsFromToken(token);
    	if(user == null){
    		response.add("Sorry, URL to verify user is not valid");
    		return new ResponseEntity<List<String>>(response, HttpStatus.OK);
    	}
    	else{
    		User tempUser = userService.getUserById(user.getId());
    		if(tempUser == null){
    			response.add("Sorry, URL to verify user is not valid");
        		return new ResponseEntity<List<String>>(response, HttpStatus.OK);
    		}
    		else{
    			if(!(user.getSecurityKey().equals(tempUser.getSecurityKey()))){
    				response.add("Sorry, URL to verify user is not valid");
    	    		return new ResponseEntity<List<String>>(response, HttpStatus.OK);
    			}
    			else{
    				tempUser.setEnabled(true);
    				userService.saveUser(tempUser);
    				response.add("Your account is verifed Successfully");
    	    		return new ResponseEntity<List<String>>(response, HttpStatus.OK);
    			}
    		}    		
    	}  
    }
}
