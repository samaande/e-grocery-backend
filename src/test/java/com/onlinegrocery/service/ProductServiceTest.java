
package com.onlinegrocery.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Category;
import com.onlinegrocery.exceptions.ProductIdNotFoundException;
import com.onlinegrocery.exceptions.ProductsNotAvailableException;
import com.onlinegrocery.repo.ProductRepo;
import com.onlinegrocery.serviceImpl.ProductServiceImpl;

public class ProductServiceTest {

	@Mock
	private ProductRepo productRepo;

	@InjectMocks
	private ProductServiceImpl productService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddProduct() {
		String productName = "Test Product";
		String description = "This is a test product.";
		double price = 10.0;
		byte[] image = { 1, 2, 3 };
		Category category = Category.DAIRY;
		int stockQuantity = 5;
		String base64Image = "base64-encoded-image";

		Product product = new Product();
		product.setProductName(productName);
		product.setDescription(description);
		product.setImage(image);
		product.setCategory(category);
		product.setPrice(price);
		product.setStockQuantity(stockQuantity);

		when(productRepo.save(any(Product.class))).thenReturn(product);

		Product savedProduct = productService.addProduct(productName, description, price, image, category,
				stockQuantity, base64Image);

		assertEquals(productName, savedProduct.getProductName());
		assertEquals(description, savedProduct.getDescription());
		assertArrayEquals(image, savedProduct.getImage());
		assertEquals(category, savedProduct.getCategory());
		assertEquals(price, savedProduct.getPrice(), 0.0);
		assertEquals(stockQuantity, savedProduct.getStockQuantity());
	}

	@Test
	public void testDeleteProduct_Success() {
		int productId = 1;
		when(productRepo.existsById(productId)).thenReturn(true);
		doNothing().when(productRepo).deleteById(productId);
		String result = productService.deleteProduct(productId);
		Assertions.assertEquals("Deleted successfully", result);
		verify(productRepo, times(1)).existsById(productId);
		verify(productRepo, times(1)).deleteById(productId);
	}

	@Test
	public void testDeleteProduct_ProductIdNotFound() {
		int productId = 1;
		when(productRepo.existsById(productId)).thenReturn(false);
		Assertions.assertThrows(ProductIdNotFoundException.class, () -> {
			productService.deleteProduct(productId);
		});
		verify(productRepo, times(1)).existsById(productId);
		verify(productRepo, times(0)).deleteById(productId);
	}

	@Test
	public void testGetAllProducts_Success() {
		List<Product> products = new ArrayList<>();
		Product product1 = new Product(1, "Product 1", "Description 1", Category.DAIRY, null, 10.0, 5);
		Product product2 = new Product(2, "Product 2", "Description 2", Category.MEAT, null, 20.0, 10);
		products.add(product1);
		products.add(product2);
		when(productRepo.findAll()).thenReturn(products);
		List<Product> result = productService.getAllProducts();
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals(product1.getProductName(), result.get(0).getProductName());
		Assertions.assertEquals(product2.getProductName(), result.get(1).getProductName());
		verify(productRepo, times(1)).findAll();
	}

	@Test
    public void testGetAllProducts_ProductsNotAvailable() {
        when(productRepo.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(ProductsNotAvailableException.class, () -> {
            productService.getAllProducts();
        });
        verify(productRepo, times(1)).findAll();
    }

	@Test
	public void testGetProductByCategory() {
		Category category = Category.GRAINS;
		List<Product> productList = new ArrayList<>();
		Product product1 = new Product();
		product1.setProductId(1);
		product1.setProductName("Product 1");
		product1.setCategory(category);
		productList.add(product1);
		Product product2 = new Product();
		product2.setProductId(2);
		product2.setProductName("Product 2");
		product2.setCategory(category);
		productList.add(product2);
		when(productRepo.findByCategory(category)).thenReturn(productList);
		List<Product> products = productService.getProductByCategory(category);
		assertEquals(2, products.size());
		assertEquals(product1, products.get(0));
		assertEquals(product2, products.get(1));
	}

	@Test
	public void testGetById_Success() {
		Product product = new Product(1, "Product 1", "Description 1", Category.DAIRY, null, 10.0, 5);
		when(productRepo.findById(1)).thenReturn(Optional.of(product));
		Product result = productService.getById(1);
		Assertions.assertEquals(product.getProductName(), result.getProductName());
		verify(productRepo, times(1)).findById(1);
	}

	@Test
    public void testGetById_ProductIdNotFound() {
        when(productRepo.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(ProductIdNotFoundException.class, () -> {
            productService.getById(1);
        });
        verify(productRepo, times(1)).findById(1);
    }

	
	

	@Test
	public void testUpdateProduct() {
		int productId = 1;
		String productName = "Updated Product";
		String description = "This is an updated product.";
		double price = 20.0;
		byte[] image = { 4, 5, 6 };
		Category category = Category.DAIRY;
		int stockQuantity = 10;
		String base64Image = "base64-encoded-image";

		Product existingProduct = new Product();
		existingProduct.setProductId(productId);
		existingProduct.setProductName("Old Product");
		existingProduct.setDescription("This is an old product.");
		existingProduct.setPrice(10.0);
		existingProduct.setImage(new byte[] { 1, 2, 3 });
		existingProduct.setCategory(Category.FRUITS);
		existingProduct.setStockQuantity(5);

		when(productRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(existingProduct));
		when(productRepo.save(Mockito.any(Product.class))).thenAnswer(i -> i.getArguments()[0]);

		Product updatedProduct = productService.updateProduct(productName, description, price, image, category,
				stockQuantity, base64Image, productId);

		assertEquals(productId, updatedProduct.getProductId());
		assertEquals(productName, updatedProduct.getProductName());
		assertEquals(description, updatedProduct.getDescription());
		assertEquals(price, updatedProduct.getPrice(), 0.0);
		assertEquals(category, updatedProduct.getCategory());
		assertEquals(stockQuantity, updatedProduct.getStockQuantity());
		// assertEquals(base64Image, updatedProduct.getBase64Image());
		assertEquals(image, updatedProduct.getImage());
	}

}
