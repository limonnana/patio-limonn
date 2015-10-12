package com.limonn.actions;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.limonn.entities.Cart;
import com.limonn.entities.User;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;

@UrlBinding("/Start.action")
public class StartActionBean extends LimonnActionBean{
	
	private String id, mensaje, deliveryHour;
	private User user;
	private Cart cartInSession;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

    public User getUser() {
		return user;
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
    @DontValidate
    public Resolution getUserById()
	{
    	if(this.id!=null){
    		long idLong = Long.parseLong(this.id);
    		this.user = UserManager.getUserById(idLong);
    	}
    	
    	cartInSession = getCartFromSession();
    	if(cartInSession != null)
    	{
	    if(cartInSession.getDate()!= null)
	    {
	    	this.deliveryHour = Utils.getDeliveryTimeFromCart(cartInSession);
	    }
	    else
	    {
	    this.deliveryHour = Utils.deliveryHour();
	    }
    	}
    	else
    	{
    		 this.deliveryHour = Utils.deliveryHour();
    	}
		return new ForwardResolution("/Start.jsp");
	}
	
	public Cart getCartFromSession()
    {
    	return (Cart)getContext().getRequest().getSession().getAttribute("cartLimonn");
    }
    
    
}
