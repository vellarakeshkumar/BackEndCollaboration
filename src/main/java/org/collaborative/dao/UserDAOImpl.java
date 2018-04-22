package org.collaborative.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.collaborative.model.BlogUserDetail;
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


	public boolean saveUser(BlogUserDetail userDetail) {
		
		try {
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



	public List<BlogUserDetail> userList() {
		 // Open a session
	    Session session = sessionFactory.openSession();

	    // DEPRECATED: Create Criteria
	    // Criteria criteria = session.createCriteria(BlogUserDetail.class);

	    // DEPRECATED: Get a list of Contact objects according to the Criteria object
	    // List<BlogUserDetail> contacts = criteria.list();

	   // UPDATED: Create CriteriaBuilder
	    CriteriaBuilder builder = session.getCriteriaBuilder();

	    // UPDATED: Create CriteriaQuery
	    CriteriaQuery<BlogUserDetail> criteria = builder.createQuery(BlogUserDetail.class);

	    // UPDATED: Specify criteria root
	    criteria.from(BlogUserDetail.class);

	    // UPDATED: Execute query
	    List<BlogUserDetail> blogUserDetail = session.createQuery(criteria).getResultList();

	    // Close the session
	    session.close();

	    return blogUserDetail;
	}

}
