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

import com.ui.dao.DistrictDAO;
import com.ui.model.District;

public class DistrictDAOImpl implements DistrictDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(DistrictDAOImpl.class);

	@Override
	public List<District> getAllDistricts() {
		logger.info("+++++ GET ALL DISTRICTS +++++");
		List<District> sta = new ArrayList<District>();
		String s = "y";
		String sql = "select district_id, state_id, country_id, district_name, district_code from district where status=? order by district_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			District district = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				district = new District(rs.getInt("district_id"), rs.getInt("state_id"), rs.getInt("country_id"),
						rs.getString("district_name"), rs.getString("district_code"));
				sta.add(district);
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
	public List<District> getAllDistrictsByPage(int pagesize, int startindex) {
		logger.info("+++++ GET DISTRICTS BY PAGE +++++");
		List<District> sta = new ArrayList<District>();
		String s = "y";
		String sql = "select district_id, state_id, country_id, district_name, district_code from district where status=? order by district_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			District district = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				district = new District(rs.getInt("district_id"), rs.getInt("state_id"), rs.getInt("country_id"),
						rs.getString("district_name"), rs.getString("district_code"));
				sta.add(district);
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
	public List<District> searchDistricts(String keyword) {
		logger.info("+++++ SERACH DISTRICTS +++++");
		List<District> sta = new ArrayList<District>();
		String s = "y";
		String sql = "select district_id, state_id, country_id, district_name, district_code from district where status=? and concat(district_name, '', district_code) like ? order by district_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			District district = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				district = new District(rs.getInt("district_id"), rs.getInt("state_id"), rs.getInt("country_id"),
						rs.getString("district_name"), rs.getString("district_code"));
				sta.add(district);
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
	public District getDistrictDetailById(int districtid) {
		logger.info("+++++ GET DISTRICT DETAIL BY ID +++++");
		District district = null;
		String sql = "select district_id, state_id, country_id, district_name, district_code from district where district_id = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, districtid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				district = new District(rs.getInt("district_id"), rs.getInt("state_id"), rs.getInt("country_id"),
						rs.getString("district_name"), rs.getString("district_code"));
			}
			rs.close();
			ps.close();

			return district;
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
	public String addDistrict(District s) {
		logger.info("+++++ ADD DISTRICT +++++");
		String sql = "insert into district (state_id, country_id, district_name, district_code, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getStateId());
			ps.setInt(2, s.getCountryId());
			ps.setString(3, s.getDistrictName());
			ps.setString(4, s.getDistrictCode());			
			ps.setString(5, s.getStatus());
			ps.setInt(6, s.getCreatedBy());
			ps.setString(7, s.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate district name or code!";
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
	public String editDistrict(District s) {
		logger.info("+++++ EDIT DISTRICT +++++");
		String sql = "update district set state_id=?, country_id=?, district_name=?, district_code=?, created_by=?, ip_address=? where district_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getStateId());
			ps.setInt(2, s.getCountryId());
			ps.setString(3, s.getDistrictName());
			ps.setString(4, s.getDistrictCode());			
			ps.setInt(5, s.getCreatedBy());
			ps.setString(6, s.getIpAddress());
			ps.setInt(7, s.getDistrictId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate district name or code!";
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
	public void deleteDistrict(int districtid) {
		logger.info("+++++ DELETE DISTRICT +++++");
		String sql = "delete from district where district_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, districtid);
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
	public List<District> getDistrictByStateId(int stateid) {
		logger.info("+++++ GET DISTRICT BY STATE ID +++++");
		List<District> sta = new ArrayList<District>();
		String s = "y";
		String sql = "select district_id, district_name from district where status=? and state_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setLong(2, stateid);
			District d = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				d = new District(rs.getInt("district_id"), rs.getString("district_name"));
				sta.add(d);
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
