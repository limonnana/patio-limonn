package com.limonn.actions;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import com.limonn.entities.User;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;


@UrlBinding("/EditUser.action")
public class EditUserActionBean extends LimonnActionBean{
	
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	    @DontValidate
	    public Resolution preEdit() {
		 this.user = UserManager.getUserById(this.user.getId());
		 
		 return new ForwardResolution("/Register.jsp");
	 }

	

}
