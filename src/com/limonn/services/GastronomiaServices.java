package com.limonn.services;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.limonn.entities.Cantidad;
import com.limonn.entities.Cart;
import com.limonn.entities.Envio;
import com.limonn.entities.EnvioFormatedDay;
import com.limonn.entities.Especialidad;
import com.limonn.entities.Pedido;
import com.limonn.entities.Plato;
import com.limonn.entities.PlatoCompuesto;
import com.limonn.entities.User;
import com.limonn.settings.Utils;



public class GastronomiaServices extends UtilsServices{
	
	
	
	public static void saveEspecialidad(Especialidad especialidad)
	{
		Session s = UtilsServices.getSession();
		Transaction tr = getSession().beginTransaction();
		
		if(especialidad.getId()!= 0)
		{
			Especialidad fromDataBase = getEspecialidadById(especialidad.getId());
			fromDataBase.setNombre(especialidad.getNombre());
			fromDataBase.setPosicion(especialidad.getPosicion());
			s.update(fromDataBase);
			tr.commit();
		}else
		{
			s.save(especialidad);
			tr.commit();
		}
		cleanSession(s);
	}
	
	public static List<Especialidad> getEspecialidadesList()
	{
        Criteria criteria = getSession().createCriteria(Especialidad.class);
        criteria.addOrder(Order.asc("posicion"));
		
        List<Especialidad> especialidades = criteria.list();
        
        return especialidades;
	}
	
	public static Especialidad getEspecialidadById(long id)
	{
		Criteria criteria = getSession().createCriteria(Especialidad.class);
		List especialidadList = criteria.add(Restrictions.eq("id", id)).list();
		return (Especialidad)especialidadList.get(0);
	}
	
    public static void deleteEspecialidadById(long id)
    {
    	String hql = "delete from Especialidad where id = "+ id;
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
    }
    
    public static void savePlato(Plato plato, Especialidad especialidad)
	{
		Session s = UtilsServices.getSession();
		Transaction tr = s.beginTransaction();
		if(plato.getId()!= 0)
		{
			s.update(plato);
			tr.commit();
		}
		else
		{
		
		long PJustInsertedId = (Long)s.save(plato);
		
		Criteria criteriaPlato = s.createCriteria(Plato.class);
        List platoList = criteriaPlato.add(Restrictions.eq("id", PJustInsertedId)).list();
		Plato platoJustInserted = (Plato)platoList.get(0);
		
		Criteria criteria = s.createCriteria(Especialidad.class);
		List especialidadList = criteria.add(Restrictions.eq("id", especialidad.getId())).list();
		Especialidad e = (Especialidad)especialidadList.get(0);
		List <Plato> eList = e.getPlatoList();
		eList.add(platoJustInserted);
		//e.getPlatoList().add(platoJustInserted);
    	e.setPlatoList(eList);
    	s.update(e);
		tr.commit();
		}
		cleanSession(s);
	}
    
    public static List<Plato> getPlatosList()
	{
        Criteria criteria = getSession().createCriteria(Plato.class);
		criteria.add(Restrictions.ne("status",-1));
        List<Plato> Platos = criteria.list();
        
        return Platos;
	}
    
    public static Plato getPlatoById(long id)
    {
        Criteria criteria = getSession().createCriteria(Plato.class);
        List platoList = criteria.add(Restrictions.eq("id", id)).list();
		
        Plato plato = (Plato)platoList.get(0);
        return plato;
    }
    
    public static void deletePlatoById(long id)
    {
    	       Session s = UtilsServices.getSession();
		       Transaction tr = getSession().beginTransaction();
    	       Criteria criteria = s.createCriteria(Plato.class);
    	       List platoList = criteria.add(Restrictions.eq("id", id)).list();
    			
    	        Plato plato = (Plato)platoList.get(0);
    	        plato.setStatus(-1);
    	        s.update(plato);
    	        tr.commit();
    	        cleanSession(s);
    	
    	
    	//    	String hql = "delete from Plato where id = "+ id;
       //		Query query = getSession().createQuery(hql);
      //		query.executeUpdate();
    }
    
    public static long savePedido(Pedido pedido)
    {
    	Session s = UtilsServices.getSession();
		Transaction tr = getSession().beginTransaction();
    	List<PlatoCompuesto> pcList = new ArrayList<PlatoCompuesto>();
    	for(PlatoCompuesto p : pedido.getCart().getPlatoCompuestoList())
    	{
    		Cantidad cantidad = new Cantidad();
    		cantidad = p.getCantidad();
    		s.save(cantidad);
    		
    		PlatoCompuesto pc = new PlatoCompuesto();
    		pc.setCantidad(cantidad);
    		pc.setPlato(p.getPlato());
    		
    		pcList.add(pc);
    		s.save(pc);
    	 }
    	
    	
    	Cart cart = new Cart();
    	cart.setPlatoCompuestoList(pcList);
    	cart.setTotal(pedido.getCart().getTotal());
    	cart.setTotalPlatos(pedido.getCart().getTotalPlatos());
    	cart.setUser(pedido.getCart().getUser());
    	
    	Cart cartJustInserted = (Cart)s.merge(cart);
    	
    	
    	pedido.setCart(cartJustInserted);
    	pedido.setDate(pedido.getDate());
    	pedido.setDeliveryComment(pedido.getDeliveryComment());
    	pedido.setKitchenComment(pedido.getKitchenComment());
    	pedido.setListo(false);
    	
    	
    	Long justInserted = (Long) s.save(pedido);
        tr.commit();
        cleanSession(s);
        return justInserted;
    }
    
    public static List<Pedido> getPedidosList()
	{
        Criteria criteria = getSession().createCriteria(Pedido.class);
		
        List<Pedido> pedidos = criteria.list();
        
        return pedidos;
	}
    public static List<Pedido> getPedidosCocinaList()
	{
        List<Pedido> pedidosCocinaList = new ArrayList<Pedido>();
    	
    	Criteria criteria = getSession().createCriteria(Pedido.class);
    	
		
        List<Pedido> pedidos = criteria.list();
        for(Pedido p : pedidos)
        {
        	if(p.isListo() == false && Utils.compararDiaTiempoDelivery(p.getDate(), 35))
    	   {
    		   pedidosCocinaList.add(p); 
    	   }
       }
        
        return pedidosCocinaList;
	}
    
    public static List<User> getDeliveryGuyList()
    {
    	Criteria criteria = getSession().createCriteria(User.class);
    	criteria.add(Restrictions.eq("role", "Delivery"));
    	
    	//List<User> listaGuys = criteria.list();
    	return criteria.list();
    }
    
    public static List<Pedido>getPedidosListoList()
    {
    	Criteria criteria = getSession().createCriteria(Pedido.class);
    	criteria.add(Restrictions.eq("listo", true));
    	return criteria.list();
    }
    public static List<Pedido>getPedidosNoEnviados()
    {
    	Criteria criteria = getSession().createCriteria(Pedido.class);
    	criteria.add(Restrictions.eq("listo", true));
    	criteria.add(Restrictions.eq("enviado", false));
    	return criteria.list();
    }
    
    public static List<Pedido>getPedidosNoEnviadosSameConnection(Session c)
    {
    	Criteria criteria = c.createCriteria(Pedido.class);
    	criteria.add(Restrictions.eq("listo", true));
    	criteria.add(Restrictions.eq("enviado", false));
    	return criteria.list();
    }
    
    public static void changeStatusToPedidos(int[]pedidos)
    {
    	Session s = UtilsServices.getSession();
		Transaction tr = getSession().beginTransaction();
    	List<Pedido> pedidoSemiFiltrado = getPedidosNoEnviadosSameConnection(s);
    	
    	for(Pedido p : pedidoSemiFiltrado)
    	{
    		for(int counter=0;counter<pedidos.length;counter++)
    		{
	    		if(p.getId() == pedidos[counter])
	    		{
	    			p.setEnviado(true);
	    			s.update(p);
	    			
	    		}
    		}
    	  }
    	 tr.commit();
    	cleanSession(s);
        }
    
    public static List<Pedido> getListaPedidosParaEnviar(int[]pedidos)
    {
        List<Pedido> listaPedidos = new ArrayList<Pedido>();
    	
    	List<Pedido> pedidoSemiFiltrado = getPedidosNoEnviadosSameConnection(getSession());
    	
    	for(Pedido p : pedidoSemiFiltrado)
    	{
    		for(int counter=0;counter<pedidos.length;counter++)
    		{
	    		if(p.getId() == pedidos[counter])
	    		{
	    			listaPedidos.add(p);
	    		}
    		}
    	  }
    	   return listaPedidos;
        }
    	
    public static Envio saveEnvio(Envio e)
    {
    	Session s = UtilsServices.getSession();
		Transaction tr = getSession().beginTransaction();
    	Envio envio =  (Envio)s.merge(e);
    	tr.commit();
    	cleanSession(s);
    	return envio;
    	
    }
    
//    public static List<EnvioFormatedDay> getEnviosList()
//    {
//    	List<EnvioFormatedDay> formatedDayList = new ArrayList<EnvioFormatedDay>();
//    	
//    	Criteria criteria = getSession().createCriteria(Envio.class);
//    	List<Envio> listaEnvios = criteria.list();
//    	for(Envio e : listaEnvios)
//    	{
//    		EnvioFormatedDay efd = new EnvioFormatedDay();
//    		efd.setDeliveryGuy(e.getDeliveryGuy());
//    		efd.setId(e.getId());
//    		efd.setListaPedidos(e.getListaPedidos());
//    		efd.setTotal(e.getTotal());
//    		if(e.getDate()!= null)
//    		{
//    		efd.setDate(Utils.getStringTimeNow(e.getDate()));
//    		}
//    		formatedDayList.add(efd);
//    	}
//    	return formatedDayList;
//    }
   // public static List<EnvioFormatedDay> getEnviosDeHoyList()
    //{
       public static List<EnvioFormatedDay> getEnviosList(){
    	List<EnvioFormatedDay> formatedDayList = new ArrayList<EnvioFormatedDay>();
    	
    	Criteria criteria = getSession().createCriteria(Envio.class);
    	List<Envio> listaEnvios = criteria.list();
    	for(Envio e : listaEnvios)
    	{
    		if(e.getDate()!= null)
    		{
    			int comparacion = Utils.compararDia(e.getDate());
    		if(comparacion == 0)
    		{
    		EnvioFormatedDay efd = new EnvioFormatedDay();
    		efd.setDeliveryGuy(e.getDeliveryGuy());
    		efd.setId(e.getId());
    		efd.setListaPedidos(e.getListaPedidos());
    		efd.setTotal(e.getTotal());
    		if(e.getDate()!= null)
    		{
    		efd.setDate(Utils.getStringTimeNow(e.getDate()));
    		}
    		formatedDayList.add(efd);
    		}
    		}
    	}
    	return formatedDayList;
    }
       
       public static Envio getEnvioById(long id)
       {
    	   Criteria criteria = getSession().createCriteria(Envio.class);
           List envioList = criteria.add(Restrictions.eq("id", id)).list();
   		
           Envio envio = (Envio)envioList.get(0);
           return envio;
       }
}
