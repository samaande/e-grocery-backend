package com.onlinegrocery.service;

import com.onlinegrocery.dto.LoginResponseDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.exceptions.AppUserException;

public interface AppUserService{
	LoginResponseDto login( String userName,String password);
	AppUser register(AppUser appUser);
	String resetPassword(String userName, String password,  String newPassword) throws AppUserException;
	
}

