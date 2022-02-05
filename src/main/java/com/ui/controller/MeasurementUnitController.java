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

import com.ui.dao.MeasurementUnitDAO;
import com.ui.model.MeasurementUnit;

@RestController
public class MeasurementUnitController {
	@Autowired
	MeasurementUnitDAO measurementUnitDAO;

	MeasurementUnit measurementUnit;

	private static final Logger logger = LoggerFactory.getLogger(MeasurementUnitController.class);

	@RequestMapping(value = "/getMeasurementUnits", method = RequestMethod.GET, produces = "application/json")
	public List<MeasurementUnit> getMeasurementUnits(HttpServletRequest request) {
		logger.info("***** GET ALL UNITS +++++");
		List<MeasurementUnit> m = measurementUnitDAO.getAllMeasurementUnits();
		return m;
	}

	@RequestMapping(value = "/getMeasurementUnitsByPage", method = RequestMethod.GET, produces = "application/json")
	public List<MeasurementUnit> getMeasurementUnitsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET UNIT BY PAGE *****");
		List<MeasurementUnit> m = measurementUnitDAO.getAllMeasurementUnitsByPage(pagesize, startindex);
		return m;
	}

	@RequestMapping(value = "/searchMeasurementUnits", method = RequestMethod.GET, produces = "application/json")
	public List<MeasurementUnit> searchMeasurementUnits(String keyword, HttpServletRequest request) {
		logger.info("***** SERACH UNIT *****");
		List<MeasurementUnit> m = measurementUnitDAO.searchMeasurementUnits(keyword);
		return m;
	}

	@RequestMapping(value = "addMeasurementUnit", method = RequestMethod.POST)
	public String addMeasurementUnit(String measurementunitname, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD UNIT *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		measurementunitname = measurementunitname.replace("$", "&");
        measurementunitname = measurementunitname.replace("~", "#");
        measurementunitname = measurementunitname.replace("!", "%");
		
		String s = "y";
		measurementUnit = new MeasurementUnit(measurementunitname, description, s, id, IpAddress);
		measurementUnitDAO.addMeasurementUnit(measurementUnit);
		return "Success";
	}

	@RequestMapping(value = "editMeasurementUnit", method = RequestMethod.POST)
	public String editMeasurementUnit(int measurementunitid, String measurementunitname, String description,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT UNIT *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		measurementunitname = measurementunitname.replace("$", "&");
        measurementunitname = measurementunitname.replace("~", "#");
        measurementunitname = measurementunitname.replace("!", "%");
		
		measurementUnit = new MeasurementUnit(measurementunitid, measurementunitname, description, id, IpAddress);
		measurementUnitDAO.editMeasurementUnit(measurementUnit);
		return "Success";
	}

	@RequestMapping(value = "deleteMeasurementUnit", method = RequestMethod.DELETE)
	public void delete(int measurementunitid) {
		logger.info("***** DELETE UNIT *****");
		measurementUnitDAO.deleteMeasurementUnit(measurementunitid);
	}

}