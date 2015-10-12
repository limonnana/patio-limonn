package com.limonn.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.limonn.entities.Pedido;
import com.limonn.entities.Settings;
import com.limonn.entities.User;
import com.limonn.settings.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Services extends UtilsServices{
	
	
	public static void saveUserAdmin(User u)
	{
		getUserById(1);
	}
	
	public static User saveUser(User u)
	{
		String nameUpper = Utils.toUpperFirstCaracter(u.getName());
    	
        u.setName(nameUpper);
        u.setLastName(Utils.toUpperFirstCaracter(u.getLastName()));
        if(StringUtils.isBlank(u.getRole()))
        {
        u.setRole("Cliente");
        }
        Session s = UtilsServices.getSession();
		Transaction tr = s.beginTransaction();
		User user = (User)s.merge(u);
        tr.commit();
        s.flush();
        s.close();
        return user;
	}
	
	public static void updateUser(User u)
	{
		Session s = UtilsServices.getSession();
		Transaction tr = s.beginTransaction();
		s.update(u);
        tr.commit();
        s.close();
	}
	
	
	public static List<User> getUsers()
	{
		Session s = UtilsServices.getSession();
	
		Criteria criteria = s.createCriteria(User.class);
		
        List<User> usuarios = criteria.list();
        
        s.flush();
        s.close();
        
        return usuarios;
	}
	
	public static User getUserById(long id)
	{
		Session s = UtilsServices.getSession();
        
		if(getUserByUsername("limonn")== null)
		{
			User u = new User();
			u.setName("Raul");
			u.setLastName("Rosenzvaig");
			u.setEmail("info@limonn.com");
			u.setUsername("limonn");
			u.setPassword("argentina");
			u.setRole("Admin");
			s.save(u);
		}
		
		Criteria criteria = s.createCriteria(User.class);
		List userList = criteria.add(Restrictions.eq("id", id)).list();
		s.flush();
		s.close();
		return (User)userList.get(0);
	}
	
	public static List getUsersListByPhone(String phone)
	{
		Session s = UtilsServices.getSession();
		Criteria criteria = s.createCriteria(User.class);
		List userList = criteria.add(Restrictions.like("cellphone", phone+ "%")).list();
		cleanSession(s);
		if(userList.isEmpty())
		{
			userList = criteria.add(Restrictions.eq("phone", phone)).list();
		}
		if(!userList.isEmpty())
		{
		return userList;
		}else
		{
			return null;
		}
	}
	
	public static ArrayList<User> getUsersListByName(String name)
	{
		String nameToUpper = Utils.toUpperFirstCaracter(name);
		Session s = UtilsServices.getSession();
		Criteria criteria = s.createCriteria(User.class);
		List userList = criteria.add(Restrictions.eq("name", nameToUpper)).list();
		ArrayList<User> userListFromDb = new ArrayList<User>();
		for(Object o : userList)
		{
			userListFromDb.add((User)o);
		}
		
		cleanSession(s);
		return userListFromDb;
	}
	
	public static ArrayList<User> getUsersListByAddress(String address)
	{
		Session s = UtilsServices.getSession();
		Criteria criteria = s.createCriteria(User.class);
		
		if(address.length()>6)
		{
		   address = address.substring(0, 6)+ "%";
		}
		else
		{
			address = address + "%";
		}
		List userList = criteria.add(Expression.like("address",address)).list();
		ArrayList<User> userListFromDb = new ArrayList<User>();
		for(Object o : userList)
		{
			userListFromDb.add((User)o);
		}
		cleanSession(s);
		return userListFromDb;
	}
	
	public static ArrayList<User> getUsersListByLastName(String lastName)
	{
		String nameToUpper = Utils.toUpperFirstCaracter(lastName);
		Session s = UtilsServices.getSession();
		Criteria criteria = s.createCriteria(User.class);
		List userList = criteria.add(Restrictions.eq("lastName", nameToUpper)).list();
		if(userList.isEmpty())
		{
			userList = criteria.add(Restrictions.eq("lastName", lastName)).list();
		}
		ArrayList<User> userListFromDb = new ArrayList<User>();
		for(Object o : userList)
		{
			userListFromDb.add((User)o);
		}
		cleanSession(s);
		return userListFromDb;
	}
	
	public static User getUserByUsername(String username)
	{
		Session s = UtilsServices.getSession();
		Criteria criteria = s.createCriteria(User.class);
		List userList = criteria.add(Restrictions.eq("username", username)).list();
		cleanSession(s);
		if(!userList.isEmpty())
		{
		   return (User)userList.get(0);
		}
		else
		{
			return null;
		}
	}
    
	public static void deleteUserById(long id)
	{
		Session s = UtilsServices.getSession();
		String hql = "delete from User where id = "+ id;
		Query query = s.createQuery(hql);
		query.executeUpdate();
		cleanSession(s);
        // int rowCount = query.executeUpdate();
        //System.out.println("Rows affected: " + rowCount);
	}
	
	public static void setListoPedido(long pedidoId)
	{
	    Session s = UtilsServices.getSession();
	    Transaction tr = s.beginTransaction();
		Criteria criteria = s.createCriteria(Pedido.class);
		List pedidosList = criteria.add(Restrictions.eq("id", pedidoId)).list();
		Pedido fromDb = (Pedido)pedidosList.get(0);
		fromDb.setListo(true);
		s.update(fromDb);
		tr.commit();
		cleanSession(s);
		
	}
	public static void updateSettings(Settings settings)
	{
		 Session s = UtilsServices.getSession();
		 Transaction tr = getSession().beginTransaction();
		if(settings.getId()== 0)
		{
			s.save(settings);
			
		}
		else
		{
			s.update(settings);
	        tr.commit();
		}
		cleanSession(s);
	}
	public static Settings getSettings()
	{
		 Session s = UtilsServices.getSession();
		long n = new Long("1");
		Criteria criteria = s.createCriteria(Settings.class);
		List se = criteria.add(Restrictions.eq("id", n)).list();
		cleanSession(s);
		if(se.isEmpty())
		{
			return null;
		}
		 return (Settings)se.get(0);
	}
	
	public static void saveObject(Object o)
	{
		 Session s = UtilsServices.getSession();
		s.save(o);
		cleanSession(s);
	}
	
	public static List dameUsuarios(int salario)
	{
		 Session s = UtilsServices.getSession();
		List<User> userList = new ArrayList();
		
		List lista = s.createQuery("from User u where u.sueldo.salario>" + salario).list();
		
		
		for(int i=0;i<lista.size();i++)
		{
			userList.add((User)lista.get(i));
			System.out.print("\n el id es: " + userList.get(i).getId());
		}
		cleanSession(s);
		return userList;
	}
	
	
}
