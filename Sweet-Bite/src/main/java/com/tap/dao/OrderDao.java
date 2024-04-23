package com.tap.dao;

import java.util.List;

import com.tap.model.Restaurant;
import com.tap.model.Order;

public interface OrderDao {

	 public String addOrder(Order order);
		
		public Order getOrder(String orderId);
		
		public List<Order> getAllOrder();
		
		public boolean updateOrder(Order order);
		
		public boolean deleteOrder(String orderId);

		List<Order> getOrderByUser(int userId);

}
