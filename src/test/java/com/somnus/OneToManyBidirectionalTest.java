package com.somnus;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.oneToManyBidirectional.BClasses;
import com.somnus.model.oneToManyBidirectional.BStudent;
import com.somnus.util.HibernateUtil;

public class OneToManyBidirectionalTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();
		/*
		 * only
		 * 只在one的一方设好many一方的关系，关联进来
		 */
		BClasses classes = new BClasses();
		classes.setName("三年二班");
		classes.setStudents(new HashSet<BStudent>());
		
		BStudent student1 = new BStudent();
		student1.setName("andy");
		
		BStudent student2 = new BStudent();
		student2.setName("july");
		
		classes.getStudents().add(student1);
		classes.getStudents().add(student2);
		
		try
		{
			//可以正确保存，但是因为加上了反转，先保存完学生，最后保存完班级后，不会去更新维护学生的信息
//			session.save(student1);
//			session.save(student2);
//			session.save(classes);
			
			//这样也是可以正确保存的  先保存班级，后保存学生，如果一的一方设了cascade 多的一方的保存save可以省略
//			session.save(classes);
			
			/*session.save(student1);
			session.save(student2);*/
			
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
	public void save2()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();
		/*
		 * only
		 * 只在many的一方设好one一方的关系，关联进来
		 */
		BClasses classes = new BClasses();
		classes.setName("三年二班");
		
		BStudent student1 = new BStudent();
		student1.setName("lucy");
		student1.setClasses(classes);
		
		
		BStudent student2 = new BStudent();
		student2.setName("tom");
		student2.setClasses(classes);

		try
		{
			//在多的一方配置文件加了cascade级联的情况下才可以不保存一的一方      才可以正确保存   不然将发生异常
			//不然就得先保存one一方session.save(classes);
			session.save(student1);
			session.save(student2);
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
	public void save3()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();
		
		/*
		 * both
		 * 在one的一方设好many一方的关系，关联进来
		 * 在many的一方设好one一方的关系，关联进来
		 */
		BClasses classes = new BClasses();
		classes.setName("三年三班");
		classes.setStudents(new HashSet<BStudent>());
		
		BStudent student1 = new BStudent();
		student1.setName("yark");
		student1.setClasses(classes);
		
		BStudent student2 = new BStudent();
		student2.setName("mm");
		student2.setClasses(classes);
		
		classes.getStudents().add(student1);
		classes.getStudents().add(student2);
		try
		{
			//在一的一方配置文件加了cascade级联的情况下才可以不保存多的一方      才可以正确保存   不然只保存班级
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
			BClasses classes = (BClasses)session.load(BClasses.class, 2);
			System.out.println("classes.name=" + classes.getName());
			Set<BStudent> students = classes.getStudents();
			for (Iterator<BStudent> iter=students.iterator(); iter.hasNext();) {
				BStudent student = (BStudent)iter.next();
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
			HibernateUtil.close();
		}
	}
	@Test
	public void query2()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try
		{
			BStudent student = (BStudent)session.load(BStudent.class, 3);
			System.out.println("student.name=" + student.getName());
			System.out.println("student.classes.name=" + student.getClasses().getName());
			session.getTransaction().commit();
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
}
