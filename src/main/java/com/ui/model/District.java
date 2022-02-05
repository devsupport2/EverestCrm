package com.ui.model;

public class District {
	public District(int districtId, String districtName) {
		super();
		this.districtId = districtId;
		this.districtName = districtName;
	}
	public District(int districtId, int stateId, int countryId, String districtName, String districtCode) {
		super();
		this.districtId = districtId;
		this.stateId = stateId;
		this.countryId = countryId;
		this.districtName = districtName;
		this.districtCode = districtCode;
	}
	public District(int districtId, int stateId, int countryId, String districtName, String districtCode, int createdBy,
			String ipAddress) {
		super();
		this.districtId = districtId;
		this.stateId = stateId;
		this.countryId = countryId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public District(int stateId, int countryId, String districtName, String districtCode, String status, int createdBy,
			String ipAddress) {
		super();
		this.stateId = stateId;
		this.countryId = countryId;
		this.districtName = districtName;
		this.districtCode = districtCode;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int districtId;
	private int stateId;
	private int countryId;
	private String districtName;
	private String districtCode;	
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getDistrictId() {
		return districtId;
	}
	public int getStateId() {
		return stateId;
	}
	public int getCountryId() {
		return countryId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public String getDistrictCode() {
		return districtCode;
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
