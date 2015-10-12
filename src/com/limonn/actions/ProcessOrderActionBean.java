package com.limonn.actions;

import com.limonn.entities.Cart;
import com.limonn.entities.User;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/ProcessOrder.action")
public class ProcessOrderActionBean extends LimonnActionBean{
	
	
    private Cart cartInSession;
    private String mensaje;
    private User user;
    private String deliveryHour;
 
	public Cart getCartInSession() {
	return cartInSession;
    }

    public void setCartInSession(Cart cartInSession) {
	this.cartInSession = cartInSession;
    }
    

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

	public String getDeliveryHour() {
		return deliveryHour;
	}

	public void setDeliveryHour(String deliveryHour) {
		this.deliveryHour = deliveryHour;
	}

	@DefaultHandler
    public Resolution processOrder()
   {
	    cartInSession = getCartFromSession();
	    if(cartInSession.getDate()!= null)
	    {
	    	this.deliveryHour = Utils.getDeliveryTimeFromCart(cartInSession);
	    }
	    else
	    {
	    this.deliveryHour = Utils.deliveryHour();
	    }
		return new ForwardResolution("/ConfirmOrder.jsp");
   }
	
	public Cart getCartFromSession()
    {
    	return (Cart)getContext().getRequest().getSession().getAttribute("cartLimonn");
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
