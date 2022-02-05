package com.ui.model;

public class RealestateSubcategory {

	public RealestateSubcategory(int realestateSubcategoryId, int realestateTypeId, String subcategoryTitle,
			String subcategoryCode, String image, String description) {
		super();
		this.realestateSubcategoryId = realestateSubcategoryId;
		this.realestateTypeId = realestateTypeId;
		this.subcategoryTitle = subcategoryTitle;
		this.subcategoryCode = subcategoryCode;
		this.image = image;
		this.description = description;
	}

	public RealestateSubcategory(int realestateSubcategoryId, String subcategoryTitle) {
		super();
		this.realestateSubcategoryId = realestateSubcategoryId;
		this.subcategoryTitle = subcategoryTitle;
	}

	public RealestateSubcategory(int realestateTypeId, String subcategoryTitle, String subcategoryCode, String image,
			String description, String status, int createdBy, String ipAddress) {
		super();
		this.realestateTypeId = realestateTypeId;
		this.subcategoryTitle = subcategoryTitle;
		this.subcategoryCode = subcategoryCode;
		this.image = image;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public RealestateSubcategory(int realestateSubcategoryId, int realestateTypeId, String subcategoryTitle,
			String subcategoryCode, String image, String description, int createdBy, String ipAddress) {
		super();
		this.realestateSubcategoryId = realestateSubcategoryId;
		this.realestateTypeId = realestateTypeId;
		this.subcategoryTitle = subcategoryTitle;
		this.subcategoryCode = subcategoryCode;
		this.image = image;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public RealestateSubcategory() {
		// TODO Auto-generated constructor stub
	}

	private int realestateSubcategoryId;
	private int realestateTypeId;
	private String subcategoryTitle;
	private String subcategoryCode;
	private String image;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String realestateTypeName;

	public int getRealestateSubcategoryId() {
		return realestateSubcategoryId;
	}

	public int getRealestateTypeId() {
		return realestateTypeId;
	}

	public String getSubcategoryTitle() {
		return subcategoryTitle;
	}

	public String getSubcategoryCode() {
		return subcategoryCode;
	}

	public String getImage() {
		return image;
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

	public void setRealestateSubcategoryId(int realestateSubcategoryId) {
		this.realestateSubcategoryId = realestateSubcategoryId;
	}

	public void setRealestateTypeId(int realestateTypeId) {
		this.realestateTypeId = realestateTypeId;
	}

	public void setSubcategoryTitle(String subcategoryTitle) {
		this.subcategoryTitle = subcategoryTitle;
	}

	public void setSubcategoryCode(String subcategoryCode) {
		this.subcategoryCode = subcategoryCode;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getRealestateTypeName() {
		return realestateTypeName;
	}

	public void setRealestateTypeName(String realestateTypeName) {
		this.realestateTypeName = realestateTypeName;
	}
}
