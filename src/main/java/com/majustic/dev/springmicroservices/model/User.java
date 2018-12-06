package com.majustic.dev.springmicroservices.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user.")
//@JsonIgnoreProperties(value= {"birthday"})
@JsonFilter("User")
public class User {
	private int id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String name;
	//@JsonIgnore
	private String lastName;
	
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthday;
	
	public User() {
		super();
	}

	public User(String name, String lastName, Date birthday) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
	}

	public User(int id, String name, String lastName, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", birthday=" + birthday + "]";
	}

}
