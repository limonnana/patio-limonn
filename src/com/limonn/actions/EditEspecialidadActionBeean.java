package com.limonn.actions;

import com.limonn.entities.Especialidad;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.settings.LimonnActionBean;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/EditEspecialidad.action")
public class EditEspecialidadActionBeean extends LimonnActionBean{
	
	private Especialidad especialidad;

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	    @DontValidate
	    public Resolution preEdit() {
		 this.especialidad = GastronomiaManager.getEspecialidadById(this.especialidad.getId());
		 
		 return new ForwardResolution("/NuevaEspecialidad.jsp");
	 }

}
