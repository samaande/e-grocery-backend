package com.onlinegrocery.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onlinegrocery.dto.OrderDto;
import com.onlinegrocery.dto.StatusDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.enums.Status;
import com.onlinegrocery.exceptions.AddressNotFoundException;
import com.onlinegrocery.exceptions.OrderNotFoundException;
import com.onlinegrocery.service.AddressService;
import com.onlinegrocery.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private OrderService orderService;

	@Mock
	private AddressService addressService;

	@InjectMocks
	private OrderController orderController;
	
	@Before
	public void init() {
	MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetOrderByUserId() throws AddressNotFoundException {
	// Setup
	AppUser user = new AppUser();
	List<Order> orders = new ArrayList<>();
	when(orderService.getOrderByUserId(user)).thenReturn(orders);
	// Execute
	ResponseEntity<List<Order>> response = orderController.getOrderByUserId(user);
	// Verify
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(orders, response.getBody());
	}
	
	@Test
	public void testUpdateOrderStatus() throws OrderNotFoundException {
	// Setup
	long orderId = 1L;
	StatusDto statusDto = new StatusDto(Status.SHIPPED);
	OrderDto updatedOrderDto = new OrderDto();
	when(orderService.updateStatus(orderId, statusDto.getStatus())).thenReturn(updatedOrderDto);
	// Execute
	ResponseEntity<OrderDto> response = orderController.updateOrderStatus(orderId, statusDto);
	// Verify
	assertEquals(HttpStatus.OK, response.getStatusCode());
	assertEquals(updatedOrderDto, response.getBody());
	}
	
	@Test
	public void testCancelOrder() throws OrderNotFoundException {
	// Setup
	long orderId = 1L;
	when(orderService.cancelOrder(orderId)).thenReturn("Order cancelled successfully");
	// Execute
	String response = orderController.cancelOrder(orderId);
	// Verify
	assertEquals("Order cancelled successfully", response);
	}
	
	@Test
	public void testViewOrder() {
	// Setup
	List<Order> orders = new ArrayList<>();
	when(orderService.viewOrder()).thenReturn(orders);
	// Execute
	List<Order> response = orderController.viewOrder();
	// Verify
	assertEquals(orders, response);
	}
	
	@Test
	public void testCreateOrder() {
	// Setup
	OrderDto orderDto = new OrderDto();
	when(orderService.createOrder(orderDto)).thenReturn("Order created successfully");
	// Execute
	String response = orderController.createOrder(orderDto);
	// Verify
	assertEquals("Order created successfully", response);
	}
	
	@Test
	public void testViewOrderByStatus() throws OrderNotFoundException {
	// Setup
	int status = 1;
	Status s = Status.SHIPPED;
	List<Order> orders = new ArrayList<>();
	when(orderService.vieworderbyStatus(s)).thenReturn(orders);
	// Execute
	List<Order> response = orderController.vieworderbyStatus(status);
	// Verify
	assertEquals(orders, response);
	verify(orderService, times(1)).vieworderbyStatus(s);
	}
}
