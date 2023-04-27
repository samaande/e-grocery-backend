package com.onlinegrocery.dto;
 
import java.util.List;
 
import lombok.Data;
 
@Data
public class CartDto {

    private int productCount;
    private double totalPrice;
    private int productId;
    private String userName;
    private int userid;
    	
}