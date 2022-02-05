package com.ui.model;

public class Occupation {
  
  private int occupationId;
  private String occupationName;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  public int getOccupationId() {
    return occupationId;
  }
  public void setOccupationId(int occupationId) {
    this.occupationId = occupationId;
  }
  public String getOccupationName() {
    return occupationName;
  }
  public void setOccupationName(String occupationName) {
    this.occupationName = occupationName;
  }
  public int getCreatedBy() {
    return createdBy;
  }
  public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  public String getIpAddress() {
    return ipAddress;
  }
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
  
  

}
