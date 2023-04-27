package com.onlinegrocery.service;

 
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
 
import com.onlinegrocery.dto.CartDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Cart;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.exceptions.CartNotFoundException;
import com.onlinegrocery.exceptions.ProductNotFoundException;
import com.onlinegrocery.exceptions.UserNotFoundException;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.CartRepo;
import com.onlinegrocery.repo.ProductRepo;
import com.onlinegrocery.serviceImpl.CartServiceImpl;
 
@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
 
    @Mock
    private CartRepo cartRepo;
 
    @Mock
    private ProductRepo productRepo;
 
    @Mock
    private AppUserRepo userRepo;
 
    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void testAddItemToCart() {
        CartDto cartDto = new CartDto();
        cartDto.setProductId(1);
        cartDto.setProductCount(2);
        cartDto.setTotalPrice(20.0);
        cartDto.setUserName("user");
        cartDto.setUserid(1);
 
        AppUser user = new AppUser();
        user.setUserid(1);
        user.setUserName("user");
        user.setPassword("password");
 
        Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
 
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("banana");
        product.setPrice(50.0);
        Mockito.when(productRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(product));
 
        Cart cart = new Cart();
        cart.setCartId(1);
        cart.setProductCount(cartDto.getProductCount());
        cart.setTotalPrice(cartDto.getTotalPrice());
        cart.setUserName(cartDto.getUserName());
        cart.setUser(user);
        cart.setProducts(Collections.singletonList(product));
        Mockito.when(cartRepo.save(Mockito.any(Cart.class))).thenReturn(cart); 
 
        Cart result = cartService.addItemToCart(cartDto);
 
        assertNotNull(result);
        assertEquals(cartDto.getProductCount(), result.getProductCount());
        assertEquals(cartDto.getTotalPrice(), result.getTotalPrice());
        assertEquals(cartDto.getUserName(), result.getUserName());
        assertEquals(user, result.getUser());
        assertEquals(Collections.singletonList(product), result.getProducts());
    }
 
    @Test(expected = ProductNotFoundException.class)
    public void testAddItemToCartProductNotFound() {
        CartDto cartDto = new CartDto();
        cartDto.setProductId(1);
 
        when(productRepo.findById(1)).thenReturn(Optional.empty());
 
        cartService.addItemToCart(cartDto);
    }
 
    @Test(expected = UserNotFoundException.class)
    public void testAddItemToCartUserNotFound() {
        CartDto cartDto = new CartDto();
        cartDto.setProductId(1);
        cartDto.setUserid(2);
 
        when(userRepo.findById(2)).thenReturn(Optional.empty());
        when(productRepo.findById(1)).thenReturn(Optional.of(new Product()));
 
        cartService.addItemToCart(cartDto);
    }


    @Test
    public void testUpdateCart() {
        int cartId = 20;
        int productId = 1;
        CartDto cartDTO = new CartDto();
        cartDTO.setProductId(1);
        cartDTO.setProductCount(2);
        cartDTO.setTotalPrice(50);
 
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Banana");
        product.setPrice(10.00);
 
        Cart cart = new Cart();
        cart.setCartId(20);
        cart.setProductCount(2);
        cart.setTotalPrice(50);
        cart.setProducts(new ArrayList<>());
 
        Mockito.when(productRepo.findById(productId)).thenReturn(Optional.of(product));
        Mockito.when(cartRepo.findById(cartId)).thenReturn(Optional.of(cart));
        Mockito.when(cartRepo.save(Mockito.any(Cart.class))).thenReturn(cart);
 
        Cart result = cartService.updateCart(cartId, cartDTO);
 
        assertNotNull(result);
        assertEquals(cartId, result.getCartId());
        assertEquals(cartDTO.getProductCount(), result.getProductCount());
        assertEquals(cartDTO.getTotalPrice(), result.getTotalPrice());
 
        verify(productRepo, Mockito.atLeastOnce()).findById(productId);
        verify(cartRepo, Mockito.atLeastOnce()).findById(cartId);
        verify(cartRepo, Mockito.atLeastOnce()).save(Mockito.any(Cart.class));
    }
 
    
    @Test
    public void testDeleteItemFromCart() {
        int cartId = 1;
        when(cartRepo.existsById(cartId)).thenReturn(true);
 
        String result = cartService.deleteItemFromCart(cartId);
 
        assertEquals("Deleted successfully", result);
        verify(cartRepo, atLeastOnce()).deleteById(cartId);
    }
 

    @Test
    public void testDeleteItemFromCartWithInvalidId() {
        int cartId = 1;
        when(cartRepo.existsById(cartId)).thenReturn(false);
 
        assertThrows(CartNotFoundException.class, () -> cartService.deleteItemFromCart(cartId));
        verify(cartRepo, never()).deleteById(cartId);
    }
 
   
    @Test
    public void testGetCartById() {
        int cartId = 20;
        Cart cart = new Cart();
        when(cartRepo.findById(cartId)).thenReturn(Optional.of(cart));
 
        Cart result = cartService.getCartById(cartId);
 
        assertNotNull(result);
        assertEquals(cart, result);
    }
 
    @Test
    public void testGetCartByIdWithInvalidId() {
        int cartId = 20;
        when(cartRepo.findById(cartId)).thenReturn(Optional.empty());
 
        assertThrows(CartNotFoundException.class, () -> cartService.getCartById(cartId));
    }
 
    
    @Test
    public void testGetAllCartItems() {
        AppUser user = new AppUser();
        user.setUserName("user");
 
        List<Cart> cartList = new ArrayList<>();
        Cart cart = new Cart();
        cart.setCartId(20);
        cart.setProductCount(2);
        cart.setTotalPrice(45.0);
        cart.setUser(user);
        cartList.add(cart);
 
        Mockito.when(userRepo.findByUserName(Mockito.anyString())).thenReturn(user);
        Mockito.when(cartRepo.findAll()).thenReturn(cartList);
 
        List<Cart> result = cartService.getAllCartItems("user");
 
        assertNotNull(result);
        assertEquals(cartList.size(), result.size());
    }
 
    @Test
    public void testGetAllCartItemsWithInvalidUser() {
        String username = "user";
        when(userRepo.findByUserName(username)).thenReturn(null);
 
        assertThrows(UserNotFoundException.class, () -> cartService.getAllCartItems(username));
        verify(cartRepo, Mockito.never()).findAll();
    }

    @Test
    public void testGetAllCartItemsWithException() {
        String username = "user1";
        when(userRepo.findByUserName(username)).thenThrow(new RuntimeException());
 
        assertThrows(UserNotFoundException.class, () -> cartService.getAllCartItems(username));
        verify(cartRepo, Mockito.never()).findAll();
    }
 
   
}


