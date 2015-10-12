package com.limonn.entityManager;

import java.util.List;
import com.limonn.entities.Envio;
import com.limonn.entities.EnvioFormatedDay;
import com.limonn.entities.Especialidad;
import com.limonn.entities.Pedido;
import com.limonn.entities.Plato;
import com.limonn.entities.User;
import com.limonn.services.GastronomiaServices;

public class GastronomiaManager {
	
	public static void saveEspecialidad(Especialidad especialidad)
	   {
		  GastronomiaServices.saveEspecialidad(especialidad);
	   }
	
	public List<Especialidad> getEspecialidadesList()
	   {
		   return GastronomiaServices.getEspecialidadesList();
	   }
	
	public static Especialidad getEspecialidadById(long id)
	{
		return GastronomiaServices.getEspecialidadById(id);
	}
	
	public static void deleteEspecialidadById(long id)
	{
		 GastronomiaServices.deleteEspecialidadById(id);
	}
	
	public static void savePlato(Plato plato, Especialidad especialidad)
	   {
		  GastronomiaServices.savePlato(plato, especialidad);
	   }
	
	public static Plato getPlatoById(long id)
	{
		return GastronomiaServices.getPlatoById(id);
	}
	
	public List<Plato> getPlatosList()
	   {
		   return GastronomiaServices.getPlatosList();
	   }
	
	public static void deletePlatoById(long id)
	{
		 GastronomiaServices.deletePlatoById(id);
	}
	public static long savePedido(Pedido pedido)
	{
		return GastronomiaServices.savePedido(pedido);
	}
	
	public List<Pedido> getPedidosList()
	{
		return GastronomiaServices.getPedidosList();
	}
	public List<Pedido> getPedidosCocinaList()
	{
		return GastronomiaServices.getPedidosCocinaList();
	}
	public List<User> getDeliveryGuyList()
	{
		return GastronomiaServices.getDeliveryGuyList();
	}
	public List<Pedido> getPedidosListoList()
	{
		return GastronomiaServices.getPedidosListoList();
	}
	public List<Pedido> getPedidosNoEnviados()
	{
		return GastronomiaServices.getPedidosNoEnviados();
	}
	public static void changeStatusToPedidos(int[]pedidos)
	{
		 GastronomiaServices.changeStatusToPedidos(pedidos);
	}
	public static List<Pedido> getListaPedidosParaEnviar(int[] pedidos)
	{
		return GastronomiaServices.getListaPedidosParaEnviar(pedidos);
	}
	public static Envio saveEnvio(Envio e)
	{
		return GastronomiaServices.saveEnvio(e);
	}
    public List<EnvioFormatedDay> getEnviosList()
    {
    	return GastronomiaServices.getEnviosList();
    }
    public static Envio getEnvioById(long id)
    {
    	return GastronomiaServices.getEnvioById(id);
    }
}
