package com.tap.model;

public class Menu {

	private int menuId;
	private String menuName;
	private double price;
	private String description;
	private String imagepath;
	private boolean isAvailable;
	private int restaurantId;
	private float ratings;
	
	public Menu(String menuName, double price,String description,int restaurantId, String fileName) {
		// TODO Auto-generated constructor stub
		super();
	
		this.menuName = menuName;
		this.price = price;
		this.description = description;
		this.imagepath = fileName;
		
		this.restaurantId = restaurantId;
		
	}

	public Menu(int menuId, String menuName, double price, String description, String imagepath, boolean isAvailable,
			int restaurantId, float ratings) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.price = price;
		this.description = description;
		this.imagepath = imagepath;
		this.isAvailable = isAvailable;
		this.restaurantId = restaurantId;
		this.ratings = ratings;
	}
	
	

	public Menu(String menuName, double price, String description, String imagepath, boolean isAvailable,
			int restaurantId, float ratings) {
		super();
		this.menuName = menuName;
		this.price = price;
		this.description = description;
		this.imagepath = imagepath;
		this.isAvailable = isAvailable;
		this.restaurantId = restaurantId;
		this.ratings = ratings;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", price=" + price + ", description=" + description
				+ ", imagepath=" + imagepath + ", isAvailable=" + isAvailable + ", restaurantId=" + restaurantId
				+ ", ratings=" + ratings + "]";
	}
	
	
	public int  equals(Menu menu1) {
		return this.menuId-menu1.menuId;
	}
	
	
}
