package com.sist.hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {

	private static final String url = "jdbc:oracle:thin:@211.238.142.102:1521:orcl";
	private static final String ID = "SIST_HR";
	private static final String PW = "SIST1224";
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, ID, PW);
		return con;
	}

}
