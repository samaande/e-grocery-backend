
package com.onlinegrocery.service;
 
import java.util.List;
 
import com.onlinegrocery.dto.AddressDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.exceptions.AddressNotFoundException;
 

public interface AddressService {
    String addAddress(AddressDto address);
    //public List<Order> getOrderByUsername(String username);
    //public List<AddressDto> getAddressByUserName(String user);
    public List<Address> getAddressByUserId(AppUser userId) throws AddressNotFoundException;
    String deleteAddress(long addressId);
    Address getAddressByAddressId(long addressId);
}

