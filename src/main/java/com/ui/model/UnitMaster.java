package com.ui.model;

public class UnitMaster {
	public UnitMaster(int unitMasterId, String unitName) {
		super();
		this.unitMasterId = unitMasterId;
		this.unitName = unitName;
	}
	public UnitMaster(String unitName, String status, int createdBy, String ipAddress) {
		super();
		this.unitName = unitName;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int unitMasterId;
	private String unitName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getUnitMasterId() {
		return unitMasterId;
	}
	public String getUnitName() {
		return unitName;
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
