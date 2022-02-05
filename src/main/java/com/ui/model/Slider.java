package com.ui.model;

public class Slider 
{
	public Slider(String sliderTitle, String image) {
		super();
		this.sliderTitle = sliderTitle;
		this.image = image;
	}
	public Slider(int sliderId, String sliderTitle, String image, String active, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.sliderId = sliderId;
		this.sliderTitle = sliderTitle;
		this.image = image;
		this.active = active;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Slider(int sliderId, String active, int createdBy, String ipAddress) {
		super();
		this.sliderId = sliderId;
		this.active = active;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Slider(String sliderTitle, String image, String active, String status, int createdBy,
			String ipAddress) {
		super();
		this.sliderTitle = sliderTitle;
		this.image = image;
		this.active = active;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public void setSliderId(int sliderId) {
		this.sliderId = sliderId;
	}
	public void setSliderTitle(String sliderTitle) {
		this.sliderTitle = sliderTitle;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setActive(String active) {
		this.active = active;
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
	//constructor to manage slider Project wise
	public Slider(String sliderTitle, String image, String active, String status, int createdBy,
			String ipAddress,int siteId) {
		super();
		this.sliderTitle = sliderTitle;
		this.image = image;
		this.active = active;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.siteId = siteId;
	}
	
	public Slider(int sliderId, String sliderTitle, String image, String active, String status, int createdBy,
			String createdDate, String ipAddress, int siteId ,String projectTitle)
	{
		super();
		this.sliderId = sliderId;
		this.sliderTitle = sliderTitle;
		this.image = image;
		this.active = active;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.siteId = siteId;
		this.projectTitle = projectTitle;
	}
	
	public Slider(int sliderId, String sliderTitle, String image, String active, int siteId, String status, int createdBy,
			String ipAddress) {
		super();
		this.sliderId = sliderId;
		this.sliderTitle = sliderTitle;
		this.image = image;
		this.active = active;
		this.siteId = siteId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Slider() {
		// TODO Auto-generated constructor stub
	}
	private int sliderId;
	private String sliderTitle;
	private String image;
	private String active;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private int siteId;
	int sliderSequence;
	String sliderSubtitle;
	String sliderDescription;
	private String projectTitle;
	
	public int getSliderSequence() {
		return sliderSequence;
	}
	public void setSliderSequence(int sliderSequence) {
		this.sliderSequence = sliderSequence;
	}
	public String getSliderSubtitle() {
		return sliderSubtitle;
	}
	public void setSliderSubtitle(String sliderSubtitle) {
		this.sliderSubtitle = sliderSubtitle;
	}
	public String getSliderDescription() {
		return sliderDescription;
	}
	public void setSliderDescription(String sliderDescription) {
		this.sliderDescription = sliderDescription;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getSliderId() {
		return sliderId;
	}
	public String getSliderTitle() {
		return sliderTitle;
	}
	public String getImage() {
		return image;
	}
	public String getActive() {
		return active;
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
  public String getProjectTitle() {
    return projectTitle;
  }
  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }
	
	
	
	
		

}
