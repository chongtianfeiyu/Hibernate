package com.somnus;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.somnus.model.hql.Score;
import com.somnus.model.hql.Student;
import com.somnus.util.HibernateUtil;

public class HQLTest
{

	@Test
	public void load() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Student student = (Student) session.load(Student.class, 10);
			System.out.println(student.getSname());
			for (Score score : student.getScore())
			{
				System.out.println(score.getGrade());
			}
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
		// ///////////////////////如果在Student中配置了@Cache就会使用二级缓存///////////////////////////////////////
		Session session2 = HibernateUtil.openSession();
		Transaction tx2 = session2.beginTransaction();
		try
		{
			Student student = (Student) session2.load(Student.class, 1);
			System.out.println(student.getSname());
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			if (tx2 != null)
			{
				tx2.rollback();
			}
		}
		finally
		{
			session2.close();
		}
	}

	@Test
	public void testHQL_01() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			// Query query = session.createQuery("from Student");
			// List<Student> list = (List<Student>)query.list();
			// for(Student data : list) {
			// System.out.println(data.getSname());
			// }
			// 查询多的一方 因为多对一 默认lazy="false" 每取一条数据都会查询一的一方
			List<Score> list2 = (List<Score>) session.createQuery("from Score").list();
			for (Score data : list2)
			{
				System.out.println(data.getGrade());
				System.out.println(data.getStudent().getSname());
			}
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
	public void testHQL_02() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sage > 21");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSname());
			}
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
	public void testHQL_03() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s order by s.sname desc");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSname());
			}
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
	public void testHQL_04() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select distinct s from Student s order by s.sname desc");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSname());
			}
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
	public void testHQL_05() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			/*
			 * Query query =session.createQuery("from Student s where s.sid > :min and s.sid < :max");
			 * //q.setParameter("min", 2); 
			 * //q.setParameter("max", 8);
			 * q.setInteger("min", 2); 
			 * q.setInteger("max", 8);
			 */

			Query query = session.createQuery("from Student s where s.sid > :min and s.sid < :max")
					.setInteger("min", 2).setInteger("max", 8);
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_06() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sid > ? and s.sid < ?");
			query.setParameter(0, 2).setParameter(1, 8);
			// q.setParameter(1, 8);
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_07() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s order by s.sname desc");
			query.setMaxResults(4);
			query.setFirstResult(2);
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_08() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select s.sid,  s.sname from Student s order by s.sname desc");
			List<Object[]> categories = (List<Object[]>) query.list();
			for (Object[] o : categories)
			{
				System.out.println(o[0] + "---->" + o[1]);
			}
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
	public void testHQL_09() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Score s where s.student.sid = 1");
			List<Score> list = (List<Score>) query.list();
			for (Score data : list)
			{
				System.out.println(data.getGrade());
				System.out.println(data.getStudent().getSname());
			}
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

	// 动手测试left right join
	// 为什么不能直接写Student名，而必须写sc.student
	// 因为有可能存在多个成员变量（同一个类），需要指明用哪一个成员变量的连接条件来做连接
	@Test
	public void testHQL_13() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select sc.grade, st.sname from Score sc join sc.student st "); 
			for (Object o : query.list())
			{
				Object[] m = (Object[]) o;
				System.out.println(m[0] + "---->" + m[1]);
			}
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
	public void testHQL_14() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s = :StudentToSearch "); // 不重要
			Student st = new Student();
			st.setSid(1);
			query.setParameter("StudentToSearch", st);

			Student student = (Student) query.uniqueResult();
			System.out.println(student.getSname());
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
	public void testHQL_15() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select count(*) from Student s");
			long count = (Long) query.uniqueResult();
			System.out.println(count);
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
	public void testHQL_16() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select max(s.sage), min(s.sage), avg(s.sage), sum(s.sage) from Student s");
			Object[] o = (Object[]) query.uniqueResult();
			System.out.println(o[0] + "---->" + o[1] + "---->" + o[2] + "---->"+ o[3]);
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
	public void testHQL_17() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sage between 21 and 22");

			List<Student> list = (List<Student>) query.list();

			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_18() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sid in (3,4, 5)");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_19() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sage is not null");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_20() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.score is empty");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_21() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sname like '%风'");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_22() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sid like '_5'");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_23() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select lower(s.ssex),"
					+ "upper(s.ssex)," + "trim(s.ssex),"
					+ "concat(s.ssex, '***')," + "length(s.ssex)"
					+ " from Student s ");
			for (Object o : query.list())
			{
				Object[] arr = (Object[]) o;
				System.out.println(arr[0] + "---->" + arr[1] + "---->" + arr[2]
						+ "---->" + arr[3] + "---->" + arr[4] + "---->");
			}
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
	public void testHQL_24() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select abs(s.sid),"
					+ "sqrt(s.sid)," + "mod(s.sid, 2)" + " from Student s ");
			for (Object o : query.list())
			{
				Object[] arr = (Object[]) o;
				System.out.println(arr[0] + "---->" + arr[1] + "---->" + arr[2]);
			}
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
	public void testHQL_25() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select current_date, current_time, current_timestamp, s.sid from Student s");
			for (Object o : query.list())
			{
				Object[] arr = (Object[]) o;
				System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]
						+ " | " + arr[3]);
			}
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
	public void testHQL_27() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select s.sdept, count(*) from Student s group by s.sdept");
			for (Object o : query.list())
			{
				Object[] arr = (Object[]) o;
				System.out.println(arr[0] + "|" + arr[1]);
			}
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
	public void testHQL_28() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("select s.sdept, count(*) from Student s group by s.sdept having count(*) > 1");
			for (Object o : query.list())
			{
				Object[] arr = (Object[]) o;
				System.out.println(arr[0] + "|" + arr[1]);
			}
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
	public void testHQL_29() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sage < (select avg(s.sage) from Student s)");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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
	public void testHQL_30() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student s where s.sid < ALL (select s.sid from Student s where mod(s.sid, 2)= 0) ");
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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

	// 用in 可以实现exists的功能
	// 但是exists执行效率高
	@Test
	public void testHQL_31() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("from Student st where not exists (select sc.scid from Score sc where sc.student.sid=st.sid)");
			// Query query =
			// session.createQuery("from Student st where exists (select sc.scid from Score sc where sc.student.sid=st.sid)")
			// ;
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSid() + "---->" + data.getSname());
			}
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

	// update and delete
	// 规范并没有说明是不是要更新persistent object，所以如果要使用，建议在单独的trasaction中执行
	@Test
	public void testHQL_32() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			Query query = session.createQuery("update Student s set s.ssex = lower(s.ssex)");
			query.executeUpdate();
			query = session.createQuery("from Student");
			for (Object o : query.list())
			{
				Student t = (Student) o;
				System.out.println(t.getSsex());
			}
			session.createQuery("update Student s set s.ssex = upper(s.ssex)")
					.executeUpdate();
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

	// Native（了解）
	@Test
	public void testHQL_34() throws Exception
	{
		Session session = HibernateUtil.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			SQLQuery query = session.createSQLQuery("select * from t_student limit 2,4").addEntity(Student.class);
			List<Student> list = (List<Student>) query.list();
			for (Student data : list)
			{
				System.out.println(data.getSname());
			}
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
}
