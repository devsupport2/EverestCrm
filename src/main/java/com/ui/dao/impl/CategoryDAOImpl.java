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

import com.ui.dao.CategoryDAO;
import com.ui.model.RealestateType;

public class CategoryDAOImpl implements CategoryDAO{
	
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	@Override
	public List<RealestateType> getCategory() {
		logger.info("+++++ GET ALL CATEGORIES +++++");
		List<RealestateType> sta = new ArrayList<RealestateType>();
		String s = "y";
		String sql = "select realestate_type_id, realestate_type_name, realestate_code, image, description from realestate_type where status=? order by realestate_type_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			RealestateType c = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new RealestateType();				
				c.setRealestateTypeId(rs.getInt("realestate_type_id"));
				c.setRealestateTypeName(rs.getString("realestate_type_name"));
				c.setRealestateCode(rs.getString("realestate_code"));
				c.setImage(rs.getString("image"));
				c.setDescription(rs.getString("description"));
				sta.add(c);
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
	public List<RealestateType> getCategoryByPage(int pagesize, int startindex) {
		logger.info("+++++ GET ALL CATEGORIES BY PAGE +++++");
		List<RealestateType> sta = new ArrayList<RealestateType>();
		String s = "y";
		String sql = "select realestate_type_id, realestate_type_name, realestate_code, image, description from realestate_type where status=? order by realestate_type_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			RealestateType c = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new RealestateType();				
				c.setRealestateTypeId(rs.getInt("realestate_type_id"));
				c.setRealestateTypeName(rs.getString("realestate_type_name"));
				c.setRealestateCode(rs.getString("realestate_code"));
				c.setImage(rs.getString("image"));
				c.setDescription(rs.getString("description"));
				sta.add(c);
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
	public List<RealestateType> searchCategory(String keyword) {
		logger.info("+++++ SEARCH CATEGORY +++++");
		List<RealestateType> sta = new ArrayList<RealestateType>();
		String s = "y";
		String sql = "select realestate_type_id, realestate_type_name, realestate_code, image, description from realestate_type where status=? and concat(realestate_type_name, '', realestate_code) like ? order by realestate_type_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			RealestateType c = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new RealestateType();				
				c.setRealestateTypeId(rs.getInt("realestate_type_id"));
				c.setRealestateTypeName(rs.getString("realestate_type_name"));
				c.setRealestateCode(rs.getString("realestate_code"));
				c.setImage(rs.getString("image"));
				c.setDescription(rs.getString("description"));
				sta.add(c);
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
	public String addRealestateType(RealestateType c) {
		logger.info("+++++ ADD CATEGORY +++++");
		String sql = "insert into realestate_type (realestate_type_name, realestate_code, image, description, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getRealestateTypeName());
			ps.setString(2, c.getRealestateCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());
			ps.setString(5, c.getStatus());
			ps.setInt(6, c.getCreatedBy());
			ps.setString(7, c.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return e.getMessage();
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
	public String editRealestateType(RealestateType c) {
		logger.info("+++++ EDIT COUNTRY +++++");
		String sql = "update realestate_type set realestate_type_name=?, realestate_code=?, image=?, description=?, created_by=?, ip_address=? where realestate_type_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getRealestateTypeName());
			ps.setString(2, c.getRealestateCode());
			ps.setString(3, c.getImage());
			ps.setString(4, c.getDescription());			
			ps.setInt(5, c.getCreatedBy());
			ps.setString(6, c.getIpAddress());
			ps.setInt(7, c.getRealestateTypeId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not updated! Duplicate entry of country code or dialing code!";
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
	public void deleteCategory(int id) {
		logger.info("+++++ DELETE CATEGORY +++++");		
		String sql = "delete from realestate_type where realestate_type_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, id);
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
	public RealestateType getCategoryDetailById(int id) {
		logger.info("+++++ GET CATEGORY DETAIL BY ID +++++");
		RealestateType c = null;
		String sql = "select realestate_type_id, realestate_type_name, realestate_code, image, description from realestate_type where realestate_type_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new RealestateType();				
				c.setRealestateTypeId(rs.getInt("realestate_type_id"));
				c.setRealestateTypeName(rs.getString("realestate_type_name"));
				c.setRealestateCode(rs.getString("realestate_code"));
				c.setImage(rs.getString("image"));
				c.setDescription(rs.getString("description"));				
			}
			rs.close();
			ps.close();
			return c;
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
