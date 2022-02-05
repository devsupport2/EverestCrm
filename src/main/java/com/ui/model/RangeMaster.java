package com.ui.model;

public class RangeMaster {
	private int rangeId;
	private float rangeFrom;
	private float rangeTo;
	private int unitId;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String unitName;
	public int getRangeId() {
		return rangeId;
	}
	public void setRangeId(int rangeId) {
		this.rangeId = rangeId;
	}
	public float getRangeFrom() {
		return rangeFrom;
	}
	public void setRangeFrom(float rangeFrom) {
		this.rangeFrom = rangeFrom;
	}
	public float getRangeTo() {
		return rangeTo;
	}
	public void setRangeTo(float rangeTo) {
		this.rangeTo = rangeTo;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
  public String getUnitName() {
    return unitName;
  }
  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }
	
}
