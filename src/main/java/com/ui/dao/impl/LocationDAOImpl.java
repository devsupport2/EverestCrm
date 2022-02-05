package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.LocationDAO;
import com.ui.model.Location;

public class LocationDAOImpl implements LocationDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(LocationDAOImpl.class);

	@Override
	public List<Location> getAllLocations() {
		logger.info("+++++ GET ALL LOCATIONS +++++");
		List<Location> sta = new ArrayList<Location>();
		String s = "y";
		String sql = "select location_id, location_name, city_village, city_survey_no from location where status=? order by location_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Location location = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				location = new Location(rs.getInt("location_id"), rs.getString("location_name"),
						rs.getString("city_village"), rs.getString("city_survey_no"));
				sta.add(location);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<Location> getAllLocationsByPage(int pagesize, int startindex) {
		logger.info("+++++ GET LOCATIONS BY PAGE +++++");
		List<Location> sta = new ArrayList<Location>();
		String s = "y";
		String sql = "select location_id, location_name, city_village, city_survey_no from location where status=? order by location_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Location location = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				location = new Location(rs.getInt("location_id"), rs.getString("location_name"),
						rs.getString("city_village"), rs.getString("city_survey_no"));
				sta.add(location);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<Location> searchLocations(String keyword) {
		logger.info("+++++ SERACH LOCATIONS +++++");
		List<Location> sta = new ArrayList<Location>();
		String s = "y";
		String sql = "select location_id, location_name, city_village, city_survey_no from location where status=? and concat(location_name, '', city_village, '', city_survey_no) like ? order by location_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Location location = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				location = new Location(rs.getInt("location_id"), rs.getString("location_name"),
						rs.getString("city_village"), rs.getString("city_survey_no"));
				sta.add(location);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public Location getLocationDetailById(int locationid) {
		logger.info("+++++ GET LOCATION DETAIL BY ID +++++");
		Location location = null;
		String sql = "select location_id, location_name, location_code, country_id, state_id, district_id, taluka_id, city_village, moje, area, zip, city_survey_no, tp, g_map_link, latitude, longitude, location_map from location where location_id = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, locationid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				location = new Location(rs.getInt("location_id"), rs.getString("location_name"),
						rs.getString("location_code"), rs.getInt("country_id"), rs.getInt("state_id"),
						rs.getInt("district_id"), rs.getInt("taluka_id"), rs.getString("city_village"),
						rs.getString("moje"), rs.getString("area"), rs.getString("zip"), rs.getString("city_survey_no"),
						rs.getString("tp"), rs.getString("g_map_link"), rs.getFloat("latitude"),
						rs.getFloat("longitude"), rs.getString("location_map"));
			}
			rs.close();
			ps.close();

			return location;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public String addLocation(Location s) {
		logger.info("+++++ ADD LOCATION +++++");
		String sql = "insert into location (location_name, location_code, country_id, state_id, district_id, taluka_id, city_village, moje, area, zip, city_survey_no, tp, g_map_link, latitude, longitude, location_map, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getLocationName());
			ps.setString(2, s.getLocationCode());
			ps.setInt(3, s.getCountryId());
			ps.setInt(4, s.getStateId());
			ps.setInt(5, s.getDistrictId());
			ps.setInt(6, s.getTalukaId());
			ps.setString(7, s.getCityVillage());
			ps.setString(8, s.getMoje());
			ps.setString(9, s.getArea());
			ps.setString(10, s.getZip());
			ps.setString(11, s.getCitySurveyNo());
			ps.setString(12, s.getTp());
			ps.setString(13, s.getgMapLink());
			ps.setFloat(14, s.getLatitude());
			ps.setFloat(15, s.getLongitude());
			ps.setString(16, s.getLocationMap());
			ps.setString(17, s.getStatus());
			ps.setInt(18, s.getCreatedBy());
			ps.setString(19, s.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Something went wrong!";
			//return e.getMessage();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public String editLocation(Location s) {
		logger.info("+++++ EDIT LOCATION +++++");
		String sql = "update location set location_name=?, location_code=?, country_id=?, state_id=?, district_id=?, taluka_id=?, city_village=?, moje=?, area=?, zip=?, city_survey_no=?, tp=?, g_map_link=?, latitude=?, longitude=?, location_map=?, created_by=?, ip_address=? where location_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getLocationName());
			ps.setString(2, s.getLocationCode());
			ps.setInt(3, s.getCountryId());
			ps.setInt(4, s.getStateId());
			ps.setInt(5, s.getDistrictId());
			ps.setInt(6, s.getTalukaId());
			ps.setString(7, s.getCityVillage());
			ps.setString(8, s.getMoje());
			ps.setString(9, s.getArea());
			ps.setString(10, s.getZip());
			ps.setString(11, s.getCitySurveyNo());
			ps.setString(12, s.getTp());
			ps.setString(13, s.getgMapLink());
			ps.setFloat(14, s.getLatitude());
			ps.setFloat(15, s.getLongitude());
			ps.setString(16, s.getLocationMap());			
			ps.setInt(17, s.getCreatedBy());
			ps.setString(18, s.getIpAddress());
			ps.setInt(19, s.getLocationId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not updated! Something went wrong!";
			//return e.getMessage();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void deleteLocation(int locationid) {
		logger.info("+++++ DELETE LOCATION +++++");
		String sql = "delete from location where location_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, locationid);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	@Override
	public List<Location> getLocationName() {
		logger.info("+++++ GET LOCATION NAME +++++");
		List<Location> sta = new ArrayList<Location>();
		String s = "y";
		String sql = "select location_id, location_name from location where status=? order by location_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Location location = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				location = new Location(rs.getInt("location_id"), rs.getString("location_name"));
				sta.add(location);
			}
			rs.close();
			ps.close();
			return sta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
