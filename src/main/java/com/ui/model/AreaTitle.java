package com.ui.model;

public class AreaTitle {

  public AreaTitle(int areaTypeId, String areaTypeTitle) {
    super();
    this.areaTypeId = areaTypeId;
    this.areaTypeTitle = areaTypeTitle;
  }
  public AreaTitle(String areaTypeTitle, String status, int createdBy, String ipAddress) {
    super();
    this.areaTypeTitle = areaTypeTitle;
    this.status = status;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
   
  
  
  private int areaTypeId;
  private String areaTypeTitle;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  
  public int getAreaTypeId() {
    return areaTypeId;
  }
  public String getAreaTypeTitle() {
    return areaTypeTitle;
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
