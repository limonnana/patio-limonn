package com.limonn.actions;

import com.limonn.services.Services;
import com.limonn.settings.LimonnActionBean;
import com.limonn.entities.Settings;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/Settings.action")
public class SettingsActionBean extends LimonnActionBean{
	
	Settings settings;
	
	public Settings getSettings() {
		return settings;
	}

    public void setSettings(Settings settings) {
		this.settings = settings;
	}

    public Resolution getSettingsFromDb()
    {
    	this.settings = Services.getSettings();
 	   return new ForwardResolution("/Settings.jsp");
    }

	@DefaultHandler
    public Resolution guardar()
   {
	   Services.updateSettings(this.settings);
		
	   return new ForwardResolution("/Manager.jsp");
   }
	
	

}
