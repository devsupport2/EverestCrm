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
import com.ui.dao.CurrencyDAO;
import com.ui.model.Currency;

@RestController
public class CurrencyController {
	@Autowired
	CurrencyDAO currencyDAO;
	
	Currency currency;

	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

	@RequestMapping(value = "/getCurrencies", method = RequestMethod.GET, produces = "application/json")
	public List<Currency> getCurrencies(HttpServletRequest request) {
		logger.info("***** GET CURRENCIES *****");
		List<Currency> currency = currencyDAO.getAllCurrencies();
		return currency;
	}

	@RequestMapping(value = "/getCurrenciesByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Currency> getCurrenciesByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET CURRENCIES BY PAGE *****");
		List<Currency> currency = currencyDAO.getAllCurrenciesByPage(pagesize, startindex);
		return currency;
	}

	@RequestMapping(value = "/searchCurrencies", method = RequestMethod.GET, produces = "application/json")
	public List<Currency> searchCurrencies(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH CURRENCIES *****");
		List<Currency> currency = currencyDAO.searchCurrencies(keyword);
		return currency;
	}

	@RequestMapping(value = "addCurrency", method = RequestMethod.POST)
	public String addCurrency(String currencyname, String currencycode, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD CURRENCY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		String result = null;
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		currency = new Currency(currencyname, currencycode, description, s, id, IpAddress);
		result = currencyDAO.addCurrency(currency);

		return result;
	}

	@RequestMapping(value = "editCurrency", method = RequestMethod.POST)
	public String editCurrency(int currencyid, String currencyname, String currencycode, String currencysymbol,
			String description, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT CURRENCY *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String result = null;
		currency = new Currency(currencyid, currencyname, currencycode, description, id, IpAddress);
		result = currencyDAO.editCurrency(currency);
		return result;
	}

	@RequestMapping(value = "deleteCurrency", method = RequestMethod.DELETE)
	public void delete(int currencyid) {
		logger.info("***** DELETE CURRENCY *****");
		currencyDAO.deleteCurrency(currencyid);
	}

}