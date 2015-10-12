package com.limonn.actions;

import com.limonn.entities.Especialidad;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.settings.LimonnActionBean;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/NuevaEspecialidad.action")
public class NuevaEspecialidadActionBean extends LimonnActionBean{
	
	private Especialidad especialidad;

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	   @DefaultHandler
	    public Resolution crearNuevaEspecialidad()
	   {
		   GastronomiaManager.saveEspecialidad(this.especialidad);
		   
		   return new RedirectResolution("/Especialidades.jsp");
	   }

}
