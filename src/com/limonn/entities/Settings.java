package com.limonn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Settings {
	
	String storeName, storeAddress, storePhone, rutaDominio;
	long id;
	Integer minutesToAddCurrentTime, minutosTardaDelivery;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
	public String getRutaDominio() {
		return rutaDominio;
	}

	public void setRutaDominio(String rutaDominio) {
		this.rutaDominio = rutaDominio;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public Integer getMinutesToAddCurrentTime() {
		return minutesToAddCurrentTime;
	}

	public void setMinutesToAddCurrentTime(Integer minutesToAddCurrentTime) {
		this.minutesToAddCurrentTime = minutesToAddCurrentTime;
	}

	public Integer getMinutosTardaDelivery() {
		return minutosTardaDelivery;
	}

	public void setMinutosTardaDelivery(Integer minutosTardaDelivery) {
		this.minutosTardaDelivery = minutosTardaDelivery;
	}

	

}
