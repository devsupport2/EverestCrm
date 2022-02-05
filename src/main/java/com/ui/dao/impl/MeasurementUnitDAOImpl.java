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

import com.ui.dao.MeasurementUnitDAO;
import com.ui.model.MeasurementUnit;

public class MeasurementUnitDAOImpl implements MeasurementUnitDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(MeasurementUnitDAOImpl.class);

	@Override
	public List<MeasurementUnit> getAllMeasurementUnits() {
		logger.info("+++++ GET ALL UNIT +++++");
		List<MeasurementUnit> sta = new ArrayList<MeasurementUnit>();
		String s = "y";
		String sql = "select measurement_unit_id, measurement_unit_name, description from measurement_unit where status=? order by measurement_unit_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			MeasurementUnit measurementUnit = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				measurementUnit = new MeasurementUnit(rs.getInt("measurement_unit_id"),
						rs.getString("measurement_unit_name"), rs.getString("description"));
				sta.add(measurementUnit);
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
	public List<MeasurementUnit> getAllMeasurementUnitsByPage(int pagesize, int startindex) {
		logger.info("+++++ UNIT BY PAGE +++++");
		List<MeasurementUnit> sta = new ArrayList<MeasurementUnit>();
		String s = "y";
		String sql = "select measurement_unit_id, measurement_unit_name, description from measurement_unit where status=? order by measurement_unit_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			MeasurementUnit measurementUnit = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				measurementUnit = new MeasurementUnit(rs.getInt("measurement_unit_id"),
						rs.getString("measurement_unit_name"), rs.getString("description"));
				sta.add(measurementUnit);
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
	public List<MeasurementUnit> searchMeasurementUnits(String keyword) {
		logger.info("+++++ SEARCH UNIT +++++");
		List<MeasurementUnit> sta = new ArrayList<MeasurementUnit>();
		String s = "y";
		String sql = "select measurement_unit_id, measurement_unit_name, description from measurement_unit where status=? and concat(measurement_unit_name) like ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			MeasurementUnit measurementUnit = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				measurementUnit = new MeasurementUnit(rs.getInt("measurement_unit_id"),
						rs.getString("measurement_unit_name"), rs.getString("description"));
				sta.add(measurementUnit);
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
	public void addMeasurementUnit(MeasurementUnit m) {
		logger.info("+++++ ADD UNIT +++++");
		String sql = "insert into measurement_unit (measurement_unit_name, description, status, created_by, ip_address) values (?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getMeasurementUnitName());
			ps.setString(2, m.getDescription());
			ps.setString(3, m.getStatus());
			ps.setInt(4, m.getCreatedBy());
			ps.setString(5, m.getIpAddress());
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
	public void editMeasurementUnit(MeasurementUnit m) {
		logger.info("+++++ EDIT UNIT +++++");
		String sql = "update measurement_unit set measurement_unit_name=?, description=?, created_by=?, ip_address=? where measurement_unit_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getMeasurementUnitName());
			ps.setString(2, m.getDescription());
			ps.setInt(3, m.getCreatedBy());
			ps.setString(4, m.getIpAddress());
			ps.setInt(5, m.getMeasurementUnitId());
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
	public void deleteMeasurementUnit(int measurementunitid) {
		logger.info("+++++ DELETE UNIT +++++");		
		String sql = "delete from measurement_unit where measurement_unit_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, measurementunitid);
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
	public MeasurementUnit getUnitDetailById(int unitid) {
		logger.info("+++++ GET UNIT DETAIL BY ID +++++");
		MeasurementUnit measurementUnit = null;		
		String sql = "select measurement_unit_id, measurement_unit_name, description from measurement_unit where measurement_unit_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, unitid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				measurementUnit = new MeasurementUnit(rs.getInt("measurement_unit_id"),
						rs.getString("measurement_unit_name"), rs.getString("description"));			
			}
			rs.close();
			ps.close();

			return measurementUnit;
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
