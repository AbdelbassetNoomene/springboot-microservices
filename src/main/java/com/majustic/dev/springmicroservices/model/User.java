package com.majustic.dev.springmicroservices.model;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String lastName;
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
