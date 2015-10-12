package com.limonn.actions;


import com.limonn.settings.LimonnActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/ShowOrder.action")
public class ShowOrderActionBean extends LimonnActionBean{

	String orderId;
	
	public String getOrderId() {
		return orderId;
	}

    public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	@DefaultHandler
    public Resolution showOrder()
   {
	   
	   return new RedirectResolution("/ShowOrder.jsp");
    }

}
