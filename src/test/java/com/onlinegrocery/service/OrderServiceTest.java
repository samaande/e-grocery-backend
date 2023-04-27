package com.onlinegrocery.service;

 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.onlinegrocery.dto.OrderDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.entity.Payment;
import com.onlinegrocery.enums.PaymentType;
import com.onlinegrocery.enums.Status;
import com.onlinegrocery.exceptions.OrderNotFoundException;
import com.onlinegrocery.repo.AddressRepo;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.OrderRepo;
import com.onlinegrocery.repo.PaymentRepo;
import com.onlinegrocery.serviceImpl.OrderServiceImpl;
 
@SpringBootTest
public class OrderServiceTest {

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;
    OrderServiceImpl impl = new OrderServiceImpl();

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private AppUserRepo appUserRepository;
 
    @Mock
    private PaymentRepo paymentRepository;
 
    @Mock
    private AddressRepo addressRepository;

    //View All Orders
    @Test
    public void viewOrderTest() {
        List<Order> requests = orderServiceImpl.viewOrder();
        assertNotNull(requests);
    }

    //Cancel Order
    @Test
      void cancelOrder_orderDoesNotExist_throwsOrderNotFoundException() {
        // Arrange
        Long orderId = 100L;
        // Act & Assert
        Assertions.assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.cancelOrder(orderId));
      }

    //Get Order By Id
    @Test
    public void GetOrderByidTest() {
        AppUser user = new AppUser();
        user.setUserid(123);
        Order order1 = new Order();
        order1.setOrderId((long) 1);
        order1.setUserId(user);
        Order order2 = new Order();
        order2.setOrderId((long) 2);
        order2.setUserId(user);
        List<Order> expectedOrders = Arrays.asList(order1, order2);
        when(orderRepo.findByUserId(user)).thenReturn(expectedOrders);
        List<Order> actualOrders = orderServiceImpl.getOrderByUserId(user);
        assertEquals(expectedOrders, actualOrders);
        verify(orderRepo).findByUserId(user);
    }

    //Create New Order
//    @Test
//    public void createOrderTest() {
//        OrderDto dto = new OrderDto();
//        dto.setUserId(1);
//        dto.setPaymentId(2);
//        dto.setAddressId(3);
//
//        AppUser appUser = new AppUser();
//        appUser.setUserid(1);
//
//        Payment payment = new Payment();
//        payment.setPaymentId(2);
//
//        Address address = new Address();
//        address.setAddressId(3);
//
//        when(appUserRepository.findById(anyInt())).thenReturn(Optional.of(appUser));
//        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(payment));
//        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(address));
//
//        String result = orderServiceImpl.createOrder(dto);
//
//        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
//        verify(orderRepo, times(1)).save(orderCaptor.capture());
//        Order savedOrder = orderCaptor.getValue();
//
//        assertEquals(dto.getUserId(), savedOrder.getUserId().getUserid());
//        assertEquals(dto.getPaymentId(), savedOrder.getPayment().getPaymentId());
//        assertEquals(dto.getAddressId(), savedOrder.getAddress().getAddressId());
//        assertEquals("Order Added Successfully", result);
//    }

}






//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.onlinegrocery.dto.OrderDto;
//import com.onlinegrocery.entity.Address;
//import com.onlinegrocery.entity.AppUser;
//import com.onlinegrocery.entity.Delivery;
//import com.onlinegrocery.entity.Order;
//import com.onlinegrocery.entity.Payment;
//import com.onlinegrocery.enums.PaymentType;
//import com.onlinegrocery.enums.Role;
//import com.onlinegrocery.enums.Status;
//import com.onlinegrocery.enums.TimeSlot;
//import com.onlinegrocery.exceptions.OrderNotFoundException;
//import com.onlinegrocery.repo.OrderRepo;
//import com.onlinegrocery.serviceImpl.OrderServiceImpl;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.Optional;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderTest {
//
//    @Mock
//    private OrderRepo orderRepository;
//
//    @InjectMocks
//    private OrderServiceImpl orderService;
//
//    @Test
//    public void testUpdateStatus_Success() throws OrderNotFoundException {
//        // create a sample order
//        AppUser user = new AppUser(1, "testuser", "testpassword",0L,Role.ADMIN);
//        Address address = new Address(1L, user,1L, "test street", "test city", null, "test state", 123456L);
//        Delivery delivery = new Delivery(1,new Date(), TimeSlot.AFTERNOON);
//        Payment payment = new Payment(1L, 9.99,user, PaymentType.CARD, LocalDate.now());
//        Order order = new Order(1L,user, LocalDate.now(),Status.ONTHEWAY,payment, address, delivery);
//
//        // mock repository behavior
//        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
//        when(orderRepository.save(order)).thenReturn(order);
//
//        // call service method
//        OrderDto result = orderService.updateStatus(1L, Status.DELIVERED);
//
//        // verify result
//        Assertions.assertEquals(Status.DELIVERED, result.getStatus());
//
//        // verify repository calls
//        verify(orderRepository, times(1)).findById(1L);
//        verify(orderRepository, times(1)).save(order);
//    }
//
//    @Test
//    public void testUpdateStatus_OrderNotFound() {
//        // mock repository behavior
//        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // call service method and assert exception is thrown
//        Assertions.assertThrows(OrderNotFoundException.class, () -> {
//            orderService.updateStatus(1L, Status.DELIVERED);
//        });
//
//        // verify repository calls
//        verify(orderRepository, times(1)).findById(1L);
//        verify(orderRepository, times(0)).save(any());
//    }
//    @Test
//    public void createOrderTest() {
//        OrderDto dto = new OrderDto();
//        dto.setUserId(1);
//        dto.setPaymentId(2);
//        dto.setAddressId(3);
//
//        AppUser appUser = new AppUser();
//        appUser.setUserid(1);
//
//        Payment payment = new Payment();
//        payment.setPaymentId(2);
//
//        Address address = new Address();
//        address.setAddressId(3);
//
//        when(appUserRepo.findById(anyInt())).thenReturn(Optional.of(appUser));
//        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(payment));
//        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(address));
//
//        String result = orderServiceImpl.createOrder(dto);
//
//        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
//        verify(orderRepo, times(1)).save(orderCaptor.capture());
//        Order savedOrder = orderCaptor.getValue();
//
//        assertEquals(dto.getUserId(), savedOrder.getUserId().getUserid());
//        assertEquals(dto.getPaymentId(), savedOrder.getPayment().getPaymentId());
//        assertEquals(dto.getAddressId(), savedOrder.getAddress().getAddressId());
//        assertEquals("Order Added Successfully", result);
//    }
//
//
//    @Test
//    public void testCancelOrderWithNonExistingOrderId() {
//        // Try to cancel an order that does not exist
//        assertThrows(OrderNotFoundException.class, () -> orderService.cancelOrder(123456L));
//    }
//
//}
//
