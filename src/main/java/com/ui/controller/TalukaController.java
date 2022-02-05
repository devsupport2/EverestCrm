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

import com.ui.dao.TalukaDAO;
import com.ui.model.Taluka;

@RestController
public class TalukaController {
	@Autowired
	TalukaDAO talukaDao;

	Taluka taluka;

	private static final Logger logger = LoggerFactory.getLogger(TalukaController.class);

	@RequestMapping(value = "/getTalukas", method = RequestMethod.GET, produces = "application/json")
	public List<Taluka> getTalukas(HttpServletRequest request) {
		logger.info("***** GET TALUKAS *****");
		List<Taluka> taluka = talukaDao.getAllTalukas();
		return taluka;
	}

	@RequestMapping(value = "/getTalukasByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Taluka> getTalukasByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET TALUKA BY PAGE *****");
		List<Taluka> taluka = talukaDao.getAllTalukasByPage(pagesize, startindex);
		return taluka;
	}

	@RequestMapping(value = "/searchTalukas", method = RequestMethod.GET, produces = "application/json")
	public List<Taluka> searchTalukas(String keyword, HttpServletRequest request) {
		logger.info("***** SERACH TALUKAS *****");
		List<Taluka> taluka = talukaDao.searchTalukas(keyword);
		return taluka;
	}

	@RequestMapping(value = "/getTalukaDetailById", method = RequestMethod.GET, produces = "application/json")
	public Taluka getTalukaDetailById(int talukaid) {
		logger.info("***** GET TALUKA DETAIL BY ID *****");
		Taluka d = talukaDao.getTalukaDetailById(talukaid);
		return d;
	}

	@RequestMapping(value = "addTaluka", method = RequestMethod.POST)
	public String addTaluka(int countryname, int statename, int districtname, String talukaname, String talukacode,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD TALUKA *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String status = "y";
		taluka = new Taluka(districtname, statename, countryname, talukaname, talukacode, status, id, IpAddress);
		result = talukaDao.addTaluka(taluka);
		return result;
	}

	@RequestMapping(value = "editTaluka", method = RequestMethod.POST)
	public String editTaluka(int talukaid, int countryname, int statename, int districtname, String talukaname,
			String talukacode, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT STATE *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		taluka = new Taluka(talukaid, districtname, statename, countryname, talukaname, talukacode, id, IpAddress);
		result = talukaDao.editTaluka(taluka);
		return result;
	}

	@RequestMapping(value = "deleteTaluka", method = RequestMethod.DELETE)
	public void delete(int talukaid) {
		logger.info("***** DELETE STATE *****");
		talukaDao.deleteTaluka(talukaid);
	}
	
	@RequestMapping(value = "/getTalukaByDistrictId", method = RequestMethod.GET, produces = "application/json")
	public List<Taluka> getTalukaByDistrictId(int districtid) {
		logger.info("***** GET TALUKA BY DISTRICT ID *****");
		List<Taluka> t = talukaDao.getTalukaByDistrictId(districtid);
		return t;
	}
}
