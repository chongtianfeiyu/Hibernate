package com.somnus.model.manyToManyUnidirectional;

import java.util.Set;

public class UUser {
	
	private int id;
	
	private String name;
	
	private Set<URole> roles; 
	
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

	public Set<URole> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<URole> roles)
	{
		this.roles = roles;
	}

	
	
}
