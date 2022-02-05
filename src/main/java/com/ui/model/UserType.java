package com.ui.model;

public class UserType {
	public UserType(int userTypeId, String userTypeName) {
		super();
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;		
	}
	public UserType(int userTypeId, String userTypeName, int createdBy, String ipAddress) {
		super();
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public UserType(String userTypeName, String status, int createdBy, String ipAddress) {
		super();
		this.userTypeName = userTypeName;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public UserType() {
		// TODO Auto-generated constructor stub
	}
	private int userTypeId;
	private String userTypeName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getUserTypeId() {
		return userTypeId;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public String getStatus() {
		return status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
}
