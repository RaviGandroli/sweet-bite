package com.tap.dao;

import java.util.List;

import com.tap.model.Order;
import com.tap.model.OrderHistory;

public interface OrderHistoryDao {

	 public boolean addOrderHistory(OrderHistory orderHistory);
		
		public OrderHistory getOrderHistory(int orderHistoryId);
		
		public List<OrderHistory> getAllOrderHistory();
		
		public boolean updateOrderHistory(OrderHistory orderHistory);
		
		public boolean deleteOrderHistory(int orderHistoryId);
		
		public List<OrderHistory> getOrderHistoryByUser(int userId);
}
