
package com.onlinegrocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.onlinegrocery.dto.CartDto;
import com.onlinegrocery.entity.Cart;
import com.onlinegrocery.service.AppUserService;
import com.onlinegrocery.service.CartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private AppUserService appUserService;

	@PostMapping("/addToCart")
	public ResponseEntity<Cart> addItemToCart(@RequestBody CartDto cartDTO) {
		return new ResponseEntity<Cart>(cartService.addItemToCart(cartDTO), HttpStatus.OK);
	}

	@PutMapping("/updateCart/{cartId}")
	ResponseEntity<Cart> updateCart(@PathVariable("cartId") int cartId, @RequestBody CartDto cartDTO) {
		return new ResponseEntity<Cart>(cartService.updateCart(cartId, cartDTO), HttpStatus.OK);
	}

	@DeleteMapping("/deleteCart/{cartId}")
	public ResponseEntity<String> deleteItemFromCart(@PathVariable("cartId") int cartId) {
		cartService.deleteItemFromCart(cartId);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}

	@GetMapping("/getCartById")
	public ResponseEntity<Cart> getCartById(@RequestParam int cartId) {
		return new ResponseEntity<Cart>(cartService.getCartById(cartId), HttpStatus.OK);
	}

	@GetMapping("/getAllCart/{username}")
	public List<Cart> getAllCartItems(@PathVariable String username) {
		return cartService.getAllCartItems(username);
	}

}
