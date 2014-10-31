package com.somnus.model.oneToOneFkBidirectional;

public class FBIdCard {
	
	private int id;
	
	private String cardNo;
	
	private FBPerson person;

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

	public FBPerson getPerson() {
		return person;
	}

	public void setPerson(FBPerson person) {
		this.person = person;
	}
}
