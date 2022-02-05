package com.ui.model;

public class AllEnquiryStatus {
	public AllEnquiryStatus(int enquiryId, String enquiryStatus, int reasonId, String status, int createdBy,
			String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.enquiryStatus = enquiryStatus;
		this.reasonId = reasonId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int allEnquiryStatusId;
	private int enquiryId;
	private String enquiryStatus;
	private int reasonId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getAllEnquiryStatusId() {
		return allEnquiryStatusId;
	}
	public int getEnquiryId() {
		return enquiryId;
	}
	public String getEnquiryStatus() {
		return enquiryStatus;
	}
	public int getReasonId() {
		return reasonId;
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
}
