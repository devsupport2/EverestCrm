package com.ui.model;

public class PriceLabel {
  
  private int priceLabelId;
  private String priceLabel;
  private String description;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  public int getPriceLabelId() {
    return priceLabelId;
  }
  public void setPriceLabelId(int priceLabelId) {
    this.priceLabelId = priceLabelId;
  }
  public String getPriceLabel() {
    return priceLabel;
  }
  public void setPriceLabel(String priceLabel) {
    this.priceLabel = priceLabel;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
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
