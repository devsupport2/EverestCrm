package com.ui.model;

public class Property {

	public Property(String realestateTitle, String realestateCode, int realestateId) {
		super();
		
		this.realestateTitle = realestateTitle;
		this.realestateCode = realestateCode;
		this.realestateId = realestateId;
	}

	public Property(int propertyId, int projectTypeId, int categoryId, int subcategoryId, int realestateId,
			String propertyTitle) {
		super();
		this.propertyId = propertyId;
		this.projectTypeId = projectTypeId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestateId = realestateId;
		this.propertyTitle = propertyTitle;
	}

	public Property(int propertyId, int sequence, String sku, int projectTypeId, int categoryId, int subcategoryId,
			int realestateId, int propertyTypeId, String propertyTitle, int propertyUnitMasterId, String floor,
			String waterSource, String drainage, String furnishing, String reservedParking, String propertyAvailability,
			String propertyAvailabilityDescription, String maintenanceCharges, float maintenanceAmount,
			String propertyDescription) {
		super();
		this.propertyId = propertyId;
		this.sequence = sequence;
		this.sku = sku;
		this.projectTypeId = projectTypeId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestateId = realestateId;
		this.propertyTypeId = propertyTypeId;
		this.propertyTitle = propertyTitle;
		this.propertyUnitMasterId = propertyUnitMasterId;
		this.floor = floor;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.furnishing = furnishing;
		this.reservedParking = reservedParking;
		this.propertyAvailability = propertyAvailability;
		this.propertyAvailabilityDescription = propertyAvailabilityDescription;
		this.maintenanceCharges = maintenanceCharges;
		this.maintenanceAmount = maintenanceAmount;
		this.propertyDescription = propertyDescription;
	}

	public Property(int propertyId, String propertyTitle, int propertyUnitMasterId) {
		super();
		this.propertyId = propertyId;
		this.propertyTitle = propertyTitle;
		this.propertyUnitMasterId = propertyUnitMasterId;
	}

	public Property(String realestateTitle, int propertyUnitMasterId, int realestateId) {
		super();
		this.realestateTitle = realestateTitle;
		this.propertyUnitMasterId = propertyUnitMasterId;
		this.realestateId = realestateId;
	}

	public Property(int subcategoryId, String subcategoryTitle) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryTitle = subcategoryTitle;
	}

	public Property(String realestateTypeName) {
		super();
		this.realestateTypeName = realestateTypeName;
	}

	public Property(String propertyTitle, String towerTitle) {
		super();
		this.propertyTitle = propertyTitle;
		this.towerTitle = towerTitle;
	}

	public Property(int propertyId, int projectTypeId, int categoryId, int subcategoryId, int realestateId,
			int propertyTypeId, String propertyTitle, int propertyUnitMasterId, String floor, String waterSource,
			String drainage, String furnishing, String reservedParking, String propertyAvailability,
			String propertyAvailabilityDescription, String maintenanceCharges, float maintenanceAmount,
			String propertyDescription, String propertyStatus, String status, int createdBy, String ipAddress) {
		super();
		this.propertyId = propertyId;
		this.projectTypeId = projectTypeId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestateId = realestateId;
		this.propertyTypeId = propertyTypeId;
		this.propertyTitle = propertyTitle;
		this.propertyUnitMasterId = propertyUnitMasterId;
		this.floor = floor;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.furnishing = furnishing;
		this.reservedParking = reservedParking;
		this.propertyAvailability = propertyAvailability;
		this.propertyAvailabilityDescription = propertyAvailabilityDescription;
		this.maintenanceCharges = maintenanceCharges;
		this.maintenanceAmount = maintenanceAmount;
		this.propertyDescription = propertyDescription;
		this.propertyStatus = propertyStatus;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Property(int propertyId, int projectTypeId, int categoryId, int subcategoryId, int realestateId,
			int propertyTypeId, String propertyTitle, int propertyUnitMasterId, String floor, String waterSource,
			String drainage, String furnishing, String reservedParking, String propertyAvailability,
			String propertyAvailabilityDescription, String maintenanceCharges, float maintenanceAmount,
			String propertyDescription, String propertyStatus) {
		super();
		this.propertyId = propertyId;
		this.projectTypeId = projectTypeId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestateId = realestateId;
		this.propertyTypeId = propertyTypeId;
		this.propertyTitle = propertyTitle;
		this.propertyUnitMasterId = propertyUnitMasterId;
		this.floor = floor;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.furnishing = furnishing;
		this.reservedParking = reservedParking;
		this.propertyAvailability = propertyAvailability;
		this.propertyAvailabilityDescription = propertyAvailabilityDescription;
		this.maintenanceCharges = maintenanceCharges;
		this.maintenanceAmount = maintenanceAmount;
		this.propertyDescription = propertyDescription;
		this.propertyStatus = propertyStatus;
	}

	public Property(int propertyId, String propertyTitle, String projectTitle, String realestateTypeName,
			String subcategoryTitle, String realestateTitle, String towerTitle, String floor, String propertyStatus) {
		super();
		this.propertyId = propertyId;
		this.propertyTitle = propertyTitle;
		this.realestateTypeName = realestateTypeName;
		this.subcategoryTitle = subcategoryTitle;
		this.realestateTitle = realestateTitle;
		this.projectTitle = projectTitle;
		this.towerTitle = towerTitle;
		this.floor = floor;
		this.propertyStatus = propertyStatus;
	}

	public Property(String sku, int sequence, int projectTypeId, int categoryId, int subcategoryId, int realestateId,
			int propertyTypeId, String propertyTitle, int propertyUnitMasterId, String floor, String waterSource,
			String drainage, String furnishing, String reservedParking, String propertyAvailability,
			String propertyAvailabilityDescription, String maintenanceCharges, float maintenanceAmount,
			String propertyDescription, String propertyStatus, String status, int createdBy, String ipAddress) {
		super();
		this.sku = sku;
		this.sequence = sequence;
		this.projectTypeId = projectTypeId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestateId = realestateId;
		this.propertyTypeId = propertyTypeId;
		this.propertyTitle = propertyTitle;
		this.propertyUnitMasterId = propertyUnitMasterId;
		this.floor = floor;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.furnishing = furnishing;
		this.reservedParking = reservedParking;
		this.propertyAvailability = propertyAvailability;
		this.propertyAvailabilityDescription = propertyAvailabilityDescription;
		this.maintenanceCharges = maintenanceCharges;
		this.maintenanceAmount = maintenanceAmount;
		this.propertyDescription = propertyDescription;
		this.propertyStatus = propertyStatus;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Property(int projectTypeId, int categoryId, int subcategoryId, int realestateId, int propertyTypeId,
			String propertyTitle, int propertyUnitMasterId, String floor, String waterSource, String drainage,
			String furnishing, String reservedParking, String propertyAvailability,
			String propertyAvailabilityDescription, String maintenanceCharges, float maintenanceAmount,
			String propertyDescription, String status, int createdBy, String ipAddress) {
		super();
		this.projectTypeId = projectTypeId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.realestateId = realestateId;
		this.propertyTypeId = propertyTypeId;
		this.propertyTitle = propertyTitle;
		this.propertyUnitMasterId = propertyUnitMasterId;
		this.floor = floor;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.furnishing = furnishing;
		this.reservedParking = reservedParking;
		this.propertyAvailability = propertyAvailability;
		this.propertyAvailabilityDescription = propertyAvailabilityDescription;
		this.maintenanceCharges = maintenanceCharges;
		this.maintenanceAmount = maintenanceAmount;
		this.propertyDescription = propertyDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int propertyId;
	private int sequence;
	private String sku;
	private int projectTypeId;
	private int categoryId;
	private int subcategoryId;
	private int realestateId;
	private int propertyTypeId;
	private String propertyTitle;
	private int propertyUnitMasterId;
	private String floor;
	private String waterSource;
	private String drainage;
	private String furnishing;
	private String reservedParking;
	private String propertyAvailability;
	private String propertyAvailabilityDescription;
	private String maintenanceCharges;
	private float maintenanceAmount;
	private String propertyDescription;
	private String projectTitle;
	private String realestateTypeName;
	private String subcategoryTitle;
	private String realestateTitle;
	private String towerTitle;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String realestateCode;
	private String propertyStatus;

	public int getPropertyId() {
		return propertyId;
	}

	public int getProjectTypeId() {
		return projectTypeId;
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

	public int getPropertyTypeId() {
		return propertyTypeId;
	}

	public String getPropertyTitle() {
		return propertyTitle;
	}

	public String getFloor() {
		return floor;
	}

	public String getWaterSource() {
		return waterSource;
	}

	public String getDrainage() {
		return drainage;
	}

	public String getFurnishing() {
		return furnishing;
	}

	public String getReservedParking() {
		return reservedParking;
	}

	public String getPropertyAvailability() {
		return propertyAvailability;
	}

	public String getPropertyAvailabilityDescription() {
		return propertyAvailabilityDescription;
	}

	public String getMaintenanceCharges() {
		return maintenanceCharges;
	}

	public float getMaintenanceAmount() {
		return maintenanceAmount;
	}

	public String getPropertyDescription() {
		return propertyDescription;
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

	public String getProjectTitle() {
		return projectTitle;
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

	public String getTowerTitle() {
		return towerTitle;
	}

	public String getRealestateCode() {
		return realestateCode;
	}

	public String getSku() {
		return sku;
	}

	public int getSequence() {
		return sequence;
	}

	public int getPropertyUnitMasterId() {
		return propertyUnitMasterId;
	}

  public String getPropertyStatus() {
    return propertyStatus;
  }

  public void setPropertyStatus(String propertyStatus) {
    this.propertyStatus = propertyStatus;
  }

  public void setPropertyId(int propertyId) {
    this.propertyId = propertyId;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public void setProjectTypeId(int projectTypeId) {
    this.projectTypeId = projectTypeId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public void setSubcategoryId(int subcategoryId) {
    this.subcategoryId = subcategoryId;
  }

  public void setRealestateId(int realestateId) {
    this.realestateId = realestateId;
  }

  public void setPropertyTypeId(int propertyTypeId) {
    this.propertyTypeId = propertyTypeId;
  }

  public void setPropertyTitle(String propertyTitle) {
    this.propertyTitle = propertyTitle;
  }

  public void setPropertyUnitMasterId(int propertyUnitMasterId) {
    this.propertyUnitMasterId = propertyUnitMasterId;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public void setWaterSource(String waterSource) {
    this.waterSource = waterSource;
  }

  public void setDrainage(String drainage) {
    this.drainage = drainage;
  }

  public void setFurnishing(String furnishing) {
    this.furnishing = furnishing;
  }

  public void setReservedParking(String reservedParking) {
    this.reservedParking = reservedParking;
  }

  public void setPropertyAvailability(String propertyAvailability) {
    this.propertyAvailability = propertyAvailability;
  }

  public void setPropertyAvailabilityDescription(String propertyAvailabilityDescription) {
    this.propertyAvailabilityDescription = propertyAvailabilityDescription;
  }

  public void setMaintenanceCharges(String maintenanceCharges) {
    this.maintenanceCharges = maintenanceCharges;
  }

  public void setMaintenanceAmount(float maintenanceAmount) {
    this.maintenanceAmount = maintenanceAmount;
  }

  public void setPropertyDescription(String propertyDescription) {
    this.propertyDescription = propertyDescription;
  }

  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }

  public void setRealestateTypeName(String realestateTypeName) {
    this.realestateTypeName = realestateTypeName;
  }

  public void setSubcategoryTitle(String subcategoryTitle) {
    this.subcategoryTitle = subcategoryTitle;
  }

  public void setRealestateTitle(String realestateTitle) {
    this.realestateTitle = realestateTitle;
  }

  public void setTowerTitle(String towerTitle) {
    this.towerTitle = towerTitle;
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

  public void setRealestateCode(String realestateCode) {
    this.realestateCode = realestateCode;
  }

}
