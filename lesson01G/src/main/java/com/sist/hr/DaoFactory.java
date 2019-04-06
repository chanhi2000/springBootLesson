package com.sist.hr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*애프리케이션 컨텍스트 또는 빈 팩토리 */
@Configuration
public class DaoFactory {

	/**
	 * DaoFactory 
	 * @return UserDao
	 */
	@Bean
	public UserDao userDao() {		
		UserDao userDao=new UserDao();
		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	
	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}
}
