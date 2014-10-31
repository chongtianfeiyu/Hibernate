package com.somnus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.somnus.model.oneToOnePkBidirectional.PBIdCard;
import com.somnus.model.oneToOnePkBidirectional.PBPerson;
import com.somnus.util.HibernateUtil;

public class OneToOnePkBidirectionalTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		PBIdCard idCard = new PBIdCard();
		idCard.setCardNo("88888888888888");
		
		PBPerson person = new PBPerson();
		person.setName("菜10");
		person.setIdCard(idCard);

		try
		{
			//不会出现TransientObjectException异常
			//因为一对一主键关联映射中，默认了cascade属性
			session.save(person);

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
	public void query1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try
		{
			PBPerson person = (PBPerson)session.load(PBPerson.class, 1);
			System.out.println("person.name=" + person.getName());
			System.out.println("person.idCard.cardNo=" + person.getIdCard().getCardNo());
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
	public void query2()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try
		{
			PBIdCard idCard = (PBIdCard)session.load(PBIdCard.class, 1);
			System.out.println("idcard.cardNo=" + idCard.getCardNo());
			System.out.println("idcard.person.name=" + idCard.getPerson().getName());
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
