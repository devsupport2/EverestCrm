package com.ui.model;

import java.util.List;

public class Enquiry {

	public Enquiry(int enquiryId, String enquiryNo, int clientId, String enquiryDate, String userCompanyName,
			String firstName, String lastName, int followupId, String followupDate, String followupTime, String remark,
			String followupStatus) {
		super();
		this.enquiryId = enquiryId;
		this.enquiryNo = enquiryNo;
		this.clientId = clientId;
		this.enquiryDate = enquiryDate;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.followupId = followupId;
		this.followupDate = followupDate;
		this.followupTime = followupTime;
		this.remark = remark;
		this.followupStatus = followupStatus;
	}

	public Enquiry(int enquiryId, String enquiryNo, int clientId, String enquiryDate, String userCompanyName,
			String firstName, String lastName, int followupId, String followupTime, String remark,
			String followupStatus) {
		super();
		this.enquiryId = enquiryId;
		this.enquiryNo = enquiryNo;
		this.clientId = clientId;
		this.enquiryDate = enquiryDate;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.followupId = followupId;
		this.followupTime = followupTime;
		this.remark = remark;
		this.followupStatus = followupStatus;
	}

	public Enquiry(int enquiryId, String status, int reasonId) {
		super();
		this.enquiryId = enquiryId;
		this.status = status;
		this.reasonId = reasonId;
	}
	
	public Enquiry(int enquiryId, int referenceId, int clientId, int employeeId, String enquiryDate, String enquiryVia,
          String enquriRemarks, int occupationId, int designationId, int createdBy, String ipAddress) {
      super();
      this.enquiryId = enquiryId;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.occupationId = occupationId;
      this.designationId = designationId;
      this.createdBy = createdBy;
      this.ipAddress = ipAddress;
  }
	
	public Enquiry(int enquiryId, int referenceId, int clientId, int employeeId, String enquiryDate, String enquiryVia,
          String enquriRemarks, int occupationId, int designationId, String projectType, int budgetId, String importantFeatures, String consideringProject, int finalizedExpectingTime, int createdBy, String ipAddress) {
      super();
      this.enquiryId = enquiryId;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.occupationId = occupationId;
      this.designationId = designationId;
      this.projectType = projectType;
      this.budgetId = budgetId;
      this.importantFeatures = importantFeatures;
      this.consideringProject = consideringProject;
      this.finalizedExpectingTime = finalizedExpectingTime;
      this.createdBy = createdBy;
      this.ipAddress = ipAddress;
  }

	public Enquiry(int enquiryId, int referenceId, int clientId, int employeeId, String enquiryDate, String enquiryVia,
			String enquriRemarks, int createdBy, String ipAddress) {
		super();
		this.enquiryId = enquiryId;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
          String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
          String firstName, String lastName, String mobileNumber, String email, String countryName) {
      super();
      this.enquiryId = enquiryId;
      this.sequence = sequence;
      this.enquiryNo = enquiryNo;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.status = status;
      this.userCompanyName = userCompanyName;
      this.firstName = firstName;
      this.lastName = lastName;
      this.mobileNumber = mobileNumber;
      this.email = email;
      this.countryName = countryName;
  }
	
	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
			String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
			String firstName, String lastName, String mobileNumber, String email, String countryName, String referenceBy) {
		super();
		this.enquiryId = enquiryId;
		this.sequence = sequence;
		this.enquiryNo = enquiryNo;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;
		this.status = status;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.countryName = countryName;
		this.referenceBy = referenceBy;
	}
	
	
	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
          String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
          String firstName, String lastName, String mobileNumber, String email, int occupationId, int designationId, String projectType, int budgetId, String importantFeatures, String consideringProject, int finalizedExpectingTime) {
      super();
      this.enquiryId = enquiryId;
      this.sequence = sequence;
      this.enquiryNo = enquiryNo;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.status = status;
      this.userCompanyName = userCompanyName;
      this.firstName = firstName;
      this.lastName = lastName;
      this.mobileNumber = mobileNumber;
      this.email = email;
      this.occupationId = occupationId;
      this.designationId = designationId;
      this.projectType = projectType;
      this.budgetId = budgetId;
      this.importantFeatures = importantFeatures;
      this.consideringProject = consideringProject;
      this.finalizedExpectingTime = finalizedExpectingTime;
      
  }
	
	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
          String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
          String firstName, String lastName, String mobileNumber, String email, int occupationId, int designationId) {
      super();
      this.enquiryId = enquiryId;
      this.sequence = sequence;
      this.enquiryNo = enquiryNo;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.status = status;
      this.userCompanyName = userCompanyName;
      this.firstName = firstName;
      this.lastName = lastName;
      this.mobileNumber = mobileNumber;
      this.email = email;
      this.occupationId = occupationId;
      this.designationId = designationId;
  }

	public Enquiry(int enquiryId, int sequence, String enquiryNo, int referenceId, int clientId, int employeeId,
			String enquiryDate, String enquiryVia, String enquriRemarks, String status, String userCompanyName,
			String firstName, String lastName, String mobileNumber, String email) {
		super();
		this.enquiryId = enquiryId;
		this.sequence = sequence;
		this.enquiryNo = enquiryNo;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;
		this.status = status;
		this.userCompanyName = userCompanyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	public Enquiry(int sequence, String enquiryNo, int referenceId, int clientId, int employeeId, String enquiryDate,
          String enquiryVia, String enquriRemarks, int occupationId, int designationId, String projectType, int budgetId, String importantFeatures, String consideringProject, int finalizedExpectingTime, String status, int createdBy, String ipAddress) {
      super();
      this.sequence = sequence;
      this.enquiryNo = enquiryNo;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.occupationId = occupationId;
      this.designationId = designationId;
      this.projectType = projectType;
      this.budgetId = budgetId;
      this.importantFeatures = importantFeatures;
      this.consideringProject = consideringProject;
      this.finalizedExpectingTime = finalizedExpectingTime;
      this.status = status;
      this.createdBy = createdBy;
      this.ipAddress = ipAddress;
  }
	
	public Enquiry(int sequence, String enquiryNo, int referenceId, int clientId, int employeeId, String enquiryDate,
          String enquiryVia, String enquriRemarks, int occupationId, int designationId, String status, int createdBy, String ipAddress) {
      super();
      this.sequence = sequence;
      this.enquiryNo = enquiryNo;
      this.referenceId = referenceId;
      this.clientId = clientId;
      this.employeeId = employeeId;
      this.enquiryDate = enquiryDate;
      this.enquiryVia = enquiryVia;
      this.enquriRemarks = enquriRemarks;
      this.occupationId = occupationId;
      this.designationId = designationId;
      this.status = status;
      this.createdBy = createdBy;
      this.ipAddress = ipAddress;
  }

	public Enquiry(int sequence, String enquiryNo, int referenceId, int clientId, int employeeId, String enquiryDate,
			String enquiryVia, String enquriRemarks, String status, int createdBy, String ipAddress) {
		super();
		this.sequence = sequence;
		this.enquiryNo = enquiryNo;
		this.referenceId = referenceId;
		this.clientId = clientId;
		this.employeeId = employeeId;
		this.enquiryDate = enquiryDate;
		this.enquiryVia = enquiryVia;
		this.enquriRemarks = enquriRemarks;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Enquiry() {
		// TODO Auto-generated constructor stub
	}

	private int enquiryId;
	private int sequence;
	private String enquiryNo;
	private int referenceId;
	private int clientId;
	private int employeeId;
	private String enquiryDate;
	private String enquiryVia;
	private String enquriRemarks;
	private String status;
	private int reasonId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String userCompanyName;
	private String firstName;
	private int occupationId;
	private int designationId;
	private String projectType;
	private int budgetId;
	private String importantFeatures;
	private String consideringProject;
	private int finalizedExpectingTime;
	private String lastName;
	private String mobileNumber;
	private String email;
	private int followupId;
	private String followupDate;
	private String followupTime;
	private String remark;
	private String followupStatus;

	private String projectTitle;
	private String unitName;
	private String propertyTitle;
	private String subcategoryTitle;
	private String realestateTitle;
	private String referenceBy;

	private String readyToInvoice;
	private String invoiceGenerated;
	private String countryName;
	private int countryEnquiryCount;

	private List<EnquiryProperty> enquiryPropertyList;

	public int getEnquiryId() {
		return enquiryId;
	}

	public int getClientId() {
		return clientId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getEnquiryDate() {
		return enquiryDate;
	}

	public String getEnquiryVia() {
		return enquiryVia;
	}

	public String getEnquriRemarks() {
		return enquriRemarks;
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getSequence() {
		return sequence;
	}

	public String getEnquiryNo() {
		return enquiryNo;
	}

	public int getReferenceId() {
		return referenceId;
	}

	public String getUserCompanyName() {
		return userCompanyName;
	}

	public int getReasonId() {
		return reasonId;
	}

	public String getFollowupTime() {
		return followupTime;
	}

	public int getFollowupId() {
		return followupId;
	}

	public String getRemark() {
		return remark;
	}

	public String getFollowupDate() {
		return followupDate;
	}

	public String getFollowupStatus() {
		return followupStatus;
	}

	public String getReadyToInvoice() {
		return readyToInvoice;
	}

	public void setReadyToInvoice(String readyToInvoice) {
		this.readyToInvoice = readyToInvoice;
	}

	public String getInvoiceGenerated() {
		return invoiceGenerated;
	}

	public void setInvoiceGenerated(String invoiceGenerated) {
		this.invoiceGenerated = invoiceGenerated;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public void setEnquiryNo(String enquiryNo) {
		this.enquiryNo = enquiryNo;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setEnquiryDate(String enquiryDate) {
		this.enquiryDate = enquiryDate;
	}

	public void setEnquiryVia(String enquiryVia) {
		this.enquiryVia = enquiryVia;
	}

	public void setEnquriRemarks(String enquriRemarks) {
		this.enquriRemarks = enquriRemarks;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
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

	public void setUserCompanyName(String userCompanyName) {
		this.userCompanyName = userCompanyName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFollowupId(int followupId) {
		this.followupId = followupId;
	}

	public void setFollowupDate(String followupDate) {
		this.followupDate = followupDate;
	}

	public void setFollowupTime(String followupTime) {
		this.followupTime = followupTime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setFollowupStatus(String followupStatus) {
		this.followupStatus = followupStatus;
	}

	public List<EnquiryProperty> getEnquiryPropertyList() {
		return enquiryPropertyList;
	}

	public void setEnquiryPropertyList(List<EnquiryProperty> enquiryPropertyList) {
		this.enquiryPropertyList = enquiryPropertyList;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getCountryEnquiryCount() {
		return countryEnquiryCount;
	}

	public void setCountryEnquiryCount(int countryEnquiryCount) {
		this.countryEnquiryCount = countryEnquiryCount;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getPropertyTitle() {
		return propertyTitle;
	}

	public void setPropertyTitle(String propertyTitle) {
		this.propertyTitle = propertyTitle;
	}

	public String getSubcategoryTitle() {
		return subcategoryTitle;
	}

	public void setSubcategoryTitle(String subcategoryTitle) {
		this.subcategoryTitle = subcategoryTitle;
	}

	public String getRealestateTitle() {
		return realestateTitle;
	}

	public void setRealestateTitle(String realestateTitle) {
		this.realestateTitle = realestateTitle;
	}

	public String getReferenceBy() {
		return referenceBy;
	}

	public void setReferenceBy(String referenceBy) {
		this.referenceBy = referenceBy;
	}

  public int getOccupationId() {
    return occupationId;
  }

  public void setOccupationId(int occupationId) {
    this.occupationId = occupationId;
  }

  public int getDesignationId() {
    return designationId;
  }

  public void setDesignationId(int designationId) {
    this.designationId = designationId;
  }

  public String getProjectType() {
    return projectType;
  }

  public void setProjectType(String projectType) {
    this.projectType = projectType;
  }

  public int getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(int budgetId) {
    this.budgetId = budgetId;
  }

  public String getImportantFeatures() {
    return importantFeatures;
  }

  public void setImportantFeatures(String importantFeatures) {
    this.importantFeatures = importantFeatures;
  }

  public String getConsideringProject() {
    return consideringProject;
  }

  public void setConsideringProject(String consideringProject) {
    this.consideringProject = consideringProject;
  }

  public int getFinalizedExpectingTime() {
    return finalizedExpectingTime;
  }

  public void setFinalizedExpectingTime(int finalizedExpectingTime) {
    this.finalizedExpectingTime = finalizedExpectingTime;
  }
}
