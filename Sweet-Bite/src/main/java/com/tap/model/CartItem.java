package com.tap.model;

public class CartItem {

	private int itemId;
	private  int restaurantId;
	private String name;
	private double price;
	private int quantity;
	private float rating;
	private String imagePath;
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CartItem(int itemId, int restaurantId, String name, double price, float rating,int quantity, String imagePath) {
		super();
		this.itemId = itemId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.price = price;
		this.rating=rating;
		this.quantity = quantity;
		this.imagePath=imagePath;
	}



	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public float getRating() {
		return rating;
	}



	public void setRating(float rating) {
		this.rating = rating;
	}

   

	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", restaurantId=" + restaurantId + ", name=" + name + ", price=" + price+" , rating"+rating
				+ ", quantity=" + quantity + " , imagePath "+imagePath+"]";
	}
	
	
}
