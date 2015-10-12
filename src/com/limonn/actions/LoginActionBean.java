package com.limonn.actions;



import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import com.limonn.entities.User;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;

@UrlBinding("/Login.action")
public class LoginActionBean extends LimonnActionBean{
	
@ValidateNestedProperties({
        
        @Validate(field="username", required=true, maxlength=50),
        @Validate(field="password", required=true, minlength=4, maxlength=50)
        })
	
	private User user;
    private String usernameNoExiste, passwordIncorrecto;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsernameNoExiste() {
		return usernameNoExiste;
	}

	public void setUsernameNoExiste(String usernameNoExiste) {
		this.usernameNoExiste = usernameNoExiste;
	}

	public String getPasswordIncorrecto() {
		return passwordIncorrecto;
	}

	public void setPasswordIncorrecto(String passwordIncorrecto) {
		this.passwordIncorrecto = passwordIncorrecto;
	}
	
	

	@DefaultHandler
    public Resolution login() {
        
		if(autenticar(this.user.getUsername(), this.user.getPassword()))
			{
			     return new ForwardResolution("/Manager.jsp");
        	 }
		else
		  {
			     return new ForwardResolution("/Login.jsp");
		  }
	}
	
	private boolean autenticar(String username, String password)
	{
		User userFromDb = UserManager.getUserByUsername(username.trim());
		
		if(userFromDb != null)
		 {
		if(userFromDb.getPassword().trim().equals(password)&& userFromDb.getRole().equals("Admin"))
		{
			setUserLimonnAdmin(userFromDb);
			return true;
		}
		else
		{
			passwordIncorrecto = "El password ingresado ( " + password + " ) no concuerda  con el que se encuentra en el sistema.";
			return false;
		}
		 }
		else
		{
			usernameNoExiste = "<b>Clientes no nesecitan autenticarse.</b> <br><br> El usuario " + username + " no existe.<br>   Si es administrador corrobore su usuario y pruebe de nuevo.";
            
            return false;
		}
    
       }

}
