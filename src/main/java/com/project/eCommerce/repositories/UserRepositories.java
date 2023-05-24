package com.project.eCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.eCommerce.entities.User;

public interface UserRepositories extends JpaRepository<User, Long> {
	User findByUsername(String username);


}