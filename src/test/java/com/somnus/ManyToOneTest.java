package com.somnus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.manyToOne.Customer;
import com.somnus.model.manyToOne.Order;
import com.somnus.util.HibernateUtil;

public class ManyToOneTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		Customer customer = new Customer();
		customer.setName("abc");

		Order order1 = new Order();
		order1.setOrderNumber("order1");
		order1.setCustomer(customer);

		Order order2 = new Order();
		order2.setOrderNumber("order2");
		order2.setCustomer(customer);

		try
		{
			//不能成功保存，抛出TransientObjectException异常
			//因为Group为Transient状态，oid没有分配值
			//persistent状态的对象是不能引用transient状态的对象的
			/*
			 * 在多的一方配置文件加了cascade级联的情况下才可以不保存一的一方      才可以正确保存   不然将发生异常
			 */
			session.save(order1);
			session.save(order2);
			
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
	public void save2() throws Exception
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		Customer customer = new Customer();
		customer.setName("abc");
		
		Order order1 = new Order();
		order1.setOrderNumber("order1");
		order1.setCustomer(customer);

		Order order2 = new Order();
		order2.setOrderNumber("order2");
		order2.setCustomer(customer);

		try
		{
			//可以正确存储
			session.save(customer);
			session.save(order1);
			session.save(order2);

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
			Order order = (Order)session.load(Order.class, 1);
			System.out.println("订单数量"+order.getOrderNumber());
			System.out.println("客户姓名"+order.getCustomer().getName());
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
