package com.somnus;

import java.util.Iterator;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.tree.Dept;
import com.somnus.util.HibernateUtil;

public class TreeTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		Dept Dept0 = new Dept();
		Dept0.setName("总公司0");
		
		Dept Dept01 = new Dept();
		Dept01.setName("分公司01");
		
		Dept Dept02 = new Dept();
		Dept02.setName("分公司02");
		
		Dept Dept011 = new Dept();
		Dept011.setName("分公司011");
		
		Dept Dept012 = new Dept();
		Dept012.setName("分公司012");
		
		Dept Dept021 = new Dept();
		Dept021.setName("分公司021");
		
		Dept Dept022 = new Dept();
		Dept022.setName("分公司022");
		
		Dept0.getChildren().add(Dept01);
		Dept0.getChildren().add(Dept02);
		Dept01.setParent(Dept0);
		Dept02.setParent(Dept0);
		
		Dept01.getChildren().add(Dept011);
		Dept01.getChildren().add(Dept012);
		Dept011.setParent(Dept01);
		Dept012.setParent(Dept01);
		
		Dept02.getChildren().add(Dept021);
		Dept02.getChildren().add(Dept022);
		Dept021.setParent(Dept02);
		Dept022.setParent(Dept02);

		try
		{
			session.save(Dept0);
			tx.commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
		}
		finally
		{
			HibernateUtil.close();
		}
	}
	@Test
	public void query1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try
		{
			Dept Dept = (Dept)session.load(Dept.class, 1);
			
			print(Dept, 0);
			
//			Dept Dept1 = (Dept)session.load(Dept.class, 1);
//			
//			print(Dept1, 0);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			if (tx != null)
			{
				tx.rollback();
			}
		}
		finally
		{
			HibernateUtil.close();
		}
		////////////////////////////////////////////////////////////////
		Session session2 = HibernateUtil.openSession();

		Transaction tx2 = session2.beginTransaction();

		try
		{
			Dept Dept = (Dept)session2.load(Dept.class, 1);
			
			print(Dept, 0);
			
//			Dept Dept1 = (Dept)session.load(Dept.class, 1);
//			
//			print(Dept1, 0);
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			if (tx2 != null)
			{
				tx2.rollback();
			}
		}
		finally
		{
			session2.close();
		}
	}
	private void print(Dept dept, int level) {
		String preStr = "";
		
		for(int i=0; i<level; i++) {
			preStr += "**";
		}
		
		System.out.println(preStr + dept.getName());

		Set<Dept> children = dept.getChildren();
		for(Iterator<Dept> it = children.iterator();it.hasNext();)
		{
			Dept child = it.next();
			print(child, level+1);
		}
	}
}
