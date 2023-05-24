package com.project.eCommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.eCommerce.entities.Basket;
import com.project.eCommerce.repositories.BasketRepository;
import com.project.eCommerce.response.BasketResponse;
import com.project.eCommerce.reuqests.BasketCreateRequest;
import com.project.eCommerce.reuqests.BasketUpdateRequest;
import com.project.eCommerce.services.BasketService;

@RestController
@RequestMapping("/basket")
public class BasketController {

	private BasketService basketService;

	public BasketController(BasketService basketService) {
		this.basketService = basketService;
	}

	@GetMapping
	public List<BasketResponse> getAllComments(@RequestParam Optional<Long> userId, 
			@RequestParam Optional<Long> product) {
		return basketService.getAllBasketWithParam(userId, product);
	}
	
	@GetMapping("/{basketId}")
	public Basket getOneComment(@PathVariable Long basketId) {
		return basketService.getOneBasketById(basketId);
	}
	


	
	@PostMapping
	public Basket createOneBasket(@RequestBody BasketCreateRequest request) {
		return basketService.createOneBasket(request);
	}
	
	@PutMapping("/{basketId}")
	public Basket updateOneBasket(@PathVariable Long basketId,@RequestBody BasketUpdateRequest request) {
		return basketService.updateOneBasketById(basketId,request);
	}
	
	@DeleteMapping("/{basketId}")
	public void deleteOneBasket(@PathVariable Long basketId) {
		basketService.deleteOneBasketById(basketId);
	}
	
}