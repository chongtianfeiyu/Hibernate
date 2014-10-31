package com.somnus;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.oneToManyUnidirectional.UClasses;
import com.somnus.model.oneToManyUnidirectional.UStudent;
import com.somnus.util.HibernateUtil;

public class OneToManyUnidirectionalTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		UStudent student1 = new UStudent();
		student1.setName("andy");
		
		UStudent student2 = new UStudent();
		student2.setName("july");
		
		
		UClasses classes = new UClasses();
		classes.setName("三年二班");
		classes.setStudents(new HashSet<UStudent>());
		classes.getStudents().add(student1);
		classes.getStudents().add(student2);

		try
		{
			//可以正确保存
			session.save(student1);
			session.save(student2);
			session.save(classes);
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
			session.close();
		}
	}
	@Test
	public void query()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try
		{
			UClasses classes = (UClasses)session.load(UClasses.class, 1);
			System.out.println("classes.name=" + classes.getName());
			Set<UStudent> students = classes.getStudents();
			for (Iterator<UStudent> iter=students.iterator(); iter.hasNext();) {
				UStudent student = (UStudent)iter.next();
				System.out.println("student.name=" + student.getName());
			}
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
			session.close();
		}
	}
}
