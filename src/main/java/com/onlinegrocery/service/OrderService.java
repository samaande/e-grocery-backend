
package com.onlinegrocery.service;
 
import java.util.List;
import java.util.Optional;
 
import com.onlinegrocery.dto.OrderDto;
import com.onlinegrocery.dto.StatusDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Status;
import com.onlinegrocery.exceptions.OrderNotFoundException;
 
 
 
public interface OrderService{

    public OrderDto updateStatus(long orderId, Status status )throws OrderNotFoundException;
    public List<Order> vieworderbyStatus(Status status)throws OrderNotFoundException;
    public List<Order> getOrderByUserId(AppUser userId);
    public String cancelOrder(Long orderId) throws OrderNotFoundException;
    public List<Order> viewOrder();
    public String createOrder(OrderDto order);
 

}

