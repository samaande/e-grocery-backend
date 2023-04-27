package com.onlinegrocery.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import com.onlinegrocery.dto.WishlistDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.entity.Wishlist;
import com.onlinegrocery.exceptions.ProductNotFoundException;
import com.onlinegrocery.exceptions.UserNotFoundException;
import com.onlinegrocery.exceptions.WishlistNotFoundException;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.ProductRepo;
import com.onlinegrocery.repo.WishlistRepo;
import com.onlinegrocery.serviceImpl.WishlistServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class WishlistServiceTest {

	@InjectMocks
	private WishlistServiceImpl wishlistService;

	@Mock
	private WishlistRepo wishlistRepo;

	@Mock
	private AppUserRepo userRepo;

	@Mock
	private ProductRepo productRepo;

	@Test // AddToWishlist
	public void testAddWishlistSuccess() throws ProductNotFoundException, UserNotFoundException {
		WishlistDto wishlistDto = new WishlistDto();
		wishlistDto.setProductId(1);
		wishlistDto.setProductPrice(10.0);
		wishlistDto.setUserName("testuser");
		wishlistDto.setUserid(1);

		Product product = new Product();
		product.setProductId(1);
		product.setProductName("Test Product");

		AppUser user = new AppUser();
		user.setUserid(1);
		user.setUserName("testuser");

		when(productRepo.findById(wishlistDto.getProductId())).thenReturn(Optional.of(product));
		when(userRepo.findById(wishlistDto.getUserid())).thenReturn(Optional.of(user));
		when(wishlistRepo.save(any(Wishlist.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

		Wishlist result = wishlistService.addWishlist(wishlistDto);

		assertNotNull(result);
		assertEquals(0, result.getWishlistId());
		assertEquals("testuser", result.getUserName());
		assertEquals(1, result.getUser().getUserid());
		assertEquals(1, result.getProducts().size());
		assertEquals(product, result.getProducts().get(0));
		assertEquals(10.0, result.getProductPrice());
	}

	@Test(expected = ProductNotFoundException.class)
	public void testAddWishlistProductNotFound() throws ProductNotFoundException, UserNotFoundException {
		WishlistDto wishlistDto = new WishlistDto();
		wishlistDto.setProductId(1);
		wishlistDto.setProductPrice(10.0);
		wishlistDto.setUserName("testuser");
		wishlistDto.setUserid(1);

		when(productRepo.findById(wishlistDto.getProductId())).thenReturn(Optional.empty());

		wishlistService.addWishlist(wishlistDto);
	}

	@Test(expected = UserNotFoundException.class)
	public void testAddWishlistUserNotFound() throws ProductNotFoundException, UserNotFoundException {

		WishlistDto wishlistDto = new WishlistDto();
		wishlistDto.setProductId(1);
		wishlistDto.setProductPrice(10.0);
		wishlistDto.setUserName("testuser");
		wishlistDto.setUserid(1);

		Product product = new Product();
		product.setProductId(1);
		product.setProductName("Test Product");

		when(productRepo.findById(wishlistDto.getProductId())).thenReturn(Optional.of(product));
		when(userRepo.findById(wishlistDto.getUserid())).thenReturn(Optional.empty());

		wishlistService.addWishlist(wishlistDto);
	}

	@Test // getWishlistById
	public void testGetWishlistByIdSuccess() {

		int wishlistId = 1;
		Wishlist wishlist = new Wishlist();
		wishlist.setWishlistId(wishlistId);
		wishlist.setUserName("Test Wishlist");

		when(wishlistRepo.findById(wishlistId)).thenReturn(Optional.of(wishlist));
		Wishlist result = wishlistService.getWishlistById(wishlistId);

		assertNotNull(result);
		assertEquals(wishlistId, result.getWishlistId());
		assertEquals("Test Wishlist", result.getUserName());
	}

	@Test(expected = WishlistNotFoundException.class)
	public void testGetWishlistByIdNotFound() {
		int wishlistId = 1;

		when(wishlistRepo.findById(wishlistId)).thenReturn(Optional.empty());
		wishlistService.getWishlistById(wishlistId);
	}

	@Test // DeleteWishlistById
	public void testDeleteWishlistSuccess() {
		int wishlistId = 1;
		String result = wishlistService.deleteWishlist(wishlistId);
		assertEquals("Wishlist deleted successfully", result);
		verify(wishlistRepo, times(1)).deleteById(wishlistId);
	}

	@Test(expected = WishlistNotFoundException.class)
	public void testDeleteWishlistNotFound() {
		int wishlistId = 1;
		doThrow(new EmptyResultDataAccessException(1)).when(wishlistRepo).deleteById(wishlistId);
		wishlistService.deleteWishlist(wishlistId);
	}

	@Test(expected = WishlistNotFoundException.class)
	public void testDeleteWishlistUnexpectedException() {
		int wishlistId = 1;
		doThrow(new WishlistNotFoundException("Database connection error")).when(wishlistRepo).deleteById(wishlistId);
		wishlistService.deleteWishlist(wishlistId);
	}

	@Test // getAllWishlist
	public void testGetAllWishlistSuccess() {

		String username = "testUser";
		AppUser user = new AppUser();
		user.setUserName(username);
		Wishlist wishlist1 = new Wishlist();
		wishlist1.setWishlistId(1);
		wishlist1.setUser(user);
		Wishlist wishlist2 = new Wishlist();
		wishlist2.setWishlistId(2);
		wishlist2.setUser(user);
		List<Wishlist> wishlist = Arrays.asList(wishlist1, wishlist2);

		when(userRepo.findByUserName(username)).thenReturn(user);
		when(wishlistRepo.findAll()).thenReturn(wishlist);

		List<Wishlist> result = wishlistService.getAllWishlist(username);

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(wishlist1, result.get(0));
		assertEquals(wishlist2, result.get(1));
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetAllWishlistNotFound() {

		String username = "testUser";
		when(userRepo.findByUserName(username)).thenReturn(null);

		wishlistService.getAllWishlist(username);
	}
}
