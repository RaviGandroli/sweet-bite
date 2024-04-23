package com.tap.model;

public class OrderHistory {

	private int orderHistoryId;
	private String orderId;
	private int userId;
	
	public OrderHistory() {
		// TODO Auto-generated constructor stub
	}

	public OrderHistory(int orderHistoryId, String orderId, int userId) {
		super();
		this.orderHistoryId = orderHistoryId;
		this.orderId = orderId;
		this.userId = userId;
	}
	
	

	public OrderHistory(String orderId, int userId) {
		super();
		this.orderId = orderId;
		this.userId = userId;
	}

	public int getOrderHistoryId() {
		return orderHistoryId;
	}

	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
