 package com.onlinegrocery.controller;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.Arrays;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.onlinegrocery.controller.ProductController;
import com.onlinegrocery.entity.Product;
import com.onlinegrocery.enums.Category;
import com.onlinegrocery.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct() throws IOException {
        // Arrange
        String productName = "Test Product";
        String description = "Test description";
        double price = 10.0;
        Category category = Category.DAIRY;
        int stockQuantity = 100;
        String base64Image = "base64image";
        byte[] images = new byte[] {1, 2, 3};
        MultipartFile image = mock(MultipartFile.class);
        when(image.getBytes()).thenReturn(images);
        Product expectedProduct = new Product(1, "Product 1", "Description 1", Category.DAIRY, null, 10.0, 5);
        when(productService.addProduct(productName, description, price, images, category, stockQuantity, base64Image)).thenReturn(expectedProduct);

        // Act
        ResponseEntity<Product> response = productController.addProduct(productName, description, price, category, stockQuantity, base64Image, image);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedProduct, response.getBody());
    }

    
    @Before
    public void init1() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testDeleteProduct() throws Exception {
        int productId = 1;
        when(productService.deleteProduct(productId)).thenReturn("Product deleted successfully");

        mockMvc.perform(delete("/product/delete/{productId}", productId))
                .andExpect(status().isOk());

        verify(productService).deleteProduct(productId);
    }
    	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // create a list of mock products
        Product mockProduct1 = new Product(1, "Product 1", "Description 1", Category.DAIRY, null, 10.0, 5);
		Product mockProduct2 = new Product(2, "Product 2", "Description 2", Category.MEAT, null, 20.0, 10);
        List<Product> mockProducts = Arrays.asList(mockProduct1, mockProduct2);

        // configure productService to return the mock products
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // perform GET request to "/getallproducts"
        mockMvc.perform(get("/product/getallproducts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].productName").value("Product 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].price").value(10.0))
                .andExpect(jsonPath("$[0].category").value(Category.DAIRY.toString()))
                .andExpect(jsonPath("$[0].stockQuantity").value(5))
                //.andExpect(jsonPath("$[0].base64Image").value(null))
                .andExpect(jsonPath("$[1].productId").value(2))
                .andExpect(jsonPath("$[1].productName").value("Product 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].price").value(20.0))
                .andExpect(jsonPath("$[1].category").value(Category.MEAT.toString()))
                .andExpect(jsonPath("$[1].stockQuantity").value(10));
                //.andExpect(jsonPath("$[1].base64Image").value(null));
    }
    
    @Test
    public void testGetProductByCategory() throws Exception {
        // create a list of mock products
        List<Product> mockProducts = Arrays.asList(
                new Product(1, "Milk", "Fresh milk", Category.DAIRY,null, 1.99,  100),
                new Product(2, "Bread", "Fresh bread", Category.FRUITS,null, 2.99,  50));

        // mock the service method to return the list of mock products
        when(productService.getProductByCategory(Category.DAIRY)).thenReturn(mockProducts);

        // perform GET request to "/getby/{category}"
        mockMvc.perform(get("/product/getby/DAIRY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productName").value("Milk"))
                .andExpect(jsonPath("$[0].description").value("Fresh milk"))
                .andExpect(jsonPath("$[0].price").value(1.99))
                .andExpect(jsonPath("$[0].category").value(Category.DAIRY.toString()))
                .andExpect(jsonPath("$[0].stockQuantity").value(100));
                //.andExpect(jsonPath("$[1].productName").doesNotExist());

        // verify that the getProductByCategory method was called with the correct argument
        verify(productService).getProductByCategory(Category.DAIRY);
    }
    
    @Test
    public void testGetById() throws Exception {
        // Create a mock Product object
        Product mockProduct = new Product();
        mockProduct.setProductId(1);
        mockProduct.setProductName("test product");
        mockProduct.setDescription("test description");
        mockProduct.setPrice(10.99);
        mockProduct.setCategory(Category.DAIRY);
        mockProduct.setStockQuantity(100);
        
        // Mock the getById method of the ProductService to return the mock Product
        when(productService.getById(1)).thenReturn(mockProduct);
        
        // Perform a GET request to "/getbyid/1"
        mockMvc.perform(get("/product/getbyid/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.productId").value(1))
               .andExpect(jsonPath("$.productName").value("test product"))
               .andExpect(jsonPath("$.description").value("test description"))
               .andExpect(jsonPath("$.price").value(10.99))
               .andExpect(jsonPath("$.category").value(Category.DAIRY.toString()))
               .andExpect(jsonPath("$.stockQuantity").value(100));
        
        // Verify that the getById method of the ProductService was called with the correct argument
        verify(productService).getById(1);
    }
    
    
   
    
  
    @Test
    public void testAddOrUpdateProduct() throws Exception {
    // Mock request parameters
    String productName = "Test Product";
    String description = "This is a test product.";
    double price = 9.99;
    MultipartFile image = mock(MultipartFile.class);
    Category category = Category.DAIRY;
    int stockQuantity = 10;
    String base64Image = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD...";
    int productId = 123;
     
    // Mock service response
    Product product = new Product();
    when(productService.updateProduct(productName, description, price, image.getBytes(), category, stockQuantity, base64Image, productId))
    .thenReturn(product);
     
    // Execute controller method
    ResponseEntity<Product> response = productController.addOrUpdateProduct(productName, description, price, image, category, stockQuantity, base64Image, productId);
     
    // Assert response
    assertNotNull(response);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(product, response.getBody());
    }


    
    

}
