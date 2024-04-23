package com.tap.model;

public class DeliveryItems {

	private int deliveryOrderId;
  private 	String agentName;
  private int OTP;
  private String orderId;
  private int orderItemId;
  private String password;
  
  public DeliveryItems() {
	// TODO Auto-generated constructor stub
}

public DeliveryItems(String agentName, int oTP, String orderId, String password) {
	super();
	this.deliveryOrderId = deliveryOrderId;
	this.agentName = agentName;
	this.OTP = oTP;
	this.orderId = orderId;
	this.orderItemId = orderItemId;
    this.password= password;

}

public DeliveryItems(String agentName, int oTP, String orderId, int orderItemId,String password) {
	super();
	this.agentName = agentName;
	this.OTP = oTP;
	this.orderId = orderId;
	this.orderItemId = orderItemId;
    this.password= password;

}

public int getDeliveryOrderId() {
	return deliveryOrderId;
}

public void setDeliveryOrderId(int deliveryOrderId) {
	this.deliveryOrderId = deliveryOrderId;
}

public String getAgentName() {
	return agentName;
}

public void setAgentName(String agentName) {
	this.agentName = agentName;
}

public int getOTP() {
	return OTP;
}

public void setOTP(int oTP) {
	OTP = oTP;
}

public String getOrderId() {
	return orderId;
}

public void setOrderId(String orderId) {
	this.orderId = orderId;
}

public int getOrderItemId() {
	return orderItemId;
}

public void setOrderItemId(int orderItemId) {
	this.orderItemId = orderItemId;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public String toString() {
	return "DeliveryItems [deliveryOrderId=" + deliveryOrderId + ", agentName=" + agentName + ", OTP=" + OTP
			+ ", orderId=" + orderId + ", orderItemId=" + orderItemId + "]";
}
  
	
}
