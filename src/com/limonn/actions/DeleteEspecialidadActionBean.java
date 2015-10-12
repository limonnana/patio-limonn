package com.limonn.actions;

import com.limonn.entities.Especialidad;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.settings.LimonnActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/DeleteEspecialidad.action")
public class DeleteEspecialidadActionBean extends LimonnActionBean{
	
	private Especialidad especialidad;

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	@DefaultHandler
	public Resolution deleteEspecialidad()
	{
	    if(this.getUserLimonnAdmin().getRole().equals("Admin"))
		GastronomiaManager.deleteEspecialidadById(this.especialidad.getId());
			return new RedirectResolution("/Especialidades.jsp");
	}

}
