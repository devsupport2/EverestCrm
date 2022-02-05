package com.ui.model;

import java.util.List;

public class DashBoard {
  
  
  
  
  private List<Project> productList;
  private List<Enquiry> enquiryCountryWiseList;

  public List<Project> getProductList() {
    return productList;
  }

  public void setProductList(List<Project> productList) {
    this.productList = productList;
  }

  public List<Enquiry> getEnquiryCountryWiseList() {
    return enquiryCountryWiseList;
  }

  public void setEnquiryCountryWiseList(List<Enquiry> enquiryCountryWiseList) {
    this.enquiryCountryWiseList = enquiryCountryWiseList;
  }
  
}
