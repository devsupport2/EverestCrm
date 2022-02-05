package com.ui.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ui.dao.DesignationDAO;
import com.ui.model.Designation;

@RestController
public class DesignationController {
	
	@Autowired
	DesignationDAO designationDAO;
	Designation designation; 
	
	public static final Logger logger=LoggerFactory.getLogger(DesignationController.class);
	
	@RequestMapping(value = "/getDesignationsByPage", method = RequestMethod.GET, produces = "application/json")
    public List<Designation> getDesignationsByPage(int pagesize, int startindex, HttpServletRequest request) {
        logger.info("***** GET DESIGNATION BY PAGE *****");
        List<Designation> d = designationDAO.getDesignationsByPage(pagesize, startindex);
        return d;
    }
	
	@RequestMapping(value = "/searchDesignations", method = RequestMethod.GET, produces = "application/json")
    public List<Designation> searchDesignations(String keyword, HttpServletRequest request) {
        logger.info("***** SEARCH DESIGNATIONS *****");
        List<Designation> d = designationDAO.searchDesignations(keyword);
        return d;
    }	
	
	@RequestMapping(value = "/getDesignationDetailById", method = RequestMethod.GET, produces = "application/json")
    public Designation getDesignationDetailById(int designationid, HttpServletRequest request) {
        logger.info("***** GET DESIGNATION DETAIL BY ID *****");
        Designation d = designationDAO.getDesignationDetailById(designationid);
        return d;
    }
	
	@RequestMapping(value = "/getAllDesignation", method = RequestMethod.GET, produces = "application/json")
    public List<Designation> getAllDesignation(HttpServletRequest request) {
        logger.info("***** GET ALL DESIGNATION*****");
        List<Designation> o= designationDAO.getAllDesignation();
        return o;
    }
	
	@RequestMapping(value = "deleteDesignation", method = RequestMethod.DELETE)
    public void delete(int designationid) {
        logger.info("***** DELETE DEPARTMENT *****");
        designationDAO.deleteDesignation(designationid);
        
    }	
	
	@RequestMapping(value = "addDesignation", method = RequestMethod.POST)
	public String addDesignation(String designationname, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD DESIGNATION *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String result = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		Designation d=new Designation();
		d.setDesignationName(designationname);
		d.setCreatedBy(id);
		d.setIpAddress(IpAddress);
		
		result = designationDAO.addDesignation(d);
		
		return result;
	}
	
	@RequestMapping(value = "editDesignation", method = RequestMethod.POST)
	public String editDesignation( int designationid,  String designationname, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT DESIGNATION *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String result = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		Designation d=new Designation();
		d.setDesignationId(designationid);
		d.setDesignationName(designationname);
		d.setCreatedBy(id);
		d.setIpAddress(IpAddress);
		
		result = designationDAO.editDesignation(d);
		
		return result;
	}
	

}
