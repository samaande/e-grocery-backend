package com.onlinegrocery.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinegrocery.dto.DeliveryDto;
import com.onlinegrocery.entity.Delivery;
import com.onlinegrocery.repo.DeliveryRepo;
import com.onlinegrocery.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService{
	
	@Autowired
	private DeliveryRepo repo;
	
	@Override
	public DeliveryDto addDeliverySlots(DeliveryDto deliverydto) {
	Delivery delivery = new Delivery();
	delivery.setDeliveryId(deliverydto.getDeliveryId());
	delivery.setDeliveryDate(deliverydto.getDeliveryDate());
	delivery.setDeliveryTime(deliverydto.getDeliveryTime());
	repo.save(delivery);
	return new DeliveryDto(delivery.getDeliveryId(),delivery.getDeliveryDate(),delivery.getDeliveryTime());
	}


}

