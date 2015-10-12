package com.limonn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class PlatoCompuesto {
	
	long id;
	Plato plato;
	Cantidad cantidad;
	
	
	@OneToOne
	public Plato getPlato() {
		return plato;
	}
	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	
	@OneToOne
	public Cantidad getCantidad() {
		return cantidad;
	}
	public void setCantidad(Cantidad cantidad) {
		this.cantidad = cantidad;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
