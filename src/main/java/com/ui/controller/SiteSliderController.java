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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.SliderDAO;
import com.ui.dao.SiteSliderDAO;
import com.ui.model.Slider;

@RestController
public class SiteSliderController
{
	@Autowired
	SiteSliderDAO siteSliderDAO;
	Slider slider;
	
	private static final Logger logger = LoggerFactory.getLogger(SiteSliderController.class);
	
	@RequestMapping(value="/getSiteSliders", method= RequestMethod.GET, produces = "application/json")
	public List<Slider> getSliders(HttpServletRequest request, HttpServletResponse res)
	{
		res.addHeader("Access-Control-Allow-Origin", "*");
		logger.info("Inside Gel All Slider Controller");
		
		List<Slider> slider = siteSliderDAO.getAllSliders();
		
		return slider;
	}
	
	@RequestMapping(value="/getSiteSlidersByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Slider> getSlidersByPage(int pagesize, int startindex, HttpServletRequest request)
	{
		logger.info("Inside Get Slider By Page Controller");
		
		List<Slider> slider = siteSliderDAO.getAllSlidersByPage(pagesize, startindex);
		
		return slider;
	}
	
	@RequestMapping(value="/getSiteActiveSliders", method= RequestMethod.GET, produces = "application/json")
	public List<Slider> getActiveSliders(HttpServletRequest request, HttpServletResponse res)
	{
		res.addHeader("Access-Control-Allow-Origin", "*");
		logger.info("Inside Gel Slider Controller");
		
		List<Slider> slider = siteSliderDAO.getActiveSliders();
		
		return slider;
	}
	
	@RequestMapping(value = "/addSiteSlider",method = RequestMethod.POST)
	public String addSlider(@RequestParam(value="image", required=false) MultipartFile image, String slidertitle, String active, String ipaddress, int valuex, int valuey, int valuew, int valueh, int projectId, HttpSession session, HttpServletRequest request)
	{
		logger.info("Inside Add Slider Controller");
		
		String s = slidertitle.replace("$","&");
		String s1 = s.replace("~","#");
		String s2 = s1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String image1="";
		
		try
	    {
	    	if(image.getOriginalFilename() != "")
			{
	    		try
				{			
					byte[] bytes =  image.getBytes();
					
					File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "slider");
	    			if (!dir.exists()) 
	    				dir.mkdirs();
	    			String path = request.getRealPath("/resources/admin/images/slider/");
		            File uploadfile = new File(path+File.separator+image.getOriginalFilename());
		            
		            /********* Today Start **********/
		            
		            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		        	try
		        	{
		        		BufferedImage img = ImageIO.read(in);
		        		
		        		Image scaledImage = img.getScaledInstance(valuew-1, valueh, Image.SCALE_SMOOTH);
		                BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew-1, valueh);
		                Graphics2D drawer = SubImgage.createGraphics();
		                drawer.setComposite(AlphaComposite.Src);
		                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		                drawer.drawImage(scaledImage, valuew-1, valueh, null);
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

					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/everest/resources/admin/images/slider/" + image.getOriginalFilename();
					 
					slider = new Slider(s2, image1, active, "y", id, ipaddress,projectId);
					siteSliderDAO.addSlider(slider);
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
		
		return "";
	}
	
	@RequestMapping(value = "/editSiteSlider",method = RequestMethod.POST)
	public String editSiteSlider(@RequestParam(value="image", required=false) MultipartFile image, int sliderid, String slidertitle, String active, String sliderimage, int valuex, int valuey, int valuew, int valueh, int projectid, HttpSession session, HttpServletRequest request)
	{
		logger.info("Inside Edit Slider Controller");
		
		String s = slidertitle.replace("$","&");
		String s1 = s.replace("~","#");
		String s2 = s1.replace("!","%");
		
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		String image1=sliderimage;
		String ipaddress = request.getHeader("X-FORWARDED-FOR");
        if (ipaddress == null) {
          ipaddress = request.getRemoteAddr();
        }
		
		try
	    {
	    	if(image.getOriginalFilename() != "")
			{
	    		try
				{
					byte[] bytes =  image.getBytes();
					
					File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "slider");
	    			if (!dir.exists()) 
	    				dir.mkdirs();
	    			String path = request.getRealPath("/resources/admin/images/slider/");
		            File uploadfile = new File(path+File.separator+image.getOriginalFilename());
		            
		            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		        	try
		        	{
		        		BufferedImage img = ImageIO.read(in);
		        		
		        		Image scaledImage = img.getScaledInstance(valuew-1, valueh, Image.SCALE_SMOOTH);
		                BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew-1, valueh);
		                Graphics2D drawer = SubImgage.createGraphics();
		                drawer.setComposite(AlphaComposite.Src);
		                drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		                drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		                drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		                drawer.drawImage(scaledImage, valuew-1, valueh, null);
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
		            
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/everest/resources/admin/images/slider/" + image.getOriginalFilename();
					 
					slider = new Slider(sliderid, s2, image1, active, projectid, "y", id, ipaddress);
					siteSliderDAO.editSlider(slider);
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
	    }
	    catch(Exception e)
	    {
	    	slider = new Slider(sliderid, s2, sliderimage, active, projectid, "y", id, ipaddress);
	    	siteSliderDAO.editSlider(slider);
	    	e.printStackTrace();
	    }
		
		return "";
	}
	
	@RequestMapping(value="/deleteSiteSlider",method= RequestMethod.DELETE  )
	public void delete(int sliderid)
	{
		logger.info("Inside delete Slider Controller...");

		siteSliderDAO.deleteSiteSlider(sliderid);
	}
	
	@RequestMapping(value="setSiteActiveOrInActiveSlider", method= RequestMethod.POST)
	public String setActiveOrInActiveSlider(int sliderid, String active, String ipaddress, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Set Active Or InActive Slider Controller");
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		
		slider = new Slider(sliderid, active, id, ipaddress);
		siteSliderDAO.setActiveOrInActiveSlider(slider);
			
		return "";
	}
	
	
}