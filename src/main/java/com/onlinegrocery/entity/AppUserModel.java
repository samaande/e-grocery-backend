package com.onlinegrocery.entity;

import javax.validation.constraints.NotNull;



public class AppUserModel {
	@NotNull(message = "password cannot be null")
	private String password;
	@NotNull(message = "username cannot be null")
	private String userName;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
