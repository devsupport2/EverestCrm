package com.ui.model;

public class EnquiryStatus {
	public EnquiryStatus(int enquiryStatusId, int enquiryId, int quotationId, int salesOrderId, String remark,
			String logDateTime) {
		super();
		this.enquiryStatusId = enquiryStatusId;
		this.enquiryId = enquiryId;
		this.quotationId = quotationId;
		this.salesOrderId = salesOrderId;
		this.remark = remark;
		this.logDateTime = logDateTime;
	}
	
	public EnquiryStatus(int enquiryId, int statusId, String remark, String logDateTime, String status, int createdBy, String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.statusId = statusId;
		this.remark = remark;
		this.logDateTime = logDateTime;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public EnquiryStatus(int enquiryId, int quotationId, int salesOrderId, int statusId, String status, int createdBy,
			String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.quotationId = quotationId;
		this.salesOrderId = salesOrderId;
		this.statusId = statusId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public EnquiryStatus(int enquiryStatusId, int enquiryId, int quotationId, int statusId, String logDateTime, int createdBy,
			String createdDate, String enquiryNo, String statusName, String firstName, String lastName) {
		super();
		this.enquiryStatusId = enquiryStatusId;
		this.enquiryId = enquiryId;
		this.quotationId = quotationId;
		this.statusId = statusId;
		this.logDateTime = logDateTime;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.enquiryNo = enquiryNo;
		this.statusName = statusName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public EnquiryStatus(int enquiryId, int quotationId, int statusId, String status, int createdBy, String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.quotationId = quotationId;
		this.statusId = statusId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int enquiryStatusId;
	private int enquiryId;
	private int quotationId;
	private int salesOrderId;
	private int statusId;
	private String remark;
	private String logDateTime;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String enquiryNo;
	private String statusName;
	private String firstName;
	private String lastName;

	public int getEnquiryStatusId() {
		return enquiryStatusId;
	}

	public int getEnquiryId() {
		return enquiryId;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public int getStatusId() {
		return statusId;
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

	public String getEnquiryNo() {
		return enquiryNo;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public String getRemark() {
		return remark;
	}

	public String getLogDateTime() {
		return logDateTime;
	}

}
