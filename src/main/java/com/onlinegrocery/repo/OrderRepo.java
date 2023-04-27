package com.onlinegrocery.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinegrocery.dto.OrderDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.enums.Status;




@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

	List<Order> findByStatus(Status status);

	

	List<Order> findByUserId(AppUser userId);


}