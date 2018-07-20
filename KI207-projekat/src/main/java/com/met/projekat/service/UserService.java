package com.met.projekat.service;

import com.met.projekat.entities.User;

public interface UserService {
	
	public User findUserByUserName(String userName);
	
	public void saveUser(User user);
	
}
