package com.sist.hr;

/**
 * IP:211.238.142.102
 * post:1521
 * SID:orcl
 * sist/1224
 * 
 * users
 * u_id	varchar2(10)
   name	varchar2(10)
   password	varchar2(20)

 * @author sist1
 *
 */
public class User {

	private String u_id;
	private String name;
	private String password;
	
	public User() {}
	public User(String u_id, String name, String password) {
		super();
		this.u_id = u_id;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", name=" + name + ", password=" + password + "]";
	}

	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
