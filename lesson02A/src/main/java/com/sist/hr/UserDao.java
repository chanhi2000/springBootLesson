package com.sist.hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class UserDao {
//	 * IP:211.238.142.102
//	 * post:1521
//	 * SID:orcl
//	 * sist/1224
	
	
	private static Logger log = Logger.getLogger(UserDao.class);
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public UserDao() {
		
	}

	
	
	
	public int del(String user_id)throws ClassNotFoundException,SQLException{
		//--------------------------------------------
		//DB연결
		//--------------------------------------------
		Connection c = dataSource.getConnection();
		
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder sb=new StringBuilder();
		sb.append(" DELETE FROM users WHERE u_id=? \n");
		log.debug("sql:\n"+sb.toString());
		
		//--------------------------------------------		
		//실행
		//--------------------------------------------		
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, user_id);
		
		int flag = ps.executeUpdate();
		log.debug("flag:\n"+flag);
		
		//--------------------------------------------		
		//실행
		//--------------------------------------------			
		ps.close();
		c.close();
		
		return flag;
	}
	
	public User get(String user_id)throws ClassNotFoundException,SQLException{
		//--------------------------------------------
		//DB연결
		//--------------------------------------------
		Connection connection = dataSource.getConnection();
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT           \n");
		sb.append("     u_id,        \n");
		sb.append("     name,        \n");
		sb.append("     password     \n");
		sb.append(" FROM users       \n");
		sb.append(" WHERE u_id = ?   \n");
		log.debug("sql:\n"+sb.toString());
		
		
		//--------------------------------------------		
		//실행
		//--------------------------------------------
		PreparedStatement ps = connection.prepareStatement(sb.toString());
		ps.setString(1, user_id);
		
		
		//--------------------------------------------		
		//조회
		//--------------------------------------------			
		ResultSet rs = ps.executeQuery();
		User user = null;
		
		if(rs.next()) {
			user = new User();
			user.setU_id(rs.getString("u_id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		log.debug("user:\n"+user.toString());
		
		rs.close();
		ps.close();
		connection.close();
		return user;
	}

	
	public void add(User user)throws ClassNotFoundException,SQLException{
		//--------------------------------------------
		//DB연결
		//--------------------------------------------
		Connection c = dataSource.getConnection();
		
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder sb=new StringBuilder();
		sb.append(" INSERT INTO users ( u_id, name, password) VALUES (?,?,?) \n");
		log.debug("sql:\n"+sb.toString());
		
		//--------------------------------------------		
		//실행
		//--------------------------------------------		
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, user.getU_id());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		int flag = ps.executeUpdate();
		log.debug("flag:\n"+flag);
		
		//--------------------------------------------		
		//실행
		//--------------------------------------------			
		ps.close();
		c.close();
	}

}
