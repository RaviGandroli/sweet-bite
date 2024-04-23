package com.tap.dao;

import java.util.List;

import com.tap.model.User;

public interface UserDao {

	public boolean addUser(User user);
	
	public User getUser(int userId);
	
	public List<User> getAllUser();
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(int userId);
	
}
