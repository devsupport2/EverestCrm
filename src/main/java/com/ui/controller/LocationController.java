package com.ui.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

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

import com.ui.dao.LocationDAO;
import com.ui.model.Location;

@RestController
public class LocationController {
	@Autowired
	LocationDAO locationDao;

	Location location;

	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);

	@RequestMapping(value = "/getLocations", method = RequestMethod.GET, produces = "application/json")
	public List<Location> getLocations(HttpServletRequest request) {
		logger.info("***** GET LOCATIONS *****");
		List<Location> location = locationDao.getAllLocations();
		return location;
	}

	@RequestMapping(value = "/getLocationsByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Location> getLocationsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET LOCATION BY PAGE *****");
		List<Location> location = locationDao.getAllLocationsByPage(pagesize, startindex);
		return location;
	}

	@RequestMapping(value = "/searchLocations", method = RequestMethod.GET, produces = "application/json")
	public List<Location> searchLocations(String keyword, HttpServletRequest request) {
		logger.info("***** SERACH LOCATIONS *****");
		List<Location> location = locationDao.searchLocations(keyword);
		return location;
	}

	@RequestMapping(value = "/getLocationDetailById", method = RequestMethod.GET, produces = "application/json")
	public Location getLocationDetailById(int locationid) {
		logger.info("***** GET LOCATION DETAIL BY ID *****");
		Location d = locationDao.getLocationDetailById(locationid);
		return d;
	}

	@RequestMapping(value = "addLocation", method = RequestMethod.POST)
	public String addLocation(@RequestParam(value = "locationmap", required = false) MultipartFile locationmap,
			String locationname, String locationcode, int countryname, int statename, int districtname, int talukaname,
			String cityvillage, String moje, String area, String zip, String citysurvey, String tp, String gmaplink,
			float latitude, float longitude, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD LOCATION *****");
		String result = null;
		String mapfile = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String status = "y";

		if (locationmap != null) {
			try {
				byte[] bytes = locationmap.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "LocationMap");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/LocationMap/");
				File uploadfile = new File(path + File.separator + locationmap.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				mapfile = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/LocationMap/" + locationmap.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		location = new Location(locationname, locationcode, countryname, statename, districtname, talukaname,
				cityvillage, moje, area, zip, citysurvey, tp, gmaplink, latitude, longitude, mapfile, status, id,
				IpAddress);
		result = locationDao.addLocation(location);
		return result;
	}

	@RequestMapping(value = "editLocation", method = RequestMethod.POST)
	public String editLocation(@RequestParam(value = "locationmapedit", required = false) MultipartFile locationmapedit,
			int locationid, String locationname, String locationcode, int countryname, int statename, int districtname,
			int talukaname, String cityvillage, String moje, String area, String zip, String citysurvey, String tp,
			String gmaplink, float latitude, float longitude, String locationmap, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** EDIT LOCATION *****");
		String result = null;
		String mapfile = null;
		int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		if (locationmapedit != null) {
			try {
				byte[] bytes = locationmapedit.getBytes();
				File dir = new File(request.getSession().getServletContext().getRealPath("")
						+ "/resources/admin/images/" + File.separator + "LocationMap");
				if (!dir.exists())
					dir.mkdirs();
				String path = request.getSession().getServletContext()
						.getRealPath("/resources/admin/images/LocationMap/");
				File uploadfile = new File(path + File.separator + locationmapedit.getOriginalFilename());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				bufferedOutputStream.write(bytes);
				bufferedOutputStream.close();
				File f = new File(path);
				File files[] = f.listFiles();

				for (int j = 0; j < files.length; j++) {
					if (files[j].isFile()) {
						System.out.println("File " + files[j].getName() + " " + files[j].length());
					}
				}
				mapfile = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ "/everest/resources/admin/images/LocationMap/" + locationmapedit.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (locationmapedit == null) {
			location = new Location(locationid, locationname, locationcode, countryname, statename, districtname,
					talukaname, cityvillage, moje, area, zip, citysurvey, tp, gmaplink, latitude, longitude,
					locationmap, id, IpAddress);
		} else {
			location = new Location(locationid, locationname, locationcode, countryname, statename, districtname,
					talukaname, cityvillage, moje, area, zip, citysurvey, tp, gmaplink, latitude, longitude,
					mapfile, id, IpAddress);
		}
		
		result = locationDao.editLocation(location);
		return result;
		
	}

	@RequestMapping(value = "deleteLocation", method = RequestMethod.DELETE)
	public void delete(int locationid) {
		logger.info("***** DELETE STATE *****");
		locationDao.deleteLocation(locationid);
	}
	
	@RequestMapping(value = "/getLocationName", method = RequestMethod.GET, produces = "application/json")
	public List<Location> getLocationName(HttpServletRequest request) {
		logger.info("***** GET LOCATION NAME *****");
		List<Location> location = locationDao.getLocationName();
		return location;
	}
}
