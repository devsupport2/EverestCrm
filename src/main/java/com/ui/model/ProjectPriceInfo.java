package com.ui.model;

public class ProjectPriceInfo {

	public ProjectPriceInfo(String priceLable, String priceValue, int unitId, String unitName) {
		super();
		this.priceLable = priceLable;
		this.priceValue = priceValue;
		this.unitId = unitId;
		this.unitName = unitName;
	}

	public ProjectPriceInfo(int projectPriceInfoId, int projectId, String priceLable, String priceValue,
			String unitName) {
		super();
		this.projectPriceInfoId = projectPriceInfoId;
		this.projectId = projectId;
		this.priceLable = priceLable;
		this.priceValue = priceValue;
		this.unitName = unitName;
	}

	public ProjectPriceInfo(int projectPriceInfoId, int projectId, String priceLable, String priceValue,
			String categoryTitle, String subcategoryTitle, String realestateTitle, String unitMasterName,
			String unitName) {
		super();
		this.projectPriceInfoId = projectPriceInfoId;
		this.projectId = projectId;
		this.priceLable = priceLable;
		this.priceValue = priceValue;
		this.categoryTitle = categoryTitle;
		this.subcategoryTitle = subcategoryTitle;
		this.realestateTitle = realestateTitle;
		this.unitMasterName = unitMasterName;
		this.unitName = unitName;
	}

	public ProjectPriceInfo(int projectId, int categoryId, int subcategoryId, int realestaeId, int unitMasterId,
			String priceLable, String priceValue, int unitId, String status, int createdBy, String ipAddress) {
		super();
		this.projectId = projectId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestaeId = realestaeId;
		this.unitMasterId = unitMasterId;
		this.priceLable = priceLable;
		this.priceValue = priceValue;
		this.unitId = unitId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int projectPriceInfoId;
	private int projectId;
	private int categoryId;
	private int subcategoryId;
	private int realestaeId;
	private int unitMasterId;
	private String priceLable;
	private String priceValue;
	private int unitId;
	private String categoryTitle;
	private String subcategoryTitle;
	private String realestateTitle;
	private String unitName;
	private String unitMasterName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getProjectPriceInfoId() {
		return projectPriceInfoId;
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

	public int getRealestaeId() {
		return realestaeId;
	}

	public String getPriceLable() {
		return priceLable;
	}

	public String getPriceValue() {
		return priceValue;
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

	public int getUnitId() {
		return unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public int getUnitMasterId() {
		return unitMasterId;
	}

	public String getUnitMasterName() {
		return unitMasterName;
	}

}
