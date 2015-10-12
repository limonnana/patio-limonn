package com.limonn.actions;

import com.limonn.entities.Plato;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.settings.LimonnActionBean;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/DeletePlato.action")
public class DeletePlatoActionBean extends LimonnActionBean{
	
	private Plato plato;

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	
	@DefaultHandler
	public Resolution deletePlato()
	{
	    if(this.getUserLimonnAdmin().getRole().equals("Admin"))
		GastronomiaManager.deletePlatoById(this.plato.getId());
			return new RedirectResolution("/Platos.jsp");
	}


}
