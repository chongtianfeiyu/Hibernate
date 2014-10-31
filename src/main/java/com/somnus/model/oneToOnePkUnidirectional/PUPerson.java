package com.somnus.model.oneToOnePkUnidirectional;

public class PUPerson {

	private int id;
	
	private String name;
	
	private PUIdCard idCard; 
	
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

	public PUIdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(PUIdCard idCard) {
		this.idCard = idCard;
	}
	
	
}
