package com.limonn.actions;







import org.apache.commons.lang.StringUtils;

import com.limonn.entities.Cart;
import com.limonn.entities.Pedido;
import com.limonn.entities.User;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/RegisterCartOn.action")
public class RegisterCartOnActionBean extends LimonnActionBean{
	

        
        private User user;
        private Cart cartInSession;
        private String mensaje, kitchenComment, deliveryComment, deliveryHour;
        private String idCookie;
        
	    public Cart getCartInSession() {
			return cartInSession;
		}

		public void setCartInSession(Cart cartInSession) {
			this.cartInSession = cartInSession;
		}

	/** The user being registered. */
	    public void setUser(User user) { this.user = user; }

	    /** The user being registered. */
	    public User getUser() {
	    	return user; }
	    
	    
	    public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
		
		public String getKitchenComment() {
			return kitchenComment;
		}

		public void setKitchenComment(String kitchenComment) {
			this.kitchenComment = kitchenComment;
		}

		public String getDeliveryComment() {
			return deliveryComment;
		}

		public void setDeliveryComment(String deliveryComment) {
			this.deliveryComment = deliveryComment;
		}
		

		public String getDeliveryHour() {
			return deliveryHour;
		}

		public void setDeliveryHour(String deliveryHour) {
			this.deliveryHour = deliveryHour;
		}

		@DefaultHandler
	    public Resolution registerCartOn() {
			
			cartInSession = getCartFromSession();
			if(cartInSession.getDate()!= null)
			{
				this.deliveryHour = Utils.getDeliveryTimeFromCart(cartInSession);
			}
			else
			{
				this.deliveryHour = Utils.deliveryHour();
			}
	    	
	    	/*
	    	 * empieza validacion en caso que se este registrando por primera vez
	    	 */
	    	if(cartInSession.getUser()== null)
	    	{
	    		if(this.user.getName()== null)
	    		{
	    			mensaje = " *** Por favor escriba su nombre *** ";
	    			return new ForwardResolution("/ConfirmOrder.jsp");
	    		}
	    		if(this.user.getAddress() == null)
	    		{
	    			mensaje = " *** Por favor escriba su direccion *** ";
	    			return new ForwardResolution("/ConfirmOrder.jsp");
	    		}
	    		
	    		if(this.user.getCellphone() == null)
	    		{
	    			mensaje = " *** Por favor dejenos algun telefono *** ";
	    			return new ForwardResolution("/ConfirmOrder.jsp");
	    		}
	    		User justInserted = UserManager.saveUser(this.user);
	    		cartInSession.setUser(justInserted);
	    		idCookie = String.valueOf(justInserted.getId());
	    	
	    	}
	    	/*
	    	 * Fin validacion en caso que se este registrando por primera vez
	    	 */
	    	
	    	Pedido pedido = new Pedido();
	    	if(this.deliveryComment != null)
	    		pedido.setDeliveryComment(this.deliveryComment);
	    	if(this.kitchenComment != null)
	    		pedido.setKitchenComment(this.kitchenComment);
	    	pedido.setCart(cartInSession);
	    	if(cartInSession.getDate()!= null)
	    	{
	    		//java.sql.Date sqlDate = new java.sql.Date(cartInSession.getDate().getTime());
	    		//pedido.setDate(sqlDate);
	    		pedido.setDate(cartInSession.getDate());
	    	}
	    	else
	    	{
	    		//pedido.setDate(getCurrentJavaSqlDate());
	    		pedido.setDate(getCurrentJavaUtilDate());
	    	}
	    	
	    	long pedidoJustInserted = GastronomiaManager.savePedido(pedido);
	    	String pedidoJustInsertedString = String.valueOf(pedidoJustInserted);
	    	
	    	deleteCartFromSession();
    		
    		mensaje =  pedidoJustInsertedString;
	    	
	    	if(StringUtils.isNotBlank(idCookie))
	    	{
	    		 return new ForwardResolution("/Success.jsp?idCookie="+ idCookie);
	    	}
	    	else
	    	{
	    	    return new ForwardResolution("/Success.jsp");
	    	}
	    }
	    
	    public Cart getCartFromSession()
	    {
	    	return (Cart)getContext().getRequest().getSession().getAttribute("cartLimonn");
	    }
	    
	    public void deleteCartFromSession()
	    {
	    	getContext().getRequest().getSession().setAttribute("cartLimonn", null);
	    }
	    
	    public static java.sql.Date getCurrentJavaSqlDate() {
	        
	    	java.util.Date today = new java.util.Date();
	        return new java.sql.Date(today.getTime());
	     }
       public static java.util.Date getCurrentJavaUtilDate() {
	        
	    	java.util.Date today = new java.util.Date();
	    	today.setHours(today.getHours() + 3);
	        return today;
	     }
}
