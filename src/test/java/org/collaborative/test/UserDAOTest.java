package org.collaborative.test;

import static org.junit.Assert.assertEquals;

import org.collaborative.config.DataBaseConfiguration;
import org.collaborative.dao.UserDAO;
import org.collaborative.model.BlogUserDetail;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Ignore
@ComponentScan(basePackages ={ "org.collaborative" })
public class UserDAOTest {


	@Autowired
	private static BlogUserDetail blogUserDetail;

	@Autowired
	private static UserDAO userDAO;
	

@SuppressWarnings("resource")
@BeforeClass
@Ignore
public static void initialize()
{
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	context.register(DataBaseConfiguration.class);
	context.scan("org.collaborative.*");
	context.refresh();

	
	blogUserDetail = (BlogUserDetail) context.getBean("blogUserDetail");
	userDAO = (UserDAO) context.getBean("userDAO");
}


@Test
@Ignore
public void createUser()
{
	blogUserDetail.setFirstName("Ravi");
	blogUserDetail.setLastName("kumar");
	blogUserDetail.setEmail("ravi@gmail.com");
	blogUserDetail.setPassword("pass");
	blogUserDetail.setEnabled(true);
	blogUserDetail.setOnline(true);
	blogUserDetail.setPhone("545653");
	boolean flag=userDAO.saveUser(blogUserDetail);
	assertEquals("createUserTestCase", true, flag);

}

}