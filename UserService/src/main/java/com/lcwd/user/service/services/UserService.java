package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {
	
	// Create user
	User saveUser(User user);
	
	// Get all user
	List<User> getAllUser();
	
	
	// Get single user of given id
	User getUser(int userId);

}
