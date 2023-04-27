
package com.onlinegrocery.entity;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long addressId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser userId;
    private long housenumber;
    private String street;
    private String landmark;
    private String district;
    private String state;
    @OneToMany
    private List<Order> order;
    @NotNull
    private long pin;

    @Override
    public String toString() {
        return "Address [addressId=" + addressId + ", user=" + userId + ", housenumber=" + housenumber + ", street="
                + street + ", landmark=" + landmark + ", district=" + district + ", state=" + state + ", pin=" + pin
                + "]";
    }
    public long getAddressId() {
        return addressId;
    }
    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
    public AppUser getUser() {
        return userId;
    }
    public void setUser(AppUser user) {
        this.userId = user;
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
    public Address(long addressId, AppUser user, long housenumber, String street, String landmark, String district,
            String state, @NotNull long pin) {
        super();
        this.addressId = addressId;
        this.userId = user;
        this.housenumber = housenumber;
        this.street = street;
        this.landmark = landmark;
        this.district = district;
        this.state = state;
        this.pin = pin;
    }
    public Address() {
        super();
    }
 
}


