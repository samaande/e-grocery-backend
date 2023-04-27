
package com.onlinegrocery.repo;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Cart;
 
public interface CartRepo extends JpaRepository<Cart, Integer>{
    Cart findByUserName(AppUser user);
 
    List<Cart> findByUserUserName(String username);
 
    
 
    void deleteByUserName(String userName);
 
 
}

