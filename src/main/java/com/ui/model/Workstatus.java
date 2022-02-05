package com.ui.model;

import java.util.List;

public class Workstatus {
  
  
  private int workstatusId;
  private int projectId;
  private String title;
  private String subtitle;
  private String date;
  private String image;
  private String description;
  private String status;
  private int createdBy;
  private String createdDate;
  private String ipAddress;
  private String projectTitle;
  
  private List<WorkstatusImage> imageList;
  
  public int getWorkstatusId() {
    return workstatusId;
  }
  public void setWorkstatusId(int workstatusId) {
    this.workstatusId = workstatusId;
  }
  public int getProjectId() {
    return projectId;
  }
  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getSubtitle() {
    return subtitle;
  }
  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
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
  public String getProjectTitle() {
    return projectTitle;
  }
  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }
  public List<WorkstatusImage> getImageList() {
    return imageList;
  }
  public void setImageList(List<WorkstatusImage> imageList) {
    this.imageList = imageList;
  }

  
}
