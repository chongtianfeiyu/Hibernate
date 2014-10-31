package com.somnus.model.hql;

import java.util.HashSet;
import java.util.Set;

public class Student
{
	private int sid;
	
	private String sname;
	
	private int sage;
	
	private String ssex;
	
	private String sdept;
	
	private Set<Score> score = new HashSet<Score>();

	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
	}

	public String getSname()
	{
		return sname;
	}

	public void setSname(String sname)
	{
		this.sname = sname;
	}

	public int getSage()
	{
		return sage;
	}

	public void setSage(int sage)
	{
		this.sage = sage;
	}

	public String getSsex()
	{
		return ssex;
	}

	public void setSsex(String ssex)
	{
		this.ssex = ssex;
	}

	public String getSdept()
	{
		return sdept;
	}

	public void setSdept(String sdept)
	{
		this.sdept = sdept;
	}

	public Set<Score> getScore()
	{
		return score;
	}

	public void setScore(Set<Score> score)
	{
		this.score = score;
	}

	
	
	
}
