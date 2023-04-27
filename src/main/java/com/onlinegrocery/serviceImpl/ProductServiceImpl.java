package com.onlinegrocery.serviceImpl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlinegrocery.dto.ProductDto;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Category;
import com.onlinegrocery.exceptions.CartNotFoundException;
import com.onlinegrocery.exceptions.ProductIdNotFoundException;
import com.onlinegrocery.exceptions.ProductsNotAvailableException;
import com.onlinegrocery.repo.ProductRepo;
import com.onlinegrocery.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	
	@Override
	public Product addProduct(String proudctName,String description,double price,byte[] image,Category category,int stockQuantity ,String base64Image) {
		// TODO Auto-generated method stub
		Product products = new Product();
		products.setProductName(proudctName);
		products.setDescription(description);
		products.setImage(image);
		products.setCategory(category);
		products.setPrice(price);
		products.setStockQuantity(stockQuantity);
		//products.setBase64Image(base64Image);
		return productRepo.save(products);
		
	}

	@Override
	public String deleteProduct(int productId) {
		if (!productRepo.existsById(productId)) {
            throw new ProductIdNotFoundException();
        }
        productRepo.deleteById(productId);
        return "Deleted successfully";	}


	@Override
	public List<Product> getAllProducts() {
		 List<Product> products = productRepo.findAll();
		 if (products.isEmpty()) {
		        throw new ProductsNotAvailableException();
		    }
		    for (Product product : products) {
		      byte[] imageData = product.getImage(); // Fetch image data from the database
		      if (imageData != null) {
		        String base64Image = Base64.getEncoder().encodeToString(imageData); // Convert to Base64
		        product.setBase64Image(base64Image); // Set the Base64-encoded image data in the Product object
		      }
		    }
		    return products;
		}

	@Override
	public List<Product> getProductByCategory(Category category) {
		return productRepo.findByCategory(category);
	}


	@Override
	public Product getById(int productId) {
		return productRepo.findById(productId).orElseThrow(
				() -> new ProductIdNotFoundException());
	}



	
	 
	    @Override
	    public Product updateProduct( String productName, String description, double price, byte[] image, Category category, int stockQuantity, String base64Image,int productId) {
	        // Find the existing product by productId from the database
	        Optional<Product> optionalProduct = productRepo.findById(productId);
	        if (optionalProduct.isPresent()) {
	            Product product = optionalProduct.get();
	            // Update the product properties
	            product.setProductName(productName);
	            product.setDescription(description);
	            product.setPrice(price);
	            product.setImage(image);
	            product.setCategory(category);
	            product.setStockQuantity(stockQuantity);
	            //product.setBase64Image(base64Image);
	            return productRepo.save(product);
	        } else {
	            // Handle case when the product with the given productId is not found
	            throw new IllegalArgumentException("Product with productId " + productId + " not found");
	        }
	    }


}
