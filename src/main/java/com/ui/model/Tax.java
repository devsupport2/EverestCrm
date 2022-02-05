package com.ui.model;

public class Tax {
	
	public Tax(int taxId, String taxName, String description) {
		super();
		this.taxId = taxId;
		this.taxName = taxName;
		this.description = description;		
	}
	public Tax(int taxId, String taxName, String description, int createdBy,
			String ipAddress) {
		super();
		this.taxId = taxId;
		this.taxName = taxName;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Tax(String taxName, String description, String status, int createdBy,
			String ipAddress) {
		super();
		this.taxName = taxName;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int taxId;
	private String taxName;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getTaxId() {
		return taxId;
	}
	public String getTaxName() {
		return taxName;
	}
	public String getDescription() {
		return description;
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
