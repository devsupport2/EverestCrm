package com.ui.model;

public class EnquiryFollowup {
	public EnquiryFollowup(int followupId, int enquiryId, String followupDateTime, String remark) {
		super();
		this.followupId = followupId;
		this.enquiryId = enquiryId;
		this.followupDateTime = followupDateTime;
		this.remark = remark;
	}
	
	public EnquiryFollowup(int followupId, String status, int createdBy, String ipAddress) {
		super();
		this.followupId = followupId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public EnquiryFollowup(int followupId, int enquiryId, String followupDateTime, String remark, int createdBy,
			String firstName, String lastName) {
		super();
		this.followupId = followupId;
		this.enquiryId = enquiryId;
		this.followupDateTime = followupDateTime;
		this.remark = remark;
		this.createdBy = createdBy;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public EnquiryFollowup(int enquiryId, String followupDateTime, String remark, String status, int createdBy,
			String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.followupDateTime = followupDateTime;
		this.remark = remark;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int followupId;
	private int enquiryId;
	private String followupDateTime;
	private String remark;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String firstName;
	private String lastName;
	public int getFollowupId() {
		return followupId;
	}
	public int getEnquiryId() {
		return enquiryId;
	}
	public String getFollowupDateTime() {
		return followupDateTime;
	}
	public String getRemark() {
		return remark;
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
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
}
