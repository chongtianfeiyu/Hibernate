package com.somnus.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil 
{
	private static SessionFactory sessionFactory;
	//使用线程局部模式
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	static
	{
		try
		{
			sessionFactory = new Configuration().configure().
				buildSessionFactory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Session openSession()
	{
		Session session = sessionFactory.openSession();
		
		return session;
	}
	
	//获取和线程关联的session
	public static Session getCurrentSession(){
		
		Session session=threadLocal.get();
		//判断是否得到
		if(session==null){
			session=sessionFactory.openSession();
			//把session对象设置到 threadLocal,相当于该session已经和线程绑定
			threadLocal.set(session);
		}
		return session;
	}
	
	public static void close(Session session)
	{
		if(session != null)
		{
			session.close();
		}
	}
}
