package com.limonn.actions;



import java.util.List;

import com.limonn.entities.Cantidad;
import com.limonn.entities.Cart;
import com.limonn.entities.Plato;
import com.limonn.entities.PlatoCompuesto;
import com.limonn.entities.User;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/Order.action")
public class OrderActionBean extends LimonnActionBean{
	
	private User user;
	private Cart cartInSession;
	private Plato platoFromDb, plato;
	private PlatoCompuesto platoCompuestoFromCart;
	
	
	
	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCartInSession(Cart cartInSession) {
		this.cartInSession = cartInSession;
	}

	public Cart getCartInSession() {
		return cartInSession;
	}
	
	
		@DefaultHandler
	    public Resolution order()
	   {
		    if(this.user!= null)
		    {
			   this.user = UserManager.getUserById(this.user.getId());
		    }
		    
	        platoFromDb = GastronomiaManager.getPlatoById(this.plato.getId());
	        
	    	cartInSession = getCartFromSession();
	    	if(cartInSession == null)
	    	{
	    		setCartIntoSession(new Cart());
	    		cartInSession = getCartFromSession();
	    	} 
	        platoCompuestoFromCart = getPlatoCompuestoFromCart(platoFromDb.getId(), cartInSession);
	    	
	    	if(platoCompuestoFromCart == null)
	    	{
	    		PlatoCompuesto platoCompuesto = new PlatoCompuesto();
	    		Cantidad cantidad = new Cantidad();
	    		cantidad.setNumber(1);
	    		cantidad.setToPay(platoFromDb.getPrecio());
	    		platoCompuesto.setCantidad(cantidad);
	    		platoCompuesto.setPlato(platoFromDb);
	    		
	    		cartInSession.getPlatoCompuestoList().add(platoCompuesto);
	    		cartInSession.setTotal(calculateTotal(cartInSession.getPlatoCompuestoList()));
	    		cartInSession.setTotalPlatos(calculateTotalUnidades(cartInSession.getPlatoCompuestoList()));
	    		
	    	}
	    	else
	    	{
	    		addOneToCart(platoCompuestoFromCart, cartInSession);
	    	}
	    	if(this.user!= null)
		    {
	    		cartInSession.setUser(this.user);
		    }
	    	
	    	
	    	
		   return new ForwardResolution("/ShowOrder.jsp");
	    }
		
		public Resolution add()
		{
		  if(this.user!= null)
			  {
				  this.user = UserManager.getUserById(this.user.getId());
			   }
			
			
			cartInSession = getCartFromSession();
			platoFromDb = GastronomiaManager.getPlatoById(this.plato.getId());
			platoCompuestoFromCart = getPlatoCompuestoFromCart(this.plato.getId(), cartInSession);
			
			addOneToCart(platoCompuestoFromCart, cartInSession);
			
		    cartInSession.setUser(this.user);
			
			return new ForwardResolution("/ShowOrder.jsp");
		}
		
		public Resolution rest()
		{
		  if(this.user!= null)
			  {
				  this.user = UserManager.getUserById(this.user.getId());
			   }
			
			
			cartInSession = getCartFromSession();
			platoFromDb = GastronomiaManager.getPlatoById(this.plato.getId());
			platoCompuestoFromCart = getPlatoCompuestoFromCart(this.plato.getId(), cartInSession);
			int cantidadFromCart = getCantidadFromCart(this.plato.getId(), cartInSession);
			
			if(cantidadFromCart > 0)
			  {
				restOneToCart(platoCompuestoFromCart, cartInSession);
			  }
			
			cartInSession.setUser(this.user);
			
			
			return new ForwardResolution("/ShowOrder.jsp");
		}
	   

		public void setCartIntoSession(Cart cart)
	    {
	    	getContext().getRequest().getSession().setAttribute("cartLimonn", cart);
	    }
	    
	    public Cart getCartFromSession()
	    {
	    	return (Cart)getContext().getRequest().getSession().getAttribute("cartLimonn");
	    }
	    public PlatoCompuesto getPlatoCompuestoFromCart(long id, Cart cartInSession)
	    {
	    	List<PlatoCompuesto> platoInCartList = cartInSession.getPlatoCompuestoList();
	    	for(PlatoCompuesto p : platoInCartList)
	    	{
	    		if(p.getPlato().getId() == id)
	    		{
	    			return p;
	    		}
	    	
	    	}
	    	return null;
	    }
	    
	    public int getCantidadFromCart(long id, Cart cartInSession)
	    {
	    	int cantidad = 0;
	    	List<PlatoCompuesto> platoInCartList = cartInSession.getPlatoCompuestoList();
	    	for(PlatoCompuesto p : platoInCartList)
	    	{
	    		if(p.getPlato().getId() == id)
	    		{
	    			cantidad = cantidad + p.getCantidad().getNumber();
	    			return cantidad;
	    		}
	    	
	    	}
	    	return 0;
	    }
	    
	    public Double calculateTotal(List<PlatoCompuesto> platoCompuestoList)
	    {
	    	Double total = 0.0;
	    	for(PlatoCompuesto p : platoCompuestoList)
	    	{
	    		total = total + p.getCantidad().getToPay();
	    	}
	    	
	    	return total;
	    }
	    
	    public int calculateTotalUnidades(List<PlatoCompuesto> platoCompuestoList)
	    {
	    	int totalUnidades = 0;
	    	for(PlatoCompuesto p : platoCompuestoList)
	    	{
	    			totalUnidades = totalUnidades + p.getCantidad().getNumber();
	    	 }
	    	return totalUnidades;
	    }
	    
	    private PlatoCompuesto addOneToCart(PlatoCompuesto p, Cart c)
	    {
	    	int cantidadNueva = p.getCantidad().getNumber() + 1;
    		Double toPayNuevo = cantidadNueva * platoFromDb.getPrecio();
    		
    		p.getCantidad().setNumber(cantidadNueva);
    		p.getCantidad().setToPay(toPayNuevo);
    		
    		c.setTotal(calculateTotal(c.getPlatoCompuestoList()));
	    	c.setTotalPlatos(calculateTotalUnidades(c.getPlatoCompuestoList()));
	    	setCartIntoSession(c);
    		
    		return p;
	    }
	    
	    private PlatoCompuesto restOneToCart(PlatoCompuesto p, Cart c)
	    {
	    	int cantidadNueva = p.getCantidad().getNumber() - 1;
    		Double toPayNuevo = cantidadNueva * platoFromDb.getPrecio();
    		
    		p.getCantidad().setNumber(cantidadNueva);
    		p.getCantidad().setToPay(toPayNuevo);
    		
    		c.setTotal(calculateTotal(c.getPlatoCompuestoList()));
	    	c.setTotalPlatos(calculateTotalUnidades(c.getPlatoCompuestoList()));
	    	setCartIntoSession(c);
    		
    		return p;
	    }

}
