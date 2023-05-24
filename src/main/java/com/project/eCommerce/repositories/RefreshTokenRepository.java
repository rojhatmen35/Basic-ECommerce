package com.project.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eCommerce.entities.RefreshToken;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	RefreshToken findByUserUserId(Long userId);


}