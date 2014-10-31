package com.somnus.model.oneToManyUnidirectional;

import java.util.Set;

public class UClasses {
	
	private int id;
	
	private String name;
	
	private Set<UStudent> students; 
	
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

	public Set<UStudent> getStudents()
	{
		return students;
	}

	public void setStudents(Set<UStudent> students)
	{
		this.students = students;
	}

	
}
