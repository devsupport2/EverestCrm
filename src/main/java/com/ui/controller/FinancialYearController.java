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

import com.ui.dao.FinancialYearDAO;
import com.ui.model.FinancialYear;

@RestController
public class FinancialYearController {
	@Autowired
	FinancialYearDAO financialYearDAO;
	FinancialYear financialYear;
	private static final Logger logger = LoggerFactory.getLogger(FinancialYearController.class);

	@RequestMapping(value = "/getFinancialYears", method = RequestMethod.GET, produces = "application/json")
	public List<FinancialYear> getFinancialYears(HttpServletRequest request) {
		logger.info("***** GET FINANCIAL YEAR *****");
		List<FinancialYear> f = financialYearDAO.getAllFinancialYears();
		return f;
	}

	@RequestMapping(value = "/getFinancialYearsByPage", method = RequestMethod.GET, produces = "application/json")
	public List<FinancialYear> getFinancialYearsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET FINANCIAL YEAR BY PAGE *****");
		List<FinancialYear> f = financialYearDAO.getAllFinancialYearsByPage(pagesize, startindex);
		return f;
	}

	@RequestMapping(value = "/searchFinancialYears", method = RequestMethod.GET, produces = "application/json")
	public List<FinancialYear> searchFinancialYears(String keyword, HttpServletRequest request) {
		logger.info("***** SERACH FINANCIAL YEAR *****");
		List<FinancialYear> f = financialYearDAO.searchFinancialYears(keyword);
		return f;
	}

	@RequestMapping(value = "addFinancialYear", method = RequestMethod.POST)
	public String addFinancialYear(String financialyearstartdate, String financialyearenddate, String financialyearcode,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD FINANCIAL YEAR *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		String result = null;
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		financialYear = new FinancialYear(financialyearstartdate, financialyearenddate, financialyearcode, "n", s, id,
				IpAddress);
		result = financialYearDAO.addFinancialYear(financialYear);
		return result;
	}

	@RequestMapping(value = "editFinancialYear", method = RequestMethod.POST)
	public String editFinancialYear(int financialyearid, String financialyearstartdate, String financialyearenddate,
			String financialyearcode, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT FINANCIAL YEAR *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		String result = null;
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		financialYear = new FinancialYear(financialyearid, financialyearstartdate, financialyearenddate,
				financialyearcode, id, IpAddress);
		result = financialYearDAO.editFinancialYear(financialYear);
		return result;
	}

	@RequestMapping(value = "deleteFinancialYear", method = RequestMethod.DELETE)
	public void delete(int financialyearid) {
		logger.info("***** DELETE FINANCIAL YEAR *****");
		financialYearDAO.deleteFinancialYear(financialyearid);
	}

	@RequestMapping(value = "addDefaultFinancialYear", method = RequestMethod.POST)
	public String addDefaultFinancialYear(int financialyearid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD DEFAULT YEAR *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		financialYear = new FinancialYear(financialyearid, id, IpAddress);
		financialYearDAO.disableDefaultFinancialYear(financialYear);

		financialYear = new FinancialYear(financialyearid, id, IpAddress);
		financialYearDAO.addDefaultFinancialYear(financialYear);

		return "Success";
	}
}