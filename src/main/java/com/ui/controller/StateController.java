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

import com.ui.dao.StateDAO;
import com.ui.model.State;

@RestController
public class StateController {
	@Autowired
	StateDAO stateDAO;

	State state;

	private static final Logger logger = LoggerFactory.getLogger(StateController.class);

	@RequestMapping(value = "/getStates", method = RequestMethod.GET, produces = "application/json")
	public List<State> getStates(HttpServletRequest request) {
		logger.info("Inside Get All State Controller");
		List<State> state = stateDAO.getAllStates();
		return state;
	}

	@RequestMapping(value = "/getStatesByPage", method = RequestMethod.GET, produces = "application/json")
	public List<State> getStatesByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("Inside Get All State By Page Controller");

		List<State> state = stateDAO.getAllStatesByPage(pagesize, startindex);

		return state;
	}

	@RequestMapping(value = "/searchStates", method = RequestMethod.GET, produces = "application/json")
	public List<State> searchStates(String keyword, HttpServletRequest request) {
		logger.info("Inside Search State Controller");

		List<State> state = stateDAO.searchStates(keyword);

		return state;
	}

	@RequestMapping(value = "/getStateByCountryId", method = RequestMethod.GET, produces = "application/json")
	public List<State> getStateByCountryId(int countryid) {
		logger.info("Inside Get State By Country Id");
		List<State> s = stateDAO.getStateByCountryId(countryid);
		return s;
	}

	@RequestMapping(value = "addState", method = RequestMethod.POST)
	public String addState(String statename, String statecode, int countryname, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD STATE *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String status = "y";
		state = new State(statename, statecode, countryname, status, id, IpAddress);
		stateDAO.addState(state);
		return "Success";
	}

	@RequestMapping(value = "editState", method = RequestMethod.POST)
	public String editState(int stateid, String statename, String statecode, int countryname,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT STATE *****");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		state = new State(stateid, statename, statecode, countryname, id, IpAddress);
		stateDAO.editState(state);
		return "Success";
	}

	@RequestMapping(value = "deleteState", method = RequestMethod.DELETE)
	public void delete(int stateid) {
		logger.info("***** DELETE STATE *****");
		stateDAO.deleteState(stateid);
	}

}