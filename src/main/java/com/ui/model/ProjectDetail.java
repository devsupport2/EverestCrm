package com.ui.model;

public class ProjectDetail {
	public ProjectDetail(String unitName, int unitMasterId) {
		super();
		this.unitName = unitName;
		this.unitMasterId = unitMasterId;
	}

	public ProjectDetail(int subcategoryId, int realestaeId, String realestateTitle) {
		super();
		this.subcategoryId = subcategoryId;
		this.realestaeId = realestaeId;
		this.realestateTitle = realestateTitle;
	}

	public ProjectDetail(int subcategoryId, String subcategoryTitle) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryTitle = subcategoryTitle;
	}

	public ProjectDetail(int projectDetailId, int projectId, int categoryId, int subcategoryId, int unitMasterId,
			int realestaeId, String numberOfUnits, String categoryTitle, String subcategoryTitle,
			String realestateTitle, String unitName) {
		super();
		this.projectDetailId = projectDetailId;
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.unitMasterId = unitMasterId;
		this.realestaeId = realestaeId;
		this.numberOfUnits = numberOfUnits;
		this.categoryTitle = categoryTitle;
		this.subcategoryTitle = subcategoryTitle;
		this.realestateTitle = realestateTitle;
		this.unitName = unitName;
	}

	public ProjectDetail(int projectId, int categoryId, int subcategoryId, int unitMasterId, int realestaeId,
			String numberOfUnits, String status, int createdBy, String ipAddress) {
		super();
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.unitMasterId = unitMasterId;
		this.realestaeId = realestaeId;
		this.numberOfUnits = numberOfUnits;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public ProjectDetail() {
		// TODO Auto-generated constructor stub
	}

	private int projectDetailId;
	private int projectId;
	private int categoryId;
	private int subcategoryId;
	private int unitMasterId;
	private int realestaeId;
	private String numberOfUnits;
	private String categoryTitle;
	private String subcategoryTitle;
	private String realestateTitle;
	private String unitName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getProjectDetailId() {
		return projectDetailId;
	}

	public int getProjectId() {
		return projectId;
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

	public int getCategoryId() {
		return categoryId;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public int getRealestaeId() {
		return realestaeId;
	}

	public String getNumberOfUnits() {
		return numberOfUnits;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public String getSubcategoryTitle() {
		return subcategoryTitle;
	}

	public String getRealestateTitle() {
		return realestateTitle;
	}

	public int getUnitMasterId() {
		return unitMasterId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setProjectDetailId(int projectDetailId) {
		this.projectDetailId = projectDetailId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public void setUnitMasterId(int unitMasterId) {
		this.unitMasterId = unitMasterId;
	}

	public void setRealestaeId(int realestaeId) {
		this.realestaeId = realestaeId;
	}

	public void setNumberOfUnits(String numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public void setSubcategoryTitle(String subcategoryTitle) {
		this.subcategoryTitle = subcategoryTitle;
	}

	public void setRealestateTitle(String realestateTitle) {
		this.realestateTitle = realestateTitle;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
