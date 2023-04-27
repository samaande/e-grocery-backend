
package com.onlinegrocery.controller;
 
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.onlinegrocery.dto.AddressDto;
import com.onlinegrocery.dto.OrderDto;
import com.onlinegrocery.dto.StatusDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.enums.Status;
import com.onlinegrocery.exceptions.AddressNotFoundException;
import com.onlinegrocery.exceptions.OrderNotFoundException;

import com.onlinegrocery.service.AddressService;
import com.onlinegrocery.service.OrderService;
 

 

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderservice;
    @Autowired
    private AddressService addressService;
    @CrossOrigin
    @GetMapping("/getorder/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable AppUser userId) {
        try {
            List<Order> addresses = orderservice.getOrderByUserId(userId);
            return ResponseEntity.ok().body(addresses);
        } catch (AddressNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateStatus/{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable long orderId,
                                                      @RequestBody StatusDto status) throws OrderNotFoundException {
        OrderDto updatedOrderDto = orderservice.updateStatus(orderId, status.getStatus());
        return ResponseEntity.ok(updatedOrderDto);
    }
    //Cancel Order
    @CrossOrigin
    @DeleteMapping("/cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable long orderId) throws OrderNotFoundException {
        String cancelOrder = this.orderservice.cancelOrder(orderId);
        return cancelOrder;
    }
 
    //View Order
    @GetMapping("/viewOrder")
    public List<Order> viewOrder() {
        List<Order> viewOrder = this.orderservice.viewOrder();
    return viewOrder;
}
    @PostMapping("/createOrder")
    public String createOrder(@RequestBody OrderDto orderDto) {
        return orderservice.createOrder(orderDto);

    }

    @GetMapping("/viewOrderByStatus/{status}")
    public List<Order> vieworderbyStatus(@PathVariable int status)throws OrderNotFoundException{
        Status s = Status.PLACED;
        System.out.println(status);
        if(status==0) {
            s=Status.PLACED;
        }
        else if(status==1) {
            s=Status.SHIPPED;
        }
        else if(status==2) {
            s=Status.PICKDUP;
        }
        else if(status==3) {
            s=Status.ONTHEWAY;
        }
        else if(status==4) {
            s=Status.DELIVERED;
        }
    return orderservice.vieworderbyStatus(s);
    }


}
 

