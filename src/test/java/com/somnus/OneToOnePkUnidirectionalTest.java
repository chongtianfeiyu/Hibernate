package com.somnus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.oneToOnePkUnidirectional.PUIdCard;
import com.somnus.model.oneToOnePkUnidirectional.PUPerson;
import com.somnus.util.HibernateUtil;

public class OneToOnePkUnidirectionalTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		PUIdCard idCard = new PUIdCard();
		idCard.setCardNo("88888888888888");
		
		PUPerson person = new PUPerson();
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
			PUPerson person = (PUPerson)session.load(PUPerson.class, 1);
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
