package com.somnus;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.manyToManyUnidirectional.URole;
import com.somnus.model.manyToManyUnidirectional.UUser;
import com.somnus.util.HibernateUtil;

public class ManyToManyUnidirectionalTest
{
	@Test
	public void save1()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		URole r1 = new URole();
		r1.setName("数据录入人员");
		session.save(r1);
		
		URole r2 = new URole();
		r2.setName("商务主管");
		session.save(r2);
		
		URole r3 = new URole();
		r3.setName("大区经理");
		session.save(r3);
		
		UUser u1 = new UUser();
		u1.setName("10");
		Set<URole> u1Roles = new HashSet<URole>();
		u1Roles.add(r1);
		u1Roles.add(r2);
		u1.setRoles(u1Roles);
		
		UUser u2 = new UUser();
		u2.setName("祖儿");
		Set<URole> u2Roles = new HashSet<URole>();
		u2Roles.add(r2);
		u2Roles.add(r3);
		u2.setRoles(u2Roles);
		
		UUser u3 = new UUser();
		u3.setName("杰伦");
		Set<URole> u3Roles = new HashSet<URole>();
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
	public void query()
	{
		Session session = HibernateUtil.openSession();

		Transaction tx = session.beginTransaction();

		try
		{
			UUser user = (UUser)session.load(UUser.class, 2);
			System.out.println(user.getName());
			for (Iterator<URole> iter=user.getRoles().iterator(); iter.hasNext();) {
				URole role = (URole)iter.next();
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
}
