package com.ui.controller;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.RangeDAO;
import com.ui.model.Currency;
import com.ui.model.RangeMaster;

@RestController
public class RangeController {
	@Autowired
	RangeDAO rangeDao;

	RangeMaster range;

	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
	
	@RequestMapping(value="getRangeByPage", method = RequestMethod.GET)
	public List<RangeMaster> getRangeByPage(int pagesize, int startindex, HttpServletRequest request){
		logger.info("***** GET RANGE BY PAGE *****");		
		return rangeDao.getRangeByPage(pagesize, startindex);
	}
	
	@RequestMapping(value="getAllRanges", method = RequestMethod.GET)
    public List<RangeMaster> getAllRanges(HttpServletRequest request){
        logger.info("***** GET ALL RANGES *****");       
        return rangeDao.getAllRanges();
    }
	
	@RequestMapping(value="getRangeDetailById", method = RequestMethod.GET)
    public RangeMaster getRangeDetailById(int rangeid, HttpServletRequest request){
        logger.info("***** GET RANGE BY ID *****");       
        return rangeDao.getRangeDetailById(rangeid);
    }

	@RequestMapping(value = "addRange", method = RequestMethod.POST)
	public String addRange(float rangefrom, float rangeto, int unitid, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD RANGE *****");

		String result = null;

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		range = new RangeMaster();
		
		range.setRangeFrom(rangefrom);
		range.setRangeTo(rangeto);
		range.setUnitId(unitid);
		range.setDescription(description);
		range.setStatus(s);
		range.setCreatedBy(id);
		range.setIpAddress(IpAddress);
		
		result = rangeDao.addRange(range);
		
		return result;
	}
	
	@RequestMapping(value = "editRange", method = RequestMethod.POST)
	public String editRange(int rangeid, float rangefrom, float rangeto, int unitid, String description, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT RANGE *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String result = null;
		range = new RangeMaster();
		
		range.setRangeId(rangeid);
		range.setRangeFrom(rangefrom);
		range.setRangeTo(rangeto);
		range.setUnitId(unitid);
		range.setDescription(description);
		range.setCreatedBy(id);
		range.setIpAddress(IpAddress);
		
		result = rangeDao.editRange(range);
		return result;
	}

	
	@RequestMapping(value = "deleteRange", method = RequestMethod.DELETE)
    public void delete(int rangeid) {
        logger.info("***** DELETE RANGE *****");
        rangeDao.deleteRange(rangeid);
        
    }   
}
