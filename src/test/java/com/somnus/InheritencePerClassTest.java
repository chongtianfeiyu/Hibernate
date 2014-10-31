package com.somnus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.somnus.model.inheritencePerClass.Flower;
import com.somnus.model.inheritencePerClass.Peony;
import com.somnus.model.inheritencePerClass.Rose;
import com.somnus.model.inheritenceSingleTable.Cat;
import com.somnus.model.inheritenceSingleTable.Dog;
import com.somnus.model.manyToOne.Order;
import com.somnus.util.HibernateUtil;

public class InheritencePerClassTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		Peony peony = new Peony();
		peony.setName("mudan");
		peony.setColor(2);
		Rose rose = new Rose();
		rose.setName("meigui");
		rose.setType(3);
		try
		{
			session.save(peony);
			session.save(rose);

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
			
			Flower flower = (Flower)session.load(Flower.class, 2);
			System.out.println("花的名字"+flower.getName());
			
			
			Peony peony = (Peony)session.load(Peony.class, 1);
			System.out.println("牡丹的颜色"+peony.getColor());
			
			Rose rose = (Rose)session.load(Rose.class, 2);
			System.out.println("玫瑰的种类"+rose.getType());
			
			
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
