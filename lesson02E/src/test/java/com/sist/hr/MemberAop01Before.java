package com.sist.hr;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "/applicationContext.xml")
public class MemberAop01Before {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ApplicationContext context;
	
	@Before
	public void setUp() {
		log.debug("context:"+context);
	}
	
	@Test
	public void memberAopBefore() {
		CommonDao dao = (CommonDao) context.getBean("dao");
		dao.do_save();
		dao.do_update();
	}
	
}
