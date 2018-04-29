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
		// TODO Auto-generated method stub
		return userDAO.userList();
	}

	public boolean isEmailValid(String email) {
		// TODO Auto-generated method stub
		return userDAO.isEmailValid(email);
	}

	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(user);
	}

}
