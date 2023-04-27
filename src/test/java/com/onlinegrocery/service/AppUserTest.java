package com.onlinegrocery.service;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.onlinegrocery.dto.LoginResponseDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.enums.Role;
import com.onlinegrocery.exceptions.AppUserException;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.service.AppUserService;
import com.onlinegrocery.serviceImpl.AppUserServiceImpl;
 
 
@SpringBootTest
public class AppUserTest {
 
@InjectMocks
private AppUserService appUserService = new AppUserServiceImpl();
 
@Mock
private AppUserRepo appUserRepo;
 
@Test
public void testRegister() {
 
AppUser appUser = new AppUser();
appUser.setUserName("testuser");
appUser.setPassword("testpassword");
 
when(appUserRepo.save(any(AppUser.class))).thenReturn(appUser);
AppUser registeredAppUser = appUserService.register(appUser);
 
 
verify(appUserRepo).save(appUser);
assertEquals(appUser, registeredAppUser);
}
@Test
public void testLogin_Success() {
 
AppUser user = new AppUser();
user.setUserName("username");
user.setPassword("password");
user.setRole(Role.ADMIN);
when(appUserRepo.findByuserName("username")).thenReturn(Optional.of(user));
 
 
LoginResponseDto loginResponseDto = appUserService.login("username", "password");
 
 
assertEquals("username", loginResponseDto.getUserName());
assertEquals("Login Successfull", loginResponseDto.getMessage());
assertSame(Role.ADMIN, loginResponseDto.getRole());
 
 
verify(appUserRepo).findByuserName("username");
}
 
@Test
public void testLogin_InvalidCredentials() {
 
when(appUserRepo.findByuserName(anyString())).thenReturn(Optional.empty());
 
LoginResponseDto loginResponseDto = appUserService.login("username", "password");
 
assertEquals("Not Registered", loginResponseDto.getMessage());
 
 
verify(appUserRepo).findByuserName("username");
}
 
@Test
public void testLogin_IncorrectPassword() {
 
AppUser user = new AppUser();
user.setUserName("username");
user.setPassword("password");
when(appUserRepo.findByuserName("username")).thenReturn(Optional.of(user));
 
LoginResponseDto loginResponseDto = appUserService.login("username", "wrongpassword");
 
 
assertEquals("Invalid Username or password", loginResponseDto.getMessage());
 
 
verify(appUserRepo).findByuserName("username");
}
//@Test
//public void testFindUserName_UserFound() {
// 
//AppUser user = new AppUser();
//user.setUserName("username");
//when(appUserRepo.findById("username")).thenReturn(Optional.of(user));
// 
// 
//String result =appUserService.findUserName("username");
// 
// 
//assertEquals("User Already Present", result);
// 
// 
//verify(appUserRepo).findById("username");
//}
// 
//@Test
//public void testFindUserName_UserNotFound() {
// 
//when(appUserRepo.findById(anyString())).thenReturn(Optional.empty());
// 
// 
//String result = appUserService.findUserName("username");
// 
// 
//assertNull(result);
// 
// 
//verify(appUserRepo).findById("username");
//}
@Test
public void testResetPassword_ValidInput() throws AppUserException {
 
AppUser user = new AppUser();
user.setUserName("username");
user.setPassword("oldPassword");
when(appUserRepo.findByuserName("username")).thenReturn(Optional.of(user));
 
 
String result = appUserService.resetPassword("username", "oldPassword", "newPassword");
 
assertEquals("Password reset successfully", result);
 
verify(appUserRepo).findByuserName("username");
verify(appUserRepo).save(user);
}
public void testResetPassword_InvalidUserName() throws AppUserException {
 
String userName = "john";
String password = "oldPassword";
String newPassword = "newPassword";
 
when(appUserRepo.findByuserName(userName)).thenReturn(Optional.empty());
 
appUserService.resetPassword(userName, password, newPassword);
}
public void testResetPassword_InvalidPassword() throws AppUserException {
 
String userName = "john";
String password = "oldPassword";
String newPassword = "newPassword";
 
AppUser user = new AppUser();
user.setUserName(userName);
user.setPassword("wrongPassword");
when(appUserRepo.findByuserName(userName)).thenReturn(Optional.of(user));
 
appUserService.resetPassword(userName, password, newPassword);
}
}
 


