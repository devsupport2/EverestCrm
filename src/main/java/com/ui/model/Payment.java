package com.ui.model;

public class Payment {
  
  
  
  public Payment(int projectId) {
    super();
    this.projectId = projectId;
  }
  public Payment(int paymentId, int projectId, String paymentLabel, String paymentSchedule, String percentage) {
    super();
    this.paymentId = paymentId;
    this.projectId = projectId;
    this.paymentLabel = paymentLabel;
    this.paymentSchedule = paymentSchedule;
    this.percentage = percentage;
  }
  public Payment(int projectId, String paymentLabel, String paymentSchedule, String percentage, int createdBy, String ipAddress) {
    super();
    this.projectId = projectId;
    this.paymentLabel = paymentLabel;
    this.paymentSchedule = paymentSchedule;
    this.percentage = percentage;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
  public Payment(int projectId, String projectTitle) {
    super();
    this.projectId = projectId;
    this.projectTitle = projectTitle;    
  }
  public Payment(int projectId, String paymentLabel, String paymentSchedule, String percentage, String status, int createdBy, String ipAddress) {
    super();
    this.projectId = projectId;
    this.paymentLabel = paymentLabel;
    this.paymentSchedule = paymentSchedule;
    this.percentage = percentage;
    this.status = status;
    this.createdBy = createdBy;
    this.ipAddress = ipAddress;
  }
  private int paymentId;
  private int projectId;
  private String paymentLabel;
  private String paymentSchedule;
  private String percentage;
  private String projectTitle;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  public int getPaymentId() {
    return paymentId;
  }
  public String getPaymentLabel() {
    return paymentLabel;
  }
  public String getPaymentSchedule() {
    return paymentSchedule;
  }
  public String getPercentage() {
    return percentage;
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
  public int getProjectId() {
    return projectId;
  }
  public String getProjectTitle() {
    return projectTitle;
  }
  
  

}
