package com.majustic.dev.springmicroservices.service;

import java.util.List;

import com.majustic.dev.springmicroservices.model.User;


public interface UserService {

	public List<User> getListUSers();
	
	public User findUser(int id);
	
	public User addUser(User user);
}
