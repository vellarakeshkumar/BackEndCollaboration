package org.collaborative.controller;
import org.collaborative.model.*;
import org.collaborative.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(name="/createUser", method=RequestMethod.POST)
	public  ResponseEntity<?> saveUserObject(@RequestBody BlogUserDetail blogUserDetail)
	{
		userService.saveUser(blogUserDetail);
		return new ResponseEntity<BlogUserDetail>(blogUserDetail, HttpStatus.OK);
		
		
	}
	
	
	

}
