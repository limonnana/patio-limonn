package com.limonn.actions;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import java.util.Date;
import com.limonn.entities.Cart;
import com.limonn.entities.User;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;

@UrlBinding("/SetTime.action")
public class SetTimeActionBean extends LimonnActionBean{
	
	private User user;
	private Cart cartInSession;
	private String deliveryHour, event;
	
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setCartIntoSession(Cart cart)
    {
    	getContext().getRequest().getSession().setAttribute("cartLimonn", cart);
    }
    
    public Cart getCartFromSession()
    {
    	return (Cart)getContext().getRequest().getSession().getAttribute("cartLimonn");
    }
	
	
	public String getDeliveryHour() {
		return deliveryHour;
	}
	public void setDeliveryHour(String deliveryHour) {
		this.deliveryHour = deliveryHour;
	}
	@DefaultHandler
    public Resolution setTime()
	{
		this.cartInSession = getCartFromSession();
		int hubicacion = this.deliveryHour.indexOf(":");
		
		String hourDelivery = this.deliveryHour.substring(0,hubicacion);
		String minutesDelivery = this.deliveryHour.substring(hubicacion+1, this.deliveryHour.length());
		this.deliveryHour = hourDelivery + " : " + minutesDelivery;
		
		int hour = Integer.parseInt(hourDelivery);
		int minutes = Integer.parseInt(minutesDelivery);
		
		Date date = Utils.getTimeNow();
		date.setHours(hour);
		date.setMinutes(minutes);
		
		if(this.cartInSession == null)
    	{
    		cartInSession = new Cart();
    	}
		
    		cartInSession.setDate(date);
    		setCartIntoSession(cartInSession);
    	 
		if("ProcessOrder.action".equals(this.event))
		{
			return new ForwardResolution("/ProcessOrder.action");
		}
		return new ForwardResolution("/index.jsp");
	}

}
