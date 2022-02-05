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

import com.ui.dao.DistrictDAO;
import com.ui.model.District;

@RestController
public class DistrictController {
	@Autowired
	DistrictDAO districtDao;

	District district;

	private static final Logger logger = LoggerFactory.getLogger(DistrictController.class);

	@RequestMapping(value = "/getDistricts", method = RequestMethod.GET, produces = "application/json")
	public List<District> getDistricts(HttpServletRequest request) {
		logger.info("***** GET DISTRICTS *****");
		List<District> district = districtDao.getAllDistricts();
		return district;
	}

	@RequestMapping(value = "/getDistrictsByPage", method = RequestMethod.GET, produces = "application/json")
	public List<District> getDistrictsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET DISTRICT BY PAGE *****");
		List<District> district = districtDao.getAllDistrictsByPage(pagesize, startindex);
		return district;
	}

	@RequestMapping(value = "/searchDistricts", method = RequestMethod.GET, produces = "application/json")
	public List<District> searchDistricts(String keyword, HttpServletRequest request) {
		logger.info("***** SERACH DISTRICTS *****");
		List<District> district = districtDao.searchDistricts(keyword);
		return district;
	}

	@RequestMapping(value = "/getDistrictDetailById", method = RequestMethod.GET, produces = "application/json")
	public District getDistrictDetailById(int districtid) {
		logger.info("***** GET DISTRICT DETAIL BY ID *****");
		District d = districtDao.getDistrictDetailById(districtid);
		return d;
	}

	@RequestMapping(value = "addDistrict", method = RequestMethod.POST)
	public String addDistrict(int countryname, int statename, String districtname, String districtcode,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD DISCTRICT *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String status = "y";
		district = new District(statename, countryname, districtname, districtcode, status, id, IpAddress);
		result = districtDao.addDistrict(district);
		return result;
	}

	@RequestMapping(value = "editDistrict", method = RequestMethod.POST)
	public String editDistrict(int districtid, int countryname, int statename, String districtname, String districtcode,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT STATE *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		district = new District(districtid, statename, countryname, districtname, districtcode, id, IpAddress);
		result = districtDao.editDistrict(district);
		return result;
	}

	@RequestMapping(value = "deleteDistrict", method = RequestMethod.DELETE)
	public void delete(int districtid) {
		logger.info("***** DELETE STATE *****");
		districtDao.deleteDistrict(districtid);
	}
	
	@RequestMapping(value = "/getDistrictByStateId", method = RequestMethod.GET, produces = "application/json")
	public List<District> getDistrictByStateId(int stateid) {
		logger.info("***** GET DISCTICT BY STATE ID *****");
		List<District> d = districtDao.getDistrictByStateId(stateid);
		return d;
	}

}
