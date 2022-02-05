package com.ui.model;

public class RoomTitle {

  public RoomTitle(int roomTitleId, String roomTitle) {
    super();
    this.roomTitleId = roomTitleId;
    this.roomTitle = roomTitle;
  }
  public RoomTitle(String roomTitle, String description, String status, int createdBy, String ipAddress) {
    super();
    this.roomTitle = roomTitle;
    this.description = description;
    this.status = status;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
  private int roomTitleId;
  private String roomTitle;
  private String description;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  
  public int getRoomTitleId() {
    return roomTitleId;
  }
  public String getRoomTitle() {
    return roomTitle;
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
