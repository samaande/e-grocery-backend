package com.onlinegrocery.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.onlinegrocery.enums.TimeSlot;



@Entity
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  deliveryId;
	private Date deliveryDate;
	private TimeSlot deliveryTime;
	
	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Delivery(int deliveryId, Date deliveryDate, TimeSlot deliveryTime) {
		super();
		this.deliveryId = deliveryId;
		this.deliveryDate = deliveryDate;
		this.deliveryTime = deliveryTime;
	}
	
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public TimeSlot getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(TimeSlot deliveryTime) {
		this.deliveryTime = deliveryTime;
	}


	@Override
	public String toString() {
		return "Delivery [deliveryId=" + deliveryId + ", deliveryDate=" + deliveryDate + ", deliveryTime="
				+ deliveryTime + "]";
	}
	
	
	

}
