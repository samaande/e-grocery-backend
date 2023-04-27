
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
import javax.validation.constraints.NotNull;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import lombok.Data;
 
@Data
@Entity
@Table(name="wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int WishlistId;
    private int productCount;
    private double ProductPrice;

    @ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private AppUser user;
    private String userName;
	public int getWishlistId() {
		return WishlistId;
	}
	public void setWishlistId(int wishlistId) {
		WishlistId = wishlistId;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public double getProductPrice() {
		return ProductPrice;
	}
	public void setProductPrice(double productPrice) {
		ProductPrice = productPrice;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public AppUser getUser() {
		return user;
	}
	public void setUser(AppUser user) {
		this.user = user;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Wishlist [WishlistId=" + WishlistId + ", productCount=" + productCount + ", ProductPrice="
				+ ProductPrice + ", products=" + products + ", user=" + user + ", userName=" + userName + "]";
	}
	public Wishlist(int wishlistId, int productCount, double productPrice, List<Product> products, AppUser user,
			String userName) {
		super();
		WishlistId = wishlistId;
		this.productCount = productCount;
		ProductPrice = productPrice;
		this.products = products;
		this.user = user;
		this.userName = userName;
	}
	public Wishlist() {
		super();
		// TODO Auto-generated constructor stub
	}
 
    
}

