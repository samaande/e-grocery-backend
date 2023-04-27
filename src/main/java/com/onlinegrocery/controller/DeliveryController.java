package com.onlinegrocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinegrocery.dto.DeliveryDto;
import com.onlinegrocery.service.DeliveryService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
@Autowired
private DeliveryService deliveryservice;
	
	@PostMapping("/add")
	public DeliveryDto addDeliverySlots(@RequestBody DeliveryDto deliverydto) {
		return deliveryservice.addDeliverySlots(deliverydto);
	}
	
}
