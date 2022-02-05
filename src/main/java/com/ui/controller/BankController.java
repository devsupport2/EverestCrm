package com.ui.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
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

import com.ui.dao.BankDAO;
import com.ui.model.Bank;
import com.ui.model.RealestateType;

@RestController
public class BankController {
  
  @Autowired
  BankDAO bankDAO;
  Bank bank;
  
  private static final Logger logger = LoggerFactory.getLogger(BankController.class);
  
  
  @RequestMapping(value = "/getAllBank", method = RequestMethod.GET, produces = "application/json")
  public List<Bank> getCategory(HttpServletRequest request) {
      logger.info("***** GET BANK *****");        
      return bankDAO.getAllBank();
  }

  @RequestMapping(value = "/getBankByPage", method = RequestMethod.GET, produces = "application/json")
  public List<Bank> getBankByPage(int pagesize, int startindex, HttpServletRequest request) {
      logger.info("***** GET BANK BY PAGE *****");        
      return bankDAO.getBankByPage(pagesize, startindex);
  }
  
  @RequestMapping(value = "/getBankDetailById", method = RequestMethod.GET, produces = "application/json")
  public Bank getBankDetailById(int id, HttpServletRequest request) {
      logger.info("***** GET BANK DETAIL BY ID *****");       
      return bankDAO.getBankDetailById(id);
  }

  @RequestMapping(value = "/searchBank", method = RequestMethod.GET, produces = "application/json")
  public List<Bank> searchBank(String keyword, HttpServletRequest request) {
      logger.info("**** SEARCH BANK *****");       
      return bankDAO.searchBank(keyword);
  }
  
  @RequestMapping(value = "addBank", method = RequestMethod.POST)
  public String addBank(@RequestParam(value = "image", required = false) MultipartFile image,
          String banktitle, String bankcode, String description, HttpServletRequest request,
          HttpSession session) {
      logger.info("***** ADD BANK *****");

      banktitle = banktitle.replace("$", "&");
      banktitle = banktitle.replace("~", "#");
      banktitle = banktitle.replace("!", "%");

      String description1 = description.replace("$", "&");
      String description2 = description1.replace("~", "#");
      String description3 = description2.replace("!", "%");

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
              try {
                  byte[] bytes = image.getBytes();

                  File dir = new File(request.getSession().getServletContext().getRealPath("")
                          + "/resources/admin/images/" + File.separator + "Bank");
                  if (!dir.exists())
                      dir.mkdirs();

                  String path = request.getSession().getServletContext()
                          .getRealPath("/resources/admin/images/Bank/");
                  File uploadfile = new File(path + File.separator + image.getOriginalFilename());

                  /********* Today Start **********/

                  int height = 150, width = 320;

                  ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                  try {
                      BufferedImage img = ImageIO.read(in);

                      int original_width = img.getWidth();
                      int original_height = img.getHeight();
                      int bound_width = 320;
                      int bound_height = 150;

                      if (original_height / bound_height > original_width / bound_width) {
                          bound_width = (int) (bound_height * original_width / original_height);
                      } else {
                          bound_height = (int) (bound_width * original_height / original_width);
                      }

                      Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);

                      BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

                      Graphics2D drawer = imageBuff.createGraphics();
                      drawer.setBackground(Color.WHITE);
                      drawer.clearRect(0, 0, width, height);

                      imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
                              (height - bound_height) / 2, new Color(0, 0, 0), null);

                      ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                      ImageIO.write(imageBuff, "jpg", buffer);

                      bytes = buffer.toByteArray();
                  } catch (IOException e) {
                      
                  }
                  /********* Today End **********/

                  System.out.println("*********************Path" + path);
                  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                          new FileOutputStream(uploadfile));
                  bufferedOutputStream.write(bytes);
                  bufferedOutputStream.close();
                  
                  image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                          + "/everest/resources/admin/images/Bank/" + image.getOriginalFilename();

                  
                  
                  bank = new Bank();
                  bank.setBankName(banktitle);
                  bank.setBankCode(bankcode);
                  bank.setImage(image1);
                  bank.setDescription(description3);
                  bank.setStatus(s);
                  bank.setCreatedBy(id);
                  bank.setIpAddress(IpAddress);
                  
                  result = bankDAO.addBank(bank);

                  return result;
              } catch (Exception e) {
                  e.printStackTrace();
                  return result;
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
          bank = new Bank();
          bank.setBankName(banktitle);
          bank.setBankCode(bankcode);
          bank.setImage(image1);
          bank.setDescription(description3);
          bank.setStatus(s);
          bank.setCreatedBy(id);
          bank.setIpAddress(IpAddress);
          
          result = bankDAO.addBank(bank);

          return result;
      }

      bank = new Bank();
      bank.setBankName(banktitle);
      bank.setBankCode(bankcode);
      bank.setImage(image1);
      bank.setDescription(description3);
      bank.setStatus(s);
      bank.setCreatedBy(id);
      bank.setIpAddress(IpAddress);
      
      result = bankDAO.addBank(bank);

      return result;
  }
  
  
  @RequestMapping(value = "editBank", method = RequestMethod.POST)
  public String editBank(@RequestParam(value = "image", required = false) MultipartFile image,
          int bankid, String banktitle, String bankcode, String description, String imageedit, HttpServletRequest request,
          HttpSession session) {
      logger.info("***** EDIT BANK *****");

      banktitle = banktitle.replace("$", "&");
      banktitle = banktitle.replace("~", "#");
      banktitle = banktitle.replace("!", "%");

      String description1 = description.replace("$", "&");
      String description2 = description1.replace("~", "#");
      String description3 = description2.replace("!", "%");

      int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

      String IpAddress = request.getHeader("X-FORWARDED-FOR");
      if (IpAddress == null) {
          IpAddress = request.getRemoteAddr();
      }

      String s = "y";

      String image1 = imageedit;
      String result = null;

      try {
          if (image != null) {
              try {
                  byte[] bytes = image.getBytes();

                  File dir = new File(request.getSession().getServletContext().getRealPath("")
                          + "/resources/admin/images/" + File.separator + "Bank");
                  if (!dir.exists())
                      dir.mkdirs();

                  String path = request.getSession().getServletContext()
                          .getRealPath("/resources/admin/images/Bank/");
                  File uploadfile = new File(path + File.separator + image.getOriginalFilename());

                  /********* Today Start **********/

                  int height = 150, width = 320;

                  ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                  try {
                      BufferedImage img = ImageIO.read(in);

                      int original_width = img.getWidth();
                      int original_height = img.getHeight();
                      int bound_width = 320;
                      int bound_height = 150;

                      if (original_height / bound_height > original_width / bound_width) {
                          bound_width = (int) (bound_height * original_width / original_height);
                      } else {
                          bound_height = (int) (bound_width * original_height / original_width);
                      }

                      Image scaledImage = img.getScaledInstance(bound_width, bound_height, Image.SCALE_SMOOTH);

                      BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

                      Graphics2D drawer = imageBuff.createGraphics();
                      drawer.setBackground(Color.WHITE);
                      drawer.clearRect(0, 0, width, height);

                      imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
                              (height - bound_height) / 2, new Color(0, 0, 0), null);

                      ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                      ImageIO.write(imageBuff, "jpg", buffer);

                      bytes = buffer.toByteArray();
                  } catch (IOException e) {
                      
                  }
                  /********* Today End **********/

                  System.out.println("*********************Path" + path);
                  BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                          new FileOutputStream(uploadfile));
                  bufferedOutputStream.write(bytes);
                  bufferedOutputStream.close();
                  
                  image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                          + "/everest/resources/admin/images/Bank/" + image.getOriginalFilename();
                  
                  
                  bank = new Bank();
                  bank.setBankId(bankid);
                  bank.setBankName(banktitle);
                  bank.setBankCode(bankcode);
                  bank.setImage(image1);
                  bank.setDescription(description3);
                  bank.setCreatedBy(id);
                  bank.setIpAddress(IpAddress);
                  
                  result = bankDAO.editBank(bank);

                  return result;
              } catch (Exception e) {
                  e.printStackTrace();
                  return result;
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
          bank = new Bank();
          bank.setBankId(bankid);
          bank.setBankName(banktitle);
          bank.setBankCode(bankcode);
          bank.setImage(image1);
          bank.setDescription(description3);
          bank.setCreatedBy(id);
          bank.setIpAddress(IpAddress);
          
          result = bankDAO.editBank(bank);

          return result;
      }

      bank = new Bank();
      bank.setBankId(bankid);
      bank.setBankName(banktitle);
      bank.setBankCode(bankcode);
      bank.setImage(image1);
      bank.setDescription(description3);
      bank.setCreatedBy(id);
      bank.setIpAddress(IpAddress);
      
      result = bankDAO.editBank(bank);

      return result;
  }
  
  @RequestMapping(value = "deleteBank", method = RequestMethod.DELETE)
  public void delete(int id) {
      logger.info("***** DELETE BANK *****");
      bankDAO.deleteBank(id);
  }
  
}
