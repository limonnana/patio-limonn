package com.limonn.entities;

import java.util.ArrayList;
import java.util.List;

public class EnvioFormatedDay {
	
	private long id;
	private List<Pedido> listaPedidos = new ArrayList<Pedido>();
	private User deliveryGuy;
	private Double total;
	private String date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}
	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	public User getDeliveryGuy() {
		return deliveryGuy;
	}
	public void setDeliveryGuy(User deliveryGuy) {
		this.deliveryGuy = deliveryGuy;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
