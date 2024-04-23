package com.tap.dao;

import java.util.List;


import com.tap.model.Restaurant;


public interface RestaurantDao {

    public boolean addRestaurant(Restaurant restaurant);
	
	public Restaurant getRestaurant(int restaurantId);
	
	public  List<Restaurant> getAllRestaurant();
	
	public boolean updateRestaurant(Restaurant restaurant);
	
	public boolean deleteRestaurant(int restaurantId);
	
	public Restaurant getRestaurantByOwner(int restaurantOwnerId);
}
