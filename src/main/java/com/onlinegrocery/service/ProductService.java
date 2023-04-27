package com.onlinegrocery.service;

import java.util.List;

import com.onlinegrocery.dto.ProductDto;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Category;



public interface ProductService {
	Product addProduct(String proudctName,String description,double price,byte[] image,Category category,int stockQuantity,String base64Image);
	String deleteProduct(int productId) ;
	List<Product> getAllProducts();
	List<Product> getProductByCategory(Category category);
	Product getById(int productId);
	

    Product updateProduct( String productName, String description, double price, byte[] image, Category category, int stockQuantity, String base64Image,int productId);
}
