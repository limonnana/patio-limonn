package com.limonn.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;  
import org.hibernate.annotations.CollectionOfElements;





@Entity
public class Especialidad { 
	
	
	private long id, posicion;
	private String nombre;
	
	
	private List<Plato> platoList = new ArrayList<Plato>();
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getPosicion() {
		return posicion;
	}
	public void setPosicion(long posicion) {
		this.posicion = posicion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Plato> getPlatoList() {
		
		List<Plato> listaPlatos = new ArrayList<Plato>();
		
		// Filtro y saco los platos borrados
		for(Plato p : this.platoList)
		{
			if(p.getStatus()!= -1)
			{
				listaPlatos.add(p);
			}
		}
		return listaPlatos;
	}
	public void setPlatoList(List<Plato> platoList) {
		this.platoList = platoList;
	}

}
