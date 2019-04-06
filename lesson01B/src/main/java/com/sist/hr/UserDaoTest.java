package com.sist.hr;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class UserDaoTest {
    static Logger LOG=Logger.getLogger(UserDaoTest.class);
	
	//115
	public static void main(String[] args) {
		User user=new User("j_115","김현룡","1224");
		UserDao dao=new UserDao();
		try {
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
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
