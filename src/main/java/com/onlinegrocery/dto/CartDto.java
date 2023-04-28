package com.onlinegrocery.dto;
 
import java.util.List;


 

public class CartDto {

    private int productCount;
    private double totalPrice;
    private int productId;
    private String userName;
    private int userid;
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
		return "CartDto [productCount=" + productCount + ", totalPrice=" + totalPrice + ", productId=" + productId
				+ ", userName=" + userName + ", userid=" + userid + "]";
	}
	public CartDto(int productCount, double totalPrice, int productId, String userName, int userid) {
		super();
		this.productCount = productCount;
		this.totalPrice = totalPrice;
		this.productId = productId;
		this.userName = userName;
		this.userid = userid;
	}
	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    	
}