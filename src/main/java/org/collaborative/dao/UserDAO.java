package org.collaborative.dao;

import java.util.List;

import org.collaborative.model.BlogUserDetail;

public interface UserDAO {
	
	public boolean saveUser(BlogUserDetail userDetail);
	
	public List<BlogUserDetail>userList();

}
