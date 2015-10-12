package com.limonn.actions;

import com.limonn.entities.Pedido;
import com.limonn.services.Services;
import com.limonn.settings.LimonnActionBean;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/Cocina.action")
public class CocinaActionBean extends LimonnActionBean{
	
	Pedido pedido;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Resolution listoPedido()
	{
		long idPedido = this.getPedido().getId();
		Services.setListoPedido(idPedido);
		
		return new ForwardResolution("/Cocina.jsp");
	}

}
