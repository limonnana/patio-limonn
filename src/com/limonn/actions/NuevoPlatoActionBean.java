package com.limonn.actions;

import com.limonn.entities.Especialidad;
import com.limonn.entities.Plato;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.settings.LimonnActionBean;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/NuevoPlato.action")
public class NuevoPlatoActionBean extends LimonnActionBean{
	
	Plato plato;
	Especialidad especialidad;

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	
	
	 public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	   @DefaultHandler
	    public Resolution crearNuevoPlato()
	   {
		   GastronomiaManager.savePlato(this.plato, this.especialidad);
		   
		      return new RedirectResolution("/Platos.jsp");  
		  
	   }

}
