package com.tap.model;

public class OrderItem {

	private int orderItemId;
	private int menuId;
	private int userId;
	private String itemName;
	private float ratings;
	private int quantity;
	private double price;
	private String orderId;
	
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int orderItemId, int menuId, int userId, String itemName, float ratings, int quantity,
			double price, String orderId) {
		super();
		this.orderItemId = orderItemId;
		this.menuId = menuId;
		this.userId = userId;
		this.itemName = itemName;
		this.ratings = ratings;
		this.quantity = quantity;
		this.price = price;
		this.orderId=orderId;
	}

	
	public OrderItem(int menuId, int userId, String itemName, float ratings, int quantity, double price,String orderId) {
		super();
		this.menuId = menuId;
		this.userId = userId;
		this.itemName = itemName;
		this.ratings = ratings;
		this.quantity = quantity;
		this.price = price;
		this.orderId=orderId;

	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", menuId=" + menuId + ", userId=" + userId + ", itemName="
				+ itemName + ", ratings=" + ratings + ", quantity=" + quantity + ", price=" + price +" , orderId "+orderId+ "]";
	}
	
}
