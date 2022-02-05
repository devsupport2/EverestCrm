package com.ui.model;

public class Country {
	
	

	public Country(int countryId, String countryName, String countryCode, String countryDialingCode) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.countryDialingCode = countryDialingCode;
	}
	public Country(int countryId, String countryName, String countryCode, String countryDialingCode, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.countryDialingCode = countryDialingCode;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Country(int countryId, String countryName, String countryCode, String countryDialingCode, int createdBy, String ipAddress) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.countryDialingCode = countryDialingCode;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Country(String countryName, String countryCode, String countryDialingCode, String status, int createdBy, String ipAddress) {
		super();
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.countryDialingCode = countryDialingCode;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int countryId;
	private String countryName;
	private String countryCode;
	private String countryDialingCode;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getCountryId() {
		return countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public String getCountryDialingCode() {
		return countryDialingCode;
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
