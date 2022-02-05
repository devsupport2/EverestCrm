package com.ui.model;

public class EnquiryAssign {
	public EnquiryAssign(int enquiryAssignId, int enquiryId, int userId) {
		super();
		this.enquiryAssignId = enquiryAssignId;
		this.enquiryId = enquiryId;
		this.userId = userId;
	}
	public EnquiryAssign(int enquiryAssignId, int enquiryId, int userId, String firstName, String lastName) {
		super();
		this.enquiryAssignId = enquiryAssignId;
		this.enquiryId = enquiryId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public EnquiryAssign(int enquiryId, int userId, int createdBy, String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.userId = userId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int enquiryAssignId;
	private int enquiryId;
	private int userId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String firstName;
	private String lastName;
	public int getEnquiryAssignId() {
		return enquiryAssignId;
	}
	public int getEnquiryId() {
		return enquiryId;
	}
	public int getUserId() {
		return userId;
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
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
}
