package com.somnus.model.manyToManyBidirectional;

import java.util.Set;

public class BUser {
	
	private int id;
	
	private String name;
	
	private Set<BRole> roles; 
	
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

	public Set<BRole> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<BRole> roles)
	{
		this.roles = roles;
	}

	
	
}
