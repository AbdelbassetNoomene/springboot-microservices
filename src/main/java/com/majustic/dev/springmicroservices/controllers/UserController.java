package com.majustic.dev.springmicroservices.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.majustic.dev.springmicroservices.exception.UserNotFoundException;
import com.majustic.dev.springmicroservices.model.User;
import com.majustic.dev.springmicroservices.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(path = "")
	public List<User> getListUsers() {
		return userService.getListUSers();
	}

	@GetMapping(path = "/{id}")
	public User getUserDetails(@PathVariable int id) {
		User user =  userService.findUser(id);
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return user;
	}

	@PostMapping(path = "")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User userAdded =  userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(userAdded.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
