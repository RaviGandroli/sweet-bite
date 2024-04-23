package com.tap.model;

public class Restaurant {

	private int restaurantId;
	private String restaurantName;
	private String restaurantImagePath;
	private float ratings;
	private String eta;
	private String cuisineType;
	private String address;
	private boolean isActive;
	private int restaurantOwnerId;
	
	public Restaurant() {
		// TODO Auto-generated constructor stub
	}

	public Restaurant( String restaurantName, String restaurantImagePath, float ratings, String eta,
			String cuisineType, String address, boolean isActive, int restaurantOwnerId) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantImagePath = restaurantImagePath;
		this.ratings = ratings;
		this.eta = eta;
		this.cuisineType = cuisineType;
		this.address = address;
		this.isActive = isActive;
		this.restaurantOwnerId = restaurantOwnerId;
	}

	
	
	public Restaurant(int restaurantId, String restaurantName, String restaurantImagePath, float ratings, String eta,
			String cuisineType, String address, boolean isActive, int restaurantOwnerId) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantImagePath = restaurantImagePath;
		this.ratings = ratings;
		this.eta = eta;
		this.cuisineType = cuisineType;
		this.address = address;
		this.isActive = isActive;
		this.restaurantOwnerId = restaurantOwnerId;
	}
	
	
	

	
	public Restaurant( String restaurantName, String restaurantImagePath,
			String cuisineType, String address, int restaurantOwnerId) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantImagePath = restaurantImagePath;
		this.ratings = ratings;
		this.eta = eta;
		this.cuisineType = cuisineType;
		this.address = address;
		this.isActive = isActive;
		this.restaurantOwnerId = restaurantOwnerId;
	}
	

	
	

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantImagePath() {
		return restaurantImagePath;
	}

	public void setRestaurantImagePath(String restaurantImagePath) {
		this.restaurantImagePath = restaurantImagePath;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getRestaurantOwnerId() {
		return restaurantOwnerId;
	}

	public void setRestaurantOwnerId(int restaurantOwnerId) {
		this.restaurantOwnerId = restaurantOwnerId;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantImagePath=" + restaurantImagePath + ", ratings=" + ratings + ", eta=" + eta
				+ ", cuisineType=" + cuisineType + ", address=" + address + ", isActive=" + isActive
				+ ", restaurantOwnerId=" + restaurantOwnerId + "]";
	}
	
	
	
}
