package com.somnus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.inheritenceSingleTable.Cat;
import com.somnus.model.inheritenceSingleTable.Dog;
import com.somnus.util.HibernateUtil;

public class InheritenceSingleTableTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		Cat cat = new Cat();
		cat.setName("maomi");
		cat.setColor(2);
		Dog dog = new Dog();
		dog.setName("wangcai");
		dog.setType(3);
		try
		{
			session.save(cat);
			session.save(dog);

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
	public void query()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try
		{
			Cat cat = (Cat)session.load(Cat.class, 1);
			System.out.println("猫咪的颜色"+cat.getColor());
			
			Dog dog = (Dog)session.load(Dog.class, 2);
			System.out.println("狗狗的种类"+dog.getType());
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
			HibernateUtil.close();
		}
	}
}
