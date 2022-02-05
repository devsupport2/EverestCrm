package com.ui.model;

public class MeasurementUnit {
	
	public MeasurementUnit(int measurementUnitId, String measurementUnitName, String description) {
		super();
		this.measurementUnitId = measurementUnitId;
		this.measurementUnitName = measurementUnitName;
		this.description = description;
	}	
	public MeasurementUnit(int measurementUnitId, String measurementUnitName, String description, int createdBy,
			String ipAddress) {
		super();
		this.measurementUnitId = measurementUnitId;
		this.measurementUnitName = measurementUnitName;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public MeasurementUnit(String measurementUnitName, String description, String status, int createdBy,
			String ipAddress) {
		super();
		this.measurementUnitName = measurementUnitName;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int measurementUnitId;
	private String measurementUnitName;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getMeasurementUnitId() {
		return measurementUnitId;
	}
	public String getMeasurementUnitName() {
		return measurementUnitName;
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
