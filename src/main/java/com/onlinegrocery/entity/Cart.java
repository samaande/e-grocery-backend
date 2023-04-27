
package com.onlinegrocery.entity;
 
 
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private int productCount;
    private double totalPrice;
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private AppUser user;
    private String userName;
	
}
 

