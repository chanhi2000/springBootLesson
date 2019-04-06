package com.sist.hr;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * 1 method의 접근 지정자는 public
 * 2.@Test
 * 
 * @author sist1
 *
 */
public class UserTest02 {

	Logger  LOG = Logger.getLogger(this.getClass());
	
	private UserDao dao;
	
	//선행 메소드: @Test가 수행 되기전 1회 수행.
	@Before
	public void setUp() {
		ApplicationContext context 
        = new GenericXmlApplicationContext("/applicationContext.xml");
	    UserDao userDao = context.getBean("userDao",UserDao.class);
	    LOG.debug("========================");
	    LOG.debug("1.setUp");
	    LOG.debug("========================");
	}
	
	@Test
	public void addAndGet() {
		LOG.debug("addAndGet");
	}
	  
	@Test
	public void count() {
		LOG.debug("count");
	}
	
	@After
	public void tearDown() {
	    LOG.debug("========================");
	    LOG.debug("3.tearDown");
	    LOG.debug("========================");		
	}
	
/*
2018-11-10 10:50:16,686 DEBUG [main] hr.UserTest01     (UserTest01.java:37)     - ========================
2018-11-10 10:50:16,686 DEBUG [main] hr.UserTest01     (UserTest01.java:38)     - 1.setUp
2018-11-10 10:50:16,686 DEBUG [main] hr.UserTest01     (UserTest01.java:39)     - ========================
2018-11-10 10:50:16,686 DEBUG [main] hr.UserTest01     (UserTest01.java:49)     - count
2018-11-10 10:50:16,686 DEBUG [main] hr.UserTest01     (UserTest01.java:54)     - ========================
2018-11-10 10:50:16,686 DEBUG [main] hr.UserTest01     (UserTest01.java:55)     - 3.tearDown
2018-11-10 10:50:16,686 DEBUG [main] hr.UserTest01     (UserTest01.java:56)     - ========================

2018-11-10 10:50:16,722 DEBUG [main] hr.UserTest01     (UserTest01.java:37)     - ========================
2018-11-10 10:50:16,723 DEBUG [main] hr.UserTest01     (UserTest01.java:38)     - 1.setUp
2018-11-10 10:50:16,723 DEBUG [main] hr.UserTest01     (UserTest01.java:39)     - ========================
2018-11-10 10:50:16,723 DEBUG [main] hr.UserTest01     (UserTest01.java:44)     - addAndGet
2018-11-10 10:50:16,723 DEBUG [main] hr.UserTest01     (UserTest01.java:54)     - ========================
2018-11-10 10:50:16,724 DEBUG [main] hr.UserTest01     (UserTest01.java:55)     - 3.tearDown
2018-11-10 10:50:16,724 DEBUG [main] hr.UserTest01     (UserTest01.java:56)     - ========================
	
 */
	

}













