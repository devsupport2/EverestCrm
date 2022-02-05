package com.ui.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.LoginDAO;
import com.ui.model.User;

@RestController
public class LoginController {
	@Autowired
	LoginDAO loginDAO;

	User user;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "checkAdminLogin", method = RequestMethod.POST)
	public List<User> login(String email, String password, HttpSession session) {
		logger.info("Inside Admin Login Controller");

		List<User> user = loginDAO.checkAdminLogin(email, password);

		if (user.size() != 0) {
			session.setMaxInactiveInterval(300 * 60); // Set Session Time Out
														// time is 30 Minutes

			session.setAttribute("useridadmin", user.get(0).getUserId());
			session.setAttribute("shownameadmin", user.get(0).getFirstName() + " " + user.get(0).getLastName());
			session.setAttribute("usertypeidadmin", user.get(0).getUserTypeId());			
			session.setAttribute("emailidadmin", user.get(0).getEmail());
			session.setAttribute("mobilenumberadmin", user.get(0).getMobileNumber());
			session.setAttribute("accessProject", user.get(0).getAccessProject());
            session.setAttribute("accessProperty", user.get(0).getAccessProperty());
            session.setAttribute("accessEnquiry", user.get(0).getAccessEnquiry());
            session.setAttribute("accessPayment", user.get(0).getAccessPayment());
            session.setAttribute("accessUser", user.get(0).getAccessUser());  
            
            System.out.println("+++++++++++++++++++++admin ID++++++++++++"+user.get(0).getUserTypeId());
            //System.out.println("+++++++++++++++++++++admin ID++++++++++++"+user.get(0).getUserTypeId());
		}

		return user;
	}

	@RequestMapping(value = "forgetPassword", method = RequestMethod.POST)
	public String forgetPassword(String email, HttpServletRequest request, HttpSession session,
			HttpServletResponse res) {
		logger.info("***** FORGET PASSWORD *****");
		res.addHeader("Access-Control-Allow-Origin", "*");	
		User u = null;
		
		u = loginDAO.isRegistered(email);
		
		if(u != null){
			
			final String username = "devsupport2@ultrainfotech.net";
			final String password = "tushar@TP";
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "mail.ultrainfotech.net");
			javax.mail.Session session1 = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			try {				
			    InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net");
				Message message = new MimeMessage(session1);
				message.setFrom(new InternetAddress("devsupport2@ultrainfotech.net", "Astar Technologies"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				message.setRecipients(Message.RecipientType.BCC, myBccList);
				message.setSubject("Reset Password");
				StringBuilder sb = new StringBuilder();
				sb.append(
						"<!DOCTYPE html><html><head><title> Contact Us </title><meta charset='UTF-8'><meta name='description' content=''>"
								+ "<meta name='keywords' content=''></head><body><table style='border:solid 1px #872175; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
								+ "<tbody><tr><td style='padding:15px 0px;  font-size: 14px;  color: #373737; ' align='center' valign='middle'> <a href='http://www.rcbs.co.in'> "
								+ "<img border='0' src='http://www.ultrasmartsolutions.com/astar/resources/admin/images/Logo.png'/></a></td></tr><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'>"
								+ "</td></tr><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>"
								+ "<table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td style='background-color:#fff; padding:15px; border:solid 1px #dbdfe6' align='left' valign='top'>"
								+ "<table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:16px;color:#005DAA; align='left' valign='top' height='25'>Dear "
								+ u.getFirstName()+" "+u.getLastName()
								+ ", <br><br>Please click below link to reset your password.<br><br>"
								+ "<a href='http://bit.do/eHNjk'>Click here...</a><br><br><br>"
								+ "<br><br><br>Thanks & Regards,<br>Astar Technologies</td></tr>"
								+ "<tr><td colspan='3' align='left' valign='top'>&nbsp;</td></tr><tr><td align='left' valign='top' width='412'>"
								+ "</td></tr></tbody></table></td></tr></tbody></table></body></html>");

				message.setContent(sb.toString(), "text/html");
				Transport.send(message);
				System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
			} catch (Exception msg) {
				System.out.println("Not send mail " + msg);
			}
			
			return "Success";
		} else {
			return "Fail";
		} 		
	}
	
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	public String resetPassword(String email, String password, HttpServletRequest request, HttpSession session,
			HttpServletResponse res) {
		logger.info("***** FORGET PASSWORD *****");
		res.addHeader("Access-Control-Allow-Origin", "*");	
		User u = null;
		String check = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		u = loginDAO.isRegistered(email);
		
		if(u != null){
			User usr = new User(email, password, IpAddress);
			check = loginDAO.resetPassword(usr);			
			
			return check;
		} else {
			return "Fail";
		} 		
	}
	
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String changePassword(int userid, String currentpassword, String newpassword, HttpServletRequest request, HttpSession session,
			HttpServletResponse res) {
		logger.info("***** CHANGE PASSWORD *****");
		res.addHeader("Access-Control-Allow-Origin", "*");	
		User u = null;
		String check = null;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		u = loginDAO.checkPassword(userid, currentpassword);
		
		if(u != null){
			User usr = new User(u.getEmail(), newpassword, IpAddress);
			check = loginDAO.resetPassword(usr);			
			
			return check;
		} else {
			return "Fail";
		} 		
	}

}