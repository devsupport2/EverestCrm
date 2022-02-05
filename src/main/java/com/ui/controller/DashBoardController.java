package com.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.EnquiryDAO;
import com.ui.dao.ProjectDAO;
import com.ui.model.DashBoard;

@RestController
public class DashBoardController {
  @Autowired
  ProjectDAO projectDao;
  @Autowired
  EnquiryDAO enquiryDAO;
  
  DashBoard dash;
  private static final Logger logger = LoggerFactory.getLogger(DashBoardController.class);

  
  @RequestMapping(value = "/getAllDashBoardDetails", method = RequestMethod.GET, produces = "application/json")
  public DashBoard getAllDashBoardDetails(HttpServletRequest request) {
      logger.info("***** GET ALL DASHBOARD DETAILS *****");
      dash = new DashBoard();
      dash.setProductList(projectDao.getProjectNameAndEnquiryCount());
      dash.setEnquiryCountryWiseList(enquiryDAO.getEnquiryListCountryWise());
      return dash;
  }
  
}
