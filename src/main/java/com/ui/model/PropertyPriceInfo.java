package com.ui.model;

public class PropertyPriceInfo {

	public PropertyPriceInfo(int propertyPriceInfoId, String propertyPriceLable, float propertyPriceValue, int unitId,
			String unitTitle) {
		super();
		this.propertyPriceInfoId = propertyPriceInfoId;
		this.propertyPriceLable = propertyPriceLable;
		this.propertyPriceValue = propertyPriceValue;
		this.unitId = unitId;
		this.unitTitle = unitTitle;
	}

	public PropertyPriceInfo(int propertyId, String propertyPriceLable, float propertyPriceValue, int unitId,
			String status, int createdBy, String ipAddress) {
		super();
		this.propertyId = propertyId;
		this.propertyPriceLable = propertyPriceLable;
		this.propertyPriceValue = propertyPriceValue;
		this.unitId = unitId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int propertyPriceInfoId;
	private int propertyId;
	private String propertyPriceLable;
	private float propertyPriceValue;
	private int unitId;
	private String unitTitle;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getPropertyPriceInfoId() {
		return propertyPriceInfoId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public String getPropertyPriceLable() {
		return propertyPriceLable;
	}

	public float getPropertyPriceValue() {
		return propertyPriceValue;
	}

	public int getUnitId() {
		return unitId;
	}

	public String getUnitTitle() {
		return unitTitle;
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
