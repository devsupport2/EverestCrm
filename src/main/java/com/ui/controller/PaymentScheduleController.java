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

import com.ui.dao.PaymentScheduleDAO;
import com.ui.model.Payment;

@RestController
public class PaymentScheduleController {
  @Autowired
  PaymentScheduleDAO paymentScheduleDAO;
  
  Payment payment;
  private static final Logger logger = LoggerFactory.getLogger(PaymentScheduleController.class);

  @RequestMapping(value = "/getPayments", method = RequestMethod.GET, produces = "application/json")
  public List<Payment> getPayments(HttpServletRequest request) {
      logger.info("***** GET PAYMENTS *****");
      List<Payment> payment = paymentScheduleDAO.getAllPayments();
      return payment;
  }

  @RequestMapping(value = "/getPaymentByPage", method = RequestMethod.GET, produces = "application/json")
  public List<Payment> getPaymentByPage(int pagesize, int startindex, HttpServletRequest request) {
      logger.info("***** GET PAYMENT BY PAGE *****");
      List<Payment> payment = paymentScheduleDAO.getAllPaymentByPage(pagesize, startindex);
      return payment;
  }

  @RequestMapping(value = "/searchPayments", method = RequestMethod.GET, produces = "application/json")
  public List<Payment> searchPayments(String keyword, HttpServletRequest request) {
      logger.info("**** SEARCH PAYMENT SCHEDULE *****");
      List<Payment> payment = paymentScheduleDAO.searchPayments(keyword);
      return payment;
  }

  @RequestMapping(value = "addPayment", method = RequestMethod.POST)
  public String addPayment(int projectid, String paymentlabel, String paymentschedule, String paymentpercentage,
          HttpServletRequest request, HttpSession session) {
      logger.info("***** ADD PAYMENT SCHEDULE *****");
      int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
      String s = "y";
      String result = null;
      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }
      payment = new Payment(projectid, paymentlabel, paymentschedule, paymentpercentage, s, id, IpAddress);
      result = paymentScheduleDAO.addPayment(payment);

      return result;
  }

  @RequestMapping(value = "editPayments", method = RequestMethod.POST)
  public String editPayments(int projectid, String paymentlabel, String paymentschedule, String paymentpercentage,
          HttpServletRequest request, HttpSession session) {
      logger.info("***** EDIT PAYMENT *****");
      int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
      String s = "y";
      String result = null;
      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }
      /*payment = new Payment(projectid, paymentlabel, paymentschedule, paymentpercentage, id, IpAddress);
      result = paymentScheduleDAO.editPayment(payment);*/
      
      payment = new Payment(projectid, paymentlabel, paymentschedule, paymentpercentage, s, id, IpAddress);
      result = paymentScheduleDAO.addPayment(payment);
      return result;
  }
  
  @RequestMapping(value = "deletePayment", method = RequestMethod.DELETE)
  public void delete(int paymentid) {
      logger.info("***** DELETE PAYMENT SCHEDULE *****");
      paymentScheduleDAO.deletePayment(paymentid);
  }
  
  @RequestMapping(value = "/getProjectIdById", method = RequestMethod.GET, produces = "application/json")
  public Payment getProjectIdById(int projectid, HttpServletRequest request) {
      logger.info("***** GET PROJECT ID BY ID *****");
      Payment payment = paymentScheduleDAO.getProjectIdById(projectid);
      return payment;
  }
  
  @RequestMapping(value = "/getPaymentDetailById", method = RequestMethod.GET, produces = "application/json")
  public List<Payment> getPaymentDetailById(int projectid, HttpServletRequest request) {
      logger.info("***** GET PAYMENT BY ID *****");
      List<Payment> payment = paymentScheduleDAO.getPaymentDetailById(projectid);
      return payment;
  }

}
