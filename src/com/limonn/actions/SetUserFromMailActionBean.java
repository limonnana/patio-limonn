package com.limonn.actions;

import com.limonn.entities.User;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;




@UrlBinding("/SetUserFromMail.action")
public class SetUserFromMailActionBean extends LimonnActionBean{
	
	
	private User user;
	private long id;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	    @DontValidate
	    public Resolution preEdit() {
		 this.user = UserManager.getUserById(this.id);
		 
		 return new ForwardResolution("/SetUserFromMail.jsp?idCookie="+ this.id);
	 }


}
