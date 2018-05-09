package org.collaborative.service;

import java.util.List;

import org.collaborative.dao.UserDAO;
import org.collaborative.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	public boolean saveUser(User userDetail) {
		return userDAO.saveUser(userDetail);
	}

	public List<User> userList() {
	
		return userDAO.userList();
	}

	public boolean isEmailValid(String email) {
	
		return userDAO.isEmailValid(email);
	}

	public User updateUser(User user) {
		
		return userDAO.updateUser(user);
	}

	public User getUserById(long id) {
		
		return userDAO.getUserById(id);
	}

	public User login(User user) {
	
		return userDAO.login(user);
	}

}
