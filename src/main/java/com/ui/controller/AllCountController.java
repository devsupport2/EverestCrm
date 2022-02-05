package com.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.AllCountDAO;
import com.ui.model.AllCount;


@RestController
public class AllCountController {
	@Autowired
	AllCountDAO allCountDao;
	
	AllCount allCount;	
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/getAllCounts", method= RequestMethod.GET, produces = "application/json")
	public AllCount getAllCounts(HttpServletRequest request, HttpSession session) {
		logger.info("Inside Get All Counts Controller");		
		AllCount allCount = allCountDao.getAllCounts(session);		
		return allCount;
	}		
	
}
