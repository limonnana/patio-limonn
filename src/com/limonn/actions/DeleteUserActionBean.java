package com.limonn.actions;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import com.limonn.entities.User;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;

@UrlBinding("/DeleteUser.action")
public class DeleteUserActionBean extends LimonnActionBean{
	
	User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@DefaultHandler
	public Resolution deleteUser()
	{
	    if(this.getUserLimonnAdmin().getRole().equals("Admin"))
		UserManager.deleteUserById(this.user.getId());
			return new RedirectResolution("/SearchUser.action");
	}

}
