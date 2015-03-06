package com.somnus;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.manyToManyBidirectional.BRole;
import com.somnus.model.manyToManyBidirectional.BUser;
import com.somnus.util.HibernateUtil;

public class ManyToManyBidirectionalTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		BRole r1 = new BRole();
		r1.setName("数据录入人员");
		session.save(r1);
		
		BRole r2 = new BRole();
		r2.setName("商务主管");
		session.save(r2);
		
		BRole r3 = new BRole();
		r3.setName("大区经理");
		session.save(r3);
		
		BUser u1 = new BUser();
		u1.setName("10");
		Set<BRole> u1Roles = new HashSet<BRole>();
		u1Roles.add(r1);
		u1Roles.add(r2);
		u1.setRoles(u1Roles);
		
		BUser u2 = new BUser();
		u2.setName("祖儿");
		Set<BRole> u2Roles = new HashSet<BRole>();
		u2Roles.add(r2);
		u2Roles.add(r3);
		u2.setRoles(u2Roles);
		
		BUser u3 = new BUser();
		u3.setName("杰伦");
		Set<BRole> u3Roles = new HashSet<BRole>();
		u3Roles.add(r1);
		u3Roles.add(r2);
		u3Roles.add(r3);
		u3.setRoles(u3Roles);
		try
		{
			session.save(u1);
			session.save(u2);
			session.save(u3);
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
			BUser user = (BUser)session.load(BUser.class, 1);
			System.out.println(user.getName());
			for (Iterator<BRole> iter=user.getRoles().iterator(); iter.hasNext();) {
				BRole role = (BRole)iter.next();
				System.out.println(role.getName());
			}
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
	@Test
	public void query2()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try
		{
			BRole role = (BRole)session.load(BRole.class, 1);
			System.out.println(role.getName());
			for (Iterator<BUser> iter=role.getUsers().iterator(); iter.hasNext();) {
				BUser user = (BUser)iter.next();
				System.out.println(user.getName());
			}
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
