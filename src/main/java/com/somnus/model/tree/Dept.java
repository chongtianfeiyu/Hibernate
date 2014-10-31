package com.somnus.model.tree;

import java.util.HashSet;
import java.util.Set;
public class Dept
{
	private int id;
	
	private String name;
	
	private Set<Dept> children = new HashSet<Dept>();
	
	private Dept parent;
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public Set<Dept> getChildren()
	{
		return children;
	}

	public void setChildren(Set<Dept> children)
	{
		this.children = children;
	}
	public Dept getParent()
	{
		return parent;
	}

	public void setParent(Dept parent)
	{
		this.parent = parent;
	}
	
	
}
