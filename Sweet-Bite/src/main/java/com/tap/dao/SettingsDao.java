package com.tap.dao;

import com.tap.model.Setting;

public interface SettingsDao {

	public boolean updateSettings(Setting settings,int userId);
	
	public boolean addSetting(Setting setting,int userId);
	
	public Setting getSetting(int userId); 
}
