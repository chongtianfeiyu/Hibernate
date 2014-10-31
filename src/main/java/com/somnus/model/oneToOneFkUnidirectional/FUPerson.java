package com.somnus.model.oneToOneFkUnidirectional;

public class FUPerson {

	private int id;
	
	private String name;
	
	private FUIdCard idCard; 
	
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

	public FUIdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(FUIdCard idCard) {
		this.idCard = idCard;
	}
	
	
}
