package org.collaborative.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;


import org.collaborative.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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


	public boolean saveUser(User userDetail) {
		
		try {
		userDetail.setEnabled(false);

		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(userDetail);
		tx.commit();
			return true;
		} catch (HibernateException e) {
			
			
			e.printStackTrace();
			
			return false;
		}
		
	}



	public List<User> userList() {
		 // Open a session
	    Session session = sessionFactory.openSession();

	    // DEPRECATED: Create Criteria
	    // Criteria criteria = session.createCriteria(BlogUserDetail.class);

	    // DEPRECATED: Get a list of Contact objects according to the Criteria object
	    // List<BlogUserDetail> contacts = criteria.list();

	   // UPDATED: Create CriteriaBuilder
	    CriteriaBuilder builder = session.getCriteriaBuilder();

	    // UPDATED: Create CriteriaQuery
	    CriteriaQuery<User> criteria = builder.createQuery(User.class);

	    // UPDATED: Specify criteria root
	    criteria.from(User.class);

	    // UPDATED: Execute query
	    List<User>users = session.createQuery(criteria).getResultList();

	    // Close the session
	    session.close();

	    return users;
	}





@Transactional
	public boolean isEmailValid(String email) {
	List<User> list = userList();

	for (User usersDetail : list) {
		if (usersDetail.getEmail().equals(email)){
			return false;// invalid user
		}
	}
	return true;// valid user
}



	public User updateUser(User user) {	
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	session.update(user);
	tx.commit();
	session.clear();
	return user;
}



	public User getUserById(long id) {
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	User userdt=(User)session.load(User.class, id);
	tx.commit();
	return userdt;
		
		
	}

}
