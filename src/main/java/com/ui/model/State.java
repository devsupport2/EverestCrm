package com.ui.model;

public class State {
	
	
	public State(int stateId, String stateCode, int countryId) {
		super();
		this.stateId = stateId;
		this.stateCode = stateCode;
		this.countryId = countryId;
	}
	public State(String stateName, String stateCode, int countryId) {
		super();
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.countryId = countryId;
	}
	public State(int stateId, String stateName) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
	}
	public State(int stateId, String stateName, String stateCode, int countryId, String countryName) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.countryId = countryId;		
		this.countryName = countryName;
	}
	public State(int stateId, String stateName, String stateCode, int countryId, int createdBy, String ipAddress) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.countryId = countryId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public State(String stateName, String stateCode, int countryId, String status, int createdBy, String ipAddress) {
		super();
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.countryId = countryId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int stateId;
	private String stateName;
	private String stateCode;
	private int countryId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String countryName;
	
	
	public int getStateId() {
		return stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public int getCountryId() {
		return countryId;
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
	public String getCountryName() {
		return countryName;
	}
	
	
	
		
	
}
