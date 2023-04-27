package com.onlinegrocery.repo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinegrocery.entity.Delivery;



@Repository
@ComponentScan
public interface DeliveryRepo extends JpaRepository<Delivery, Integer> {

}
