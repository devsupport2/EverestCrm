package com.ui.model;

public class Project {
	
	public String getProjmainimg() {
		return projmainimg;
	}
	public void setProjmainimg(String projmainimg) {
		this.projmainimg = projmainimg;
	}
	public Project(int projectId, String projectTitle) {
    super();
    this.projectId = projectId;
    this.projectTitle = projectTitle;
  }
  public Project(int projectId, String projectTitle, String projectSubtitle, String projectCode, String projectLogo,
			int locationId, int architectId, int structuralDesignerId, int legalAdvisorId, int developerCompanyId, int propertyTypeId,
			String reraApprove, String reraNo, String layoutMap, String waterSource, String drainage, String workStatus, String description, int createdBy,
			String ipAddress) {
		super();
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.projectSubtitle = projectSubtitle;
		this.projectCode = projectCode;
		this.projectLogo = projectLogo;
		this.locationId = locationId;
		this.architectId = architectId;
		this.structuralDesignerId = structuralDesignerId;
		this.legalAdvisorId = legalAdvisorId;
		this.developerCompanyId = developerCompanyId;
		this.propertyTypeId = propertyTypeId;
		this.reraApprove = reraApprove;
		this.reraNo = reraNo;
		this.layoutMap = layoutMap;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.workStatus = workStatus;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
  
  //-----------
  public Project(int projectId, String projectTitle, String projectSubtitle, String projectCode, String projectLogo,
			int locationId, int architectId, int structuralDesignerId, int legalAdvisorId, int developerCompanyId, int propertyTypeId,
			String reraApprove, String reraNo, String layoutMap, String waterSource, String drainage, String workStatus, String description, int createdBy,
			String ipAddress, String projmainimageparam, String projectPDF, String siteLink, int sequence,String shown_project) {
		super();
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.projectSubtitle = projectSubtitle;
		this.projectCode = projectCode;
		this.projectLogo = projectLogo;
		this.locationId = locationId;
		this.architectId = architectId;
		this.structuralDesignerId = structuralDesignerId;
		this.legalAdvisorId = legalAdvisorId;
		this.developerCompanyId = developerCompanyId;
		this.propertyTypeId = propertyTypeId;
		this.reraApprove = reraApprove;
		this.reraNo = reraNo;
		this.layoutMap = layoutMap;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.workStatus = workStatus;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.projmainimg = projmainimageparam;
		this.projectPDF = projectPDF;
		this.siteLink = siteLink;
		this.sequence = sequence;
		this.shown_project=shown_project;
		
	}
  //-------------
	public Project(int projectId, String projectTitle, String projectSubtitle, String projectCode, String projectLogo,
			int locationId, int architectId, int structuralDesignerId, int legalAdvisorId, int developerCompanyId, int propertyTypeId,
			String reraApprove, String reraNo, String layoutMap, String waterSource, String drainage, String workStatus, String description) {
		super();
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.projectSubtitle = projectSubtitle;
		this.projectCode = projectCode;
		this.projectLogo = projectLogo;
		this.locationId = locationId;
		this.architectId = architectId;
		this.structuralDesignerId = structuralDesignerId;
		this.legalAdvisorId = legalAdvisorId;
		this.developerCompanyId = developerCompanyId;
		this.propertyTypeId = propertyTypeId;
		this.reraApprove = reraApprove;
		this.reraNo = reraNo;
		this.layoutMap = layoutMap;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.workStatus = workStatus;
		this.description = description;
	}
	
	public Project(int projectId, String projectTitle, String projectSubtitle, String projectCode, String projectLogo,
			int locationId, int architectId, int structuralDesignerId, int legalAdvisorId, int developerCompanyId, int propertyTypeId,
			String reraApprove, String reraNo, String layoutMap, String waterSource, String drainage, String workStatus, String description,String projmainimg, String projectPDF, String siteLink, int sequence,String shown_project) {
		super();
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.projectSubtitle = projectSubtitle;
		this.projectCode = projectCode;
		this.projectLogo = projectLogo;
		this.locationId = locationId;
		this.architectId = architectId;
		this.structuralDesignerId = structuralDesignerId;
		this.legalAdvisorId = legalAdvisorId;
		this.developerCompanyId = developerCompanyId;
		this.propertyTypeId = propertyTypeId;
		this.reraApprove = reraApprove;
		this.reraNo = reraNo;
		this.layoutMap = layoutMap;
		this.waterSource = waterSource;
		this.drainage = drainage;
		this.workStatus = workStatus;
		this.description = description;
		this.projmainimg = projmainimg;
		this.projectPDF = projectPDF;
		this.siteLink = siteLink;
		this.sequence = sequence;
		this.shown_project=shown_project;
	}
	
	public Project(int projectId, String projectTitle, String projectSubtitle, String projectCode, int sequence) {
		super();
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.projectSubtitle = projectSubtitle;
		this.projectCode = projectCode;
		this.sequence = sequence;
	}
	public Project(String projectTitle, String projectSubtitle, String projectCode, String projectLogo, int locationId,
			int architectId, int structuralDesignerId, int legalAdvisorId, int developerCompanyId, int propertyTypeId, String reraApprove,
			String reraNo, String layoutMap, String drainage, String waterSource, String workStatus, String description, String status, int createdBy, String ipAddress) {
		super();
		this.projectTitle = projectTitle;
		this.projectSubtitle = projectSubtitle;
		this.projectCode = projectCode;
		this.projectLogo = projectLogo;
		this.locationId = locationId;
		this.architectId = architectId;
		this.structuralDesignerId = structuralDesignerId;
		this.legalAdvisorId = legalAdvisorId;
		this.developerCompanyId = developerCompanyId;
		this.propertyTypeId = propertyTypeId;
		this.reraApprove = reraApprove;
		this.reraNo = reraNo;
		this.layoutMap = layoutMap;
		this.drainage = drainage;
		this.waterSource = waterSource;
		this.workStatus = workStatus;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Project() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Project(String projecttitle, String projectsubtitle, String projectcode, String logo, int locationid,
			int architectid, int structuraldesignerid, int legaladvisorid, int developercompanyid,
			int propertytypeid, String rereapproved, String rerano, String layout, String drainage,
			String watersource, String workstatus, String des3, String status, int createdby, String ipAddress,
			String projmainimg, String projectPDF, String siteLink, int sequence,String shown_project) 
	{
		// TODO Auto-generated constructor stub
		super();
		this.projectTitle = projecttitle;
		this.projectSubtitle = projectsubtitle;
		this.projectCode = projectcode;
		this.projectLogo = logo;
		this.locationId = locationid;
		this.architectId = architectid;
		this.structuralDesignerId = structuraldesignerid;
		this.legalAdvisorId = legaladvisorid;
		this.developerCompanyId = developercompanyid;
		this.propertyTypeId = propertytypeid;
		this.reraApprove = rereapproved;
		this.reraNo = rerano;
		this.layoutMap = layout;
		this.drainage = drainage;
		this.waterSource = watersource;
		this.workStatus = workstatus;
		this.description = des3;
		this.status = status;
		this.createdBy = createdby;
		this.ipAddress = ipAddress;
		this.projmainimg = projmainimg;
		this.projectPDF = projectPDF;
		this.siteLink = siteLink;
		this.sequence = sequence;
		this.shown_project=shown_project;
	}
	
	private int projectId;
	private String projectTitle;
	private String projectSubtitle;
	private String projectCode;
	private String projectLogo;
	private int locationId;
	private int architectId;
	private int structuralDesignerId;
	private int legalAdvisorId;
	private int developerCompanyId;
	private String reraApprove;
	private String reraNo;
	private String layoutMap;
	private String waterSource;
	private String drainage;
	private String workStatus;
	private String description;
	private int propertyTypeId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private int projectEnquiryCount;
	private String projmainimg;
	private String projectPDF;
	private String siteLink;
	private int sequence;
	private String shown_project;
	
	
	
	
	
  public String getShown_project() {
    return shown_project;
  }
  public void setShown_project(String shown_project) {
    this.shown_project = shown_project;
  }
  public int getProjectId() {
		return projectId;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public String getProjectSubtitle() {
		return projectSubtitle;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public String getProjectLogo() {
		return projectLogo;
	}
	public int getLocationId() {
		return locationId;
	}
	public int getArchitectId() {
		return architectId;
	}
	public int getStructuralDesignerId() {
		return structuralDesignerId;
	}
	public int getLegalAdvisorId() {
		return legalAdvisorId;
	}
	public int getDeveloperCompanyId() {
		return developerCompanyId;
	}
	public String getReraApprove() {
		return reraApprove;
	}
	public String getReraNo() {
		return reraNo;
	}
	public String getLayoutMap() {
		return layoutMap;
	}
	public String getWaterSource() {
	    return waterSource;
	}
	public String getDrainage() {
	    return drainage;
	}
	public String getDescription() {
		return description;
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
	public String getWorkStatus() {
		return workStatus;
	}
    public int getPropertyTypeId() {
      return propertyTypeId;
    }
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public void setProjectSubtitle(String projectSubtitle) {
		this.projectSubtitle = projectSubtitle;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public void setProjectLogo(String projectLogo) {
		this.projectLogo = projectLogo;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public void setArchitectId(int architectId) {
		this.architectId = architectId;
	}
	public void setStructuralDesignerId(int structuralDesignerId) {
		this.structuralDesignerId = structuralDesignerId;
	}
	public void setLegalAdvisorId(int legalAdvisorId) {
		this.legalAdvisorId = legalAdvisorId;
	}
	public void setDeveloperCompanyId(int developerCompanyId) {
		this.developerCompanyId = developerCompanyId;
	}
	public void setReraApprove(String reraApprove) {
		this.reraApprove = reraApprove;
	}
	public void setReraNo(String reraNo) {
		this.reraNo = reraNo;
	}
	public void setLayoutMap(String layoutMap) {
		this.layoutMap = layoutMap;
	}
	public void setWaterSource(String waterSource) {
		this.waterSource = waterSource;
	}
	public void setDrainage(String drainage) {
		this.drainage = drainage;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPropertyTypeId(int propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
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
    public int getProjectEnquiryCount() {
      return projectEnquiryCount;
    }
    public void setProjectEnquiryCount(int projectEnquiryCount) {
      this.projectEnquiryCount = projectEnquiryCount;
    }
    public String getProjectPDF() {
      return projectPDF;
    }
    public void setProjectPDF(String projectPDF) {
      this.projectPDF = projectPDF;
    }
    public String getSiteLink() {
      return siteLink;
    }
    public void setSiteLink(String siteLink) {
      this.siteLink = siteLink;
    }
    public int getSequence() {
      return sequence;
    }
    public void setSequence(int sequence) {
      this.sequence = sequence;
    }
	
}
