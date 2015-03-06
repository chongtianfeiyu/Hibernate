package com.somnus.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HibernateUtil{
	private static final Logger log = LoggerFactory.getLogger(HibernateUtil.class);
	
	private static SessionFactory sessionFactory;
	
	//使用线程局部模式
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	static{
		try{
			sessionFactory = new Configuration().configure().
				buildSessionFactory();
		}
		catch(Exception e){
			log.error("初始化SessionFactory失败！", e);
			throw new ExceptionInInitializerError(e);
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
		// 如果Session还没有打开，则新开一个Session
		if(session==null){
			session=sessionFactory.openSession();
			//把session对象设置到 threadLocal,相当于该session已经和线程绑定
			threadLocal.set(session);
		}
		return session;
	}
	
	public static void close(){
		//获取线程局部变量，并强制转换为Session类型
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);
		if(session != null){
			session.close();
		}
	}
}
