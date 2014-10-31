package com.somnus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.somnus.model.oneToOneFkBidirectional.FBIdCard;
import com.somnus.model.oneToOneFkBidirectional.FBPerson;
import com.somnus.util.HibernateUtil;

public class OneToOneFkBidirectionalTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		FBIdCard idCard = new FBIdCard();
		idCard.setCardNo("88888888888888");
		
		FBPerson person = new FBPerson();
		person.setName("菜10");
		person.setIdCard(idCard);

		try
		{
			///不能成功保存，因为IdCard是Transient状态
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
	public void save2()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		FBIdCard idCard = new FBIdCard();
		idCard.setCardNo("88888888888888");
		
		FBPerson person = new FBPerson();
		person.setName("菜10");
		person.setIdCard(idCard);

		try
		{
			session.save(idCard);
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
			FBPerson person = (FBPerson)session.load(FBPerson.class, 2);
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
			FBIdCard idCard = (FBIdCard)session.load(FBIdCard.class, 1);
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
