package com.ui.dao;

import java.util.List;

import com.ui.model.User;

public interface LoginDAO {
	List<User> checkAdminLogin(String email, String password);
	public User isRegistered(String email);	
	public String resetPassword(User u);
	public User checkPassword(int userid, String currentpassword);
}

