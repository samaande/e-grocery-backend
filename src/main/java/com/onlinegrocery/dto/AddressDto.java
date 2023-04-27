
package com.onlinegrocery.dto;
 
import java.util.List;
 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
 
public class AddressDto {
    private long addressId;
    private long housenumber;
    private String street;
    private String landmark;
    private String district;
    private String state;
    private long pin;
    private int userId;
    


    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public long getAddressId() {
        return addressId;
    }
    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
    public long getHousenumber() {
        return housenumber;
    }
    public void setHousenumber(long housenumber) {
        this.housenumber = housenumber;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getLandmark() {
        return landmark;
    }
    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public long getPin() {
        return pin;
    }
    public void setPin(long pin) {
        this.pin = pin;
    }

    public AddressDto(long addressId, long housenumber, String street, String landmark, String district, String state,
            long pin, int userId) {
        super();
        this.addressId = addressId;
        this.housenumber = housenumber;
        this.street = street;
        this.landmark = landmark;
        this.district = district;
        this.state = state;
        this.pin = pin;
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "AddressDto [addressId=" + addressId + ", housenumber=" + housenumber + ", street=" + street
                + ", landmark=" + landmark + ", district=" + district + ", state=" + state + ", pin=" + pin
                + ", userId=" + userId + "]";
    }
    public AddressDto() {
        super();
    }

}

