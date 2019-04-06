package com.sist.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JdbcContext {

	private Logger log= Logger.getLogger(JdbcContext.class);
	
	private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	//컨텍스트 
	public void workWithStatementStrategy(StatementStrategy stmt) 
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
				try { ps.close(); }
				catch(SQLException e) { }
			}
			
			if(c != null) {
				try { c.close(); }
				catch(SQLException e) {}
			}			
		}		
	}
		
		
}
