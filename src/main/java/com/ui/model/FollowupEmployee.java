package com.ui.model;

public class FollowupEmployee {
	public FollowupEmployee(int followupEmpId, int followupId, int userId) {
		super();
		this.followupEmpId = followupEmpId;
		this.followupId = followupId;
		this.userId = userId;
	}
	
	public FollowupEmployee(int followupId, int userId, int createdBy, String ipAddress) {
		super();
		this.followupId = followupId;
		this.userId = userId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int followupEmpId;
	private int followupId;
	private int userId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getFollowupEmpId() {
		return followupEmpId;
	}
	public int getFollowupId() {
		return followupId;
	}
	public int getUserId() {
		return userId;
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
