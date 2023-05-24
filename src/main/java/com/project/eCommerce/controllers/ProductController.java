package com.project.eCommerce.controllers;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.eCommerce.entities.Product;
import com.project.eCommerce.entities.User;
import com.project.eCommerce.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productId}")
	public Product getOneProduct(@PathVariable Long productId) {
		// custom exception
		return productService.getOneProduct(productId);
	}
	
	@PostMapping
	public String createProduct(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("price") double price, @RequestParam("category") String category) throws IOException {

		return productService.createOneProduct(file, name, price, category);
	}
	
	@PatchMapping("/{productId}")
	public Product updateProductFields(@PathVariable Long productId,@RequestBody Map<String, Object> fields) {
		return productService.updateProductByFields(productId, fields);
	}
	
	@DeleteMapping("/{productId}")
	public void deleteOneProduct(@PathVariable Long productId) {
		productService.deleteOneProduct(productId);
	}
	

}
