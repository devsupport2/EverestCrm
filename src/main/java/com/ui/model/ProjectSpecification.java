package com.ui.model;

public class ProjectSpecification {
	public ProjectSpecification(int projectSpecificationId, int projectId, String title, String subtitle,
			String description, String attachment) {
		super();
		this.projectSpecificationId = projectSpecificationId;
		this.projectId = projectId;
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.attachment = attachment;
	}
	public ProjectSpecification(int projectId, String title, String subtitle, String description, String attachment,
			int createdBy, String ipAddress) {
		super();
		this.projectId = projectId;
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.attachment = attachment;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int projectSpecificationId;
	private int projectId;
	private String title;
	private String subtitle;
	private String description;
	private String attachment;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getProjectSpecificationId() {
		return projectSpecificationId;
	}
	public int getProjectId() {
		return projectId;
	}
	public String getTitle() {
		return title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public String getDescription() {
		return description;
	}
	public String getAttachment() {
		return attachment;
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
