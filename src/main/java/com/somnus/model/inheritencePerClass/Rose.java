package com.somnus.model.inheritencePerClass;

import javax.persistence.Entity;

@Entity
public class Rose extends Flower {
	
	private int type;

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	
}
