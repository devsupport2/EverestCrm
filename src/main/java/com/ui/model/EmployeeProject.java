package com.ui.model;

public class EmployeeProject {
	private int employeeProjectId;
	private int userId;
	private int projectId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String projectTitle;
	
	public int getEmployeeProjectId() {
		return employeeProjectId;
	}
	public void setEmployeeProjectId(int employeeProjectId) {
		this.employeeProjectId = employeeProjectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
}
