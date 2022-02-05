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

import com.ui.dao.TaxDAO;
import com.ui.model.Tax;

@RestController
public class TaxController {
	@Autowired
	TaxDAO taxDAO;
	Tax tax;
	private static final Logger logger = LoggerFactory.getLogger(TaxController.class);
	@RequestMapping(value = "/getTaxes", method = RequestMethod.GET, produces = "application/json")
	public List<Tax> getTaxes(HttpServletRequest request) {
		logger.info("***** GET TAXES *****");
		List<Tax> t = taxDAO.getAllTaxes();
		return t;
	}

	@RequestMapping(value = "/getTaxesByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Tax> getTaxesByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET TAX BY PAGE *****");
		List<Tax> t = taxDAO.getAllTaxesByPage(pagesize, startindex);
		return t;
	}

	@RequestMapping(value = "/searchTaxes", method = RequestMethod.GET, produces = "application/json")
	public List<Tax> searchTaxes(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH TAX *****");
		List<Tax> t = taxDAO.searchTaxes(keyword);
		return t;
	}

	@RequestMapping(value = "addTax", method = RequestMethod.POST)
	public String addTax(String taxname, String description, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD TAX *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		tax = new Tax(taxname, description, s, id, IpAddress);
		result = taxDAO.addTax(tax);
		return result;
	}

	@RequestMapping(value = "editTax", method = RequestMethod.POST)
	public String editTax(int taxid, String taxname, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT TAX *****");
		String result = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		tax = new Tax(taxid, taxname, description, id, IpAddress);
		result = taxDAO.editTax(tax);
		return result;
	}

	@RequestMapping(value = "deleteTax", method = RequestMethod.DELETE)
	public void delete(int taxid) {
		logger.info("***** DELETE TAX *****");
		taxDAO.deleteTax(taxid);
	}
}