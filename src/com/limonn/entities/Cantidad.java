package com.limonn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cantidad {
	
	@Id
    private long id;
	
	private int number;
	
	private Double toPay;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Double getToPay() {
		return toPay;
	}
	public void setToPay(Double toPay) {
		this.toPay = toPay;
	}
	
	

}
