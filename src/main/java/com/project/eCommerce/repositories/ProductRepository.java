package com.project.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eCommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
