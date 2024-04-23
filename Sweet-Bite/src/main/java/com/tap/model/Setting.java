package com.tap.model;

public class Setting {
	private String language;
	private String theme;
	private String privacy;
	private String emailNotification;
	
	
	public Setting() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Setting(String language, String theme, String privacy, String emailNotification) {
		super();
		this.language = language;
		this.theme = theme;
		this.privacy = privacy;
		this.emailNotification = emailNotification;
	}



	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public String getEmailNotification() {
		return emailNotification;
	}
	public void setEmailNotification(String emailNotification) {
		this.emailNotification = emailNotification;
	}



	@Override
	public String toString() {
		return "Setting [language=" + language + ", theme=" + theme + ", privacy=" + privacy + ", emailNotification="
				+ emailNotification + "]";
	}
	
	
}
