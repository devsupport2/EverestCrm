package com.ui.model;

public class PropertyArea {

  
  
  public PropertyArea(int propertyAreaId, int areaTypeId, float areaValue, int unitId, int propertyId, String areaTypeTitle, String unitTitle) {
    super();
    this.propertyAreaId = propertyAreaId;
    this.areaTypeId = areaTypeId;
    this.areaValue = areaValue;
    this.unitId = unitId;
    this.propertyId = propertyId;
    this.areaTypeTitle = areaTypeTitle;
    this.unitTitle = unitTitle;
    
  }
  public PropertyArea(int areaTypeId, float areaValue, int unitId, int propertyId, String status, int createdBy, String ipAddress) {
    super();
    this.areaTypeId = areaTypeId;
    this.areaValue = areaValue;
    this.unitId = unitId;
    this.propertyId = propertyId;
    this.status = status;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
  private int propertyAreaId;
  private int areaTypeId;
  private float areaValue;
  private int unitId;
  private int propertyId;
  private String areaTypeTitle;
  private String unitTitle;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  
  public int getPropertyAreaId() {
    return propertyAreaId;
  }
  public int getAreaTypeId() {
    return areaTypeId;
  }
  public float getAreaValue() {
    return areaValue;
  }
  public int getUnitId() {
    return unitId;
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
  public int getPropertyId() {
    return propertyId;
  }
  public String getAreaTypeTitle() {
    return areaTypeTitle;
  }
  public String getUnitTitle() {
    return unitTitle;
  }
  
}
