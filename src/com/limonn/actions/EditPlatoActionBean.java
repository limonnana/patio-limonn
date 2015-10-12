package com.limonn.actions;

import java.util.List;

import com.limonn.entities.Plato;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/EditPlato.action")
public class EditPlatoActionBean extends LimonnActionBean{
	
	Plato plato;

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	
	    @DontValidate
	    public Resolution preEdit() {
		 this.plato = GastronomiaManager.getPlatoById(this.plato.getId());
		 
		 return new ForwardResolution("/NuevoPlato.jsp");
	 }

}
