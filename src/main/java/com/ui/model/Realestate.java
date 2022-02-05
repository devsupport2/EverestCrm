package com.ui.model;

public class Realestate {

	public Realestate(int realestateId, int realestateTypeId, int realestateSubcategoryId, String realestateTitle,
			String realestateCode, String image, String description) {
		super();
		this.realestateId = realestateId;
		this.realestateTypeId = realestateTypeId;
		this.realestateSubcategoryId = realestateSubcategoryId;
		this.realestateTitle = realestateTitle;
		this.realestateCode = realestateCode;
		this.image = image;
		this.description = description;
	}

	public Realestate(int realestateId, String realestateTitle) {
		super();
		this.realestateId = realestateId;
		this.realestateTitle = realestateTitle;
	}

	public Realestate(int realestateTypeId, int realestateSubcategoryId, String realestateTitle, String realestateCode,
			String image, String description, String status, int createdBy, String ipAddress) {
		super();
		this.realestateTypeId = realestateTypeId;
		this.realestateSubcategoryId = realestateSubcategoryId;
		this.realestateTitle = realestateTitle;
		this.realestateCode = realestateCode;
		this.image = image;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Realestate(int realestateId, int realestateTypeId, int realestateSubcategoryId, String realestateTitle,
			String realestateCode, String image, String description, int createdBy, String ipAddress) {
		super();
		this.realestateId = realestateId;
		this.realestateTypeId = realestateTypeId;
		this.realestateSubcategoryId = realestateSubcategoryId;
		this.realestateTitle = realestateTitle;
		this.realestateCode = realestateCode;
		this.image = image;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Realestate() {
		// TODO Auto-generated constructor stub
	}

	private int realestateId;
	private int realestateTypeId;
	private int realestateSubcategoryId;
	private String realestateTitle;
	private String realestateCode;
	private String image;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getRealestateId() {
		return realestateId;
	}

	public int getRealestateTypeId() {
		return realestateTypeId;
	}

	public int getRealestateSubcategoryId() {
		return realestateSubcategoryId;
	}

	public String getRealestateTitle() {
		return realestateTitle;
	}

	public String getRealestateCode() {
		return realestateCode;
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

	public void setRealestateId(int realestateId) {
		this.realestateId = realestateId;
	}

	public void setRealestateTypeId(int realestateTypeId) {
		this.realestateTypeId = realestateTypeId;
	}

	public void setRealestateSubcategoryId(int realestateSubcategoryId) {
		this.realestateSubcategoryId = realestateSubcategoryId;
	}

	public void setRealestateTitle(String realestateTitle) {
		this.realestateTitle = realestateTitle;
	}

	public void setRealestateCode(String realestateCode) {
		this.realestateCode = realestateCode;
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

}
