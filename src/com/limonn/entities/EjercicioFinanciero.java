package com.limonn.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import org.hibernate.annotations.IndexColumn;

@Entity
public class EjercicioFinanciero {
	
	long id;
	User user;
	List<Activo> activos = new ArrayList<Activo>();
	List<Pasivo> pasivos = new ArrayList<Pasivo>();
	Date date;
	int status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
	@OneToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@IndexColumn(name="INDEX_COL")
    public List<Activo> getActivos() {
		return activos;
	}

	public void setActivos(List<Activo> activos) {
		this.activos = activos;
	}
    
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	public List<Pasivo> getPasivos() {
		return pasivos;
	}

	public void setPasivos(List<Pasivo> pasivos) {
		this.pasivos = pasivos;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	

}
