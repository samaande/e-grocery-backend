package com.onlinegrocery.service;

import java.util.List;
import javax.validation.Valid;

import com.onlinegrocery.dto.PaymentDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Payment;



public interface PaymentService {

	PaymentDto payBill(@Valid PaymentDto paymentDTO);
	List<Payment> viewBillById(AppUser userId);
	Payment getPaymentById(long paymentId);
}
