package com.tap.model;

public class userFeedBack {

	private int userId;
	private String feedBack;
	
	public userFeedBack() {
		// TODO Auto-generated constructor stub
	}

	public userFeedBack(int userId, String feedBack) {
		super();
		this.userId = userId;
		this.feedBack = feedBack;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	
	
	
}
