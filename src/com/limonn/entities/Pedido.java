package com.limonn.entities;


import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pedido {
	
	
	private long id;
	private Cart cart;
	private Date date;
	private String deliveryComment, kitchenComment;
	private boolean listo, enviado;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	@OneToOne( optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @org.hibernate.annotations.Cascade({
            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN
            })
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDeliveryComment() {
		return deliveryComment;
	}
	public void setDeliveryComment(String deliveryComment) {
		this.deliveryComment = deliveryComment;
	}
	public String getKitchenComment() {
		return kitchenComment;
	}
	public void setKitchenComment(String kitchenComment) {
		this.kitchenComment = kitchenComment;
	}
	public boolean isListo() {
		return listo;
	}
	public void setListo(boolean listo) {
		this.listo = listo;
	}
	public boolean isEnviado() {
		return enviado;
	}
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}
	

}
