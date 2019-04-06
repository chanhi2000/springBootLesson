package com.sist.hr;

public class DaoFactory {

	/**
	 * DaoFactory 
	 * @return UserDao
	 */
	public UserDao userDao() {		
		return new UserDao(connectionMaker());
	}
	
//	/**
//	 * DaoFactory 
//	 * @return UserDao
//	 */
//	public BoardDao board() {
//		ConnectionMaker connectionMaker=new NConnectionMaker();
//		UserDao dao=new UserDao(connectionMaker);	
//		return dao;
//	}	
	
	public ConnectionMaker connectionMaker() {
		return new NConnectionMaker();
	}
}
