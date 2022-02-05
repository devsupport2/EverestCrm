package com.ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.EnquiryDAO;
import com.ui.dao.FinancialYearDAO;
import com.ui.dao.ProjectDAO;
import com.ui.dao.PropertyDAO;
import com.ui.dao.StateDAO;
import com.ui.dao.UserDAO;
import com.ui.model.AllEnquiryStatus;
import com.ui.model.EmployeeProject;
import com.ui.model.Enquiry;
import com.ui.model.EnquiryAssign;
import com.ui.model.EnquiryFollowup;
import com.ui.model.EnquiryProperty;
import com.ui.model.EnquiryStatus;
import com.ui.model.EnquiryStatusReason;
import com.ui.model.FollowupEmployee;
import com.ui.model.Project;
import com.ui.model.ProjectDetail;
import com.ui.model.Property;
import com.ui.model.RealestateSubcategory;
import com.ui.model.RealestateType;
import com.ui.model.State;
import com.ui.model.User;
import com.ui.model.UserType;

@RestController
public class EnquiryController {
	@Autowired
	EnquiryDAO enquiryDao;
	@Autowired
	FinancialYearDAO financialYearDAO;
	@Autowired
	PropertyDAO propertyDao;
	@Autowired
	UserDAO userDao;
	@Autowired
	StateDAO stateDao;
	@Autowired
	ProjectDAO projectDao;

	Enquiry enquiry;
	User user;
	EnquiryStatus enquiryStatus;
	EnquiryProperty enquiryProperty;
	FollowupEmployee followupEmployee;
	EnquiryFollowup enquiryFollowup;
	EnquiryStatusReason enquiryStatusReason;
	AllEnquiryStatus allEnquiryStatus;
	Property property;
	EnquiryAssign enquiryAssign;
	State state;
	UserType userType;
	Project project;
	RealestateType category;
	RealestateSubcategory subcategory;

	private static final Logger logger = LoggerFactory.getLogger(EnquiryController.class);
	
	
	@RequestMapping(value = "/getLastHundredEnquiryList", method = RequestMethod.GET, produces = "application/json")
    public List<Enquiry> getLastHundredEnquiryList(HttpServletRequest request,
            HttpSession session) {
        logger.info("***** GET LAST 100 ENQUIRIES *****");

        int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        
        List<Enquiry> enquiry = enquiryDao.getLastHundredEnquiryList(session);

        List<Enquiry> enq = new ArrayList<Enquiry>();

        if (typeid == 1) {
            for (Enquiry e : enquiry) {
                e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));
                enq.add(e);
            }
        }

        if (typeid == 2) {

            for (Enquiry e : enquiry) {
                e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

                List<EnquiryProperty> enqProperty = enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId());

                List<EmployeeProject> empProject = userDao.getEmployeeProjectListById(id);

                for (EmployeeProject ep : empProject) {

                    for (EnquiryProperty enqpro : enqProperty) {
                        if (ep.getProjectId() == enqpro.getProjectId()) {
                            enq.add(e);
                        }
                    }

                }
            }
        }

        return enq;
    }
	
	

	@RequestMapping(value = "/getEnquiryListByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getEnquiryListByDate(String startdate, String enddate, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** GET ENQUIRIES BY DATE *****");

		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		List<Enquiry> enquiry = enquiryDao.getEnquiryListByDate(startdate, enddate);

		List<Enquiry> enq = new ArrayList<Enquiry>();

		if (typeid == 1) {
			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));
				enq.add(e);
			}
		}

		if (typeid == 2) {

			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				List<EnquiryProperty> enqProperty = enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId());

				List<EmployeeProject> empProject = userDao.getEmployeeProjectListById(id);

				for (EmployeeProject ep : empProject) {

					for (EnquiryProperty enqpro : enqProperty) {
						if (ep.getProjectId() == enqpro.getProjectId()) {
							enq.add(e);
						}
					}

				}
			}
		}

		return enq;
	}

	@RequestMapping(value = "/getEnquiriesByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getEnquiriesByPage(int pagesize, int startindex, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** GET ENQUIRIES BY PAGE *****");

		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		List<Enquiry> enquiry = enquiryDao.getEnquiriesByPage(pagesize, startindex, session);

		List<Enquiry> enq = new ArrayList<Enquiry>();

		if (typeid == 1) {
			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				enq.add(e);
			}
		}

		if (typeid == 2) {

			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				List<EnquiryProperty> enqProperty = enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId());

				List<EmployeeProject> empProject = userDao.getEmployeeProjectListById(id);

				for (EmployeeProject ep : empProject) {

					for (EnquiryProperty enqpro : enqProperty) {
						if (ep.getProjectId() == enqpro.getProjectId()) {
							enq.add(e);
						}
					}

				}
			}
		}

		return enq;
	}
	
	@RequestMapping(value = "/getEnquiryListByDateAndProject", method = RequestMethod.GET, produces = "application/json")
    public List<Enquiry> getEnquiryListByDateAndProject(int pagesize, int startindex, int projectid, HttpServletRequest request,
            HttpSession session) {
        logger.info("***** GET ENQUIRIES LIST BY PAGE AND ID *****");

        int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

        List<Enquiry> enquiry = enquiryDao.getEnquiriesByPageAndId(pagesize, startindex, projectid, session);

        List<Enquiry> enq = new ArrayList<Enquiry>();

        if (typeid == 1) {
            for (Enquiry e : enquiry) {
                e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

                enq.add(e);
            }
        }

        if (typeid == 2) {

            for (Enquiry e : enquiry) {
                e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

                List<EnquiryProperty> enqProperty = enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId());

                List<EmployeeProject> empProject = userDao.getEmployeeProjectListById(id);

                for (EmployeeProject ep : empProject) {

                    for (EnquiryProperty enqpro : enqProperty) {
                        if (ep.getProjectId() == enqpro.getProjectId()) {
                            enq.add(e);
                        }
                    }

                }
            }
        }

        return enq;
    }

	@RequestMapping(value = "/getAllOpenEnquiries", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getEnquiries(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET ENQUIRIES *****");

		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		List<Enquiry> enquiry = enquiryDao.getAllOpenEnquiries(session);

		List<Enquiry> enq = new ArrayList<Enquiry>();

		if (typeid == 1) {
			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				enq.add(e);
			}
		}

		if (typeid == 2) {

			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				List<EnquiryProperty> enqProperty = enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId());

				List<EmployeeProject> empProject = userDao.getEmployeeProjectListById(id);

				for (EmployeeProject ep : empProject) {

					for (EnquiryProperty enqpro : enqProperty) {
						if (ep.getProjectId() == enqpro.getProjectId()) {
							enq.add(e);
						}
					}
				}
			}
		}

		return enq;
	}

	@RequestMapping(value = "/searchEnquiry", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> searchEnquiry(String keyword, HttpServletRequest request, HttpSession session) {
		logger.info("***** SEARCH ENQUIRIES *****");

		List<Enquiry> enquiry = enquiryDao.searchEnquiry(keyword, session);

		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		List<Enquiry> enq = new ArrayList<Enquiry>();

		if (typeid == 1) {
			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				enq.add(e);
			}
		}

		if (typeid == 2) {

			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				List<EnquiryProperty> enqProperty = enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId());

				List<EmployeeProject> empProject = userDao.getEmployeeProjectListById(id);

				for (EmployeeProject ep : empProject) {

					for (EnquiryProperty enqpro : enqProperty) {
						if (ep.getProjectId() == enqpro.getProjectId()) {
							enq.add(e);
						}
					}
				}
			}
		}

		return enq;
	}

	@RequestMapping(value = "/getEnquiriesByStatus", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getEnquiriesByStatus(String status, HttpServletRequest request, HttpSession session) {
		logger.info("***** GET ENQUIRIES BY STATUS *****");
		List<Enquiry> enquiry = enquiryDao.getEnquiriesByStatus(status, session);

		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		List<Enquiry> enq = new ArrayList<Enquiry>();

		if (typeid == 1) {
			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				enq.add(e);
			}
		}

		if (typeid == 2) {

			for (Enquiry e : enquiry) {
				e.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId()));

				List<EnquiryProperty> enqProperty = enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId());

				List<EmployeeProject> empProject = userDao.getEmployeeProjectListById(id);

				for (EmployeeProject ep : empProject) {

					for (EnquiryProperty enqpro : enqProperty) {
						if (ep.getProjectId() == enqpro.getProjectId()) {
							enq.add(e);
						}
					}
				}
			}
		}

		return enq;
	}

	@RequestMapping(value = "/getEnquiriesByFilter", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getEnquiriesByFilter(int id, String flag, HttpServletRequest request, HttpSession session) {
		logger.info("***** GET ENQUIRIES BY FILTER *****");

		List<Enquiry> enq = new ArrayList<Enquiry>();

		List<Enquiry> enquiry = enquiryDao.getAllOpenEnquiries(session);

		if (flag.equals("P")) {
			for (Enquiry e : enquiry) {
				List<EnquiryProperty> ep = enquiryDao.getEnquiryPropertyByEnquiryId(e.getEnquiryId());

				for (EnquiryProperty ep1 : ep) {
					if (ep1.getProjectId() == id) {
						Enquiry e1 = enquiryDao.getEnquiryDetailsById(ep1.getEnquiryId());

						e1.setEnquiryPropertyList(enquiryDao.getEnquiryPropertyByEnquiryId(e1.getEnquiryId()));

						enq.add(e1);
					}
				}
			}
		}

		return enq;
	}

	@RequestMapping(value = "/getReferenceTitleByUserTypeId", method = RequestMethod.GET, produces = "application/json")
	public List<User> getReferenceTitleByUserTypeId(int usertypeid, HttpServletRequest request) {
		logger.info("***** GET Reference Name By USER TYPE ID  *****");
		List<User> user = enquiryDao.getReferenceTitleByUserTypeId(usertypeid);
		return user;
	}

	@RequestMapping(value = "/getClientAndProspectTitle", method = RequestMethod.GET, produces = "application/json")
	public List<User> getClientAndProspectTitle(HttpServletRequest request) {
		logger.info("***** GET Client AND PROSPECT Name  *****");
		List<User> user = enquiryDao.getClientAndProspectTitle();
		return user;
	}

	@RequestMapping(value = "/getAllEnquiryPropertyName", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryProperty> getAllEnquiryPropertyName(HttpServletRequest request) {
		logger.info("***** ALL ENQUIRY PRODUCT NAME *****");
		List<EnquiryProperty> enquiryProperty = enquiryDao.getAllEnquiryPropertyName();
		return enquiryProperty;
	}

	@RequestMapping(value = "deleteFollowup", method = RequestMethod.DELETE)
	public void deleteFollowup(int followupid) {
		logger.info("***** DELETE FOLLOWUP *****");
		enquiryDao.deleteFollowup(followupid);
	}

	@RequestMapping(value = "/getFollowupEnquiriesByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getFollowupEnquiriesByDate(String fromdate, String todate, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** GETFOLLOWUP ENQUIRIES BY DATE *****");
		List<Enquiry> enquiry = enquiryDao.getFollowupEnquiriesByDate(session, fromdate, todate);
		return enquiry;
	}

	@RequestMapping(value = "/getEnquiryDetailsById", method = RequestMethod.GET, produces = "application/json")
	public Enquiry getEnquiryDetailsById(int enquiryid, HttpServletRequest request) {
		logger.info("***** GET ENQUIRY DETAILS BY ID CONTROLLER *****");
		Enquiry enquiry = enquiryDao.getEnquiryDetailsById(enquiryid);
		return enquiry;
	}

	@RequestMapping(value = "/getEnquiryPropertyByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryProperty> getEnquiryPropertyByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** GET ENQUIRY PROPERTY BY ID CONTROLLER *****");
		List<EnquiryProperty> enquiryProperty = enquiryDao.getEnquiryPropertyByEnquiryId(enquiryid);
		return enquiryProperty;
	}

	@RequestMapping(value = "/getTodayFollowupEnquiries", method = RequestMethod.GET, produces = "application/json")
	public List<Enquiry> getTodayFollowupEnquiries(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET TODAY FOLLOWUP ENQUIRIES *****");
		List<Enquiry> enquiry = enquiryDao.getTodayFollowupEnquiries(session);
		return enquiry;
	}

	@RequestMapping(value = "/getEnquiryFollowupByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryFollowup> getEnquiryFollowupByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY FOLLOW-UP BY ENQUIRY ID *****");
		List<EnquiryFollowup> enquiryFollowup = enquiryDao.getEnquiryFollowupByEnquiryId(enquiryid);
		return enquiryFollowup;
	}

	@RequestMapping(value = "/getEnquiryLogByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryStatus> getEnquiryLogByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY LOG BY ENQUIRY ID *****");
		List<EnquiryStatus> enquiryStatus = enquiryDao.getEnquiryLogByEnquiryId(enquiryid);
		return enquiryStatus;
	}

	@RequestMapping(value = "/getStatusReason", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryStatusReason> getStatusReason(HttpServletRequest request, HttpSession session) {
		logger.info("***** GET STATUS REASON *****");
		List<EnquiryStatusReason> enquiryStatusReason = enquiryDao.getStatusReason();
		return enquiryStatusReason;
	}

	@RequestMapping(value = "addEnquiry", method = RequestMethod.POST)
	public String addEnquiry(String enquirydate, String enquiryvia, int referenceid, int clientid, int employeeid,
			String remark, int occupationid, int designationid, String chooseoption, int budgetid, String features, String consideringproject, int expectingtime, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		String enquiryno = null;
		int sequence = 0;
		String currentYearCode = financialYearDAO.getCurrentFinancialYearCode();
		Enquiry enquiry = null;
		enquiry = enquiryDao.getLastEnquiryDetail();
		if (enquiry == null) {
			sequence = 1;
			enquiryno = "EVE-" + currentYearCode + "-I0001";
			enquiry = new Enquiry(sequence, enquiryno, referenceid, clientid, employeeid, enquirydate, enquiryvia,
					remark, occupationid, designationid, chooseoption, budgetid, features, consideringproject, expectingtime, s, id, IpAddress);
			enquiryDao.addEnquiry(enquiry);
			int eid = enquiryDao.getLastEnquiryId();
			enquiryStatus = new EnquiryStatus(eid, 0, 1, "Y", id, IpAddress);
			enquiryDao.addEnquiryStatus(enquiryStatus);
			return "Success";
		} else {
			String fc = enquiry.getEnquiryNo();
			String financialyearcode1 = fc.substring(4, 8);

			sequence = enquiryDao.getLastEnquirySequence();

			System.out.println("Last Sequence---------------------------->" + sequence);
			if (sequence == 0) {
				sequence = 1;
				enquiryno = "EVE-" + currentYearCode + "-I0001";
				enquiry = new Enquiry(sequence, enquiryno, referenceid, clientid, employeeid, enquirydate, enquiryvia,
						remark, occupationid, designationid, chooseoption, budgetid, features, consideringproject, expectingtime, s, id, IpAddress);
				enquiryDao.addEnquiry(enquiry);
				int eid = enquiryDao.getLastEnquiryId();
				enquiryStatus = new EnquiryStatus(eid, 0, 1, "Y", id, IpAddress);
				enquiryDao.addEnquiryStatus(enquiryStatus);
				return "Success";

			} else {
				if (currentYearCode.equals(financialyearcode1)) {
					sequence = sequence + 1;
					System.out.println("Sequence Plus---------------------------->" + sequence);
				} else {
					sequence = 1;
					System.out.println("Sequence 1---------------------------->" + sequence);
				}
				if (sequence >= 1 && sequence < 10) {
					enquiryno = "EVE-" + currentYearCode + "-I0000" + Integer.toString(sequence);
				} else if (sequence >= 10 && sequence < 100) {
					enquiryno = "EVE-" + currentYearCode + "-I000" + Integer.toString(sequence);
				} else if (sequence >= 100 && sequence < 1000) {
					enquiryno = "EVE-" + currentYearCode + "-I00" + Integer.toString(sequence);
				} else if (sequence >= 1000 && sequence < 10000) {
					enquiryno = "EVE-" + currentYearCode + "-I0" + Integer.toString(sequence);
				}else if (sequence >= 10000 && sequence < 100000) {
                  enquiryno = "EVE-" + currentYearCode + "-I" + Integer.toString(sequence);
              }
				enquiry = new Enquiry(sequence, enquiryno, referenceid, clientid, employeeid, enquirydate, enquiryvia,
						remark, occupationid, designationid, chooseoption, budgetid, features, consideringproject, expectingtime, s, id, IpAddress);
				enquiryDao.addEnquiry(enquiry);

				int eid = enquiryDao.getLastEnquiryId();
				enquiryStatus = new EnquiryStatus(eid, 0, 1, "Y", id, IpAddress);
				enquiryDao.addEnquiryStatus(enquiryStatus);

				return "Success";
			}
		}
	}

	@RequestMapping(value = "editEnquiry", method = RequestMethod.POST)
	public String editEnquiry(int enquiryid, String enquirydate, String enquiryvia, int referenceid, int clientid,
			int employeeid, String remark, int occupationid, int designationid, String chooseoption, int budgetid, String features, String consideringproject, int expectingtime, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY CONTROLLER *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		enquiry = new Enquiry(enquiryid, referenceid, clientid, employeeid, enquirydate, enquiryvia, remark, occupationid, designationid, chooseoption, budgetid, features, consideringproject, expectingtime, id,
				IpAddress);
		enquiryDao.editEnquiry(enquiry);

		enquiryStatus = new EnquiryStatus(enquiryid, 0, 2, "Y", id, IpAddress);
		enquiryDao.addEnquiryStatus(enquiryStatus);

		return "Success";
	}

	@RequestMapping(value = "addEnquiryProperty", method = RequestMethod.POST)
	public String addEnquiryProperty(int projectid, int realestatetypeid, int realestateSubcategoryid, int realestateid,
			int unitmasterid, int propertyid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY PROPERTY *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		int eid = enquiryDao.getLastEnquiryId();
		enquiryProperty = new EnquiryProperty(eid, projectid, realestatetypeid, realestateSubcategoryid, realestateid,
				unitmasterid, propertyid, s, id, IpAddress);
		enquiryDao.addEnquiryProperty(enquiryProperty);

		return "Success";
	}

	@RequestMapping(value = "editEnquiryProperty", method = RequestMethod.POST)
	public String editEnquiryProperty(int enquiryid, int projectid, int categoryid, int subcategoryid, int realestateid,
			int unitmasterid, int propertyid, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT PROJECT DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		enquiryProperty = new EnquiryProperty(enquiryid, projectid, categoryid, subcategoryid, realestateid,
				unitmasterid, propertyid, s, id, IpAddress);
		enquiryDao.addEnquiryProperty(enquiryProperty);

		return "Success";
	}

	@RequestMapping(value = "deleteEnquiryProperty", method = RequestMethod.DELETE)
	public void delete(int enquirypropertyid) {
		logger.info("***** DELETE ENQUIRY PROPERTY *****");
		enquiryDao.deleteEnquiryProperty(enquirypropertyid);
	}

	@RequestMapping(value = "changeEnquiryStatus", method = RequestMethod.POST)
	public String changeEnquiryStatus(int enquiryid, String enquirystatus, int statusreason, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** UPDATE ENQUIRY STATUS *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		allEnquiryStatus = new AllEnquiryStatus(enquiryid, enquirystatus, statusreason, "y", id, IpAddress);
		enquiryDao.insertEnquiryStatus(allEnquiryStatus);

		enquiry = new Enquiry(enquiryid, enquirystatus, statusreason);
		enquiryDao.changeEnquiryStatus(enquiry);

		enquiryStatus = new EnquiryStatus(enquiryid, 0, 8, "Y", id, IpAddress);
		enquiryDao.addEnquiryStatus(enquiryStatus);
		return "Success";
	}

	@RequestMapping(value = "markCompleteFollowup", method = RequestMethod.POST)
	public String markCompleteFollowup(int followupid, String status, HttpServletRequest request, HttpSession session) {
		logger.info("***** MARK FOLLOWUP AS COMPLETE *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		enquiryFollowup = new EnquiryFollowup(followupid, status, id, IpAddress);
		result = enquiryDao.markCompleteFollowup(enquiryFollowup);
		return result;
	}

	@RequestMapping(value = "addStatusReason", method = RequestMethod.POST)
	public String addStatusReason(String reason, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD STATUS REASON *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		reason = reason.replace("$", "&");
		reason = reason.replace("~", "#");
		reason = reason.replace("!", "%");

		enquiryStatusReason = new EnquiryStatusReason(reason, "y", id, IpAddress);
		enquiryDao.addStatusReason(enquiryStatusReason);
		return "Success";
	}

	@RequestMapping(value = "addEnquiryLog", method = RequestMethod.POST)
	public String addEnquiryLog(int enquiryid, String enquirylog, String logdatetime, String notifylogviaemail,
			String notifylogviasms, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY LOG *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		enquirylog = enquirylog.replace("$", "&");
		enquirylog = enquirylog.replace("~", "#");
		enquirylog = enquirylog.replace("!", "%");

		enquiryStatus = new EnquiryStatus(enquiryid, 0, enquirylog, logdatetime, "y", id, IpAddress);
		enquiryDao.addEnquiryLog(enquiryStatus);

		Enquiry e = enquiryDao.getEnquiryDetailsById(enquiryid);

		if (e.getStatus().equals("y")) {
			enquiryDao.openEnquiry(enquiryid);
		}

		session.setAttribute("notifylogviaemail", notifylogviaemail);
		session.setAttribute("notifylogviasms", notifylogviasms);

		return "Success";
	}

	@RequestMapping(value = "addEnquiryFollowup", method = RequestMethod.POST)
	public String addEnquiryFollowup(int enquiryid, String followupremark, String followupdatetime,
			String notifyfollowupviaemail, String notifyfollowupviasms, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD ENQUIRY FOLLOW-UP *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		followupremark = followupremark.replace("$", "&");
		followupremark = followupremark.replace("~", "#");
		followupremark = followupremark.replace("!", "%");

		enquiryFollowup = new EnquiryFollowup(enquiryid, followupdatetime, followupremark, "y", id, IpAddress);
		enquiryDao.addEnquiryFollowup(enquiryFollowup);

		session.setAttribute("notifyfollowupviaemail", notifyfollowupviaemail);
		session.setAttribute("notifyfollowupviasms", notifyfollowupviasms);

		return "Success";
	}

	@RequestMapping(value = "addEmpFollowup", method = RequestMethod.POST)
	public String addEmpFollowup(int userid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD EMPLOYEE IN FOLLOWUP *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int followupid = enquiryDao.getLastFollowupId();
		followupEmployee = new FollowupEmployee(followupid, userid, id, IpAddress);
		enquiryDao.addEmpFollowup(followupEmployee);
		return "Success";
	}

	@RequestMapping(value = "addEnquiryAssign", method = RequestMethod.POST)
	public String addEnquiryAssign(int userid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ENQUIRY ASSIGN *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int enquiryid = enquiryDao.getLastEnquiryId();
		enquiryAssign = new EnquiryAssign(enquiryid, userid, id, IpAddress);
		enquiryDao.addEnquiryAssign(enquiryAssign);
		return "Success";
	}

	@RequestMapping(value = "/getEnquiryAssignByEnquiryId", method = RequestMethod.GET, produces = "application/json")
	public List<EnquiryAssign> getEnquiryAssignByEnquiryId(int enquiryid, HttpServletRequest request) {
		logger.info("***** ENQUIRY ASSIGN BY ENQUIRY ID *****");
		List<EnquiryAssign> enquiryAssign = enquiryDao.getEnquiryAssignByEnquiryId(enquiryid);
		return enquiryAssign;
	}

	@RequestMapping(value = "editEnquiryAssign", method = RequestMethod.POST)
	public String editEnquiryAssign(int enquiryid, int userid, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT ENQUIRY ASSIGN *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		enquiryAssign = new EnquiryAssign(enquiryid, userid, id, IpAddress);
		enquiryDao.addEnquiryAssign(enquiryAssign);
		return "Success";
	}

	@RequestMapping(value = "/deleteEnquiryAssignRow", method = RequestMethod.DELETE)
	public void deleteEnquiryAssignRow(int id) {
		logger.info("***** DELETE ENQUIRY ASSIGN ROW *****");
		enquiryDao.deleteEnquiryAssignRow(id);
	}

	@RequestMapping(value = "deleteEnquiry", method = RequestMethod.DELETE)
	public void deleteEnquiry(int enquiryid) {
		logger.info("***** DELETE ENQUIRY CONTROLLER *****");
		enquiryDao.deleteEnquiry(enquiryid);
	}

	/*@RequestMapping(value = "importEnquiry", method = RequestMethod.POST)
	public int importEnquiry(@RequestParam(value = "enquiryfile", required = false) MultipartFile enquiryfile,
			HttpServletRequest request, HttpSession session) {

		logger.info("***** IMPORT ENQUIRY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";

		int count = 0;
		int i = 1;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(enquiryfile.getInputStream());
			HSSFSheet worksheet = workbook.getSheetAt(0);
			
		
			
while (i <= worksheet.getLastRowNum()) {
				int sequence = 0;
				int enquiryno = 0;
				int  referenceid = 0;
				int clientid = 0;
				int employeeid = 0;
				String enquirydate = null;
				String enquiryvia = null;
				String remark = null;
				int occupationid = 0;
				int designationid = 0;
				String chooseoption = null;
				int budgetid = 0;
				String features = null;
				String consideringproject = null;
				String expectingtime = null;
				String s = null;
				int id = 0;
				String IpAddress = null;
				

				count = count + 1;
				HSSFRow row = worksheet.getRow(i++);

				try {
					if (row.getCell(2).getIntCellValue() != null) {
					  sequence = row.getCell(2).getIntCellValue();
					} else {
					  sequence = 0;
					}
				} catch (Exception e) {
				  sequence = 0;
				}

				try {
					if (row.getCell(3).getIntCellValue() != null) {
					  referenceid = row.getCell(3).getIntCellValue();
					} else {
					  referenceid = 0;
					}
				} catch (Exception e) {
				  referenceid = 0;
				}

				try {
					if (row.getCell(4).getIntCellValue() != null) {
					  clientid = row.getCell(4).getIntCellValue();
					} else {
					  clientid = 0;
					}
				} catch (Exception e) {
				  clientid = 0;
				}

				try {
					if (row.getCell(5).getIntCellValue() != null) {
					  employeeid = row.getCell(5).getIntCellValue();
					} else {
					  employeeid = 0;
					}
				} catch (Exception e) {
				  employeeid = 0;
				}

				try {
					if (row.getCell(6).getStringCellValue() != null) {
					  enquirydate = row.getCell(6).getStringCellValue();
					} else {
					  enquirydate = null;
					}
				} catch (Exception e) {
				  enquirydate = null;
				}

				try {
					if (row.getCell(7).getStringCellValue() != null) {
					  enquiryvia = row.getCell(7).getStringCellValue();
					} else {
					  enquiryvia = null;
					}
				} catch (Exception e) {
				  enquiryvia = null;
				}

				try {
					if (row.getCell(8).getStringCellValue() != null) {
					  remark = row.getCell(8).getStringCellValue();
					} else {
					  remark = null;
					}
				} catch (Exception e) {
				  remark = null;
				}

				try {
					if (row.getCell(9).getIntCellValue() != null) {
					  occupationid = row.getCell(9).getIntCellValue();
					} else {
					  occupationid = 0;
					}
				} catch (Exception e) {
				  occupationid = 0;
				}

				try {
					if (row.getCell(10).getIntCellValue() != null) {
					  designationid = row.getCell(10).getIntCellValue();
					} else {
					  designationid = 0;
					}
				} catch (Exception e) {
				  designationid = 0;
				}
				try {
					if (row.getCell(13).getStringCellValue() != null) {
					  chooseoption = row.getCell(13).getStringCellValue();
					} else {
					  chooseoption = null;
					}
				} catch (Exception e) {
				  chooseoption = null;
				}

				try {
					if (row.getCell(15).getIntCellValue() != null) {
					  budgetid = row.getCell(15).getIntCellValue();
					} else {
					  budgetid = 0;
					}
				} catch (Exception e) {
				  budgetid = 0;
				}

				try {
					if (row.getCell(16).getStringCellValue() != null) {
					  features = row.getCell(16).getStringCellValue();
					} else {
					  features = null;
					}
				} catch (Exception e) {
				  features = null;
				}
				
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    consideringproject = row.getCell(16).getStringCellValue();
                  } else {
                    consideringproject = null;
                  }
              } catch (Exception e) {
                consideringproject = null;
              }
			    
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    expectingtime = row.getCell(16).getStringCellValue();
                  } else {
                    expectingtime = null;
                  }
              } catch (Exception e) {
                expectingtime = null;
              }
			    
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    IpAddress = row.getCell(16).getStringCellValue();
                  } else {
                    IpAddress = null;
                  }
              } catch (Exception e) {
                IpAddress = null;
              }
			    
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    s = row.getCell(16).getStringCellValue();
                  } else {
                    s = null;
                  }
              } catch (Exception e) {
                s = null;
              }
			    
			    try {
                  if (row.getCell(16).getStringCellValue() != null) {
                    ip = row.getCell(16).getStringCellValue();
                  } else {
                    ip = null;
                  }
              } catch (Exception e) {
                ip = null;
              }

				Enquiry enquiry = new Enquiry(sequence, enquiryno, referenceid, clientid, employeeid, enquirydate, enquiryvia,
			            remark, occupationid, designationid, chooseoption, budgetid, features, consideringproject, expectingtime, s, id, IpAddress);
				System.out.println("======== Hakuna matata ==================="+user.getFirstName()+" ================= "+user.getMobileNumber());    
				
				enquiryDao.addEnquiry(enquiry);

				clientid = enquiryDao.getLastEnquiryId();

				try {} catch (Exception e) {}

				try {} catch (Exception e) {}

				try {} catch (Exception e) {
					categoryid = 0;
				}

				try {} catch (Exception e) {
					subcategoryid = 0;
				}

				try {} catch (Exception e) {}

				
					} else {}
				}

				

				

				enquiryDao.addEnquiryProperty(ep);

				enquiryStatus = new EnquiryStatus(eid, 0, 1, "Y", id, IpAddress);
				enquiryDao.addEnquiryStatus(enquiryStatus);
			}

			workbook.close();

		} catch (IOException e) {}

		return count;
	}


*/
}