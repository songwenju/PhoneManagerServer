package com.wjustudio.user.domain;

public class UserContact {
	private int id;
	private int userId;
	private String contactJsonUrl;
	
	public UserContact(int userId, String contactJsonUrl) {
		super();
		this.userId = userId;
		this.contactJsonUrl = contactJsonUrl;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getContactJsonUrl() {
		return contactJsonUrl;
	}

	public void setContactJsonUrl(String contactJsonUrl) {
		this.contactJsonUrl = contactJsonUrl;
	}

	@Override
	public String toString() {
		return "UserContact [id=" + id + ", userId=" + userId
				+ ", contactJsonUrl=" + contactJsonUrl + "]";
	}
	
}
