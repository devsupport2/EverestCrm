package com.ui.model;

public class ProjectPaymentSchedule {

	public ProjectPaymentSchedule(int projectPaymentScheduleId, String sequence, String sequenceTitle,
			String paymentLable, float paymentValue, int unitId, String unitName) {
		super();
		this.projectPaymentScheduleId = projectPaymentScheduleId;
		this.sequence = sequence;
		this.sequenceTitle = sequenceTitle;
		this.paymentLable = paymentLable;
		this.paymentValue = paymentValue;
		this.unitId = unitId;
		this.unitName = unitName;
	}

	public ProjectPaymentSchedule(int projectPaymentScheduleId, int projectId, String sequence, String sequenceTitle,
			String paymentLable, float paymentValue, int unitId, String unitName) {
		super();
		this.projectPaymentScheduleId = projectPaymentScheduleId;
		this.projectId = projectId;
		this.sequence = sequence;
		this.sequenceTitle = sequenceTitle;
		this.paymentLable = paymentLable;
		this.paymentValue = paymentValue;
		this.unitId = unitId;
		this.unitName = unitName;
	}

	public ProjectPaymentSchedule(int projectId, String sequence, String sequenceTitle, String paymentLable,
			float paymentValue, int unitId, String status, int createdBy, String ipAddress) {
		super();
		this.projectId = projectId;
		this.sequence = sequence;
		this.sequenceTitle = sequenceTitle;
		this.paymentLable = paymentLable;
		this.paymentValue = paymentValue;
		this.unitId = unitId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int projectPaymentScheduleId;
	private int projectId;
	private String sequence;
	private String sequenceTitle;
	private String paymentLable;
	private float paymentValue;
	private int unitId;
	private String unitName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getProjectPaymentScheduleId() {
		return projectPaymentScheduleId;
	}

	public int getProjectId() {
		return projectId;
	}

	public String getSequence() {
		return sequence;
	}

	public String getSequenceTitle() {
		return sequenceTitle;
	}

	public String getPaymentLable() {
		return paymentLable;
	}

	public float getPaymentValue() {
		return paymentValue;
	}

	public int getUnitId() {
		return unitId;
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

	public String getUnitName() {
		return unitName;
	}

}
