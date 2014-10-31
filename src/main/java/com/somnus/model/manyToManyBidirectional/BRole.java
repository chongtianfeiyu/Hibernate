package com.somnus.model.manyToManyBidirectional;

import java.util.Set;

public class BRole {
	
	private int id;
	
	private String name;
	
	private Set<BUser> users;

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

	public Set<BUser> getUsers()
	{
		return users;
	}

	public void setUsers(Set<BUser> users)
	{
		this.users = users;
	}

	
}
