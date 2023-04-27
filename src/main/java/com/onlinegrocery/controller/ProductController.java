package com.onlinegrocery.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onlinegrocery.dto.ProductDto;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Category;
import com.onlinegrocery.service.ProductService;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	

	@CrossOrigin
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestParam String productName,@RequestParam String description,@RequestParam double price,@RequestParam Category category,@RequestParam int stockQuantity,@RequestParam String base64Image, @RequestParam MultipartFile image ) throws IOException{
		byte[] images = image.getBytes();
		return new ResponseEntity<Product>(productService.addProduct(productName, description, price, images, category, stockQuantity,base64Image),HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{productId}")
	public String deleteProduct(@PathVariable int productId) {
		return productService.deleteProduct(productId);
	}
	@CrossOrigin
	@GetMapping("/getallproducts")
	public List<Product> getAllProducts(){
		
		return productService.getAllProducts();
	}
	@CrossOrigin
	@GetMapping("/getby/{category}")
	public List<Product> getProductByCategory(@PathVariable Category category){
		return productService.getProductByCategory(category);
	}
	@CrossOrigin
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> addOrUpdateProduct(
            // Optional productId for updating
            @RequestParam String productName,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam MultipartFile image,
            @RequestParam Category category,
            @RequestParam int stockQuantity,
            @RequestParam String base64Image,
            @PathVariable int productId
            ) throws IOException {

        byte[] images = image.getBytes();
        Product product;

        
            // If productId is provided, update the existing product
            product = productService.updateProduct( productName, description, price, images, category, stockQuantity, base64Image,productId);
       
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

	@GetMapping("/getbyid/{productId}")
	public Product getById(@PathVariable int productId) {
		return productService.getById(productId);
	}
	
	

    
    
}

