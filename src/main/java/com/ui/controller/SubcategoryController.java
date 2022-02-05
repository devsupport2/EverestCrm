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

import com.ui.dao.SubcategoryDAO;
import com.ui.model.RealestateSubcategory;

@RestController
public class SubcategoryController {
	@Autowired
	SubcategoryDAO subcategoryDAO;

	RealestateSubcategory realestateSubcategory;

	private static final Logger logger = LoggerFactory.getLogger(SubcategoryController.class);

	@RequestMapping(value = "/getSubcategory", method = RequestMethod.GET, produces = "application/json")
	public List<RealestateSubcategory> getSubcategory(HttpServletRequest request) {
		logger.info("***** GET SUBCATEGORY *****");
		return subcategoryDAO.getSubcategory();
	}

	@RequestMapping(value = "/getSubcategoryByPage", method = RequestMethod.GET, produces = "application/json")
	public List<RealestateSubcategory> getSubcategoryByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET SUBCATEGORY BY PAGE *****");
		return subcategoryDAO.getSubcategoryByPage(pagesize, startindex);
	}

	@RequestMapping(value = "/searchSubcategory", method = RequestMethod.GET, produces = "application/json")
	public List<RealestateSubcategory> searchSubcategory(String keyword, HttpServletRequest request) {
		logger.info("**** SEARCH COUNTRY *****");
		return subcategoryDAO.searchSubcategory(keyword);
	}

	@RequestMapping(value = "deleteSubcategory", method = RequestMethod.DELETE)
	public void delete(int id) {
		logger.info("***** DELETE SUBCATEGORY *****");
		subcategoryDAO.deleteSubcategory(id);
	}

	@RequestMapping(value = "/getSubcategoryDetailById", method = RequestMethod.GET, produces = "application/json")
	public RealestateSubcategory getSubcategoryDetailById(int id, HttpServletRequest request) {
		logger.info("***** GET SUBCATEGORY DETAIL BY ID *****");
		return subcategoryDAO.getSubcategoryDetailById(id);
	}

	@RequestMapping(value = "addRealestateSubcategoryType", method = RequestMethod.POST)
	public String addRealestateSubcategoryType(@RequestParam(value = "image", required = false) MultipartFile image,
			int realestateid, String realestatesubtitle, String realestatesubcode, String description,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD REALESTATE SUBCATEGORY *****");

		String title1 = realestatesubtitle.replace("$", "&");
		String title2 = title1.replace("~", "#");
		String title = title2.replace("!", "%");

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
							+ "/resources/admin/images/" + File.separator + "Realestate");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getSession().getServletContext()
							.getRealPath("/resources/admin/images/Realestate/");
					File uploadfile = new File(path + File.separator + image.getOriginalFilename());

					/********* Today Start **********/

					int height = 247, width = 370;

					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					try {
						BufferedImage img = ImageIO.read(in);

						int original_width = img.getWidth();
						int original_height = img.getHeight();
						int bound_width = 370;
						int bound_height = 247;

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
							+ "/everest/resources/admin/images/Realestate/" + image.getOriginalFilename();

					realestateSubcategory = new RealestateSubcategory(realestateid, title, realestatesubcode, image1,
							description3, s, id, IpAddress);
					result = subcategoryDAO.addRealestateSubcategoryType(realestateSubcategory);

					return result;
				} catch (Exception e) {
					e.printStackTrace();
					realestateSubcategory = new RealestateSubcategory(realestateid, title, realestatesubcode, image1,
							description3, s, id, IpAddress);
					result = subcategoryDAO.addRealestateSubcategoryType(realestateSubcategory);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			realestateSubcategory = new RealestateSubcategory(realestateid, title, realestatesubcode, image1,
					description3, s, id, IpAddress);
			result = subcategoryDAO.addRealestateSubcategoryType(realestateSubcategory);

			return result;
		}

		realestateSubcategory = new RealestateSubcategory(realestateid, title, realestatesubcode, image1, description3,
				s, id, IpAddress);
		result = subcategoryDAO.addRealestateSubcategoryType(realestateSubcategory);

		return result;
	}

	@RequestMapping(value = "editRealestateSubcategoryType", method = RequestMethod.POST)
	public String editRealestateSubcategoryType(@RequestParam(value = "image", required = false) MultipartFile image,
			int id, int realestateid, String realestatesubtitle, String realestatesubcode, String description,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT REALESTATE SUBCATEGORY *****");

		String title1 = realestatesubtitle.replace("$", "&");
		String title2 = title1.replace("~", "#");
		String title = title2.replace("!", "%");

		String description1 = description.replace("$", "&");
		String description2 = description1.replace("~", "#");
		String description3 = description2.replace("!", "%");

		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}	

		String image1 = null;
		String result = null;

		try {
			if (image != null) {
				try {
					byte[] bytes = image.getBytes();

					File dir = new File(request.getSession().getServletContext().getRealPath("")
							+ "/resources/admin/images/" + File.separator + "Realestate");
					if (!dir.exists())
						dir.mkdirs();

					String path = request.getSession().getServletContext()
							.getRealPath("/resources/admin/images/Realestate/");
					File uploadfile = new File(path + File.separator + image.getOriginalFilename());

					int height = 247, width = 370;

					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					try {
						BufferedImage img = ImageIO.read(in);

						int original_width = img.getWidth();
						int original_height = img.getHeight();
						int bound_width = 370;
						int bound_height = 247;

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

					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();

					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/everest/resources/admin/images/Realestate/" + image.getOriginalFilename();

					realestateSubcategory = new RealestateSubcategory(id, realestateid, title, realestatesubcode,
							image1, description3, userid, IpAddress);
					result = subcategoryDAO.editRealestateSubcategoryType(realestateSubcategory);

					return result;
				} catch (Exception e) {
					e.printStackTrace();
					realestateSubcategory = new RealestateSubcategory(id, realestateid, title, realestatesubcode,
							image1, description3, userid, IpAddress);
					result = subcategoryDAO.editRealestateSubcategoryType(realestateSubcategory);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			realestateSubcategory = new RealestateSubcategory(id, realestateid, title, realestatesubcode, image1,
					description3, userid, IpAddress);
			result = subcategoryDAO.editRealestateSubcategoryType(realestateSubcategory);

			return result;
		}

		realestateSubcategory = new RealestateSubcategory(id, realestateid, title, realestatesubcode, image1,
				description3, userid, IpAddress);
		result = subcategoryDAO.editRealestateSubcategoryType(realestateSubcategory);

		return result;
	}
}
