package com.ui.model;

public class PaymentSchedule {
  
  
  
  public PaymentSchedule(int paymentScheduleId, int propertyId, float saleDeed, float gst, float stampDuty, float development, float maintenanceDeposit, float total) {
    super();
    this.paymentScheduleId = paymentScheduleId;
    this.propertyId = propertyId;
    this.saleDeed = saleDeed;
    this.gst = gst;
    this.stampDuty = stampDuty;
    this.development = development;
    this.maintenanceDeposit = maintenanceDeposit;
    this.total = total;
  }
  public PaymentSchedule(int propertyId, float saleDeed, float gst, float stampDuty, float development, float maintenanceDeposit, float total, String status, int createdBy, String ipAddress) {
    super();
    this.propertyId = propertyId;
    this.saleDeed = saleDeed;
    this.gst = gst;
    this.stampDuty = stampDuty;
    this.development = development;
    this.maintenanceDeposit = maintenanceDeposit;
    this.total = total;
    this.status = status;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
  private int paymentScheduleId;  
  private int propertyId;  
  private float saleDeed;
  private float gst;
  private float stampDuty;
  private float development;
  private float maintenanceDeposit;
  private float total;  
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  public int getPaymentScheduleId() {
    return paymentScheduleId;
  }
  public int getPropertyId() {
    return propertyId;
  }
  public float getSaleDeed() {
    return saleDeed;
  }
  public float getGst() {
    return gst;
  }
  public float getStampDuty() {
    return stampDuty;
  }
  public float getDevelopment() {
    return development;
  }
  public float getMaintenanceDeposit() {
    return maintenanceDeposit;
  }
  public float getTotal() {
    return total;
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
