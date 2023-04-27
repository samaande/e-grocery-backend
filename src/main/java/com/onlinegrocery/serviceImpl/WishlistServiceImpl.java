package com.onlinegrocery.serviceImpl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.onlinegrocery.service.WishlistService;
 
@Service
public class WishlistServiceImpl implements WishlistService {
 
    @Autowired
    private WishlistRepo wishlistRepo;
 
    @Autowired
    private ProductRepo productRepo;
 
    @Autowired
    private AppUserRepo userRepo;
 
    @Override
    public Wishlist addWishlist(WishlistDto wishlistDto) throws ProductNotFoundException, UserNotFoundException {
        Optional<Product> optionalProduct = productRepo.findById(wishlistDto.getProductId());
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException();
        }
        Product product = optionalProduct.get();
        
        Wishlist wishlist = new Wishlist();
        wishlist.setProductPrice(wishlistDto.getProductPrice());
        wishlist.setUserName(wishlistDto.getUserName());
        
        Optional<AppUser> optionalUser = userRepo.findById(wishlistDto.getUserid());
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        AppUser user = optionalUser.get();
        wishlist.setUser(user);
        
        List<Product> listProducts = new ArrayList<>();
        listProducts.add(product);
        wishlist.setProducts(listProducts);
        return wishlistRepo.save(wishlist);
    }
 
    @Override
    public String deleteWishlist(int wishlistId) {
        try {
            wishlistRepo.deleteById(wishlistId);
            return "Wishlist deleted successfully";
        } catch (Exception e) {
            throw new WishlistNotFoundException(e.getMessage());
        }
    }
 
    @Override
    public List<Wishlist> getAllWishlist(String username) {
        AppUser user = userRepo.findByUserName(username);
        List<Wishlist> wishlist = wishlistRepo.findAll().stream().filter(w -> w.getUser().equals(user))
                .collect(Collectors.toList());
        if (user==null) {
            throw new UserNotFoundException(username);
        }
        return wishlist;
    }
 
    @Override
    public Wishlist getWishlistById(int wishlistId) {
        return wishlistRepo.findById(wishlistId).orElseThrow(() -> new WishlistNotFoundException());
    }
 
}

