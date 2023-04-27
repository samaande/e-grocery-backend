
package com.onlinegrocery.dto;
 
import java.time.LocalDate;
import java.util.Date;
 
import com.onlinegrocery.enums.PaymentType;
import com.onlinegrocery.enums.Status;
 
 
public class OrderDto {
    private Long orderId;
    
    private LocalDate orderDate;
    private Status status;
    private long addressId;
    private int userId;
    private long paymentId;
   private int deliveryId;
	public int getDeliveryId() {
	return deliveryId;
}
public void setDeliveryId(int deliveryId) {
	this.deliveryId = deliveryId;
}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	
	
	public OrderDto(Long orderId, LocalDate orderDate, Status status, long addressId, int userId, long paymentId,
			int deliveryId) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.status = status;
		this.addressId = addressId;
		this.userId = userId;
		this.paymentId = paymentId;
		this.deliveryId = deliveryId;
	}
	@Override
	public String toString() {
		return "OrderDto [orderId=" + orderId + ", orderDate=" + orderDate + ", status=" + status + ", addressId="
				+ addressId + ", userId=" + userId + ", paymentId=" + paymentId + ", userName=]";
	}
	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}