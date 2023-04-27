package com.onlinegrocery.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinegrocery.dto.PaymentDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.entity.Payment;
import com.onlinegrocery.exceptions.AddressNotFoundException;
import com.onlinegrocery.serviceImpl.PaymentServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentServiceImpl iPaymentService;
	@CrossOrigin
	@PostMapping("/pay")
	public PaymentDto payBill(@Valid @RequestBody  PaymentDto paymentDTO ) {
		return iPaymentService.payBill(paymentDTO);
	}
	
	 @GetMapping("/getbill/{userId}")
	    public ResponseEntity<List<Payment>> getOrderByUserId(@PathVariable AppUser userId) {
	        try {
	            List<Payment> bills = iPaymentService.viewBillById(userId);
	            return ResponseEntity.ok().body(bills);
	        } catch (AddressNotFoundException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	@GetMapping("/{paymentId}")
	public Payment getPaymentById(@PathVariable long paymentId) {
		return iPaymentService.getPaymentById(paymentId);
	}
}








