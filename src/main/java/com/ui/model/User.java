package com.ui.model;

import java.util.List;

public class User {

    public User(int userId, String userCompanyName, String firstName, String middleName, String lastName, String gender,
            String dateOfBirth, String aadharNumber, String passportNumber, String panNumber, String profilePicture,
            String address1, String address2, String address3, int countryId, int stateId, String cityName,
            String pincode, String mobileNumber, String landlineNumber, String email, String password, int userTypeId,
            String accessProject, String accessProperty, String accessEnquiry, String accessPayment, String accessUser, String gstNumber,
            int createdBy, String ipAddress) {
        super();
        this.userId = userId;
        this.userCompanyName = userCompanyName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.aadharNumber = aadharNumber;
        this.passportNumber = passportNumber;
        this.panNumber = panNumber;
        this.profilePicture = profilePicture;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.countryId = countryId;
        this.stateId = stateId;
        this.cityName = cityName;
        this.pincode = pincode;
        this.mobileNumber = mobileNumber;
        this.landlineNumber = landlineNumber;
        this.email = email;
        this.password = password;
        this.userTypeId = userTypeId;
        this.accessProject = accessProject;
        this.accessProperty = accessProperty;
        this.accessEnquiry = accessEnquiry;
        this.accessPayment = accessPayment;
        this.accessUser = accessUser;
        this.gstNumber = gstNumber;
        this.createdBy = createdBy;
        this.ipAddress = ipAddress;
    }

    public User(int userId, String userCompanyName, String firstName, String middleName, String lastName, String gender,
            String dateOfBirth, String aadharNumber, String passportNumber, String panNumber, String profilePicture,
            String address1, String address2, String address3, int countryId, int stateId, String cityName,
            String pincode, String mobileNumber, String landlineNumber, String email, String password, String gstNumber, int userTypeId,
            String accessProject, String accessProperty, String accessEnquiry, String accessPayment,
            String accessUser) {
        super();
        this.userId = userId;
        this.userCompanyName = userCompanyName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.aadharNumber = aadharNumber;
        this.passportNumber = passportNumber;
        this.panNumber = panNumber;
        this.profilePicture = profilePicture;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.countryId = countryId;
        this.stateId = stateId;
        this.cityName = cityName;
        this.pincode = pincode;
        this.mobileNumber = mobileNumber;
        this.landlineNumber = landlineNumber;
        this.email = email;
        this.password = password;
        this.gstNumber = gstNumber;
        this.userTypeId = userTypeId;
        this.accessProject = accessProject;
        this.accessProperty = accessProperty;
        this.accessEnquiry = accessEnquiry;
        this.accessPayment = accessPayment;
        this.accessUser = accessUser;
    }

    public User(int userId, String userCompanyName, String firstName, String middleName, String lastName) {
        super();
        this.userId = userId;
        this.userCompanyName = userCompanyName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        
    }
    
    public User(int userId, String userCompanyName, String firstName, String middleName, String lastName,String mobileNumber) {
      super();
      this.userId = userId;
      this.userCompanyName = userCompanyName;
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.mobileNumber = mobileNumber;
  }

    public User(int userId, String userCompanyName) {
        super();
        this.userId = userId;
        this.userCompanyName = userCompanyName;
    }

    public User(int userId, String userCompanyName, String firstName, String middleName, String lastName,
            String mobileNumber, String email) {
        super();
        this.userId = userId;
        this.userCompanyName = userCompanyName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
    
    public User(String userCompanyName, String firstName, String middleName, String lastName, String gender,
          String dateOfBirth, String aadharNumber, String passportNumber, String panNumber, String profilePicture,
          String address1, String address2, String address3, int countryId, int stateId, String cityName,
          String pincode, String mobileNumber, String landlineNumber, String email, String password, int userTypeId,
          String accessProject, String accessProperty, String accessEnquiry, String accessPayment, String accessUser,
          String status, int createdBy, String ipAddress) {
      super();
      this.userCompanyName = userCompanyName;
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.gender = gender;
      this.dateOfBirth = dateOfBirth;
      this.aadharNumber = aadharNumber;
      this.passportNumber = passportNumber;
      this.panNumber = panNumber;
      this.profilePicture = profilePicture;
      this.address1 = address1;
      this.address2 = address2;
      this.address3 = address3;
      this.countryId = countryId;
      this.stateId = stateId;
      this.cityName = cityName;
      this.pincode = pincode;
      this.mobileNumber = mobileNumber;
      this.landlineNumber = landlineNumber;
      this.email = email;
      this.password = password;
      this.userTypeId = userTypeId;
      this.accessProject = accessProject;
      this.accessProperty = accessProperty;
      this.accessEnquiry = accessEnquiry;
      this.accessPayment = accessPayment;
      this.accessUser = accessUser;
      this.status = status;
      this.createdBy = createdBy;
      this.ipAddress = ipAddress;
  }

   /* public User(int countryId, String cityName,
            String pincode, String mobileNumber, String landlineNumber, String email) {
        super();
        this.userCompanyName = userCompanyName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.aadharNumber = aadharNumber;
        this.passportNumber = passportNumber;
        this.panNumber = panNumber;
        this.profilePicture = profilePicture;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.countryId = countryId;
        this.stateId = stateId;
        this.cityName = cityName;
        this.pincode = pincode;
        this.mobileNumber = mobileNumber;
        this.landlineNumber = landlineNumber;
        this.email = email;
        this.password = password;
        this.userTypeId = userTypeId;
        this.accessProject = accessProject;
        this.accessProperty = accessProperty;
        this.accessEnquiry = accessEnquiry;
        this.accessPayment = accessPayment;
        this.accessUser = accessUser;
        this.gstNumber = gstNumber;
        this.status = status;
        this.createdBy = createdBy;
        this.ipAddress = ipAddress;
    }*/

    public User(String userCompanyName, String firstName, String middleName, String lastName, String gender,
            String dateOfBirth, String aadharNumber, String passportNumber, String panNumber, String profilePicture,
            String address1, String address2, String address3, int stateId, String cityName, String pincode,
            String mobileNumber, String landlineNumber, String email, String password, int userTypeId, String status,
            int createdBy, String ipAddress) {
        super();
        this.userCompanyName = userCompanyName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.aadharNumber = aadharNumber;
        this.passportNumber = passportNumber;
        this.panNumber = panNumber;
        this.profilePicture = profilePicture;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.stateId = stateId;
        this.cityName = cityName;
        this.pincode = pincode;
        this.mobileNumber = mobileNumber;
        this.landlineNumber = landlineNumber;
        this.email = email;
        this.password = password;
        this.userTypeId = userTypeId;
        this.status = status;
        this.createdBy = createdBy;
        this.ipAddress = ipAddress;
    }

    public User(String email, String password, String ipAddress) {
        super();
        this.email = email;
        this.password = password;
        this.ipAddress = ipAddress;
    }

    public User(int userId, String firstName, String middleName, String lastName) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public User(int userId, String firstName, String middleName, String lastName, String profilePicture,
            String mobileNumber, String email, int userTypeId, String accessProject, String accessProperty,
            String accessEnquiry, String accessPayment, String accessUser) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.userTypeId = userTypeId;
        this.accessProject = accessProject;
        this.accessProperty = accessProperty;
        this.accessEnquiry = accessEnquiry;
        this.accessPayment = accessPayment;
        this.accessUser = accessUser;
    }

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String companyname, String firstname2, String middlename2, String lastname2, String gender2, String dateofbirth2, String aadharnumber2, String passportnumber2, String pannumber2,
          String image1, String address12, String address22, String address32, int countryname, int statename, String cityname2, String pincode2, String mobilenumber2, String landlinenumber2,
          String email2, String password2, int usertypename2, String accessproject2, String accessproperty2, String accessenquiry2, String accesspayment2, String accessuser2, String gstno, String s,
          int id, String ipAddress2) {
      // TODO Auto-generated constructor stub
    	this.userCompanyName=companyname;
    	this.firstName = firstname2;
        this.middleName = middlename2;
        this.lastName = lastname2;
        this.gender=gender2;
        this.dateOfBirth=dateofbirth2;
        this.aadharNumber=aadharnumber2;
        this.passportNumber=passportnumber2;
        this.panNumber=pannumber2;
       this.profilePicture=image1;
       this.address1=address12;
       this.address2=address22;
       this.address3=address32;
       this.countryId=countryname;
       this.stateId=statename;
       this.cityName=cityname2;
       this.pincode=pincode2;
       this.mobileNumber=mobilenumber2;
       this.landlineNumber=landlinenumber2;
       this.email=email2;
       this.password=pannumber2;
       this.userTypeId=usertypename2;
       this.accessProject=accessproject2;
       this.accessProperty=accessproperty2;
       this.accessEnquiry=accessenquiry2;
       this.accessPayment=accesspayment2;
       this.accessUser=accessuser2;
       this.gstNumber=gstno;
       this.status=s;
       this.createdBy=id;
       this.ipAddress=ipAddress2;
       
    	
    }

    private int userId;
    private String userCompanyName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String aadharNumber;
    private String passportNumber;
    private String panNumber;
    private String profilePicture;
    private String address1;
    private String address2;
    private String address3;
    private int countryId;
    private int stateId;
    private String cityName;
    private String pincode;
    private String mobileNumber;
    private String landlineNumber;
    private String email;
    private String password;
    private String gstNumber;
    private int userTypeId;
    private String accessProject;
    private String accessProperty;
    private String accessEnquiry;
    private String accessPayment;
    private String accessUser;
    private String status;
    private int createdBy;
    private String createdDate;
    private String ipAddress;
    private String userTypeName;

    private List<EmployeeProject> employeeProjectList;

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public int getStateId() {
        return stateId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPincode() {
        return pincode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserTypeId() {
        return userTypeId;
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

    public String getUserTypeName() {
        return userTypeName;
    }

    public String getUserCompanyName() {
        return userCompanyName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserCompanyName(String userCompanyName) {
        this.userCompanyName = userCompanyName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
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

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public List<EmployeeProject> getEmployeeProjectList() {
        return employeeProjectList;
    }

    public void setEmployeeProjectList(List<EmployeeProject> employeeProjectList) {
        this.employeeProjectList = employeeProjectList;
    }

    public String getAccessProject() {
        return accessProject;
    }

    public void setAccessProject(String accessProject) {
        this.accessProject = accessProject;
    }

    public String getAccessProperty() {
        return accessProperty;
    }

    public void setAccessProperty(String accessProperty) {
        this.accessProperty = accessProperty;
    }

    public String getAccessEnquiry() {
        return accessEnquiry;
    }

    public void setAccessEnquiry(String accessEnquiry) {
        this.accessEnquiry = accessEnquiry;
    }

    public String getAccessPayment() {
        return accessPayment;
    }

    public void setAccessPayment(String accessPayment) {
        this.accessPayment = accessPayment;
    }

    public String getAccessUser() {
        return accessUser;
    }

    public void setAccessUser(String accessUser) {
        this.accessUser = accessUser;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getGstNumber() {
      return gstNumber;
    }
  
    public void setGstNumber(String gstNumber) {
      this.gstNumber = gstNumber;
    }
    
}
