package com.ui.model;

public class FinancialYear {

	public FinancialYear(int financialYearId, int createdBy, String ipAddress) {
		super();
		this.financialYearId = financialYearId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public FinancialYear(int financialYearId, String financialYearStartDate, String financialYearEndDate,
			String financialYearCode, String defaultt) {
		super();
		this.financialYearId = financialYearId;
		this.financialYearStartDate = financialYearStartDate;
		this.financialYearEndDate = financialYearEndDate;
		this.financialYearCode = financialYearCode;
		this.defaultt = defaultt;
	}

	public FinancialYear(int financialYearId, String financialYearStartDate, String financialYearEndDate,
			String financialYearCode, int createdBy, String ipAddress) {
		super();
		this.financialYearId = financialYearId;
		this.financialYearStartDate = financialYearStartDate;
		this.financialYearEndDate = financialYearEndDate;
		this.financialYearCode = financialYearCode;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public FinancialYear(String financialYearStartDate, String financialYearEndDate, String financialYearCode,
			String defaultt, String status, int createdBy, String ipAddress) {
		super();
		this.financialYearStartDate = financialYearStartDate;
		this.financialYearEndDate = financialYearEndDate;
		this.financialYearCode = financialYearCode;
		this.defaultt = defaultt;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int financialYearId;
	private String financialYearStartDate;
	private String financialYearEndDate;
	private String financialYearCode;
	private String defaultt;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;

	public int getFinancialYearId() {
		return financialYearId;
	}

	public String getFinancialYearStartDate() {
		return financialYearStartDate;
	}

	public String getFinancialYearEndDate() {
		return financialYearEndDate;
	}

	public String getFinancialYearCode() {
		return financialYearCode;
	}

	public String getDefaultt() {
		return defaultt;
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
