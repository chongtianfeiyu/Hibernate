package com.somnus.model.oneToOnePkBidirectional;

public class PBPerson {

	private int id;
	
	private String name;
	
	private PBIdCard idCard; 
	
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

	public PBIdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(PBIdCard idCard) {
		this.idCard = idCard;
	}
	
	
}
