package com.limonn.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration; 


public class UtilsServices {
	
	
	private static SessionFactory factory;
     
	static {
        try {
        	
        	factory = new AnnotationConfiguration().configure().buildSessionFactory();
        	
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
	}
	
	
        public static Session getSession() throws HibernateException {
            return factory.openSession();
        }

	    public static void cleanSession(Session s)
	    {
	    	s.flush();
	    	s.close();
	    }
	
//	public static void commit()
//	{
//		Transaction tr = getSession().beginTransaction();
//		tr.commit();
//		getSession().close();
//	}
	
	

}
