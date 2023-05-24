package com.project.eCommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.eCommerce.entities.RefreshToken;
import com.project.eCommerce.entities.User;
import com.project.eCommerce.response.AuthResponse;
import com.project.eCommerce.reuqests.RefreshRequest;
import com.project.eCommerce.reuqests.RegisterRequest;
import com.project.eCommerce.reuqests.UserRequest;
import com.project.eCommerce.security.JwtTokenProvider;
import com.project.eCommerce.services.RefreshTokenService;
import com.project.eCommerce.services.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserService userService;
	private PasswordEncoder passwordEncoder;

	private AuthenticationManager authenticationManager;

	private JwtTokenProvider jwtTokenProvider;

	private RefreshTokenService refreshTokenService;

	public AuthController(UserService userService, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService,
			JwtTokenProvider jwtTokenProvider) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.refreshTokenService = refreshTokenService;
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		User user = userService.getOneUserByUsername(loginRequest.getUsername());
		AuthResponse authResponse = new AuthResponse();
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getUserId());
		return authResponse;

	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
		AuthResponse authResponse = new AuthResponse();
		if (userService.getOneUserByUsername(registerRequest.getUsername()) != null) {
			authResponse.setMessage("username var knk zaten");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		} else {
			User user = new User();
			user.setName(registerRequest.getName());
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			user.setRole(registerRequest.getRole());
			user.setSurname(registerRequest.getSurname());
			user.setUsername(registerRequest.getUsername());
			user.setEmail(registerRequest.getEmail());
			userService.saveOneUser(user);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUsername(), registerRequest.getPassword());
			Authentication auth = authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(auth);
			String jwtToken = jwtTokenProvider.generateJwtToken(auth);
			
			authResponse.setMessage("User successfully registered.");
			authResponse.setAccessToken("Bearer " + jwtToken);
			authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
			authResponse.setUserId(user.getUserId());
			
			return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
		}
	}
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
		AuthResponse response = new AuthResponse();
		RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
		if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
				!refreshTokenService.isRefreshExpired(token)) {

			User user = token.getUser();
			String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getUserId());
			response.setMessage("token başarılıyla yenilendi.");
			response.setAccessToken("Bearer " + jwtToken);
			response.setUserId(user.getUserId());
			return new ResponseEntity<>(response, HttpStatus.OK);		
		} else {
			response.setMessage("refresh token geçersiz.");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
		
	}
}