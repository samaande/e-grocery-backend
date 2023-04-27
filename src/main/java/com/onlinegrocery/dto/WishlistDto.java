
package com.onlinegrocery.dto;
 
import java.util.List;
 
 
import lombok.Data;
 
@Data
public class WishlistDto {
 
    private int productId;
    private double ProductPrice;
    private String userName;
    private int userid;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getProductPrice() {
		return ProductPrice;
	}
	public void setProductPrice(double productPrice) {
		ProductPrice = productPrice;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "WishlistDto [productId=" + productId + ", ProductPrice=" + ProductPrice + ", userName=" + userName
				+ ", userid=" + userid + "]";
	}
	public WishlistDto(int productId, double productPrice, String userName, int userid) {
		super();
		this.productId = productId;
		ProductPrice = productPrice;
		this.userName = userName;
		this.userid = userid;
	}
	public WishlistDto() {
		super();
		// TODO Auto-generated constructor stub
	}



 
    }

