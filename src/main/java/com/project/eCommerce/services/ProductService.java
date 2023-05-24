package com.project.eCommerce.services;

import java.util.List;
import java.util.Optional;
import java.io.File;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.lang.reflect.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.project.eCommerce.entities.Product;
import com.project.eCommerce.entities.User;
import com.project.eCommerce.repositories.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getOneProduct(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	public String createOneProduct(MultipartFile file, String name, double price, String category) throws IOException {
		File convertFile = new File("C:\\Users\\nadir\\MyAllProjects\\SpringBoot-ReactJS\\YazilimProjeYonetimi\\FE\\ecommercefe\\public\\images\\" + file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();

		String imageUrl = "./images/" + file.getOriginalFilename();

		Product product = new Product();
		product.setName(name);
		product.setImagePath(imageUrl);
		product.setCategory(category);
		product.setPrice(price);

		productRepository.save(product);

		return "File is uploaded successfully";
	}

	public Product updateProductByFields(Long productId, Map<String, Object> fields) {
		Optional<Product> existingProduct = productRepository.findById(productId);

		if (existingProduct.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Product.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, existingProduct.get(), value);
			});
			return productRepository.save(existingProduct.get());
		}
		return null;
	}

	public void deleteOneProduct(Long productId) {
		productRepository.deleteById(productId);
	}

}
