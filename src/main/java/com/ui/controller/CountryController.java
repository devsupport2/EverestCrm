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

import com.ui.dao.CountryDAO;
import com.ui.model.Country;

@RestController
public class CountryController {
	@Autowired
	CountryDAO countryDAO;

	Country country;

	private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

	@RequestMapping(value = "/getCountries", method = RequestMethod.GET, produces = "application/json")
	public List<Country> getCountries(HttpServletRequest request) {
		logger.info("***** GET COUNTRY *****");
		List<Country> country = countryDAO.getAllCountries();
		return country;
	}

	@RequestMapping(value = "/getCountriesByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Country> getCountriesByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET COUNTRY BY PAGE *****");
		List<Country> country = countryDAO.getAllCountriesByPage(pagesize, startindex);
		return country;
	}

	@RequestMapping(value = "/searchCountries", method = RequestMethod.GET, produces = "application/json")
	public List<Country> searchCountries(String keyword, HttpServletRequest request) {
		logger.info("**** SEARCH COUNTRY *****");
		List<Country> country = countryDAO.searchCountries(keyword);
		return country;
	}

	@RequestMapping(value = "addCountry", method = RequestMethod.POST)
	public String addCountry(String countryname, String countrycode, String countrydialingcode,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD COUNTRY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String s = "y";
		String result = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		country = new Country(countryname, countrycode, countrydialingcode, s, id, IpAddress);
		result = countryDAO.addCountry(country);

		return result;
	}

	@RequestMapping(value = "editCountry", method = RequestMethod.POST)
	public String editCountry(int countryid, String countryname, String countrycode, String countrydialingcode,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT COUNTRY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String result = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		country = new Country(countryid, countryname, countrycode, countrydialingcode, id, IpAddress);
		result = countryDAO.editCountry(country);
		return result;
	}

	@RequestMapping(value = "deleteCountry", method = RequestMethod.DELETE)
	public void delete(int countryid) {
		logger.info("***** DELETE COUNTRY *****");
		countryDAO.deleteCountry(countryid);
	}
	
	@RequestMapping(value = "/getCountryDetailById", method = RequestMethod.GET, produces = "application/json")
	public Country getCountryDetailById(int countryid, HttpServletRequest request) {
		logger.info("***** GET COUNTRY DETAIL BY ID *****");
		Country country = countryDAO.getCountryDetailById(countryid);
		return country;
	}

}