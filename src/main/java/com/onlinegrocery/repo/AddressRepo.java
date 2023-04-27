package com.onlinegrocery.repo;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.onlinegrocery.dto.AddressDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{
 
//    void save(AddressDto add);
////    @Query("select a from Address a where a.user.userid=:userid")
//   // List<Address> findAddressByUserName(String userName);
	
	List<Address> findByUserId(AppUser userId);

	
 
}

