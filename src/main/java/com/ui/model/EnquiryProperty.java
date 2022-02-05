package com.ui.model;

public class EnquiryProperty {

	public EnquiryProperty(int enquiryPropertyId, int enquiryId, String unitName, String propertyTitle,
			String projectTitle, String categoryTitle, String subcategoryTitle, String realestateTitle) {
		super();
		this.enquiryPropertyId = enquiryPropertyId;
		this.enquiryId = enquiryId;
		this.unitName = unitName;
		this.propertyTitle = propertyTitle;
		this.projectTitle = projectTitle;
		this.categoryTitle = categoryTitle;
		this.subcategoryTitle = subcategoryTitle;
		this.realestateTitle = realestateTitle;
	}

	public EnquiryProperty(int enquiryPropertyId, int enquiryId, int projectId, String unitName, String propertyTitle,
			String projectTitle, String categoryTitle, String subcategoryTitle, String realestateTitle) {
		super();
		this.enquiryPropertyId = enquiryPropertyId;
		this.enquiryId = enquiryId;
		this.projectId = projectId;
		this.unitName = unitName;
		this.propertyTitle = propertyTitle;
		this.projectTitle = projectTitle;
		this.categoryTitle = categoryTitle;
		this.subcategoryTitle = subcategoryTitle;
		this.realestateTitle = realestateTitle;
	}

	public EnquiryProperty(int enquiryId, int projectId, int categoryId, int subcategoryId, int realestaeId,
			int propertyUnitMasterId, int propertyId, String status, int createdBy, String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestaeId = realestaeId;
		this.propertyUnitMasterId = propertyUnitMasterId;
		this.propertyId = propertyId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public EnquiryProperty() {
		// TODO Auto-generated constructor stub
	}

	private int enquiryPropertyId;
	private int enquiryId;
	private int projectId;
	private int categoryId;
	private int subcategoryId;
	private int realestaeId;
	private int propertyUnitMasterId;
	private int propertyId;
	private String unitName;
	private String propertyTitle;
	private String projectTitle;
	private String categoryTitle;
	private String subcategoryTitle;
	private String realestateTitle;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getEnquiryPropertyId() {
		return enquiryPropertyId;
	}

	public int getEnquiryId() {
		return enquiryId;
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

	public String getProjectTitle() {
		return projectTitle;
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

	public int getProjectId() {
		return projectId;
	}

	public int getPropertyUnitMasterId() {
		return propertyUnitMasterId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public String getUnitName() {
		return unitName;
	}

	public String getPropertyTitle() {
		return propertyTitle;
	}

	public void setEnquiryPropertyId(int enquiryPropertyId) {
		this.enquiryPropertyId = enquiryPropertyId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
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

	public void setRealestaeId(int realestaeId) {
		this.realestaeId = realestaeId;
	}

	public void setPropertyUnitMasterId(int propertyUnitMasterId) {
		this.propertyUnitMasterId = propertyUnitMasterId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setPropertyTitle(String propertyTitle) {
		this.propertyTitle = propertyTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
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
