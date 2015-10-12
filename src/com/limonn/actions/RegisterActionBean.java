package com.limonn.actions;


import org.apache.commons.lang.StringUtils;

import com.limonn.entities.User;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;
import com.limonn.services.Services;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.EmailTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;


@UrlBinding("/Register.action")
public class RegisterActionBean extends LimonnActionBean{
	
	@ValidateNestedProperties({
        
        @Validate(field="name", required=true, maxlength=50),
        @Validate(field="cellphone", required=true, maxlength=50),
        @Validate(field="address", required=true, maxlength=70),
        @Validate(field="email", converter=EmailTypeConverter.class)
        })

	        
      private User user;
	 /** The user being registered. */
	    public void setUser(User user) { this.user = user; }

	    /** The user being registered. */
	    public User getUser() {
	    	return user; }
	 
	 /**
	     * Registers a new user, logs them in, and redirects them to the admin control panel list page.
	     */
	    @DefaultHandler
	    public Resolution guardar() {
	        
	    	String nameUpper = Utils.toUpperFirstCaracter(this.user.getName());
	    	
	        this.user.setName(nameUpper);
	        this.user.setLastName(Utils.toUpperFirstCaracter(this.user.getLastName()));
	        if(StringUtils.isBlank(this.user.getRole()))
	        {
	        this.user.setRole("Cliente");
	        }
	        if(this.user.getId()!= 0)
	        {
	        	UserManager.updateUser(this.user);
	        	if(getUserLimonnAdmin() != null)
	        	{
	        		if(getUserLimonnAdmin().getRole().equals("Admin"))
	        	        return new RedirectResolution("/SearchUser.action");
	        		else
	        			return new RedirectResolution("/index.jsp");	
	        	}
	        	else 
	        	{
	        		return new RedirectResolution("/index.jsp");
	        	}
	        }
	        else
	        {
	        	if(getUserLimonnAdmin()!= null)
	        	{   
	        		if(getUserLimonnAdmin().getRole().equals("Admin")||getUserLimonnAdmin().getRole().equals("Manager"))
	        		{
	        			String idCookie = String.valueOf(UserManager.saveUser(this.user).getId());
	        			//ahora mando mail para que le poga una cookie en la compu del cliente si hay mail
	        			
	        			if(this.user.getEmail()!= null)
	        			{
	        				String contextoWeb = Services.getSettings().getRutaDominio();
	        				String contextPath = this.getContext().getRequest().getContextPath();
	        				String message = "Felicitaciones ahora puede hacer sus pedidos en un click !!\n Presione el link de abajo o peguelo en la barra del buscador \n "+
	        				" para activar su cuenta \n  http://"+ contextoWeb + "/SetUserFromMail.action?id="+idCookie;
	        				String subject = "Sus pedidos de comida en un click";
	        				String emailToRecipient = this.user.getEmail();
	        				
	        				System.out.print(message);
	        				//TODO setear bien los mails
	        				//Utils.mail(message, subject, emailToRecipient);
	        			}
	        			
	        			
	        		return new RedirectResolution("/SearchUser.action");
	        		}
	        		else
	        		{
	        			String idCookie = String.valueOf(UserManager.saveUser(this.user).getId());
	   	        	 return new RedirectResolution("/Start.jsp?idCookie="+ idCookie);
	        		}
        	        
	        	}else
	        	{
	        	   String idCookie = String.valueOf(UserManager.saveUser(this.user).getId());
	        	 return new RedirectResolution("/Start.jsp?idCookie="+ idCookie);
	        	}
	        }
	         
	       
	    }

	    
	    
}
