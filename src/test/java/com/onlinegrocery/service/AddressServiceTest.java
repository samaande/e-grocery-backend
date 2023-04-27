package com.onlinegrocery.service;


 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
 
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
 
import com.onlinegrocery.dto.AddressDto;
import com.onlinegrocery.entity.Address;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.exceptions.AddressNotFoundException;
import com.onlinegrocery.repo.AddressRepo;
import com.onlinegrocery.serviceImpl.AddressServiceImpl;
 
@SpringBootTest
public class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;
    AddressServiceImpl impl = new AddressServiceImpl();
    @Mock
    private AddressRepo addressRepo;

      @Test
        public void deleteAddressTest() {
            long addressId = 123L;
            String expectedResponse = "Address deleted successfully";
 
            String actualResponse = addressServiceImpl.deleteAddress(addressId);
 
            verify(addressRepo).deleteById(addressId);
            assertEquals(expectedResponse, actualResponse);
        }

      @Test
        public void getAddressByAddressIdTest() {
            long addressId = 1L;
            Address address = new Address();
            address.setAddressId(addressId);
            when(addressRepo.findById(addressId)).thenReturn(Optional.of(address));
            Address result = addressServiceImpl.getAddressByAddressId(addressId);
            assertEquals(addressId, result.getAddressId());
        }
 
 
    
}


