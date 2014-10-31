package com.somnus.model.inheritencePerClass;

import javax.persistence.Entity;

@Entity
public class Peony extends Flower {
	private int color;

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}


	
}
