package com.somnus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.somnus.model.inheritenceJoined.Fish;
import com.somnus.model.inheritenceJoined.Shark;
import com.somnus.model.inheritenceJoined.Whale;
import com.somnus.model.inheritenceSingleTable.Cat;
import com.somnus.model.inheritenceSingleTable.Dog;
import com.somnus.util.HibernateUtil;

public class InheritenceJoinedTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		Whale whale = new Whale();
		whale.setName("hutoujing");
		whale.setColor(2);
		Shark shark = new Shark();
		shark.setName("dabaisha");
		shark.setType(3);
		try
		{
			session.save(whale);
			session.save(shark);

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
			Fish fish = (Fish)session.load(Fish.class, 1);
			System.out.println("鱼的名字"+fish.getName());
			
			Whale whale = (Whale)session.load(Whale.class, 1);
			System.out.println("虎头鲸的颜色"+whale.getColor());
			
			Shark shark = (Shark)session.load(Shark.class, 2);
			System.out.println("大白鲨的种类"+shark.getType());
//			Customer customer = (Customer) session.get(Customer.class,
//					new Long(1));
//			System.out.println(customer.getName());
//			Set set = customer.getOrders();
//
//			for (Iterator<Order> it = set.iterator(); it.hasNext();)
//			{
//				Order order = it.next();
//
//				// System.out.println(order.getOrderNumber());
//			}
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
