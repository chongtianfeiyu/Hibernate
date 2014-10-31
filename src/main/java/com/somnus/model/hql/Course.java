package com.somnus.model.hql;

import java.util.HashSet;
import java.util.Set;

public class Course
{
	private int cid;
	
	private String cname;
	
	private Set score = new HashSet(0);

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public Set getScore()
	{
		return score;
	}

	public void setScore(Set score)
	{
		this.score = score;
	}
	
	
}
