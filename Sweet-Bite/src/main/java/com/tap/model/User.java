package com.tap.model;

import java.sql.Date;

import java.time.LocalDateTime;

public class User {

	private int userId;
	private String name;
	private String email;
	private long phoneNo;
	private String address;
	private String userName;
	private String password;
	private String role;
	private Date createdDate;
	private Date lastLogin;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String name, String email, long phoneNo, String address, String userName, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.userName = userName;
		this.password = password;
	}






	public User(int userId, String name, String email, long phoneNo, String address, String userName, String password,
			String role, Date createdDate, Date lastLogin) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLogin = lastLogin;
	}



	public User(int userId, String name, String email, long phoneNo, String address, String userName, String password, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.role=role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long l) {
		this.phoneNo = l;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Date getLastLogin() {
		return lastLogin;
	}



	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", address="
				+ address + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
}
