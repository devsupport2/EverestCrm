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

import com.ui.dao.WorkstatusDAO;
import com.ui.model.Achievement;
import com.ui.model.Workstatus;
import com.ui.model.WorkstatusImage;

@RestController
public class WorkStatusController {
  
@Autowired
WorkstatusDAO dao;
Workstatus workstatus;
  private static final Logger logger = LoggerFactory.getLogger(WorkStatusController.class);

  
  @RequestMapping(value = "/getAllWorkstatus", method = RequestMethod.GET, produces = "application/json")
  public List<Workstatus> getCategory(HttpServletRequest request) {
      logger.info("***** GET ALL WORKSTATUS *****");        
      return dao.getAllWorkstatus();
  }
  
  @RequestMapping(value = "/getWorkstatusByPage", method = RequestMethod.GET, produces = "application/json")
  public List<Workstatus> getWorkstatusByPage(int pagesize, int startindex, HttpServletRequest request) {
      logger.info("***** GET WORKSTATUS BY PAGE *****");
      return dao.getWorkstatusByPage(pagesize, startindex);
  }
  
  @RequestMapping(value = "/getWorkstatusDetailsById", method = RequestMethod.GET, produces = "application/json")
  public Workstatus getWorkstatusDetailsById(int id, HttpServletRequest request) {
      logger.info("***** GET WORKSTATUS DETAILS BY ID *****");
      
      Workstatus w = dao.getWorkstatusDetailsById(id);
      w.setImageList(dao.getAllImageById(id));
      return w;
            
  }
  
  
  @RequestMapping(value = "addWorkStatus", method = RequestMethod.POST)
  public String addWorkStatus(int id, String title, String subtitle, String adddate, String description, HttpServletRequest request,
          HttpSession session) {
      logger.info("***** ADD WorkStatus *****");
      int aid = Integer.parseInt(session.getAttribute("useridadmin").toString());
      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }
      String s = "y";
      String result = null;
      Workstatus sr = new Workstatus();
      sr.setProjectId(id);
      sr.setTitle(title);
      sr.setSubtitle(subtitle);
      sr.setDate(adddate);
      sr.setDescription(description);
      sr.setStatus(s);
      sr.setCreatedBy(aid);
      sr.setIpAddress(IpAddress);
             
      result = dao.addWorkStatus(sr);

      return result;
  }
  
  @RequestMapping(value = "addImage", method = RequestMethod.POST)
  public String addImage(@RequestParam(value = "imageadd", required = false) MultipartFile[] imageadd,
          String imagetitle[], String imagesubtitle[], int valuex[], int valuey[], int valuew[], int valueh[], HttpServletRequest request,
          HttpSession session) {
      logger.info("***** ADD WORKSTATUS IMAGE *****");
      int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }

      int wid = dao.getLastWorkstatusId();

      String image1 = "";
      
      try {
          for (int i = 0; i < imageadd.length; i++) {
              if (imageadd[i].getOriginalFilename() != null && imageadd[i].getOriginalFilename() != "") {
                  try {
                      byte[] bytes = imageadd[i].getBytes();

                      System.out.println("image length----test esinside");
                      File dir = new File(request.getSession().getServletContext().getRealPath("")
                              + "/resources/admin/images/" + File.separator + "WorkStatusImage");
                      if (!dir.exists())
                          dir.mkdirs();
                      String path = request.getSession().getServletContext()
                              .getRealPath("/resources/admin/images/WorkStatusImage/");
                      File uploadfile = new File(path + File.separator + wid + imageadd[i].getOriginalFilename());

                      ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                      try {
                          BufferedImage img = ImageIO.read(in);

                          Image scaledImage = img.getScaledInstance(valuew[i], valueh[i], Image.SCALE_SMOOTH);
                          BufferedImage SubImgage = img.getSubimage(valuex[i], valuey[i], valuew[i], valueh[i]);
                          Graphics2D drawer = SubImgage.createGraphics();
                          drawer.setComposite(AlphaComposite.Src);
                          drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                  RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                          drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                          drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                          drawer.drawImage(scaledImage, valuew[i], valueh[i], null);
                          drawer.dispose();
                          ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                          ImageIO.write(SubImgage, "png", buffer);
                          bytes = buffer.toByteArray();
                      } catch (IOException e) {

                      }

                      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                              new FileOutputStream(uploadfile));
                      bufferedOutputStream.write(bytes);
                      bufferedOutputStream.close();

                      image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                              + "/everest/resources/admin/images/WorkStatusImage/" + wid
                              + imageadd[i].getOriginalFilename();
                      
                      
                      //Code for Compressed Image
                      
                      
                      WorkstatusImage ss = new WorkstatusImage();
                      
                      ss.setWorkstatusId(wid);
                      ss.setTitle(imagetitle[i]);
                      ss.setSubtitle(imagesubtitle[i]);
                      ss.setImage(image1);
                      ss.setCreatedBy(id);
                      ss.setIpAddress(IpAddress);
                                             
                      dao.addImage(ss);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }

      return "Success";
  }
  
  @RequestMapping(value = "editWorkstatusImage", method = RequestMethod.POST)
  public String editWorkstatusImage(@RequestParam(value = "imageedit", required = false) MultipartFile imageedit,
          int wid, String imagetitle, String imagesubtitle, int valuex, int valuey, int valuew, int valueh, HttpServletRequest request,
          HttpSession session) {
      logger.info("***** EDIT WORKSTATUS IMAGE *****");
      int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }

      String image1 = "";
      
      try {
          if (imageedit.getOriginalFilename() != null && imageedit.getOriginalFilename() != "") {
              try {
                  byte[] bytes = imageedit.getBytes();

                  System.out.println("image length----test esinside");
                  File dir = new File(request.getSession().getServletContext().getRealPath("")
                          + "/resources/admin/images/" + File.separator + "WorkStatusImage");
                  if (!dir.exists())
                      dir.mkdirs();
                  String path = request.getSession().getServletContext()
                          .getRealPath("/resources/admin/images/WorkStatusImage/");
                  File uploadfile = new File(path + File.separator + wid + imageedit.getOriginalFilename());

                  ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                  try {
                      BufferedImage img = ImageIO.read(in);

                      Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
                      BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
                      Graphics2D drawer = SubImgage.createGraphics();
                      drawer.setComposite(AlphaComposite.Src);
                      drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                              RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                      drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                      drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                      drawer.drawImage(scaledImage, valuew, valueh, null);
                      drawer.dispose();
                      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                      ImageIO.write(SubImgage, "png", buffer);
                      bytes = buffer.toByteArray();
                  } catch (IOException e) {

                  }

                  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                          new FileOutputStream(uploadfile));
                  bufferedOutputStream.write(bytes);
                  bufferedOutputStream.close();

                  image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                          + "/everest/resources/admin/images/WorkStatusImage/" + wid
                          + imageedit.getOriginalFilename();
                  
                  
                  //Code for Compressed Image
                  
                  
                  WorkstatusImage ss = new WorkstatusImage();
                  
                  ss.setWorkstatusId(wid);
                  ss.setTitle(imagetitle);
                  ss.setSubtitle(imagesubtitle);
                  ss.setImage(image1);
                  ss.setCreatedBy(id);
                  ss.setIpAddress(IpAddress);
                                         
                  dao.addImage(ss);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
         
      } catch (Exception e) {
          e.printStackTrace();
      }

      return "Success";
  }
  
  @RequestMapping(value = "editWorkStatus", method = RequestMethod.POST)
  public String editWorkStatus(int wid, int id, String title, String subtitle, String adddate, String description, HttpServletRequest request,
          HttpSession session) {
      logger.info("***** EDIT WorkStatus *****");
      int aid = Integer.parseInt(session.getAttribute("useridadmin").toString());
      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }
      String s = "y";
      String result = null;
      Workstatus sr = new Workstatus();
      
      sr.setWorkstatusId(wid);
      sr.setProjectId(id);
      sr.setTitle(title);
      sr.setSubtitle(subtitle);
      sr.setDate(adddate);
      sr.setDescription(description);
      sr.setCreatedBy(aid);
      sr.setIpAddress(IpAddress);
             
      result = dao.editWorkStatus(sr);

      return result;
  }
  
  @RequestMapping(value = "deleteWorkstatus", method = RequestMethod.DELETE)
  public void delete(int wid) {
      logger.info("***** DELETE WORKSTATUS *****");
      dao.deleteWorkstatus(wid);
  }
  @RequestMapping(value = "deleteWorkstatusImage", method = RequestMethod.DELETE)
  public void deleteWorkstatusImage(int workstatusImageId) {
      logger.info("***** DELETE WORKSTATUS IMAGE *****");
      dao.deleteWorkstatusImage(workstatusImageId);
  }
  
  
}//end
