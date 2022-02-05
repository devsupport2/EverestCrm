package com.ui.model;

public class PropertyRoom {

    
  public PropertyRoom(int propertyRoomId, int roomTitleId, int propertyId, float roomLength, int lengthUnitId, float roomBreadth, int breadthUnitId, float roomHeight, int heightUnitId,
        String roomTitle, String lengthUnit, String breadthUnit, String heightUnit) {
    super();
    this.propertyRoomId = propertyRoomId;
    this.roomTitleId = roomTitleId;
    this.propertyId = propertyId;
    this.roomLength = roomLength;
    this.lengthUnitId = lengthUnitId;
    this.roomBreadth = roomBreadth;
    this.breadthUnitId = breadthUnitId;
    this.roomHeight = roomHeight;
    this.heightUnitId = heightUnitId;
    this.roomTitle = roomTitle;
    this.lengthUnit = lengthUnit;
    this.breadthUnit = breadthUnit;
    this.heightUnit = heightUnit;
  }
  public PropertyRoom(int roomTitleId, int propertyId, float roomLength, int lengthUnitId, float roomBreadth, int breadthUnitId, float roomHeight, int heightUnitId, String status, int createdBy,
        String ipAddress) {
    super();
    this.roomTitleId = roomTitleId;
    this.propertyId = propertyId;
    this.roomLength = roomLength;
    this.lengthUnitId = lengthUnitId;
    this.roomBreadth = roomBreadth;
    this.breadthUnitId = breadthUnitId;
    this.roomHeight = roomHeight;
    this.heightUnitId = heightUnitId;
    this.status = status;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
  private int propertyRoomId;
  private int roomTitleId;
  private int propertyId;  
  private float roomLength;
  private int lengthUnitId;
  private float roomBreadth;
  private int breadthUnitId;
  private float roomHeight;
  private int heightUnitId;
  private String roomTitle;
  private String unitName;
  private String lengthUnit;
  private String breadthUnit;
  private String heightUnit;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  
  public int getPropertyRoomId() {
    return propertyRoomId;
  }
  public int getRoomTitleId() {
    return roomTitleId;
  }
  public int getPropertyId() {
    return propertyId;
  }
  public float getRoomLength() {
    return roomLength;
  }
  public int getLengthUnitId() {
    return lengthUnitId;
  }
  public float getRoomBreadth() {
    return roomBreadth;
  }
  public int getBreadthUnitId() {
    return breadthUnitId;
  }
  public float getRoomHeight() {
    return roomHeight;
  }
  public int getHeightUnitId() {
    return heightUnitId;
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
  public String getRoomTitle() {
    return roomTitle;
  }
  public String getUnitName() {
    return unitName;
  }
  public String getLengthUnit() {
    return lengthUnit;
  }
  public String getBreadthUnit() {
    return breadthUnit;
  }
  public String getHeightUnit() {
    return heightUnit;
  }
  
}
