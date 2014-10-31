package com.somnus.model.oneToOneFkBidirectional;

public class FBPerson {

	private int id;
	
	private String name;
	
	private FBIdCard idCard; 
	
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

	public FBIdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(FBIdCard idCard) {
		this.idCard = idCard;
	}
	
	
}
