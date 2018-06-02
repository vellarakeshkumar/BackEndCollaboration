package org.collaborative.test;

import static org.junit.Assert.assertEquals;
import org.collaborative.config.DataBaseConfiguration;
import org.collaborative.dao.UserDAO;

import org.collaborative.model.User;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages ={ "org.collaborative" })
public class UserDaoTest1{


	@Autowired
	private static User user;

	@Autowired
	private static UserDAO userDAO;
	

@SuppressWarnings("resource")
@BeforeClass
public static void initialize()
{
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	context.register(DataBaseConfiguration.class);
	context.scan("org.collaborative.*");
	context.refresh();
	user = (User) context.getBean("user");
	userDAO = (UserDAO) context.getBean("userDAO");
}


@Test
public void createUser()
{
	user.setFirstName("piyush");
	user.setLastName("sharma");
	user.setEmail("sr.piyush94@gmail.com");
	user.setPassword("pass");
	user.setEnabled(true);
	
	boolean flag=userDAO.saveUser(user);
	assertEquals("createUserTestCase", true, flag);

}


@Test
public void fetchAllUser()
{
	  /* List<BlogUserDetail> users = userDAO.userList();
       Assert.assertEquals(blogUserDetail.getEmail(), users.get(0).getEmail());
	*/
		int noofuser=userDAO.userList().size();
		assertEquals("getAllUserTestCase", noofuser);
	
	

	

}

}