package com.onlinegrocery.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;


@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Integer>{

	AppUser findByUserName(String userName);


	Optional<AppUser> findByuserName(String userName);

}