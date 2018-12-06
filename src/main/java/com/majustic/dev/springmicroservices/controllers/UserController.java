package com.majustic.dev.springmicroservices.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.majustic.dev.springmicroservices.exception.UserNotFoundException;
import com.majustic.dev.springmicroservices.model.User;
import com.majustic.dev.springmicroservices.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping(path = "")
	public MappingJacksonValue getListUsers() {
		List<User> users= userService.getListUSers();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","lastName");
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("User", filter);
		MappingJacksonValue  mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		return mapping;
	}

	@GetMapping(value = "/{id}")
	public Resource<User> getUserDetails(@PathVariable int id) {
		User user =  userService.findUser(id);
		if(user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getListUsers());
		resource.add(linkTo.withRel("all-users"));
		//HATEOAS
		
		return resource;
	}

	@PostMapping(path = "")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User userAdded =  userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(userAdded.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);		
	}

}
