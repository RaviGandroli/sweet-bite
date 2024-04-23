package com.tap.dao;

import java.util.List;

import com.tap.model.DeliveryItems;




public interface DeliveryDao {

	  public boolean addDeliveryOrders(DeliveryItems deliveryitems);
		
			public DeliveryItems getDeliveryOrders(int deliveryOrderId);
			
			public List<DeliveryItems> getAllDeliveryOrders();
			
			public boolean updateDeliveryOrders(DeliveryItems deliveryitems);
			
			
			boolean deleteDeliveryOrders(int deliveryOrderId);

			List<DeliveryItems> getAllDeliveryOrdersByAgent(String agentName, String password);
			
			
}
