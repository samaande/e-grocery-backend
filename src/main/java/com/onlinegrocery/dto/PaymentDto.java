package com.onlinegrocery.dto;



import java.time.LocalDate;
import java.util.Date;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
 
import com.onlinegrocery.enums.PaymentType;
 
 
public class PaymentDto {

    private long paymentId;
    private double amount;
    private PaymentType type;
    private int userId;
    private LocalDate date;


    public PaymentDto() {
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


	public PaymentType getType() {
		return type;
	}


	public void setType(PaymentType type) {
		this.type = type;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public PaymentDto(long paymentId, double amount, PaymentType type, int userId, LocalDate date) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.type = type;
		this.userId = userId;
		this.date = date;
	}


	@Override
	public String toString() {
		return "PaymentDto [paymentId=" + paymentId + ", amount=" + amount + ", type=" + type + ", userId=" + userId
				+ ", date=" + date + "]";
	}
 

  


}
 


