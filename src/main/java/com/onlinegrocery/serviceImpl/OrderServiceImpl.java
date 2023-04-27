
package com.onlinegrocery.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.onlinegrocery.dto.OrderDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Delivery;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.entity.Payment;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Status;
import com.onlinegrocery.exceptions.OrderNotFoundException;
import com.onlinegrocery.repo.AddressRepo;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.DeliveryRepo;
import com.onlinegrocery.repo.OrderRepo;
import com.onlinegrocery.repo.PaymentRepo;
import com.onlinegrocery.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private AppUserRepo appUserRepo;

	@Autowired
	private PaymentRepo paymentRepo;
@Autowired
private DeliveryRepo deliveryRepo;
	@Override
    public OrderDto updateStatus(long orderId, Status status) throws OrderNotFoundException {
        // Find the order by orderId
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        // Update the status of the order
        order.setStatus(status);
        order = orderRepo.save(order);

        // Convert the updated order to OrderDto
        OrderDto orderDto = convertToOrderDto(order);

        return orderDto;
    }

    // Helper method to convert Order entity to OrderDto
    private OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setStatus(order.getStatus());
        orderDto.setUserId(order.getUserId().getUserid());
        orderDto.setPaymentId(order.getPayment().getPaymentId());
        orderDto.setAddressId(order.getAddress().getAddressId());
        orderDto.setDeliveryId(order.getDelivery().getDeliveryId());
        return orderDto;
    }

	@Override
	public String cancelOrder(Long orderId) throws OrderNotFoundException {
		if (orderRepo.findById(orderId).isPresent()) {
			orderRepo.deleteById(orderId);
			return "Order Cancel";
		}
		throw new OrderNotFoundException("Order does not exist!");
	}

	@Override
	public List<Order> viewOrder() {
		List<Order> find = orderRepo.findAll();
		return find;
	}

	@Override
	public List<Order> getOrderByUserId(AppUser userId) {
		// TODO Auto-generated method stub
		List<Order> findByUserUserName = orderRepo.findByUserId(userId);
		return findByUserUserName;
	}

	@Override
	public String createOrder(OrderDto order) {
	    Order orderEntity = new Order();
	    orderEntity.setOrderId(order.getOrderId());
	    orderEntity.setOrderDate(order.getOrderDate());
	    orderEntity.setStatus(order.getStatus());

	    // Set User
	    AppUser user = appUserRepo.findById(order.getUserId()).orElseThrow();
	    orderEntity.setUserId(user);

	    // Set Payment
	    Payment payment = paymentRepo.findById(order.getPaymentId()).orElseThrow();
	    orderEntity.setPayment(payment);

	    // Set Address
	    Address address = addressRepo.findById(order.getAddressId()).orElseThrow();
	    orderEntity.setAddress(address);
	    
	    Delivery delivery = deliveryRepo.findById(order.getDeliveryId()).orElseThrow();
	    orderEntity.setDelivery(delivery);
	    orderRepo.save(orderEntity);
	    return "Order Added Successfully";
	}

	@Override
	public List<Order> vieworderbyStatus(Status status) throws OrderNotFoundException {
		List<Order> orderEntities = orderRepo.findByStatus(status);
		List<OrderDto> orderdtos = new ArrayList<>();
		for (Order orderEntity : orderEntities) {
			 OrderDto orderDto = new OrderDto();
			    orderDto.setOrderId(orderEntity.getOrderId());
			    orderDto.setOrderDate(orderEntity.getOrderDate());
			    orderDto.setStatus(orderEntity.getStatus());
			    orderDto.setUserId(orderEntity.getUserId().getUserid());
			    orderDto.setPaymentId(orderEntity.getPayment().getPaymentId());
			    orderDto.setAddressId(orderEntity.getAddress().getAddressId());
			    orderDto.setDeliveryId(orderEntity.getDelivery().getDeliveryId());
			    orderdtos.add(orderDto);
		}
		return orderEntities;
	}

}
