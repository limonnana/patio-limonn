package com.limonn.entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	
	
	long id;
	List<PlatoCompuesto> platoCompuestoList = new ArrayList<PlatoCompuesto>();
	Double total;
	int totalPlatos;
	User user;
	Date date;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@OneToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	public int getTotalPlatos() {
		return totalPlatos;
	}
	public void setTotalPlatos(int totalPlatos) {
		this.totalPlatos = totalPlatos;
	}
	
	@OneToMany(  fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL )
    @org.hibernate.annotations.Cascade({
            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN
            }
    )
	public List<PlatoCompuesto> getPlatoCompuestoList() {
		return platoCompuestoList;
	}
	public void setPlatoCompuestoList(List<PlatoCompuesto> platoCompuestoList) {
		this.platoCompuestoList = platoCompuestoList;
	}
	
	
	
	
    
}
