
package com.onlinegrocery.entity;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
import com.onlinegrocery.enums.PaymentType;
import com.onlinegrocery.enums.Status;
 

@Entity
@Table(name= "orders")
public class Order { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
     @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private AppUser userId;
    
    private LocalDate orderDate;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
     @ManyToOne
      @JoinColumn(name = "address_id")
      private Address address;
     @OneToOne
     @JoinColumn(name="delivery_id")
     private Delivery delivery;
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", orderDate=" + orderDate + ", status=" + status
				+ ", payment=" + payment + ", address=" + address + "]";
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public AppUser getUserId() {
		return userId;
	}
	public void setUserId(AppUser userId) {
		this.userId = userId;
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
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Order(Long orderId, AppUser userId, LocalDate orderDate, Status status, Payment payment, Address address,
			Delivery delivery) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.status = status;
		this.payment = payment;
		this.address = address;
		this.delivery = delivery;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

}