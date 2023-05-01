
package com.onlinegrocery.serviceImpl;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.onlinegrocery.dto.AddressDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.exceptions.AddressNotFoundException;
import com.onlinegrocery.repo.AddressRepo;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.service.AddressService;
 
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AppUserRepo appUserRepo;
    @Override
    public String addAddress(AddressDto address) {
        Optional<AppUser> optionalUser = appUserRepo.findById(address.getUserId());
        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();
            
            Address add = new Address();
            add.setHousenumber(address.getHousenumber());
            add.setLandmark(address.getLandmark());
            add.setStreet(address.getStreet());
            add.setDistrict(address.getDistrict());
            add.setState(address.getState());
            add.setPin(address.getPin());
            add.setUser(user);
            
            addressRepo.save(add);
            return "Address added successfully!";
        } else {
            return "User not found!";
        }
    }

 
    @Override
    public List<Address> getAddressByUserId(AppUser userId) throws AddressNotFoundException {
        List<Address> addresses = addressRepo.findByUserId(userId);
        if(addresses.isEmpty()) {
            throw new AddressNotFoundException("No addresses found for user with id: " + userId);
        }
        return addresses;
    }

	@Override
	public String deleteAddress(long addressId) {
		 addressRepo.deleteById(addressId);
		 return "Address deleted successfully";
	}

	@Override
	public Address getAddressByAddressId(long addressId) {
		return addressRepo.findById(addressId).orElseThrow();
	}
 

}

