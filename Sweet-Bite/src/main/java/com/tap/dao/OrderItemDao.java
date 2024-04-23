package com.tap.dao;

import java.util.List;

import com.tap.model.Order;
import com.tap.model.OrderItem;

public interface OrderItemDao {

	 public boolean addOrderItem(OrderItem orderItem);
		
		public OrderItem getOrderItem(int orderItemId);
		
		public List<OrderItem> getAllOrderItems();
		
		public boolean updateOrderItem(OrderItem orderItem);
		
		public boolean deleteOrderItem(int orderItemId);

		List<OrderItem> getOrderItemByUser(int userId);
}
