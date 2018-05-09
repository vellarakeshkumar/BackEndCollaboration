package org.collaborative.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.collaborative.model.Post;
import org.collaborative.model.User;
import org.collaborative.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<?> createBlogs(@RequestBody Post post) {

		User validUser = (User) session.getAttribute("validUser");
		if (validUser == null)

		{
			Error error = new Error("Unauthorized user,so you can't create blogs");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		post.setPostedOn(new Date());
		post.setPostedBy(validUser);
		boolean result = postService.postBlog(post);
		if (result) {
			return new ResponseEntity<Post>(post, HttpStatus.OK);
		} else {
			Error error = new Error("unable to create blogs");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
