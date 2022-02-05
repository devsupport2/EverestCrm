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

import com.ui.dao.TypeDAO;
import com.ui.model.Realestate;

@RestController
public class TypeController {
	@Autowired
	TypeDAO typeDAO;

	Realestate realestate;

	private static final Logger logger = LoggerFactory.getLogger(TypeController.class);

	@RequestMapping(value = "/getType", method = RequestMethod.GET, produces = "application/json")
	public List<Realestate> getType(HttpServletRequest request) {
		logger.info("***** GET TYPE *****");
		return typeDAO.getType();
	}

	@RequestMapping(value = "/getTypeByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Realestate> getTypeByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET TYPE BY PAGE *****");
		return typeDAO.getTypeByPage(pagesize, startindex);
	}

	@RequestMapping(value = "/searchType", method = RequestMethod.GET, produces = "application/json")
	public List<Realestate> searchType(String keyword, HttpServletRequest request) {
		logger.info("**** SEARCH TYPE *****");
		return typeDAO.searchType(keyword);
	}

	@RequestMapping(value = "addRealestate", method = RequestMethod.POST)
	public String addRealestate(@RequestParam(value = "image", required = false) MultipartFile image, int realestateid,
			int realestatesubid, String realestatetitle, String realestatecode, String description,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD TYPE *****");

		String title1 = realestatetitle.replace("$", "&");
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

					realestate = new Realestate(realestateid, realestatesubid, title, realestatecode, image1,
							description3, s, id, IpAddress);
					result = typeDAO.addRealestate(realestate);

					return result;
				} catch (Exception e) {
					e.printStackTrace();
					realestate = new Realestate(realestateid, realestatesubid, title, realestatecode, image1,
							description3, s, id, IpAddress);
					result = typeDAO.addRealestate(realestate);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			realestate = new Realestate(realestateid, realestatesubid, title, realestatecode, image1, description3, s,
					id, IpAddress);
			result = typeDAO.addRealestate(realestate);

			return result;
		}

		realestate = new Realestate(realestateid, realestatesubid, title, realestatecode, image1, description3, s, id,
				IpAddress);
		result = typeDAO.addRealestate(realestate);

		return result;
	}

	@RequestMapping(value = "editRealestate", method = RequestMethod.POST)
	public String addRealestate(@RequestParam(value = "image", required = false) MultipartFile image, int id,
			int realestateid, int realestatesubid, String realestatetitle, String realestatecode, String description,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD TYPE *****");

		String title1 = realestatetitle.replace("$", "&");
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

					realestate = new Realestate(id, realestateid, realestatesubid, title, realestatecode, image1,
							description3, userid, IpAddress);
					result = typeDAO.editRealestate(realestate);

					return result;
				} catch (Exception e) {
					e.printStackTrace();
					realestate = new Realestate(id, realestateid, realestatesubid, title, realestatecode, image1,
							description3, userid, IpAddress);
					result = typeDAO.editRealestate(realestate);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			realestate = new Realestate(id, realestateid, realestatesubid, title, realestatecode, image1, description3,
					userid, IpAddress);
			result = typeDAO.editRealestate(realestate);

			return result;
		}

		realestate = new Realestate(id, realestateid, realestatesubid, title, realestatecode, image1, description3,
				userid, IpAddress);
		result = typeDAO.editRealestate(realestate);

		return result;
	}

	@RequestMapping(value = "deleteType", method = RequestMethod.DELETE)
	public void delete(int id) {
		logger.info("***** DELETE TYPE *****");
		typeDAO.deleteType(id);
	}

	@RequestMapping(value = "/getTypeDetailById", method = RequestMethod.GET, produces = "application/json")
	public Realestate getTypeDetailById(int id, HttpServletRequest request) {
		logger.info("***** GET TYPE DETAIL BY ID *****");
		return typeDAO.getTypeDetailById(id);
	}
}
