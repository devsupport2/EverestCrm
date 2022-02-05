package com.ui.model;

public class PropertyType {

  
  public PropertyType(int propertyTypeId, String propertyTypeTitle) {
    super();
    this.propertyTypeId = propertyTypeId;
    this.propertyTypeTitle = propertyTypeTitle;
  }
  public PropertyType(String propertyTypeTitle, String propertyCode, String image, String description, String status, int createdBy, String ipAddress) {
    super();
    this.propertyTypeTitle = propertyTypeTitle;
    this.propertyCode = propertyCode;
    this.image = image;
    this.description = description;
    this.status = status;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
  private int propertyTypeId;
  private String propertyTypeTitle;
  private String propertyCode;
  private String image;
  private String description;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  
  public int getPropertyTypeId() {
    return propertyTypeId;
  }
  public String getPropertyTypeTitle() {
    return propertyTypeTitle;
  }
  public String getPropertyCode() {
    return propertyCode;
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
}
