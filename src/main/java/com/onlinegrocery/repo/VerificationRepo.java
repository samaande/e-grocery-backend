package com.onlinegrocery.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinegrocery.entity.Verification;


@Repository
public interface VerificationRepo extends JpaRepository<Verification,String> {

	

}