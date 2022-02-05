package com.ui.model;

public class RealestateType {

	public RealestateType(int realestateTypeId, String realestateTypeName, String realestateCode, String image,
			String description) {
		super();
		this.realestateTypeId = realestateTypeId;
		this.realestateTypeName = realestateTypeName;
		this.realestateCode = realestateCode;
		this.image = image;
		this.description = description;
	}

	public RealestateType(int realestateTypeId, String realestateTypeName) {
		super();
		this.realestateTypeId = realestateTypeId;
		this.realestateTypeName = realestateTypeName;
	}

	public RealestateType(String realestateTypeName, String realestateCode, String image, String description,
			String status, int createdBy, String ipAddress) {
		super();
		this.realestateTypeName = realestateTypeName;
		this.realestateCode = realestateCode;
		this.image = image;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public RealestateType(int realestateTypeId, String realestateTypeName, String realestateCode, String image,
			String description, int createdBy, String ipAddress) {
		super();
		this.realestateTypeId = realestateTypeId;
		this.realestateTypeName = realestateTypeName;
		this.realestateCode = realestateCode;
		this.image = image;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public RealestateType() {
		// TODO Auto-generated constructor stub
	}

	private int realestateTypeId;
	private String realestateTypeName;
	private String realestateCode;
	private String image;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getRealestateTypeId() {
		return realestateTypeId;
	}

	public String getRealestateTypeName() {
		return realestateTypeName;
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

	public String getRealestateCode() {
		return realestateCode;
	}

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public void setRealestateTypeId(int realestateTypeId) {
		this.realestateTypeId = realestateTypeId;
	}

	public void setRealestateTypeName(String realestateTypeName) {
		this.realestateTypeName = realestateTypeName;
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
