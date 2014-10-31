package com.somnus.model.oneToOnePkBidirectional;

public class PBIdCard {
	
	private int id;
	
	private String cardNo;
	
	private PBPerson person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public PBPerson getPerson() {
		return person;
	}

	public void setPerson(PBPerson person) {
		this.person = person;
	}
}
