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

import com.mchange.v2.c3p0.impl.NewProxyDatabaseMetaData;
import com.ui.dao.OccupationDao;

import com.ui.model.Occupation;

@RestController
public class OccupationController {
	
	@Autowired
	OccupationDao occupationDao;
	Occupation occupation;
	
	public static final Logger logger=LoggerFactory.getLogger(OccupationController.class);
	
	@RequestMapping(value = "addOccupation", method = RequestMethod.POST)
	public String addOccupation(String occupationname, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD OCCUPATION *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String result = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		Occupation d=new Occupation();
		d.setOccupationName(occupationname);
		d.setCreatedBy(id);
		d.setIpAddress(IpAddress);
		
		result = occupationDao.addOccupation(d);
		
		return result;
	}
	
	@RequestMapping(value = "/getOccupationsByPage", method = RequestMethod.GET, produces = "application/json")
    public List<Occupation> getOccupationsByPage(int pagesize, int startindex, HttpServletRequest request) {
        logger.info("***** GET OCCUPATION BY PAGE *****");
        List<Occupation> o = occupationDao.getOccupationsByPage(pagesize, startindex);
        return o;
    }
	
	@RequestMapping(value = "/getOccupationDetailById", method = RequestMethod.GET, produces = "application/json")
    public Occupation getOccupationDetailById(int occupationid, HttpServletRequest request) {
        logger.info("***** GET OCCUPATION DETAIL BY ID *****");
        Occupation o = occupationDao.getOccupationDetailById(occupationid);
        return o;
    }

    @RequestMapping(value = "/getAllOccupation", method = RequestMethod.GET, produces = "application/json")
    public List<Occupation> getAllOccupation(HttpServletRequest request) {
        logger.info("***** GET ALL OCCUPATION*****");
        List<Occupation> o= occupationDao.getAllOccupation();
        return o;
    }
	
	@RequestMapping(value = "deleteOccupation", method = RequestMethod.DELETE)
    public void delete(int occupationid) {
        logger.info("***** DELETE DEPARTMENT *****");
        occupationDao.deleteOccupation(occupationid);
        
    }
	
	@RequestMapping(value = "editOccupation", method = RequestMethod.POST)
	public String editOccupation(int occupationid, String occupationname, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT OCCUPATION*****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String result = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		Occupation o = new Occupation();
		o.setOccupationId(occupationid);
        o.setOccupationName(occupationname);
        o.setCreatedBy(id);
        o.setIpAddress(IpAddress);
		result = occupationDao.editOccupation(o);
		return result;
	}
}
