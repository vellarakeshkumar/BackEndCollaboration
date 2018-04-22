package org.collaborative.service;

import org.collaborative.dao.UserDAO;
import org.collaborative.model.BlogUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	public boolean saveUser(BlogUserDetail userDetail) {
		userDAO.saveUser(userDetail);
		return true;
	}

}
