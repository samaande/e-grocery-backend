package com.onlinegrocery.entity;



 
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
 
import com.onlinegrocery.enums.PaymentType;
 
 
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser userId;

    private PaymentType type;
    private LocalDate date;

    public Payment() {
        super();

    }

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public AppUser getUserId() {
		return userId;
	}

	public void setUserId(AppUser userId) {
		this.userId = userId;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Payment(long paymentId, double amount, AppUser userId, PaymentType type, LocalDate date) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.userId = userId;
		this.type = type;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", userId=" + userId + ", type=" + type
				+ ", date=" + date + "]";
	}

	 
}
 
    

