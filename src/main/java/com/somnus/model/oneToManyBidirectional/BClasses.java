package com.somnus.model.oneToManyBidirectional;

import java.util.Set;

public class BClasses {
	
	private int id;
	
	private String name;
	
	private Set<BStudent> students; 
	
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

	public Set<BStudent> getStudents()
	{
		return students;
	}

	public void setStudents(Set<BStudent> students)
	{
		this.students = students;
	}

	
	
}
