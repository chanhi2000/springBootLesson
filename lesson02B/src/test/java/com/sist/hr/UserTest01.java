package com.sist.hr;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.apache.log4j.Logger;
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
public class UserTest01 {

	Logger  LOG = Logger.getLogger(this.getClass());
	
	
	//메소드 예외 처리
	@Test(expected=NullPointerException.class)
	public void getUserFailure()throws SQLException, ClassNotFoundException{
		ApplicationContext context 
        = new GenericXmlApplicationContext("/applicationContext.xml");
	 UserDao userDao = context.getBean("userDao",UserDao.class);
		 User user01=new User("j01_115","Spring Boot","spring1234");
		 User user02=new User("j02_115","Spring Boot","spring1234");
		 User user03=new User("j03_115","Spring Boot","spring1234");
		 
		 userDao.del(user01.getU_id());
		 userDao.del(user02.getU_id());
		 userDao.del(user03.getU_id());
		 assertThat(userDao.getCount("115"), is(0));
		 
		 userDao.get("unknown_id");
		 
	}
	
	@Test
	@Ignore
	public void count()throws SQLException, ClassNotFoundException{
		 ApplicationContext context 
	        = new GenericXmlApplicationContext("/applicationContext.xml");
		 UserDao userDao = context.getBean("userDao",UserDao.class);
		 
		 User user01=new User("j01_115","Spring Boot","spring1234");
		 User user02=new User("j02_115","Spring Boot","spring1234");
		 User user03=new User("j03_115","Spring Boot","spring1234");
		 
		 userDao.del(user01.getU_id());
		 userDao.del(user02.getU_id());
		 userDao.del(user03.getU_id());
		 
		 userDao.add(user01);
		 assertThat(userDao.getCount("115"), is(1));
		 
		 userDao.add(user02);
		 assertThat(userDao.getCount("115"), is(2));
		 
		 userDao.add(user03);
		 assertThat(userDao.getCount("115"), is(3));		 
		
	}
	
	@Test
	@Ignore
	public void addAndGet()throws SQLException, ClassNotFoundException{
	    ApplicationContext context 
        = new GenericXmlApplicationContext("/applicationContext.xml");

	User user=new User("j_115","김현룡","1224");

	//Spring은 SingleTon으로 객체를 생성한다.
	//1.객체 2개 생성확인
	//2.객체 1개 생성확인
	UserDao dao=context.getBean("userDao", UserDao.class);
	UserDao dao01=context.getBean("userDao", UserDao.class);
	
	LOG.debug("=============================");
	LOG.debug("=dao="+dao);
	LOG.debug("=dao01="+dao01);
	LOG.debug("=============================");
	
	
	//--------------------------------
	//단건 삭제
	//--------------------------------	
	int result = dao.del(user.getU_id());
	if(result>0) {
		LOG.debug("=============================");
		LOG.debug("=del result="+result);
		LOG.debug("=============================");
	}
	
	//--------------------------------
	//단건 등록
	//--------------------------------			
	dao.add(user);
	
	//--------------------------------
	//단건 조회
	//--------------------------------				
	User inUser = dao.get(user.getU_id());
	
	//--------------------------------
	//비교
	//--------------------------------				
	assertThat(user.getU_id(),is(inUser.getU_id()));
	assertThat(user.getName(),is(inUser.getName()));
	assertThat(user.getPassword(),is(inUser.getPassword()));
	
	}
	
	@Test
	public void count01() {
		LOG.debug("count01");
		assertThat("1", is("1"));
	}
}
