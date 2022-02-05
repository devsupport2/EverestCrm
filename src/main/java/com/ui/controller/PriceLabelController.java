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

import com.ui.dao.PriceLabelDAO;
import com.ui.model.PriceLabel;

@RestController
public class PriceLabelController {
  
  @Autowired
  PriceLabelDAO priceLabelDAO;
  PriceLabel priceLabel;
  private static final Logger logger = LoggerFactory.getLogger(PriceLabelController.class);
  

  @RequestMapping(value = "/searchPrices", method = RequestMethod.GET, produces = "application/json")
  public List<PriceLabel> searchPrices(String keyword, HttpServletRequest request) {
      logger.info("***** SEARCH PRICE LABEL *****");
      List<PriceLabel> t = priceLabelDAO.searchPrices(keyword);
      return t;
  }
  @RequestMapping(value = "/getAllPrices", method = RequestMethod.GET, produces = "application/json")
  public List<PriceLabel> getAllPrices(HttpServletRequest request) {
      logger.info("***** GET ALL PRICES *****");
      List<PriceLabel> t = priceLabelDAO.getAllPrices();
      return t;
  } 
  
  @RequestMapping(value = "/getPricesByPage", method = RequestMethod.GET, produces = "application/json")
  public List<PriceLabel> getPricesByPage(int pagesize, int startindex, HttpServletRequest request) {
      logger.info("***** GET PRICE BY PAGE *****");
      List<PriceLabel> t = priceLabelDAO.getPricesByPage(pagesize, startindex);
      return t;
  }

  @RequestMapping(value = "addPriceLabel", method = RequestMethod.POST)
  public String addPriceLabel(String pricelabel, String description, HttpServletRequest request, HttpSession session) {
      logger.info("***** ADD PRICE LABEL *****");
      String result = null;
      int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }
      String s = "y";
      
      priceLabel = new PriceLabel();
      priceLabel.setPriceLabel(pricelabel);
      priceLabel.setDescription(description);
      priceLabel.setStatus(s);
      priceLabel.setCreatedBy(id);
      priceLabel.setIpAddress(IpAddress);
      result = priceLabelDAO.addPriceLabel(priceLabel);
      return result;
  }

  @RequestMapping(value = "editPriceLabel", method = RequestMethod.POST)
  public String editPriceLabel(int priceid, String pricelabel, String description, HttpServletRequest request,
          HttpSession session) {
      logger.info("***** EDIT PRICE LABEL *****");
      String result = null;
      int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }
      priceLabel = new PriceLabel();
      priceLabel.setPriceLabelId(priceid);
      priceLabel.setPriceLabel(pricelabel);
      priceLabel.setDescription(description);
      priceLabel.setCreatedBy(id);
      priceLabel.setIpAddress(IpAddress);
      
      result = priceLabelDAO.editPriceLabel(priceLabel);
      return result;
  }

  @RequestMapping(value = "deletePrice", method = RequestMethod.DELETE)
  public void delete(int priceid) {
      logger.info("***** DELETE PRICE LABEL *****");
      priceLabelDAO.deletePrice(priceid);
  }

}
