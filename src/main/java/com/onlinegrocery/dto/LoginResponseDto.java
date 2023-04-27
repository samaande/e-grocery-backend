package com.onlinegrocery.dto;

import com.onlinegrocery.enums.Role;

public class LoginResponseDto {
	private long userid;
	private Role role;
	private String userName;
	private String message;
	private long mobileNumber;
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LoginResponseDto(long userid, Role role, String userName, String message) {
		super();
		this.userid = userid;
		this.role = role;
		this.userName = userName;
		this.message = message;
	}
	@Override
	public String toString() {
		return "LoginResponseDto [userid=" + userid + ", role=" + role + ", userName=" + userName + ", message="
				+ message + "]";
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public LoginResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

