package com.project.eCommerce.response;

import com.project.eCommerce.entities.Basket;

public class BasketResponse {

	private Long basketId;
	private Long userId;
	private Long productId;
	private String name;
	private double price;
	private String image;
	private String category;
	private String state;

	public BasketResponse(Basket entity) {
		this.basketId = entity.getBasketId();
		this.userId = entity.getUser().getUserId();
		this.productId = entity.getProduct().getProductId();
		this.name = entity.getProduct().getName();
		this.price = entity.getProduct().getPrice();
		this.image = entity.getProduct().getImagePath();
		this.category = entity.getProduct().getCategory();
		this.state = entity.getState();
	}

	public long getBasketId() {
		return basketId;
	}

	public void setBasketId(long basketId) {
		this.basketId = basketId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
