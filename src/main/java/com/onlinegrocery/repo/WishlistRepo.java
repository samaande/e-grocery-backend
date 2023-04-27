package com.onlinegrocery.repo;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Wishlist;
 
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
    Wishlist findByUserName(AppUser user);
 
    List<Wishlist> findByUserUserName(String username);
 
    List<Wishlist> findAllByUser(AppUser user);
 
}

