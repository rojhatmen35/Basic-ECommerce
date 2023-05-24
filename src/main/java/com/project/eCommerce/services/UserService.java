package com.project.eCommerce.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.project.eCommerce.entities.User;
import com.project.eCommerce.repositories.UserRepositories;

@Service
public class UserService {

	private UserRepositories userRepositories;

	public UserService(UserRepositories userRepositories) {
		this.userRepositories = userRepositories;
	}

	public List<User> getAllUsers() {
		return userRepositories.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepositories.save(newUser);
	}

	public User getOneUser(Long userId) {
		return userRepositories.findById(userId).orElse(null);
	}

	public void deleteById(Long userId) {
		userRepositories.deleteById(userId);
	}

	public User updateUserByFields(Long userId, Map<String, Object> fields) {
		Optional<User> existingProduct = userRepositories.findById(userId);

		if (existingProduct.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(User.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, existingProduct.get(), value);
			});
			return userRepositories.save(existingProduct.get());
		}
		return null;
	}

	public User getOneUserByUsername(String username) {
		return userRepositories.findByUsername(username);

	}
}
