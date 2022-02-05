package com.ui.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ui.model.AllEnquiryStatus;
import com.ui.model.Enquiry;
import com.ui.model.EnquiryAssign;
import com.ui.model.EnquiryFollowup;
import com.ui.model.EnquiryProperty;
import com.ui.model.EnquiryStatus;
import com.ui.model.EnquiryStatusReason;
import com.ui.model.FollowupEmployee;
import com.ui.model.User;

public interface EnquiryDAO {
	
	public List<Enquiry> getEnquiryListByDate(String startdate, String enddate);
	public List<Enquiry> getAllOpenEnquiries(HttpSession session);
	public List<Enquiry> getEnquiriesByStatus(String status, HttpSession session);
	public List<Enquiry> searchEnquiry(String keyword, HttpSession session);
	public List<User> getReferenceTitleByUserTypeId(int usertypeid);
	public List<User> getClientAndProspectTitle();
	public List<Enquiry> getEnquiriesByPage(int pagesize, int startindex, HttpSession session);
	public List<EnquiryStatus> getEnquiryLogByEnquiryId(int enquiryid);
	public List<Enquiry> getFollowupEnquiriesByDate(HttpSession session, String fromdate, String todate);
	public List<EnquiryFollowup> getEnquiryFollowupByEnquiryId(int enquiryid);
	public List<EnquiryStatusReason> getStatusReason();
	public List<EnquiryProperty> getAllEnquiryPropertyName();
	public String markCompleteFollowup(EnquiryFollowup ef);
	public Enquiry getLastEnquiryDetail();
	public void deleteFollowup(int followupid);
	public void changeEnquiryStatus(Enquiry e);
	public void insertEnquiryStatus(AllEnquiryStatus aes);
	public void addStatusReason(EnquiryStatusReason esr);
	public void addEnquiry(Enquiry e);
	public void addEnquiryStatus(EnquiryStatus e);
	public void addEnquiryProperty(EnquiryProperty ep);
	public void addEmpFollowup(FollowupEmployee e);
	public void addEnquiryFollowup(EnquiryFollowup e);
	public void addEnquiryLog(EnquiryStatus e);
	public void openEnquiry(int enquiryid);
	public int getLastEnquiryId();
	public void editEnquiry(Enquiry e);
	public Enquiry getEnquiryDetailsById(int enquiryid);
	public List<EnquiryProperty> getEnquiryPropertyByEnquiryId(int enquiryid);
	public List<Enquiry> getTodayFollowupEnquiries(HttpSession session);
	public int getLastEnquirySequence();
	public int getLastFollowupId();
	public void deleteEnquiryProperty(int enquirypropertyid);
	public void addEnquiryAssign(EnquiryAssign e);
	public List<EnquiryAssign> getEnquiryAssignByEnquiryId(int enquiryid);
	public void deleteEnquiryAssignRow(int id);
	public EnquiryStatus getLastEnquiryStatusDetail();
	public EnquiryFollowup getLastEnquiryFollowupDetail();
	public List<FollowupEmployee> getFollowupEmployeeByFollowupId(int followupid);
	public void deleteEnquiry(int enquiryid);	
	public List<Enquiry> getEnquiryListCountryWise();	
	public List<Enquiry> getLastHundredEnquiryList(HttpSession session);
	public List<Enquiry> getEnquiriesByPageAndId(int pagesize, int startindex, int projectid, HttpSession session);
}
