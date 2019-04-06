package com.sist.hr;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {
    static Logger LOG=Logger.getLogger(UserDaoTest.class);
	
	//115
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
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
			if(user.getU_id().equals(inUser.getU_id()) && 
			   user.getName().equals(inUser.getName()) &&
			   user.getPassword().equals(inUser.getPassword())
			) {
				LOG.debug("=============================");
				LOG.debug("=^^등록성공^^=");
				LOG.debug("=============================");
				
			}else {
				LOG.debug("=============================");
				LOG.debug("=^^등록실패^^=");
				LOG.debug("=============================");				
			}
			
	

	}

}
