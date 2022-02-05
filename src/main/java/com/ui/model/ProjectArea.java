package com.ui.model;

public class ProjectArea {
	public ProjectArea(int areaId, int unitId, String areaValue, String areaTypeTitle, String measurementUnitName) {
		super();
		this.areaId = areaId;
		this.unitId = unitId;
		this.areaValue = areaValue;
		this.areaTypeTitle = areaTypeTitle;
		this.measurementUnitName = measurementUnitName;
	}
	public ProjectArea(int projectAreaId, int projectId, int categoryId, int subcategoryId, int realestateId,
			int unitMasterId, int areaId, int unitId, String areaValue, String realestateTypeName, String subcategoryTitle,
			String realestateTitle, String areaTypeTitle, String measurementUnitName, String unitName) {
		super();
		this.projectAreaId = projectAreaId;
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestateId = realestateId;
		this.unitMasterId = unitMasterId;
		this.areaId = areaId;
		this.unitId = unitId;
		this.areaValue = areaValue;
		this.realestateTypeName = realestateTypeName;
		this.subcategoryTitle = subcategoryTitle;
		this.realestateTitle = realestateTitle;
		this.areaTypeTitle = areaTypeTitle;
		this.measurementUnitName = measurementUnitName;
		this.unitName = unitName;
	}
	public ProjectArea(int projectId, int categoryId, int subcategoryId, int realestateId, int unitMasterId, int areaId, int unitId,
			String areaValue, int createdBy, String ipAddress) {
		super();
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestateId = realestateId;
		this.unitMasterId = unitMasterId;
		this.areaId = areaId;
		this.unitId = unitId;
		this.areaValue = areaValue;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int projectAreaId;
	private int projectId;
	private int categoryId;
	private int subcategoryId;
	private int realestateId;
	private int unitMasterId;
	private int areaId;
	private int unitId;
	private String areaValue;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String realestateTypeName;
	private String subcategoryTitle;
	private String realestateTitle;
	private String areaTypeTitle;
	private String measurementUnitName;
	private String unitName;
	
	public int getProjectAreaId() {
		return projectAreaId;
	}
	public int getProjectId() {
		return projectId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public int getSubcategoryId() {
		return subcategoryId;
	}
	public int getRealestateId() {
		return realestateId;
	}
	public int getAreaId() {
		return areaId;
	}
	public int getUnitId() {
		return unitId;
	}
	public String getAreaValue() {
		return areaValue;
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
	public String getRealestateTypeName() {
		return realestateTypeName;
	}
	public String getSubcategoryTitle() {
		return subcategoryTitle;
	}
	public String getRealestateTitle() {
		return realestateTitle;
	}
	public String getAreaTypeTitle() {
		return areaTypeTitle;
	}
	public String getMeasurementUnitName() {
		return measurementUnitName;
	}
	public int getUnitMasterId() {
		return unitMasterId;
	}
	public String getUnitName() {
		return unitName;
	}	
}
