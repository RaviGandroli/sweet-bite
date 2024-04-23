package com.tap.dao;

import java.util.List;

import com.tap.model.Menu;
import com.tap.model.Restaurant;

public interface MenuDao {

	  public boolean addMenu(Menu menu);
		
		public Menu getMenu(int menuId);
		
		public List<Menu> getAllMenu(int restaurantId);
		
		public boolean updateMenu(Menu menu);
		
		public boolean deleteMenu(int menuId);
		
		public boolean deleteMenuByRes(int restaurantId);
		public boolean deleteMenuByResAndName(int restaurantId,String menuName);
}
