package com.onlinegrocery.serviceImpl;

import java.util.Optional;
import java.util.Random;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.onlinegrocery.dto.LoginResponseDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Verification;
import com.onlinegrocery.exceptions.AppUserException;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.VerificationRepo;
import com.onlinegrocery.service.AppUserService;


@Service
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	private AppUserRepo appUserRepo;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VerificationRepo verificationrepo;

	@Override
	public AppUser register(AppUser appUser) {
		this.appUserRepo.save(appUser);
		return appUser;
	}




	@Override
	public LoginResponseDto login(String userName, String password) {
		Optional<AppUser> login = appUserRepo.findByuserName(userName);
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		if(login.isPresent() ) {
			AppUser user = login.get();
			if(login.get().getPassword().equals(password)) {
				loginResponseDto.setUserName(userName);
				loginResponseDto.setUserid(user.getUserid());
				loginResponseDto.setMessage("Login Successfull");
				loginResponseDto.setRole(user.getRole());
				loginResponseDto.setMobileNumber(user.getMobileNumber());
			}
			else {
				loginResponseDto.setMessage("Invalid Username or password");
			}
			
		}
		else {
			loginResponseDto.setMessage("Not Registered");
		}
		return loginResponseDto;
	}

	
	@Override
	public String resetPassword(String userName, String password, String newPassword) throws AppUserException {
	Optional<AppUser> loginObj= appUserRepo.findByuserName(userName);
	String s="";
	if(loginObj.isPresent()) {
	if( loginObj.get().getPassword().equals(password)) {
	//set the password with new Password and save it
	AppUser user = loginObj.get();
	user.setPassword(newPassword);
	appUserRepo.save(user);
	return "Password reset successfully";
	}else {
	s="enter valid password";
	}
	}else {
	s="enter valid userName";
	}
	throw new AppUserException(s);
	}
	
	



	




	
	
}
