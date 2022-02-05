package com.ui.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
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

import com.ui.dao.AchievementDAO;
import com.ui.model.Achievement;
import com.ui.model.Country;
import com.ui.model.RealestateType;

@RestController
public class AchievementController {
  
  
  
  @Autowired
  AchievementDAO achievementDAO;
  
  Achievement achievement;
  
  private static final Logger logger = LoggerFactory.getLogger(AchievementController.class);
  
  @RequestMapping(value = "/getAchievement", method = RequestMethod.GET, produces = "application/json")
  public List<Achievement> getCategory(HttpServletRequest request) {
      logger.info("***** GET Achievement *****");        
      return achievementDAO.getAchievement();
  }
  
  @RequestMapping(value = "/getAchievementByPage", method = RequestMethod.GET, produces = "application/json")
  public List<Achievement> getAchievementByPage(int pagesize, int startindex, HttpServletRequest request) {
      logger.info("***** GET Achievement BY PAGE *****");
      return achievementDAO.getAchievementByPage(pagesize, startindex);
  }
  
  @RequestMapping(value = "/getAchievementDetailById", method = RequestMethod.GET, produces = "application/json")
  public Achievement getAchievementDetailById(int id, HttpServletRequest request) {
      logger.info("***** GET Achievement DETAIL BY ID *****");       
      return achievementDAO.getAchievementDetailById(id);
  }
  
  @RequestMapping(value = "/searchAchievement", method = RequestMethod.GET, produces = "application/json")
  public List<Achievement> searchAchievement(String keyword, HttpServletRequest request) {
      logger.info("**** SEARCH ACHIEVEMENT *****");
      List<Achievement> a = achievementDAO.searchAchievement(keyword);
      return a;
  }
  
  @RequestMapping(value = "addAchivement", method = RequestMethod.POST)
  public String addAchivement(@RequestParam(value = "image", required = false) MultipartFile image,
          String achievementtitle, String achievementsubtitle, String description, int valuex, int valuey, int valuew, int valueh, HttpServletRequest request,
          HttpSession session) {
      logger.info("***** ADD ACHIEVEMENT *****");

      achievementtitle = achievementtitle.replace("$", "&");
      achievementtitle = achievementtitle.replace("~", "#");
      achievementtitle = achievementtitle.replace("!", "%");

      description = description.replace("$", "&");
      description = description.replace("~", "#");
      description = description.replace("!", "%");

      int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }

      String s = "y";

      String image1 = null;
      String result = null;

      try {
          if (image != null) {
            try
            {           
                byte[] bytes =  image.getBytes();
                
                File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "Achievement");
                if (!dir.exists()) 
                    dir.mkdirs();
                String path = request.getRealPath("/resources/admin/images/Achievement/");
                File uploadfile = new File(path+File.separator+image.getOriginalFilename());
                
                
                
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
                    //throw new ApplicationException("IOException in scale");
                }
               
                
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();

                //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/jamnadasghariwalanew/resources/admin/images/slider/" + image.getOriginalFilename();
                image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/everest/resources/admin/images/Achievement/" + image.getOriginalFilename();
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
          }
      } catch (Exception e) {
          
      }

      achievement = new Achievement();
      
      achievement.setTitle(achievementtitle);
      achievement.setSubtitle(achievementsubtitle);
      achievement.setImage(image1);
      achievement.setDescription(description);
      achievement.setCreatedBy(id);
      achievement.setStatus(s);
      achievement.setIpAddress(IpAddress);
      result = achievementDAO.addAchivement(achievement);

      return result;
  }
  
  
  @RequestMapping(value = "editAchivement", method = RequestMethod.POST)
  public String editAchivement(@RequestParam(value = "imagedit", required = false) MultipartFile imagedit,
          int id, String achievementtitle, String achievementsubtitle, String description, int valuex, int valuey, int valuew, int valueh, String imageedit, HttpServletRequest request,
          HttpSession session) {
      logger.info("***** EDIT ACHIEVEMENT *****");

      achievementtitle = achievementtitle.replace("$", "&");
      achievementtitle = achievementtitle.replace("~", "#");
      achievementtitle = achievementtitle.replace("!", "%");

      description = description.replace("$", "&");
      description = description.replace("~", "#");
      description = description.replace("!", "%");

      int aid = Integer.parseInt(session.getAttribute("useridadmin").toString());

      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }

      String s = "y";

      String image1 = imageedit;
      String result = null;
      
      try {
          if (imagedit != null) {
            try
            {           
                byte[] bytes =  imagedit.getBytes();
                
                File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "Achievement");
                if (!dir.exists()) 
                    dir.mkdirs();
                String path = request.getRealPath("/resources/admin/images/Achievement/");
                File uploadfile = new File(path+File.separator+imagedit.getOriginalFilename());
                
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
                    //throw new ApplicationException("IOException in scale");
                }
               
                
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();

                //image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/jamnadasghariwalanew/resources/admin/images/slider/" + image.getOriginalFilename();
                image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/everest/resources/admin/images/Achievement/" + imagedit.getOriginalFilename();
                                
                
                achievement = new Achievement();
                
                achievement.setAchievementId(id);
                achievement.setTitle(achievementtitle);
                achievement.setSubtitle(achievementsubtitle);
                achievement.setImage(image1);
                achievement.setDescription(description);
                achievement.setCreatedBy(aid);
                achievement.setStatus(s);
                achievement.setIpAddress(IpAddress);
                result = achievementDAO.editAchivement(achievement);
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
          }
      } catch (Exception e) {
          
      }

      achievement = new Achievement();
      
      achievement.setAchievementId(id);
      achievement.setTitle(achievementtitle);
      achievement.setSubtitle(achievementsubtitle);
      achievement.setImage(image1);
      achievement.setDescription(description);
      achievement.setCreatedBy(aid);
      achievement.setStatus(s);
      achievement.setIpAddress(IpAddress);
      result = achievementDAO.editAchivement(achievement);

      return result;
  }
  
  @RequestMapping(value = "deleteAchievement", method = RequestMethod.DELETE)
  public void delete(int id) {
      logger.info("***** DELETE Achievement *****");
      achievementDAO.deleteAchievement(id);
  }

}//end
