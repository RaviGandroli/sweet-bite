package com.tap.model;

public class Order {

	private String orderId;
	private double totalAmout;
	private String modeOfPayment;
	private String status;
	private int restaurantId;
	private int userId;
	private String ETA;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String orderId, double totalAmout, String modeOfPayment, String status, int restaurantId, int userId, String ETA) {
		super();
		this.orderId = orderId;
		this.totalAmout = totalAmout;
		this.modeOfPayment = modeOfPayment;
		this.status = status;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.ETA=ETA;
	}
	
	

	public Order(double totalAmout, String modeOfPayment, String status, int restaurantId, int userId,String ETA) {
		super();
		this.totalAmout = totalAmout;
		this.modeOfPayment = modeOfPayment;
		this.status = status;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.ETA=ETA;
	
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getTotalAmout() {
		System.out.println("i am gettin th e amt");
		return totalAmout;
	}

	public void setTotalAmout(double totalAmout) {
		System.out.println(totalAmout);
		System.out.println("i am settin the amt");
		this.totalAmout = totalAmout;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setETA(String ETA) {
		this.ETA=ETA;
		
	}
	public String getETA() {
		return ETA;
	}

	@Override
	public String toString() {
		return "order [orderId=" + orderId + ", totalAmout=" + totalAmout + ", modeOfPayment=" + modeOfPayment
				+ ", status=" + status + ", restaurantId=" + restaurantId + ", userId=" + userId + ",ETA="+ETA+ "]";
	}

	
	
	
	
}
