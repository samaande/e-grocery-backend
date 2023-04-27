
package com.onlinegrocery.service;
import java.util.List;
import java.util.Optional;
import com.onlinegrocery.dto.CartDto;
import com.onlinegrocery.dto.WishlistDto;
import com.onlinegrocery.entity.Cart;
import com.onlinegrocery.entity.Wishlist;
public interface WishlistService {
public String deleteWishlist(int wishlistId);
public Wishlist getWishlistById(int wishlistId);
    public Wishlist addWishlist(WishlistDto wishlistDto);
 
    public List<Wishlist> getAllWishlist(String username);
}

