package com.limonn.actions;


import java.util.List;
import com.limonn.entities.Especialidad;
import com.limonn.entities.Plato;
import com.limonn.entityManager.GastronomiaManager;
import com.limonn.settings.LimonnActionBean;
import com.limonn.settings.Utils;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/EditEspecialidad.action")
public class EditEspecialidadActionBean extends LimonnActionBean{
	
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
		 
		 List<Plato> listaFiltrada =  Utils.removeDeleted(this.especialidad.getPlatoList());
		 
		 this.especialidad.setPlatoList(listaFiltrada);
		 return new ForwardResolution("/NuevaEspecialidad.jsp");
	 }

}
