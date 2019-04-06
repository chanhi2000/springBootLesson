package com.sist.hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
//	 * IP:211.238.142.102
//	 * post:1521
//	 * SID:orcl
//	 * sist/1224
	
	
	private static Logger log = Logger.getLogger(UserDao.class);
	private RowMapper<User> userMapper = 
			new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setU_id(rs.getString("u_id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					return user;
				}
    };
	
	//전체 삭제,등록
    private JdbcTemplate jdbcTemplate;
    


	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	public UserDao() {
		
	}

	
	public int getCount(String user_id)throws ClassNotFoundException,SQLException{
		
		
		int cnt = 0;
		//--------------------------------------------
		//DB연결
		//--------------------------------------------
		Connection c = dataSource.getConnection();
		
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder sb=new StringBuilder();		
		sb.append(" SELECT            \n");
		sb.append("     count(*) cnt  \n");
		sb.append(" FROM users        \n");
		sb.append(" WHERE u_id LIKE ? \n");
		log.debug("sql:\n"+sb.toString());
		//--------------------------------------------		
		//실행
		//--------------------------------------------
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, "%"+user_id+"%");
		log.debug("param user_id:"+user_id);
		
		//--------------------------------------------		
		//조회
		//--------------------------------------------			
		ResultSet rs = ps.executeQuery();	
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		log.debug("cnt:"+cnt);
		
		rs.close();
		ps.close();
		c.close();
		
		return cnt;
	}
	
	//컨텍스트 
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) 
			throws ClassNotFoundException, SQLException {
		//--------------------------------------------
		//DB연결
		//--------------------------------------------
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = dataSource.getConnection();
			
			
			ps = stmt.makeStatement(c);
			
			int flag = ps.executeUpdate();
			log.debug("flag:\n"+flag);
			
		}catch(SQLException e) {
			throw e;
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(SQLException e) {
					
				}
			}
			
			if(c != null) {
				try {
					c.close();
				}catch(SQLException e) {
					
				}
			}			
		}		
	}
	
	
	/*
	 * 
	 */
	public void delAll()throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("DELETE FROM users");
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
	
	
	public List<User> getAll()throws ClassNotFoundException,SQLException{
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT           \n");
		sb.append("     u_id,        \n");
		sb.append("     name,        \n");
		sb.append("     password     \n");
		sb.append(" FROM users       \n");
		sb.append(" ORDER BY  u_id   \n");
		log.debug("sql:\n"+sb.toString());
		return this.jdbcTemplate.query(sb.toString()
				,  userMapper);
	}
	
	
	public User get(String user_id)throws ClassNotFoundException,SQLException{
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
		
		return this.jdbcTemplate.queryForObject(sb.toString()
				, new Object[] {user_id}
		        , userMapper
		      );
		
	}

	
	public void add(final User user)throws ClassNotFoundException,SQLException{
		StringBuilder sb=new StringBuilder();
		sb.append(" INSERT INTO users ( u_id, name, password) VALUES (?,?,?) \n");
		log.debug("sql:\n"+sb.toString());
		
		jdbcTemplate.update(sb.toString(), user.getU_id(),user.getName(),user.getPassword());

	}

}
