package com.project.eCommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eCommerce.entities.Basket;
import com.project.eCommerce.response.BasketResponse;

public interface BasketRepository extends JpaRepository<Basket, Long> {

	List<Basket> findByUserUserIdAndProductProductId(Long userId, Long productId);

	List<Basket> findByUserUserId(Long userId);

	List<Basket> findByProductProductId(Long productId);



}
