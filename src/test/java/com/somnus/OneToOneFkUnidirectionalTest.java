package com.somnus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.somnus.model.oneToOneFkUnidirectional.FUIdCard;
import com.somnus.model.oneToOneFkUnidirectional.FUPerson;
import com.somnus.util.HibernateUtil;

public class OneToOneFkUnidirectionalTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		FUIdCard idCard = new FUIdCard();
		idCard.setCardNo("88888888888888");
		
		FUPerson person = new FUPerson();
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
			HibernateUtil.close();
		}
	}
	@Test
	public void save2()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		FUIdCard idCard = new FUIdCard();
		idCard.setCardNo("88888888888888");
		
		FUPerson person = new FUPerson();
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
			FUPerson person = (FUPerson)session.load(FUPerson.class, 1);
			System.out.println("person.name=" + person.getName());
			System.out.println("idCard.cardNo=" + person.getIdCard().getCardNo());
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
