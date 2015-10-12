package com.limonn.settings;


import java.util.Random;

import com.limonn.entities.User;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;



	
	public abstract class LimonnActionBean implements ActionBean {
		
		private ActionBeanContext context;
     
		public ActionBeanContext getContext()
		{
			return context;
		}

		public void setContext(ActionBeanContext context)
		{
			this.context = context;
		}
		
		
//	    public String getSessionLimonn() {
//	        return (String) getContext().getRequest().getSession().getAttribute("sessionLimonn");
//	    }
//
//	   
//	    public void setSessionLimonn() {
//	    	//getContext().getRequest().getSession().setMaxInactiveInterval(2147483647);
//	    	Random generator = new Random();
//	    	int r = generator.nextInt();
//	    	getContext().getRequest().getSession().setAttribute("sessionLimonn", r);
//	        
//	    }
		
		
		/** Gets the currently logged in user, or null if no-one is logged in. */
	    public User getUserLimonnAdmin() {
	        return (User) getContext().getRequest().getSession().getAttribute("userLimonnAdmin");
	    }

	    /** Sets the currently logged in user. */
	    public void setUserLimonnAdmin(User currentUser) {
	    	getContext().getRequest().getSession().setMaxInactiveInterval(2147483647);
	    	getContext().getRequest().getSession().setAttribute("userLimonnAdmin", currentUser);
	        
	    }

	    /** Logs the user out by invalidating the session. */
	    public void logout() {
	    	getContext().getRequest().getSession().invalidate();
	    }

		
	}	    
	    