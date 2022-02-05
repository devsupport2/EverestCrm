package com.ui.model;

public class PaymentLabel {
  
  public PaymentLabel(String priceTypeLabel, String status, int createdBy, String ipAddress) {
    super();
    this.priceTypeLabel = priceTypeLabel;
    this.status = status;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
  private int paymentLabelId;
  private String priceTypeLabel;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  public int getPaymentLabelId() {
    return paymentLabelId;
  }
  public String getPriceTypeLabel() {
    return priceTypeLabel;
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
