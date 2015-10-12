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


@UrlBinding("/SetUp.action")
public class SetUp extends LimonnActionBean{
	
@ValidateNestedProperties({
        
        @Validate(field="user.username", required=true, maxlength=50),
        @Validate(field="user.password", required=true, minlength=4, maxlength=50)
        })
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@DefaultHandler
    public Resolution insertUser() {
        
		UserManager.saveUserAdmin(this.user);
		
		return new ForwardResolution("/Login.jsp");
	}

}
