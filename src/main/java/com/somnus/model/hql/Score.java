package com.somnus.model.hql;

public class Score
{
	private int scid;
	
	private Course course;
	
	private Student student;
	
	private int grade;

	public int getScid()
	{
		return scid;
	}

	public void setScid(int scid)
	{
		this.scid = scid;
	}

	public Student getStudent()
	{
		return student;
	}

	public void setStudent(Student student)
	{
		this.student = student;
	}

	public int getGrade()
	{
		return grade;
	}

	public void setGrade(int grade)
	{
		this.grade = grade;
	}

	public Course getCourse()
	{
		return course;
	}

	public void setCourse(Course course)
	{
		this.course = course;
	}

	
	
}
