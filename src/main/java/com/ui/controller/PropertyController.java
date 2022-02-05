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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.ProjectDAO;
import com.ui.dao.PropertyDAO;
import com.ui.model.AreaTitle;
import com.ui.model.PaymentLabel;
import com.ui.model.PaymentSchedule;
import com.ui.model.Project;
import com.ui.model.ProjectFloorLayout;
import com.ui.model.ProjectPaymentSchedule;
import com.ui.model.ProjectPriceInfo;
import com.ui.model.Property;
import com.ui.model.PropertyArea;
import com.ui.model.PropertyLayout;
import com.ui.model.PropertyPaymentSchedule;
import com.ui.model.PropertyPriceInfo;
import com.ui.model.PropertyRoom;
import com.ui.model.PropertyType;
import com.ui.model.Realestate;
import com.ui.model.RealestateSubcategory;
import com.ui.model.RealestateType;
import com.ui.model.RoomTitle;

@RestController
public class PropertyController {

	@Autowired
	PropertyDAO propertyDAO;
	@Autowired
	ProjectDAO projectDAO;

	Property property;
	RealestateType realestateType;
	RealestateSubcategory realestateSubcategory;
	PropertyType propertyType;
	Realestate realestate;
	AreaTitle areaTitle;
	RoomTitle roomTitle;
	PropertyArea propertyArea;
	PropertyRoom propertyRoom;
	PaymentSchedule paymentSchedule;
	PaymentLabel paymentLabel;
	PropertyPriceInfo propertyPriceInfo;
	PropertyPaymentSchedule propertyPaymentSchedule;
	PropertyLayout propertyLayout;

	private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

	@RequestMapping(value = "/getProperty", method = RequestMethod.GET, produces = "application/json")
	public List<Property> getProperty(HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY *****");
		List<Property> t = propertyDAO.getAllProperty();
		return t;
	}

	@RequestMapping(value = "/getPropertyByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Property> getPropertyByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY BY PAGE *****");
		List<Property> t = propertyDAO.getPropertyByPage(pagesize, startindex);
		return t;
	}
	
	
	@RequestMapping(value = "/getAllPropertyByProjectId", method = RequestMethod.GET, produces = "application/json")
    public List<Property> getAllPropertyByProjectId(int projectid, HttpServletRequest request) {
        logger.info("***** GET ALL PROPERTY BY PROJECT ID *****");
        List<Property> t = propertyDAO.getAllPropertyByProjectId(projectid);
        return t;
    }
	
	@RequestMapping(value = "/getAllPropertyByProjectIdAndUnitID", method = RequestMethod.GET, produces = "application/json")
    public List<Property> getAllPropertyByProjectIdAndUnitID(int projectid, int projectunitid, HttpServletRequest request) {
        logger.info("***** GET ALL PROPERTY BY PROJECT ID AND UNIT ID *****");
        List<Property> t = propertyDAO.getAllPropertyByProjectIdAndUnitID(projectid, projectunitid);
        return t;
    }

	@RequestMapping(value = "/getPropertyDetailById", method = RequestMethod.GET, produces = "application/json")
	public Property getPropertyDetailById(int propertyid, HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY DETAILS BY Id *****");
		Property t = propertyDAO.getPropertyDetailById(propertyid);
		return t;
	}

	@RequestMapping(value = "/getPropertyAreaById", method = RequestMethod.GET, produces = "application/json")
	public List<PropertyArea> getPropertyAreaById(int propertyid, HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY BY ID *****");
		List<PropertyArea> t = propertyDAO.getPropertyAreaById(propertyid);
		return t;
	}

	@RequestMapping(value = "/getPropertyRoomById", method = RequestMethod.GET, produces = "application/json")
	public List<PropertyRoom> getPropertyRoomById(int propertyid, HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY ROOM BY ID *****");
		List<PropertyRoom> t = propertyDAO.getPropertyRoomById(propertyid);
		return t;
	}

	@RequestMapping(value = "/getPropertyPriceById", method = RequestMethod.GET, produces = "application/json")
	public List<PaymentSchedule> getPropertyPriceById(int propertyid, HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY ROOM BY ID *****");
		List<PaymentSchedule> t = propertyDAO.getPropertyPriceById(propertyid);
		return t;
	}

	@RequestMapping(value = "/getPropertyPriceInfoById", method = RequestMethod.GET, produces = "application/json")
	public List<PropertyPriceInfo> getPropertyPriceInfoById(int propertyid, HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY PRICE INFO BY ID *****");
		List<PropertyPriceInfo> t = propertyDAO.getPropertyPriceInfoById(propertyid);
		return t;
	}

	@RequestMapping(value = "/getPropertyPaymentScheduleById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectPaymentSchedule> getPropertyPaymentScheduleById(int projectid, HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY PAYMENT SCHEDULE BY ID *****");
		List<ProjectPaymentSchedule> t = propertyDAO.getPropertyPaymentScheduleById(projectid);
		return t;
	}

	@RequestMapping(value = "/getPriceInfoById", method = RequestMethod.GET, produces = "application/json")
	public List<ProjectPriceInfo> getPriceInfoById(int projectid, int categoryid, int subcategoryid, int realestateid,
			HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY PRICE INFO BY ID *****");
		List<ProjectPriceInfo> t = propertyDAO.getPriceInfoById(projectid, categoryid, subcategoryid, realestateid);
		return t;
	}

	@RequestMapping(value = "/getPropertyPaymentsScheduleById", method = RequestMethod.GET, produces = "application/json")
	public List<PropertyPaymentSchedule> getPropertyPaymentsScheduleById(int propertyid, HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY PAYMENT SCHEDULE BY ID *****");
		List<PropertyPaymentSchedule> t = propertyDAO.getPropertyPaymentsScheduleById(propertyid);
		return t;
	}
	
	@RequestMapping(value = "/getPropertyLayoutById", method = RequestMethod.GET, produces = "application/json")
    public List<PropertyLayout> getPropertyLayoutById(int propertyid, HttpServletRequest request) {
        logger.info("***** GET ALL PROPERTY LAYOUT BY ID *****");
        List<PropertyLayout> t = propertyDAO.getPropertyLayoutById(propertyid);
        return t;
    }

	@RequestMapping(value = "/getRealestateName", method = RequestMethod.GET, produces = "application/json")
	public List<RealestateType> getRealestateName(HttpServletRequest request) {
		logger.info("***** GET REALESTATE NAME *****");
		List<RealestateType> t = propertyDAO.getRealestateName();
		return t;
	}
	
	@RequestMapping(value = "/getAllRealestateType", method = RequestMethod.GET, produces = "application/json")
    public List<Realestate> getAllRealestateType(HttpServletRequest request) {
        logger.info("***** GET REALESTATE TYPE NAME *****");
        List<Realestate> t = propertyDAO.getAllRealestateType();
        return t;
    }

	@RequestMapping(value = "/getPropertyName", method = RequestMethod.GET, produces = "application/json")
	public List<PropertyType> getPropertyName(HttpServletRequest request) {
		logger.info("***** GET PROPERTY NAME *****");
		List<PropertyType> t = propertyDAO.getPropertyName();
		return t;
	}

	@RequestMapping(value = "/getAreaName", method = RequestMethod.GET, produces = "application/json")
	public List<AreaTitle> getAreaName(HttpServletRequest request) {
		logger.info("***** GET AREA NAME *****");
		List<AreaTitle> t = propertyDAO.getAreaName();
		return t;
	}

	@RequestMapping(value = "/getRoomName", method = RequestMethod.GET, produces = "application/json")
	public List<RoomTitle> getRoomName(HttpServletRequest request) {
		logger.info("***** GET ROOM NAME *****");
		List<RoomTitle> t = propertyDAO.getRoomName();
		return t;
	}

	@RequestMapping(value = "/searchProperties", method = RequestMethod.GET, produces = "application/json")
	public List<Property> searchProperties(String keyword, HttpServletRequest request) {
		logger.info("**** SEARCH PROPERTIES *****");
		List<Property> property = propertyDAO.searchProperties(keyword);
		return property;
	}

	@RequestMapping(value = "addPropertyPaymentSchedule", method = RequestMethod.POST)
	public String addPropertyPaymentSchedule(String sequence, String sequencetitle, String lable, float value,
			int measurementunitid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD Property PAYMENT SCHEDULE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		int propertid = propertyDAO.getLastPropertyId();
		propertyPaymentSchedule = new PropertyPaymentSchedule(propertid, sequence, sequencetitle, lable, value,
				measurementunitid, s, id, IpAddress);
		propertyDAO.addPropertyPaymentSchedule(propertyPaymentSchedule);

		return "Success";
	}

	@RequestMapping(value = "addPropertyType", method = RequestMethod.POST)
	public String addPropertyType(@RequestParam(value = "image", required = false) MultipartFile image,
			String propertytitle, String propertycode, String description, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD REALESTATE TYPE *****");

		String title1 = propertytitle.replace("$", "&");
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
						// throw new ApplicationException("IOException in
						// scale");
					}
					/********* Today End **********/

					System.out.println("*********************Path" + path);
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
							new FileOutputStream(uploadfile));
					bufferedOutputStream.write(bytes);
					bufferedOutputStream.close();

					// image1 = request.getScheme() + "://" +
					// request.getServerName() + ":" +
					// request.getServerPort()+"/resources/admin/images/Collection/"+image.getOriginalFilename();
					image1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/everest/resources/admin/images/Realestate/" + image.getOriginalFilename();

					propertyType = new PropertyType(title, propertycode, image1, description3, s, id, IpAddress);
					result = propertyDAO.addPropertyType(propertyType);

					return result;
				} catch (Exception e) {
					e.printStackTrace();
					propertyType = new PropertyType(title, propertycode, image1, description3, s, id, IpAddress);
					result = propertyDAO.addPropertyType(propertyType);

					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			propertyType = new PropertyType(title, propertycode, image1, description3, s, id, IpAddress);
			result = propertyDAO.addPropertyType(propertyType);

			return result;
		}

		propertyType = new PropertyType(title, propertycode, image1, description3, s, id, IpAddress);
		result = propertyDAO.addPropertyType(propertyType);

		return result;
	}

	@RequestMapping(value = "addAreaType", method = RequestMethod.POST)
	public String addAreaType(String areatitle, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD REALESTATE TYPE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";

		areaTitle = new AreaTitle(areatitle, s, id, IpAddress);
		propertyDAO.addAreaType(areaTitle);

		return "Success";
	}

	@RequestMapping(value = "addPriceType", method = RequestMethod.POST)
	public String addPriceType(String pricelabel, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PRICE TYPE TITLE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";

		paymentLabel = new PaymentLabel(pricelabel, s, id, IpAddress);
		propertyDAO.addPriceType(paymentLabel);

		return "Success";
	}

	@RequestMapping(value = "addRoomType", method = RequestMethod.POST)
	public String addRoomType(String roomtitle, String description, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD REALESTATE TYPE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";

		roomTitle = new RoomTitle(roomtitle, description, s, id, IpAddress);
		propertyDAO.addRoomType(roomTitle);

		return "Success";
	}

	@RequestMapping(value = "addProperty", method = RequestMethod.POST)
	public String addProperty(int projectid, int realecateid, int realestatesubid, int realestatetypeid,
			int propertytypeoid, String propertytitle, int propertyunitmasterid, String floor, String watersource,
			String drainage, String furnishing, String parking, String availability, String description, String charges,
			float maintenanceamount, String pdescription, String propertystatus, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PROPERTY *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		int sequence = 0;
		String subcategorynamefirstchar = "";
		String typenamefirstchar = "";
		String sku = null;
		String projectnamefirstchar = "";
		String categotynamefirstchar = "";
		String propertyunitnamefirstchar = "";
		String result = null;

		Property p = propertyDAO.getLastPropertyDetailByProjectId(projectid);

		if (p == null) {
			sequence = 1;

			Project project = projectDAO.getProjectDetailById(projectid);
			projectnamefirstchar = project.getProjectTitle().substring(0, 1);

			RealestateType rt = projectDAO.getRealestateTypeDetailById(realecateid);
			categotynamefirstchar = rt.getRealestateTypeName().substring(0, 1);

			RealestateSubcategory rs = projectDAO.getRealestateSubcategoryDetailById(realestatesubid);
			if (rs != null) {
				subcategorynamefirstchar = rs.getSubcategoryTitle().substring(0, 1);
			}

			Realestate r = projectDAO.getRealestateDetailById(realestatetypeid);
			if (r != null) {
				typenamefirstchar = r.getRealestateTitle().substring(0, 1);
			}

			propertyunitnamefirstchar = String.valueOf(propertyunitmasterid);

		} else {
			sequence = p.getSequence() + 1;

			Project project = projectDAO.getProjectDetailById(projectid);
			projectnamefirstchar = project.getProjectTitle().substring(0, 1);

			RealestateType rt = projectDAO.getRealestateTypeDetailById(realecateid);
			categotynamefirstchar = rt.getRealestateTypeName().substring(0, 1);

			RealestateSubcategory rs = projectDAO.getRealestateSubcategoryDetailById(realestatesubid);
			if (rs != null) {
				subcategorynamefirstchar = rs.getSubcategoryTitle().substring(0, 1);
			}

			Realestate r = projectDAO.getRealestateDetailById(realestatetypeid);
			if (r != null) {
				typenamefirstchar = r.getRealestateTitle().substring(0, 1);
			}

			propertyunitnamefirstchar = String.valueOf(propertyunitmasterid);
		}

		sku = projectnamefirstchar + categotynamefirstchar + subcategorynamefirstchar + typenamefirstchar
				+ propertyunitnamefirstchar + sequence;

		property = new Property(sku, sequence, projectid, realecateid, realestatesubid, realestatetypeid,
				propertytypeoid, propertytitle, propertyunitmasterid, floor, watersource, drainage, furnishing, parking,
				availability, description, charges, maintenanceamount, pdescription, propertystatus, s, id, IpAddress);
		result = propertyDAO.addProperty(property);

		return result;
	}

	@RequestMapping(value = "createCloneById", method = RequestMethod.GET)
	public String createCloneById(int propertyid, HttpServletRequest request, HttpSession session) {
		logger.info("***** CREATE CLONE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String s = "y";
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int sequence = 0;
		String subcategorynamefirstchar = "";
		String typenamefirstchar = "";
		String sku = null;
		String projectnamefirstchar = "";
		String categotynamefirstchar = "";
		String propertyunitnamefirstchar = "";

		Property p = propertyDAO.getPropertyDetailById(propertyid);
		Property pt = propertyDAO.getLastPropertyDetailByProjectId(p.getProjectTypeId());
		List<PropertyArea> pa = propertyDAO.getPropertyAreaById(propertyid);
		List<PropertyRoom> pr = propertyDAO.getPropertyRoomById(propertyid);
		List<PropertyPaymentSchedule> pps = propertyDAO.getPropertyPaymentsScheduleById(propertyid);
		List<PropertyPriceInfo> ppi = propertyDAO.getPropertyPriceInfoById(propertyid);
		List<PropertyLayout> pl = propertyDAO.getPropertyLayoutById(propertyid);
		
		String propertyno = pt.getPropertyTitle();
		int propertynumber = Integer.parseInt(propertyno);
		
		propertynumber = propertynumber + 1;

		propertyno = String.valueOf(propertynumber);

		int projectid = p.getProjectTypeId();
		int realecateid = p.getCategoryId();
		int realestatesubid = p.getSubcategoryId();
		int realestatetypeid = p.getRealestateId();
		int propertytypeoid = p.getPropertyTypeId();
		int unitname = p.getPropertyUnitMasterId();
		String floor = p.getFloor();
		String watersource = p.getWaterSource();
		String drainage = p.getDrainage();
		String furnishing = p.getFurnishing();
		String parking = p.getReservedParking();
		String availability = p.getPropertyAvailability();
		String description = p.getPropertyAvailabilityDescription();
		String charges = p.getMaintenanceCharges();
		float maintenanceamount = p.getMaintenanceAmount();
		String pdescription = p.getPropertyDescription();
		String propertystatus = p.getPropertyStatus();

		sequence = pt.getSequence() + 1;

		Project project = projectDAO.getProjectDetailById(projectid);
		projectnamefirstchar = project.getProjectTitle().substring(0, 1);

		RealestateType rt = projectDAO.getRealestateTypeDetailById(realecateid);
		categotynamefirstchar = rt.getRealestateTypeName().substring(0, 1);

		RealestateSubcategory rs = projectDAO.getRealestateSubcategoryDetailById(realestatesubid);
		if (rs != null) {
			subcategorynamefirstchar = rs.getSubcategoryTitle().substring(0, 1);
		}

		Realestate r = projectDAO.getRealestateDetailById(realestatetypeid);
		if (r != null) {
			typenamefirstchar = r.getRealestateTitle().substring(0, 1);
		}

		propertyunitnamefirstchar = String.valueOf(p.getPropertyUnitMasterId());

		sku = projectnamefirstchar + categotynamefirstchar + subcategorynamefirstchar + typenamefirstchar
				+ propertyunitnamefirstchar + sequence;

		property = new Property(sku, sequence, projectid, realecateid, realestatesubid, realestatetypeid,
				propertytypeoid, propertyno, unitname, floor, watersource, drainage, furnishing, parking, availability,
				description, charges, maintenanceamount, pdescription, propertystatus, s, id, IpAddress);
		String result = propertyDAO.addProperty(property);

		if (result.equals("Success")) {

			int proid = propertyDAO.getLastPropertyId();

			for (PropertyArea pArea : pa) {
				propertyArea = new PropertyArea(pArea.getAreaTypeId(), pArea.getAreaValue(), pArea.getUnitId(), proid,
						s, id, IpAddress);
				propertyDAO.addAreaDetails(propertyArea);
			}

			for (PropertyPaymentSchedule pp : pps) {
				propertyPaymentSchedule = new PropertyPaymentSchedule(proid, pp.getSequence(), pp.getSequenceTitle(),
						pp.getPaymentLable(), pp.getPaymentValue(), pp.getUnitId(), s, id, IpAddress);
				propertyDAO.addPropertyPaymentSchedule(propertyPaymentSchedule);
			}

			for (PropertyRoom pRoom : pr) {
				propertyRoom = new PropertyRoom(pRoom.getRoomTitleId(), proid, pRoom.getRoomLength(),
						pRoom.getLengthUnitId(), pRoom.getRoomBreadth(), pRoom.getBreadthUnitId(),
						pRoom.getRoomHeight(), pRoom.getHeightUnitId(), s, id, IpAddress);
				propertyDAO.addRoomDetails(propertyRoom);
			}

			for (PropertyPriceInfo ppin : ppi) {
				propertyPriceInfo = new PropertyPriceInfo(proid, ppin.getPropertyPriceLable(),
						ppin.getPropertyPriceValue(), ppin.getUnitId(), s, id, IpAddress);
				propertyDAO.addPriceDetailInfo(propertyPriceInfo);
			}
			for(PropertyLayout ppl : pl){
			  propertyLayout = new PropertyLayout();
			  propertyLayout.setPropertyId(proid);
			  propertyLayout.setLayoutTitle(ppl.getLayoutTitle());
			  propertyLayout.setImage(ppl.getImage());
			  propertyLayout.setCreatedBy(id);
			  propertyLayout.setIpAddress(IpAddress);
			  propertyDAO.addPropertyLayout(propertyLayout);
			}
		}
		return "Success";
	}

	@RequestMapping(value = "editProperty", method = RequestMethod.POST)
	public String editProperty(int propertyid, int projectid, int realecateid, int realestatesubid,
			int realestatetypeid, int propertytypeoid, String propertytitle, int propertyunitmasterid, String floor,
			String watersource, String drainage, String furnishing, String parking, String availability,
			String description, String charges, float amount, String pdescription, String propertystatus, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT PROPERTY *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";

		property = new Property(propertyid, projectid, realecateid, realestatesubid, realestatetypeid, propertytypeoid,
				propertytitle, propertyunitmasterid, floor, watersource, drainage, furnishing, parking, availability,
				description, charges, amount, pdescription, propertystatus, s, id, IpAddress);
		propertyDAO.editProperty(property);

		return "Success";
	}

	@RequestMapping(value = "addAreaDetails", method = RequestMethod.POST)
	public String addAreaDetails(int areatitleid, float value, int unit, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD AREA DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		int propertyid = propertyDAO.getLastPropertyId();
		propertyArea = new PropertyArea(areatitleid, value, unit, propertyid, s, id, IpAddress);
		propertyDAO.addAreaDetails(propertyArea);

		return "Success";
	}

	@RequestMapping(value = "editAreaDetails", method = RequestMethod.POST)
	public String editAreaDetails(int areatitleid, float value, int unit, int propertyid, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT AREA DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		propertyArea = new PropertyArea(areatitleid, value, unit, propertyid, s, id, IpAddress);
		propertyDAO.addAreaDetails(propertyArea);

		return "Success";
	}

	@RequestMapping(value = "addPriceDetails", method = RequestMethod.POST)
	public String addPriceDetails(float saleDeed, float gst, float stampDuty, float development, float maintenance,
			float total, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ROOM DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		int propertyid = propertyDAO.getLastPropertyId();
		paymentSchedule = new PaymentSchedule(propertyid, saleDeed, gst, stampDuty, development, maintenance, total, s,
				id, IpAddress);
		propertyDAO.addPriceDetails(paymentSchedule);

		return "Success";
	}

	@RequestMapping(value = "addRoomDetails", method = RequestMethod.POST)
	public String addRoomDetails(int roomtitleid, float length, int lengthunit, float breadth, int breadthunit,
			float height, int heightunit, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ROOM DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		int propertyid = propertyDAO.getLastPropertyId();
		propertyRoom = new PropertyRoom(roomtitleid, propertyid, length, lengthunit, breadth, breadthunit, height,
				heightunit, s, id, IpAddress);
		propertyDAO.addRoomDetails(propertyRoom);

		return "Success";
	}

	@RequestMapping(value = "addPriceDetailInfo", method = RequestMethod.POST)
	public String addPriceDetailInfo(String pricelable, float pricevalue, int measurementunitid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PROPERTY PRICE DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		pricelable = pricelable.replace("$", "&");
		pricelable = pricelable.replace("~", "#");
		pricelable = pricelable.replace("!", "%");

		String s = "y";
		int propertyid = propertyDAO.getLastPropertyId();
		propertyPriceInfo = new PropertyPriceInfo(propertyid, pricelable, pricevalue, measurementunitid, s, id,
				IpAddress);
		propertyDAO.addPriceDetailInfo(propertyPriceInfo);

		return "Success";
	}
	
	@RequestMapping(value = "/addPropertyLayout", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addPropertyLayout(@RequestParam(value = "filelayout", required = false) MultipartFile[] filelayout,
            String layouttitle[], HttpServletRequest request, HttpSession session) {
        logger.info("***** ADD Property Floor Layout *****");

        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        String filepath = null;
        if (filelayout.length != 0) {
            try {
                for (int i = 0; i < filelayout.length; i++) {
                    if (filelayout[i].getOriginalFilename() != null && filelayout[i].getOriginalFilename() != "") {
                        try {
                            byte[] bytes = filelayout[i].getBytes();
                            File dir = new File(request.getSession().getServletContext().getRealPath("")
                                    + "/resources/admin/images/" + File.separator + "PropertyLayout");
                            if (!dir.exists())
                                dir.mkdirs();
                            String path = request.getSession().getServletContext()
                                    .getRealPath("/resources/admin/images/PropertyLayout/");
                            File uploadfile = new File(path + File.separator + filelayout[i].getOriginalFilename());
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                    new FileOutputStream(uploadfile));
                            bufferedOutputStream.write(bytes);
                            bufferedOutputStream.close();
                            File f = new File(path);
                            File files[] = f.listFiles();

                            for (int j = 0; j < files.length; j++) {
                                if (files[j].isFile()) {
                                    System.out.println("File " + files[j].getName() + " " + files[j].length());
                                }
                            }
                            filepath = request.getScheme() + "://" + request.getServerName() + ":"
                                    + request.getServerPort() + "/everest/resources/admin/images/PropertyLayout/"
                                    + filelayout[i].getOriginalFilename();

                            int propertyid = propertyDAO.getLastPropertyId();
                            
                            propertyLayout = new PropertyLayout();
                            
                            propertyLayout.setPropertyId(propertyid);
                            propertyLayout.setLayoutTitle(layouttitle[i]);
                            propertyLayout.setImage(filepath);
                            propertyLayout.setCreatedBy(id);
                            propertyLayout.setIpAddress(IpAddress);
                           
                            propertyDAO.addPropertyLayout(propertyLayout);

                        } catch (Exception e) {
                            e.printStackTrace();

                            int propertyid = propertyDAO.getLastPropertyId();
                            propertyLayout = new PropertyLayout();
                            
                            propertyLayout.setPropertyId(propertyid);
                            propertyLayout.setLayoutTitle(layouttitle[i]);
                            propertyLayout.setImage(filepath);
                            propertyLayout.setCreatedBy(id);
                            propertyLayout.setIpAddress(IpAddress);
                            propertyDAO.addPropertyLayout(propertyLayout);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < layouttitle.length; i++) {
                
                int propertyid = propertyDAO.getLastPropertyId();
                propertyLayout = new PropertyLayout();
                
                propertyLayout.setPropertyId(propertyid);
                propertyLayout.setLayoutTitle(layouttitle[i]);
                propertyLayout.setImage(filepath);
                propertyLayout.setCreatedBy(id);
                propertyLayout.setIpAddress(IpAddress);
                propertyDAO.addPropertyLayout(propertyLayout);
            }
        }
        return "Success";
    }
	
	
	@RequestMapping(value = "/editPropertyLayout", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editPropertyLayout(@RequestParam(value = "filelayout", required = false) MultipartFile filelayout,
            int propertyid, String layouttitle, HttpServletRequest request, HttpSession session) {
        logger.info("***** EDIT Property Floor Layout *****");

        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        String filepath = null;
        
            try {
                if (filelayout.getOriginalFilename() != null && filelayout.getOriginalFilename() != "") {
                    try {
                        byte[] bytes = filelayout.getBytes();
                        File dir = new File(request.getSession().getServletContext().getRealPath("")
                                + "/resources/admin/images/" + File.separator + "PropertyLayout");
                        if (!dir.exists())
                            dir.mkdirs();
                        String path = request.getSession().getServletContext()
                                .getRealPath("/resources/admin/images/PropertyLayout/");
                        File uploadfile = new File(path + File.separator + filelayout.getOriginalFilename());
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                new FileOutputStream(uploadfile));
                        bufferedOutputStream.write(bytes);
                        bufferedOutputStream.close();
                        File f = new File(path);
                        File files[] = f.listFiles();

                        for (int j = 0; j < files.length; j++) {
                            if (files[j].isFile()) {
                                System.out.println("File " + files[j].getName() + " " + files[j].length());
                            }
                        }
                        filepath = request.getScheme() + "://" + request.getServerName() + ":"
                                + request.getServerPort() + "/everest/resources/admin/images/PropertyLayout/"
                                + filelayout.getOriginalFilename();
 
                        propertyLayout = new PropertyLayout();
                        
                        propertyLayout.setPropertyId(propertyid);
                        propertyLayout.setLayoutTitle(layouttitle);
                        propertyLayout.setImage(filepath);
                        propertyLayout.setCreatedBy(id);
                        propertyLayout.setIpAddress(IpAddress);
                       
                        propertyDAO.addPropertyLayout(propertyLayout);

                    } catch (Exception e) {
                        e.printStackTrace();

                        propertyLayout = new PropertyLayout();
                        
                        propertyLayout.setPropertyId(propertyid);
                        propertyLayout.setLayoutTitle(layouttitle);
                        propertyLayout.setImage(filepath);
                        propertyLayout.setCreatedBy(id);
                        propertyLayout.setIpAddress(IpAddress);
                        propertyDAO.addPropertyLayout(propertyLayout);
                    }
                }
              
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "Success";
    }

	@RequestMapping(value = "editPropertyPriceDetails", method = RequestMethod.POST)
	public String editPropertyPriceDetails(int propertyid, String pricelable, float pricevalue, int measurementunitid,
			HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT PROPERTY PRICE DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		pricelable = pricelable.replace("$", "&");
		pricelable = pricelable.replace("~", "#");
		pricelable = pricelable.replace("!", "%");

		String s = "y";
		propertyPriceInfo = new PropertyPriceInfo(propertyid, pricelable, pricevalue, measurementunitid, s, id,
				IpAddress);
		propertyDAO.addPriceDetailInfo(propertyPriceInfo);

		return "Success";
	}

	@RequestMapping(value = "editRoomDetails", method = RequestMethod.POST)
	public String editRoomDetails(int roomtitleid, int propertyid, float length, int lengthunit, float breadth,
			int breadthunit, float height, int heightunit, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT ROOM DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		propertyRoom = new PropertyRoom(roomtitleid, propertyid, length, lengthunit, breadth, breadthunit, height,
				heightunit, s, id, IpAddress);
		propertyDAO.addRoomDetails(propertyRoom);

		return "Success";
	}

	@RequestMapping(value = "editPropertyPaymentsSchedule", method = RequestMethod.POST)
	public String editPropertyPaymentsSchedule(int propertyid, String sequence, String sequencetitle, String lable,
			int value, int measurementunitid, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT PROPERTY PAYMENT SCHEDULE *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		propertyPaymentSchedule = new PropertyPaymentSchedule(propertyid, sequence, sequencetitle, lable, value,
				measurementunitid, s, id, IpAddress);
		propertyDAO.addPropertyPaymentSchedule(propertyPaymentSchedule);

		return "Success";
	}

	@RequestMapping(value = "editPriceDetails", method = RequestMethod.POST)
	public String editPriceDetails(int propertyid, float saledeed, float gst, float stampduty, float development,
			float maintenance, float total, HttpServletRequest request, HttpSession session) {
		logger.info("***** EDIT PRICE DETAILS *****");

		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String s = "y";
		paymentSchedule = new PaymentSchedule(propertyid, saledeed, gst, stampduty, development, maintenance, total, s,
				id, IpAddress);
		propertyDAO.addPriceDetails(paymentSchedule);

		return "Success";
	}

	@RequestMapping(value = "/getAllRealestateSubcategoryTitleByRealestateId", method = RequestMethod.GET, produces = "application/json")
	public List<RealestateSubcategory> getAllRealestateSubcategoryTitleByRealestateId(int realestateid,
			HttpServletRequest request) {
		logger.info("***** GET ALL SUBCATEGORY TITLES *****");
		List<RealestateSubcategory> realestateSubcategory = propertyDAO
				.getAllRealestateSubcategoryTitleByRealestateId(realestateid);
		return realestateSubcategory;
	}

	@RequestMapping(value = "/getAllRealestateTypeTitleByRealestateSubcategoryId", method = RequestMethod.GET, produces = "application/json")
	public List<Realestate> getAllRealestateTypeTitleByRealestateSubcategoryId(int realestatesubcategoryid,
			HttpServletRequest request) {
		logger.info("***** GET ALL SUBCATEGORY TITLES *****");
		List<Realestate> realestate = propertyDAO
				.getAllRealestateTypeTitleByRealestateSubcategoryId(realestatesubcategoryid);
		return realestate;
	}

	@RequestMapping(value = "deleteAreaDetail", method = RequestMethod.DELETE)
	public void delete(int propertyareaid) {
		logger.info("***** DELETE AREA *****");
		propertyDAO.deleteAreaDetail(propertyareaid);
	}

	@RequestMapping(value = "deleteRoomDetail", method = RequestMethod.DELETE)
	public void deletes(int propertyroomid) {
		logger.info("***** DELETE ROOM *****");
		propertyDAO.deleteRoomDetail(propertyroomid);
	}

	@RequestMapping(value = "deleteProperty", method = RequestMethod.DELETE)
	public void deleteProperty(int propertyid) {
		logger.info("***** DELETE PROPERTY *****");
		propertyDAO.deleteProperty(propertyid);
	}

	@RequestMapping(value = "deletePriceDetail", method = RequestMethod.DELETE)
	public void deletePriceDetail(int paymentscheduleid) {
		logger.info("***** DELETE PRICE *****");
		propertyDAO.deletePriceDetail(paymentscheduleid);
	}
	
	@RequestMapping(value = "deletePropertyLayout", method = RequestMethod.DELETE)
    public void deletePropertyLayout(int layoutid) {
        logger.info("***** DELETE LAYOUT *****");
        propertyDAO.deletePropertyLayout(layoutid);
    }

	@RequestMapping(value = "deleteTowerName", method = RequestMethod.DELETE)
	public void deleteTowerName(int towernameid) {
		logger.info("***** DELETE TOWER NAME *****");
		propertyDAO.deleteTowerName(towernameid);
	}

	@RequestMapping(value = "deletePropertyPaymentSchedule", method = RequestMethod.DELETE)
	public void deletePropertyPaymentSchedule(int id) {
		logger.info("***** DELETE PROPERTY PAYMENT SCHEDULE *****");
		propertyDAO.deletePropertyPaymentSchedule(id);
	}

	@RequestMapping(value = "deletePropertyPriceDetail", method = RequestMethod.DELETE)
	public void deletePropertyPriceDetail(int propertypriceinfoid) {
		logger.info("***** DELETE Property PRICE *****");
		propertyDAO.deletePropertyPriceDetail(propertypriceinfoid);
	}

	@RequestMapping(value = "/getPropertyNumberListById", method = RequestMethod.GET, produces = "application/json")
	public List<Property> getPropertyNumberListById(int projectid, int categoryid, int subcategoryid, int typeid,
			HttpServletRequest request) {
		logger.info("***** GET ALL PROPERTY NUMBER BY ID *****");
		List<Property> p = propertyDAO.getPropertyNumberListById(projectid, categoryid, subcategoryid, typeid);
		return p;
	}
	
	@RequestMapping(value = "/getPropertyNumberListForEnquiryById", method = RequestMethod.GET, produces = "application/json")
    public List<Property> getPropertyNumberListForEnquiryById(int projectid, int categoryid, int subcategoryid,
            HttpServletRequest request) {
        logger.info("***** GET ALL PROPERTY NUMBER FOR ENQUIRY BY ID *****");
        List<Property> p = propertyDAO.getPropertyNumberListForEnquiryById(projectid, categoryid, subcategoryid);
        return p;
    }

	@RequestMapping(value = "/getPropertyNumberListByIdAndUnitMasterId", method = RequestMethod.GET, produces = "application/json")
	public List<Property> getPropertyNumberListByIdAndUnitMasterId(int projectid, int categoryid, int subcategoryid,
			int typeid, int unitmasterid, HttpServletRequest request) {
		logger.info("***** GET PROPERTY NUMBER BY ID AND UNIT MASTER ID *****");
		List<Property> p = propertyDAO.getPropertyNumberListById(projectid, categoryid, subcategoryid, typeid, unitmasterid);
		return p;
	}

}
