package com.ui.model;

public class Taluka {
	public Taluka(int talukaId, String talukaName) {
		super();
		this.talukaId = talukaId;
		this.talukaName = talukaName;
	}
	public Taluka(int talukaId, int districtId, int stateId, int countryId, String talukaName, String talukaCode) {
		super();
		this.talukaId = talukaId;
		this.districtId = districtId;
		this.stateId = stateId;
		this.countryId = countryId;
		this.talukaName = talukaName;
		this.talukaCode = talukaCode;
	}
	public Taluka(int talukaId, int districtId, int stateId, int countryId, String talukaName, String talukaCode,
			int createdBy, String ipAddress) {
		super();
		this.talukaId = talukaId;
		this.districtId = districtId;
		this.stateId = stateId;
		this.countryId = countryId;
		this.talukaName = talukaName;
		this.talukaCode = talukaCode;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Taluka(int districtId, int stateId, int countryId, String talukaName, String talukaCode, String status,
			int createdBy, String ipAddress) {
		super();
		this.districtId = districtId;
		this.stateId = stateId;
		this.countryId = countryId;
		this.talukaName = talukaName;
		this.talukaCode = talukaCode;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int talukaId;
	private int districtId;
	private int stateId;
	private int countryId;
	private String talukaName;
	private String talukaCode;	
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getTalukaId() {
		return talukaId;
	}
	public int getDistrictId() {
		return districtId;
	}
	public int getStateId() {
		return stateId;
	}
	public int getCountryId() {
		return countryId;
	}
	public String getTalukaName() {
		return talukaName;
	}
	public String getTalukaCode() {
		return talukaCode;
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
