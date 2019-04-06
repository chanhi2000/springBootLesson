package com.sist.hr;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 1 method의 접근 지정자는 public
 * 2.@Test
 * 
 * @author sist1
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "/applicationContext.xml")
public class UserTest03 {

	private Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	private ApplicationContext context;
	private UserDao dao;
	
	private User user01, user02, user03;
	
	@Before
	public void setup() {
		user01 = new User("j01_115","Spring Boot","spring1234");
		user02 = new User("j02_115","Spring Boot","spring1234");
		user03 = new User("j03_115","Spring Boot","spring1234");
		
		dao = context.getBean("userDao",UserDao.class);
		LOG.debug("setup");
		LOG.debug("**context**:"+context);
		LOG.debug("this:"+this);
	}
	    
	@Test
	@Ignore
	public void getAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		List<User> list = dao.getAll();
		assertThat(list.size(), is(0));
		LOG.debug("list:"+list);
	}
	
	@Test
	@Ignore
	public void delAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		assertThat(dao.getCount(""),is(0));
	}
	
	
	//메소드 예외 처리
	@Test(expected=NullPointerException.class)
	@Ignore
	public void getUserFailure()throws SQLException, ClassNotFoundException,EmptyResultDataAccessException{
		ApplicationContext context
				= new GenericXmlApplicationContext("/applicationContext.xml");
		UserDao userDao = context.getBean("userDao",UserDao.class);
		User user01=new User("j01_115","Spring Boot","spring1234");
		User user02=new User("j02_115","Spring Boot","spring1234");
		User user03=new User("j03_115","Spring Boot","spring1234");

		userDao.del(user01.getU_id());
		userDao.del(user02.getU_id());
		userDao.del(user03.getU_id());

		userDao.delAll();
		assertThat(userDao.getCount(""), is(0));

		userDao.get("unknown_id");
	}
	
	@Test
	@Ignore
	public void count()throws SQLException, ClassNotFoundException{
		dao.del(user01.getU_id());
		dao.del(user02.getU_id());
		dao.del(user03.getU_id());

		dao.add(user01);
		assertThat(dao.getCount("115"), is(1));

		dao.add(user02);
		assertThat(dao.getCount("115"), is(2));

		dao.add(user03);
		assertThat(dao.getCount("115"), is(3));
	}
	
	@Test
	public void addAndGet()throws SQLException, ClassNotFoundException{
		//Spring은 SingleTon으로 객체를 생성한다.
		//1.객체 2개 생성확인
		LOG.debug("=============================");
		LOG.debug("=dao="+dao);
		LOG.debug("=============================");


		//--------------------------------
		//단건 삭제
		//--------------------------------
		int result = dao.del(user01.getU_id());
		if(result>0) {
			LOG.debug("=============================");
			LOG.debug("=del result="+result);
			LOG.debug("=============================");
		}

		//--------------------------------
		//단건 등록
		//--------------------------------
		dao.add(user01);

		//--------------------------------
		//단건 조회
		//--------------------------------
		User inUser = dao.get(user01.getU_id());

		//--------------------------------
		//비교
		//--------------------------------
		assertThat(user01.getU_id(),is(inUser.getU_id()));
		assertThat(user01.getName(),is(inUser.getName()));
		assertThat(user01.getPassword(),is(inUser.getPassword()));
	}
	
	@Test
	@Ignore
	public void count01() {
		LOG.debug("count01");
		assertThat("1", is("1"));
	}
}
