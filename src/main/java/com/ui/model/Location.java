package com.ui.model;

public class Location {
	public Location(int locationId, String locationName) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
	}
	public Location(int locationId, String locationName, String locationCode, int countryId, int stateId,
			int districtId, int talukaId, String cityVillage, String moje, String area, String zip, String citySurveyNo,
			String tp, String gMapLink, float latitude, float longitude, String locationMap, int createdBy,
			String ipAddress) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationCode = locationCode;
		this.countryId = countryId;
		this.stateId = stateId;
		this.districtId = districtId;
		this.talukaId = talukaId;
		this.cityVillage = cityVillage;
		this.moje = moje;
		this.area = area;
		this.zip = zip;
		this.citySurveyNo = citySurveyNo;
		this.tp = tp;
		this.gMapLink = gMapLink;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationMap = locationMap;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Location(int locationId, String locationName, String locationCode, int countryId, int stateId,
			int districtId, int talukaId, String cityVillage, String moje, String area, String zip, String citySurveyNo,
			String tp, String gMapLink, float latitude, float longitude, String locationMap) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationCode = locationCode;
		this.countryId = countryId;
		this.stateId = stateId;
		this.districtId = districtId;
		this.talukaId = talukaId;
		this.cityVillage = cityVillage;
		this.moje = moje;
		this.area = area;
		this.zip = zip;
		this.citySurveyNo = citySurveyNo;
		this.tp = tp;
		this.gMapLink = gMapLink;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationMap = locationMap;
	}
	public Location(int locationId, String locationName, String cityVillage, String citySurveyNo) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.cityVillage = cityVillage;
		this.citySurveyNo = citySurveyNo;
	}
	public Location(String locationName, String locationCode, int countryId, int stateId, int districtId, int talukaId,
			String cityVillage, String moje, String area, String zip, String citySurveyNo, String tp, String gMapLink,
			float latitude, float longitude, String locationMap, String status, int createdBy, String ipAddress) {
		super();
		this.locationName = locationName;
		this.locationCode = locationCode;
		this.countryId = countryId;
		this.stateId = stateId;
		this.districtId = districtId;
		this.talukaId = talukaId;
		this.cityVillage = cityVillage;
		this.moje = moje;
		this.area = area;
		this.zip = zip;
		this.citySurveyNo = citySurveyNo;
		this.tp = tp;
		this.gMapLink = gMapLink;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationMap = locationMap;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int locationId;
	private String locationName;
	private String locationCode;
	private int countryId;
	private int stateId;
	private int districtId;
	private int talukaId;
	private String cityVillage;
	private String moje;
	private String area;
	private String zip;
	private String citySurveyNo;
	private String tp;
	private String gMapLink;
	private float latitude;
	private float longitude;
	private String locationMap;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getLocationId() {
		return locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public int getCountryId() {
		return countryId;
	}
	public int getStateId() {
		return stateId;
	}
	public int getDistrictId() {
		return districtId;
	}
	public int getTalukaId() {
		return talukaId;
	}
	public String getCityVillage() {
		return cityVillage;
	}
	public String getMoje() {
		return moje;
	}
	public String getArea() {
		return area;
	}
	public String getZip() {
		return zip;
	}
	public String getCitySurveyNo() {
		return citySurveyNo;
	}
	public String getTp() {
		return tp;
	}
	public String getgMapLink() {
		return gMapLink;
	}
	public float getLatitude() {
		return latitude;
	}
	public float getLongitude() {
		return longitude;
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
	public String getLocationCode() {
		return locationCode;
	}
	public String getLocationMap() {
		return locationMap;
	}	
}
