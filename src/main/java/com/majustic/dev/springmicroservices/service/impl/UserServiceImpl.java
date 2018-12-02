package com.majustic.dev.springmicroservices.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.majustic.dev.springmicroservices.model.User;
import com.majustic.dev.springmicroservices.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static List<User> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new User(1, "Ahmed", "ali", new Date()));
		users.add(new User(2, "Salah", "Mejri", new Date()));
		users.add(new User(3, "Jack", "Bisan", new Date()));
	}

	@Override
	public List<User> getListUSers() {
		return users;
	}

	@Override
	public User findUser(int id) {
		return users.stream().filter(u -> id == u.getId()).findAny().orElse(null);
	}

	@Override
	public User addUser(User user) {
		if(user.getId()==0) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}


}
