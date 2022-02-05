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

import com.ui.dao.TalukaDAO;
import com.ui.model.Taluka;

public class TalukaDAOImpl implements TalukaDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(TalukaDAOImpl.class);

	@Override
	public List<Taluka> getAllTalukas() {
		logger.info("+++++ GET ALL TALUKAS +++++");
		List<Taluka> sta = new ArrayList<Taluka>();
		String s = "y";
		String sql = "select taluka_id, district_id, state_id, country_id, taluka_name, taluka_code from taluka where status=? order by taluka_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Taluka taluka = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				taluka = new Taluka(rs.getInt("taluka_id"), rs.getInt("district_id"), rs.getInt("state_id"),
						rs.getInt("country_id"), rs.getString("taluka_name"), rs.getString("taluka_code"));
				sta.add(taluka);
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
	public List<Taluka> getAllTalukasByPage(int pagesize, int startindex) {
		logger.info("+++++ GET TALUKAS BY PAGE +++++");
		List<Taluka> sta = new ArrayList<Taluka>();
		String s = "y";
		String sql = "select taluka_id, district_id, state_id, country_id, taluka_name, taluka_code from taluka where status=? order by taluka_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Taluka taluka = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				taluka = new Taluka(rs.getInt("taluka_id"), rs.getInt("district_id"), rs.getInt("state_id"),
						rs.getInt("country_id"), rs.getString("taluka_name"), rs.getString("taluka_code"));
				sta.add(taluka);
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
	public List<Taluka> searchTalukas(String keyword) {
		logger.info("+++++ SERACH TALUKAS +++++");
		List<Taluka> sta = new ArrayList<Taluka>();
		String s = "y";
		String sql = "select taluka_id, district_id, state_id, country_id, taluka_name, taluka_code from taluka where status=? and concat(taluka_name, '', taluka_code) like ? order by taluka_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Taluka taluka = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				taluka = new Taluka(rs.getInt("taluka_id"), rs.getInt("district_id"), rs.getInt("state_id"),
						rs.getInt("country_id"), rs.getString("taluka_name"), rs.getString("taluka_code"));
				sta.add(taluka);
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
	public Taluka getTalukaDetailById(int talukaid) {
		logger.info("+++++ GET TALUKA DETAIL BY ID +++++");
		Taluka taluka = null;
		String sql = "select taluka_id, district_id, state_id, country_id, taluka_name, taluka_code from taluka where taluka_id = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, talukaid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				taluka = new Taluka(rs.getInt("taluka_id"), rs.getInt("district_id"), rs.getInt("state_id"),
						rs.getInt("country_id"), rs.getString("taluka_name"), rs.getString("taluka_code"));				
			}
			rs.close();
			ps.close();

			return taluka;
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
	public String addTaluka(Taluka s) {
		logger.info("+++++ ADD TALUKA +++++");
		String sql = "insert into taluka (district_id, state_id, country_id, taluka_name, taluka_code, status, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getDistrictId());
			ps.setInt(2, s.getStateId());
			ps.setInt(3, s.getCountryId());
			ps.setString(4, s.getTalukaName());
			ps.setString(5, s.getTalukaCode());
			ps.setString(6, s.getStatus());
			ps.setInt(7, s.getCreatedBy());
			ps.setString(8, s.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate taluka name or code!";
			// return e.getMessage();
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
	public String editTaluka(Taluka s) {
		logger.info("+++++ EDIT TALUKA +++++");
		String sql = "update taluka set district_id=?, state_id=?, country_id=?, taluka_name=?, taluka_code=?, created_by=?, ip_address=? where taluka_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getDistrictId());
			ps.setInt(2, s.getStateId());
			ps.setInt(3, s.getCountryId());
			ps.setString(4, s.getTalukaName());
			ps.setString(5, s.getTalukaCode());
			ps.setInt(6, s.getCreatedBy());
			ps.setString(7, s.getIpAddress());
			ps.setInt(8, s.getTalukaId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate taluka name or code!";
			// return e.getMessage();
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
	public void deleteTaluka(int talukaid) {
		logger.info("+++++ DELETE TALUKA +++++");
		String sql = "delete from taluka where taluka_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, talukaid);
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
	public List<Taluka> getTalukaByDistrictId(int districtid) {
		logger.info("+++++ GET TALUKA BY DISTRICT ID +++++");
		List<Taluka> sta = new ArrayList<Taluka>();
		String s = "y";
		String sql = "select taluka_id, taluka_name from taluka where status=? and district_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setLong(2, districtid);
			Taluka t = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				t = new Taluka(rs.getInt("taluka_id"), rs.getString("taluka_name"));
				sta.add(t);
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
