package com.limonn.actions;




import java.util.List;

import com.limonn.services.FinancialServices;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.limonn.entities.Envio;
import com.limonn.entities.Pasivo;
import com.limonn.entities.User;
import com.limonn.entities.Pedido;
import com.limonn.entityManager.FinancialManager;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.entityManager.UserManager;


@UrlBinding("/Envios.action")
public class EnviosActionBean extends LimonnActionBean{
	
	private int[] pedido;//esto tiene que ser un array o lista porque son muchos hay que iterar
	private String mensaje;
	private User user;
	private Envio envio;
	private String sheliaj;
	
	
	
   public String getSheliaj() {
		return sheliaj;
	}

	public void setSheliaj(String sheliaj) {
		this.sheliaj = sheliaj;
	}

   public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

   public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

   public int[] getPedido() {
		return pedido;
	}

	public void setPedido(int[] pedido) {
		this.pedido = pedido;
	}

   public User getUser() {
		return user;
	}

   public void setUser(User user) {
		this.user = user;
	}


    @DefaultHandler
	public Resolution enviar()
	{
	  	
    	if(this.user == null)
    	{
    		this.mensaje = "Se olvido elejir un delivery";
    		return new ForwardResolution("/Envios.jsp");
    	}
    	//nesecito el nombre del delivery Guy
    	User fromDb = UserManager.getUserById(this.user.getId());
    	
    	if(this.pedido == null)
    	{
    		this.mensaje = "No escogio ningun pedido";
    		return new ForwardResolution("/Envios.jsp");
    	}
    	
    	
    	 List<Pedido> pedidosParaEnviar = GastronomiaManager.getListaPedidosParaEnviar(this.pedido);
    	 
    	 changeStatusToPedidos(this.pedido);
    	 
    	 Envio e = new Envio();
    	 fromDb.setName( fromDb.getName().toUpperCase());
    	 e.setDeliveryGuy(fromDb);
    	 e.setListaPedidos(pedidosParaEnviar);
    	 e.setTotal(pedidosParaEnviar);
    	 e.setDate(Utils.getTimeNow());
    	 
    	 Envio envio = GastronomiaManager.saveEnvio(e);
    	 e.setId(envio.getId());
    	 
    	 setEnvio(e);
    	 setSheliaj(e.getDeliveryGuy().getName() + " " + e.getDeliveryGuy().getLastName());
    	 
    	 Pasivo pasivo = new Pasivo();
    	 pasivo.setUser(fromDb);
    	 pasivo.setConcepto("envio " + e.getId());
    	 pasivo.setDate(Utils.getTimeNow());
    	 pasivo.setCantidad(e.getTotal());
    	 
    	 //le cargo esta deuda al delivery
    	 FinancialServices.savePasivo(pasivo);
    	 
    	 
    	//this.user = null;
		//return new RedirectResolution("/Envios.jsp");
    	return new ForwardResolution("/PrintEnvio.jsp");
	}
    
    //estos pedidos fueron enviados then cambio status
    public static void changeStatusToPedidos(int[]pedidos)
    {
    	 GastronomiaManager.changeStatusToPedidos(pedidos);
    }
    
    public Resolution preEdit()
	{
    	setEnvio(GastronomiaManager.getEnvioById(this.envio.getId()));
    	return new ForwardResolution("/EditEnvio.jsp");
	}

}
