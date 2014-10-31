package com.somnus.model.oneToManyBidirectional;

public class BStudent {
	
	private int id;
	
	private String name;
	
	private BClasses classes;

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

	public BClasses getClasses() {
		return classes;
	}

	public void setClasses(BClasses classes) {
		this.classes = classes;
	}
}
