package com.limonn.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Envio {
	
	private long id;
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();
	private User deliveryGuy;
	private Double total;
	private Date date;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}
	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public void setTotal(List<Pedido> listaPedidos) {
		
		this.total = 0.0;
		
		for(Pedido p : listaPedidos)
		{
			this.total = this.total + p.getCart().getTotal();
		}
		
	}
	
	@OneToOne
	public User getDeliveryGuy() {
		return deliveryGuy;
	}
	public void setDeliveryGuy(User deliveryGuy) {
		this.deliveryGuy = deliveryGuy;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
    
	
}
