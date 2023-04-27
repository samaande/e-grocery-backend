
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.onlinegrocery.dto.AddressDto;
import com.onlinegrocery.dto.CartDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Cart;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.exceptions.AddressNotFoundException;
import com.onlinegrocery.service.AddressService;
import com.onlinegrocery.service.CartService;
 
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/address")
public class AddressController {
 
    @Autowired
    private AddressService addressService;
 
    @PostMapping("/addAddress/useAddress")
    public String addAddress(@RequestBody AddressDto address) {
    return addressService.addAddress(address);
    }
    @GetMapping("/getaddress/{userId}")
    public ResponseEntity<List<Address>> getAddressByUserId(@PathVariable AppUser userId) {
        try {
            List<Address> addresses = addressService.getAddressByUserId(userId);
            return ResponseEntity.ok().body(addresses);
        } catch (AddressNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
@DeleteMapping("/delete/{addressId}")
public String deleteAddress(@PathVariable long addressId) {
	return addressService.deleteAddress(addressId);
}

@GetMapping("/{addressId}")
public Address getAddressByAddressId(@PathVariable long addressId) {
	return addressService.getAddressByAddressId(addressId);
}
}

