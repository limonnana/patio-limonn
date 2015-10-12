package com.limonn.services;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.exception.*;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.limonn.entities.Activo;
import com.limonn.entities.Pasivo;
import com.limonn.entities.User;
import com.limonn.entities.EjercicioFinanciero;
import com.limonn.settings.Utils;

public class FinancialServices extends UtilsServices{
	
	
	
	public static Pasivo savePasivo(Pasivo pasivo)
	{
		
		Session s = null;
		Transaction tr = null;
		Pasivo p = null;
		
		try{
		
		 s = UtilsServices.getSession();
		 tr = getSession().beginTransaction();
		 p =  (Pasivo)s.merge(pasivo);
		 tr.commit();
		 
		}catch(DataException de){
			  
			System.out.print(de.toString());
			tr.rollback();
		}
	    
		EjercicioFinanciero ej = getEjercicioFinanciero(pasivo.getUser());
		
		try{
			
		s = UtilsServices.getSession();
		tr = getSession().beginTransaction();
			
		if(ej == null)
		{
			ej = new EjercicioFinanciero();
			ej.setUser(p.getUser());
			ej.setDate(Utils.getTimeNow());
			//List <Pasivo> pasivosList = ej.getPasivos();
			//pasivosList.add(p);
			//ej.setPasivos(pasivosList);
		    ej.getPasivos().add(p);
			//s.save(ej);
		    s.merge(ej);
		    tr.commit();
		}
		else
		{
			
			ej.getPasivos().add(p);
//			List <Pasivo> pasivosList = ej.getPasivos();
//			pasivosList.add(p);
//			ej.setPasivos(pasivosList);
			s.update(ej);
			tr.commit();
		}
		
		}catch(DataException de){
			  
			System.out.print(de.toString());
			tr.rollback();
		}
		
		cleanSession(s);
    	return p;
	}
	
	public static Activo saveActivo(Activo activo)
	{
        Session s = getSession();
        Transaction tr = getSession().beginTransaction();
		Activo a =  (Activo)s.merge(activo);
	    tr.commit();
	    
		EjercicioFinanciero ej = getEjercicioFinanciero(activo.getUser());
		if(ej == null)
		{
			ej = new EjercicioFinanciero();
			ej.setUser(a.getUser());
			ej.setDate(Utils.getTimeNow());
		    ej.getActivos().add(a);
			s.save(ej);
			tr.commit();
		}
		else
		{
			
			ej.getActivos().add(a);
			s.update(ej);
			tr.commit();
		}
		
		cleanSession(s);
    	return a;
	}
	
	public static EjercicioFinanciero getEjercicioFinanciero(User user)
	{
		Session s = getSession();
		Criteria criteria = s.createCriteria(EjercicioFinanciero.class);
		criteria.add(Restrictions.ne("status",-1));
		criteria.add(Restrictions.eq("user.id",user.getId()));
		List ef = criteria.list();
		cleanSession(s);
		if(!ef.isEmpty())
		{
		  return (EjercicioFinanciero)ef.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public static EjercicioFinanciero getEjercicioFinanciero(long id)
	{
		Session s = getSession();
		Criteria criteria = s.createCriteria(EjercicioFinanciero.class);
		criteria.add(Restrictions.eq("id",id));
		List ef = criteria.list();
		cleanSession(s);
		if(!ef.isEmpty())
		{
		  return (EjercicioFinanciero)ef.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public static void closeEjercicio(EjercicioFinanciero e)
	{
		EjercicioFinanciero eDb = getEjercicioFinanciero(e.getId());
		Session s = null;
		Transaction tr = null;
		
		try{
			
		
		 s = getSession();
		 tr = s.beginTransaction();
		
		   for(Activo a : eDb.getActivos())
		   {
			   a.setStatus(-1);
			   s.update(a);
			   
		   }
		   for(Pasivo p : eDb.getPasivos())
		   {
			   p.setStatus(-1);
			   s.update(p);
			  
		   }
		   eDb.setStatus(-1);
		   s.update(eDb);
		   tr.commit();
           cleanSession(s);
           
		}catch(DataException de){
			  
			System.out.print(de.toString());
			tr.rollback();
		}
		
		   
	}
	
	

}
