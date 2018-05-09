package org.collaborative.dao;

import javax.transaction.Transactional;

import org.collaborative.model.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDAOImpl implements PostDAO {

	
	@Autowired
	
	private SessionFactory sessionFactory;
	
	
	
	@Transactional
	public boolean postBlog(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
		return true;
	}

}
