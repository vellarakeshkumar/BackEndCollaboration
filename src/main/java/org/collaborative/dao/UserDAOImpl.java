package org.collaborative.dao;

import javax.transaction.Transactional;

import org.collaborative.model.BlogUserDetail;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		
	}
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public boolean saveUser(BlogUserDetail userDetail) {
		
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(userDetail);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

}
