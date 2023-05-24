package com.project.eCommerce.services;

import org.springframework.stereotype.Service;

import com.project.eCommerce.entities.RefreshToken;
import com.project.eCommerce.entities.User;
import com.project.eCommerce.repositories.RefreshTokenRepository;

import java.util.Date;
import java.util.UUID;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;

@Service
public class RefreshTokenService {

	@Value("${refresh.token.expires.in}")
	Long expireSeconds;

	private RefreshTokenRepository refreshTokenRepository;

	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}

	public boolean isRefreshExpired(RefreshToken token) {
		return token.getExpiryDate().before(new Date());
	}

	public String createRefreshToken(User user) {
		RefreshToken token = refreshTokenRepository.findByUserUserId(user.getUserId());
		if (token == null) {
			token = new RefreshToken();
			token.setUser(user);
		}
		token.setToken(UUID.randomUUID().toString());
		token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
		refreshTokenRepository.save(token);
		return token.getToken();
	}

	public RefreshToken getByUser(Long userId) {
		return refreshTokenRepository.findByUserUserId(userId);	
	}

}