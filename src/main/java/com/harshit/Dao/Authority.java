package com.harshit.Dao;

/*
 *  The purpose of this class is to map rid,role pairs 
 *  from the database 
 */

public class Authority {
	private String rid;
	private String role;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}