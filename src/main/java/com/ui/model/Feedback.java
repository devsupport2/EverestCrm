package com.ui.model;

public class Feedback {
   
  private int feedbackId;
	private int userId;
	private String firstName;
	private String lastName;
	private String orgnaizationName;
	private String feedback;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String image;
	private String userFirstName;
	private String userLastName;
  public int getFeedbackId() {
    return feedbackId;
  }
  public int getUserId() {
    return userId;
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getOrgnaizationName() {
    return orgnaizationName;
  }
  public String getFeedback() {
    return feedback;
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
  
  public String getUserFirstName() {
    return userFirstName;
  }
  public String getUserLastName() {
    return userLastName;
  }
  public void setFeedbackId(int feedbackId) {
    this.feedbackId = feedbackId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public void setOrgnaizationName(String orgnaizationName) {
    this.orgnaizationName = orgnaizationName;
  }
  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
  public void setUserFirstName(String userFirstName) {
    this.userFirstName = userFirstName;
  }
  public void setUserLastName(String userLastName) {
    this.userLastName = userLastName;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }
	
	
}
