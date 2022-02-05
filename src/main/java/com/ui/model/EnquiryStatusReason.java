package com.ui.model;

public class EnquiryStatusReason {
	public EnquiryStatusReason(int reasonId, String statusReason) {
		super();
		this.reasonId = reasonId;
		this.statusReason = statusReason;
	}
	public EnquiryStatusReason(String statusReason, String status, int createdBy, String ipAddress) {
		super();
		this.statusReason = statusReason;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int reasonId;
	private String statusReason;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getReasonId() {
		return reasonId;
	}
	public String getStatusReason() {
		return statusReason;
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
