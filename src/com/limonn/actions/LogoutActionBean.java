package com.limonn.actions;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import com.limonn.settings.LimonnActionBean;


@UrlBinding("/Logout.action")
public class LogoutActionBean extends LimonnActionBean{
	
	@DefaultHandler
	public Resolution logoutZaraza(){
        this.logout();
		
        return new RedirectResolution("/index.jsp");
	}

}
