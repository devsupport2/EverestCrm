package com.ui.controller;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.FeedbackDAO;
import com.ui.model.Feedback;
import com.ui.model.Slider;

@RestController
public class FeedbackController {
  
	@Autowired
	FeedbackDAO feedbackDAO;

	Feedback feedback;

	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@RequestMapping(value = "/getAllFeedback", method = RequestMethod.GET, produces = "application/json")
	public List<Feedback> getAllFeedback(HttpServletRequest request) {
		logger.info("***** GET ALL FEEDBACK *****");
		List<Feedback> feedback = feedbackDAO.getAllFeedback();
		return feedback;
	}
	
	/*@RequestMapping(value = "/getProgramFeedback", method = RequestMethod.GET, produces = "application/json")
    public List<Feedback> getProgramFeedback(HttpServletRequest request) {
        logger.info("***** GET ALL FEEDBACK  FOR FRONT *****");
        List<Feedback> feedback = feedbackDAO.getProgramFeedback();
        return feedback;
    }*/

	@RequestMapping(value = "/getFeedbackByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Feedback> getFeedbackByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET FEEDBACK BY PAGE *****");
		List<Feedback> feedback = feedbackDAO.getFeedbackByPage(pagesize, startindex);
		return feedback;
	}

	@RequestMapping(value = "/searchFeedback", method = RequestMethod.GET, produces = "application/json")
	public List<Feedback> searchFeedback(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH FEEDBACK *****");
		List<Feedback> feedback = feedbackDAO.searchFeedback(keyword);
		return feedback;
	}
	
	
	@RequestMapping(value = "/getFeedbackById", method = RequestMethod.GET, produces = "application/json")
    public Feedback getFeedbackById(int feedbackid, HttpServletRequest request) {
        logger.info("***** GET FEEDBACK BY ID *****");
        Feedback feedback = feedbackDAO.getFeedbackById(feedbackid);
        return feedback;
    }

	@RequestMapping(value = "addFeedback", method = RequestMethod.POST)
	public String addFeedback(@RequestParam(value="image", required=false) MultipartFile image, int userid, String firstname, String lastname, String orgnaization, String feedbackdis, int valuex, int valuey, int valuew, int valueh, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD FEEDBACK *****");

		feedbackdis = feedbackdis.replace("$", "&");
		feedbackdis = feedbackdis.replace("~", "#");
		feedbackdis = feedbackdis.replace("!", "%");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		
		String result = null;
		
		String image1="";
        
        try
        {
            if(image.getOriginalFilename() != "")
            {
                try
                {
                    byte[] bytes =  image.getBytes();
                    
                    File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "Testimonial");
                    if (!dir.exists()) 
                        dir.mkdirs();
                    String path = request.getRealPath("/resources/admin/images/Testimonial/");
                    File uploadfile = new File(path+File.separator+image.getOriginalFilename());
                    
                    /********* Today Start **********/
                    
                    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                    try
                    {
                        BufferedImage img = ImageIO.read(in);
                        
                        Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
                        BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
                        Graphics2D drawer = SubImgage.createGraphics();
                        drawer.setComposite(AlphaComposite.Src);
                        drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        drawer.drawImage(scaledImage, valuew, valueh, null);
                        drawer.dispose();
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        ImageIO.write(SubImgage, "png", buffer);
                        bytes = buffer.toByteArray();
                    }
                    catch (IOException e)
                    {
                        
                    }
                    
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();

                    image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/everest/resources/admin/images/Testimonial/" + image.getOriginalFilename();
                    
                }catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        feedback = new Feedback();
        feedback.setUserId(userid);
        feedback.setFirstName(firstname);
        feedback.setLastName(lastname);
        feedback.setOrgnaizationName(orgnaization);
        feedback.setImage(image1);
        feedback.setFeedback(feedbackdis);
        feedback.setStatus(s);
        feedback.setCreatedBy(id);
        feedback.setIpAddress(IpAddress);
		result = feedbackDAO.addFeedback(feedback);

		return result;
	}
	
	@RequestMapping(value = "editFeedback", method = RequestMethod.POST)
    public String editFeedback(@RequestParam(value="image", required=false) MultipartFile image, int feedbackid, int userid, String firstname, String lastname, String orgnaization, String feedbackdis, String oldimage, int valuex, int valuey, int valuew, int valueh, HttpServletRequest request, HttpSession session) {
        logger.info("***** EDIT FEEDBACK *****");

        feedbackdis = feedbackdis.replace("$", "&");
        feedbackdis = feedbackdis.replace("~", "#");
        feedbackdis = feedbackdis.replace("!", "%");

        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
                
        String result = null;

        String image1="";
        
        try
        {
            if(image.getOriginalFilename() != "")
            {
                try
                {
                    byte[] bytes =  image.getBytes();
                    
                    File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "Testimonial");
                    if (!dir.exists()) 
                        dir.mkdirs();
                    String path = request.getRealPath("/resources/admin/images/Testimonial/");
                    File uploadfile = new File(path+File.separator+image.getOriginalFilename());
                    
                    /********* Today Start **********/
                    
                    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                    try
                    {
                        BufferedImage img = ImageIO.read(in);
                        
                        Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
                        BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
                        Graphics2D drawer = SubImgage.createGraphics();
                        drawer.setComposite(AlphaComposite.Src);
                        drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        drawer.drawImage(scaledImage, valuew, valueh, null);
                        drawer.dispose();
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        ImageIO.write(SubImgage, "png", buffer);
                        bytes = buffer.toByteArray();
                    }
                    catch (IOException e)
                    {
                        
                    }
                    
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
                    bufferedOutputStream.write(bytes);
                    bufferedOutputStream.close();

                    image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/everest/resources/admin/images/Testimonial/" + image.getOriginalFilename();
                    
                }catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        feedback = new Feedback();
        feedback.setFeedbackId(feedbackid);
        feedback.setUserId(userid);
        feedback.setFirstName(firstname);
        feedback.setLastName(lastname);
        feedback.setOrgnaizationName(orgnaization);
        feedback.setImage(image1);
        feedback.setFeedback(feedbackdis);
        feedback.setCreatedBy(id);
        feedback.setIpAddress(IpAddress);
        result = feedbackDAO.editFeedback(feedback);
        
        return result;
    }

	@RequestMapping(value = "deleteFeedback", method = RequestMethod.DELETE)
    public void delete(int feedbackid) {
        logger.info("***** DELETE FEEDBACK *****");
        feedbackDAO.deleteFeedback(feedbackid);
    }
	
	/*
	@RequestMapping(value = "/getTestimonialDetails", method = RequestMethod.GET, produces = "application/json")
    public List<Feedback> getTestimonialDetails(HttpServletRequest request) {
        logger.info("***** GET ALL TESTIMONIAL FOR FRONT Page *****");
        List<Feedback> testimonial = feedbackDAO.getTestimonialDetails();
        return testimonial;
    }
	@RequestMapping(value = "/getLastThreeTestimonialDetails", method = RequestMethod.GET, produces = "application/json")
    public List<Feedback> getLastThreeTestimonialDetails(HttpServletRequest request) {
        logger.info("***** GET ALL TESTIMONIAL FOR HOME *****");
        List<Feedback> testimonial = feedbackDAO.getLastThreeTestimonialDetails();
        return testimonial;
    }*/
}
