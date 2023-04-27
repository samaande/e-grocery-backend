package com.onlinegrocery.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.onlinegrocery.enums.TimeSlot;



public class DeliveryDto {
	 @NotNull(message=" deliveryId can not be null")
	private int  deliveryId;
	 @NotNull(message=" deliveryDate can not be null")
	private Date deliveryDate;
	 @NotNull(message=" TimeSlot can not be null")
	private TimeSlot deliveryTime;
	
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
		return "Deliverydto [deliveryId=" + deliveryId + ", deliveryDate=" + deliveryDate + ", deliveryTime="
				+ deliveryTime + "]";
	}
	public DeliveryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeliveryDto(int deliveryId, Date deliveryDate, TimeSlot deliveryTime) {
		super();
		this.deliveryId = deliveryId;
		this.deliveryDate = deliveryDate;
		this.deliveryTime = deliveryTime;
	}

}

