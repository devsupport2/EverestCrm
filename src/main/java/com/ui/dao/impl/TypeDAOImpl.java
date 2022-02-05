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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.TypeDAO;
import com.ui.model.Realestate;

public class TypeDAOImpl implements TypeDAO {
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(TypeDAOImpl.class);
	
	@Override
	public List<Realestate> getType() {
		logger.info("+++++ GET ALL TYPE +++++");
		List<Realestate> sta = new ArrayList<Realestate>();
		String s = "y";
		String sql = "select realestate_id, realestate_title, realestate_code, image, description from realestate where status=? order by realestate_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Realestate t = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				t = new Realestate();
				
				t.setRealestateId(rs.getInt("realestate_id"));
				t.setRealestateTitle(rs.getString("realestate_title"));
				t.setRealestateCode(rs.getString("realestate_code"));
				t.setImage(rs.getString("image"));
				t.setDescription(rs.getString("description"));
				
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
	
	@Override
	public List<Realestate> getTypeByPage(int pagesize, int startindex) {
		logger.info("+++++ GET ALL TYPE BY PAGE +++++");
		List<Realestate> sta = new ArrayList<Realestate>();
		String s = "y";
		String sql = "select realestate_id, realestate_title, realestate_code, image, description from realestate where status=? order by realestate_title limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Realestate t = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				t = new Realestate();
				
				t.setRealestateId(rs.getInt("realestate_id"));
				t.setRealestateTitle(rs.getString("realestate_title"));
				t.setRealestateCode(rs.getString("realestate_code"));
				t.setImage(rs.getString("image"));
				t.setDescription(rs.getString("description"));
				
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
	
	@Override
	public List<Realestate> searchType(String keyword) {
		logger.info("+++++ SEARCH TYPE +++++");
		List<Realestate> sta = new ArrayList<Realestate>();
		String s = "y";
		String sql = "select realestate_id, realestate_title, realestate_code, image, description from realestate where status=? and concat(realestate_title, '', realestate_code) like ? order by realestate_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Realestate t = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				t = new Realestate();
				
				t.setRealestateId(rs.getInt("realestate_id"));
				t.setRealestateTitle(rs.getString("realestate_title"));
				t.setRealestateCode(rs.getString("realestate_code"));
				t.setImage(rs.getString("image"));
				t.setDescription(rs.getString("description"));
				
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
	
	@Override
	public String addRealestate(Realestate s) {
		logger.info("+++++ ADD CATEGORY +++++");
		String sql = "insert into realestate (realestate_type_id, realestate_subcategory_id, realestate_title, realestate_code, image, description, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getRealestateTypeId());
			ps.setInt(2, s.getRealestateSubcategoryId());
			ps.setString(3, s.getRealestateTitle());
			ps.setString(4, s.getRealestateCode());
			ps.setString(5, s.getImage());
			ps.setString(6, s.getDescription());
			ps.setString(7, s.getStatus());
			ps.setInt(8, s.getCreatedBy());
			ps.setString(9, s.getIpAddress());
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
	public String editRealestate(Realestate c) {
		logger.info("+++++ EDIT TYPE +++++");
		String sql = "update realestate set realestate_type_id=?, realestate_subcategory_id=?, realestate_title=?, realestate_code=?, image=?, description=?, created_by=?, ip_address=? where realestate_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getRealestateTypeId());
			ps.setInt(2, c.getRealestateSubcategoryId());
			ps.setString(3, c.getRealestateTitle());
			ps.setString(4, c.getRealestateCode());
			ps.setString(5, c.getImage());
			ps.setString(6, c.getDescription());			
			ps.setInt(7, c.getCreatedBy());
			ps.setString(8, c.getIpAddress());
			ps.setInt(9, c.getRealestateId());
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
	public void deleteType(int id) {
		logger.info("+++++ DELETE CATEGORY +++++");		
		String sql = "delete from realestate where realestate_id=?";
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
	public Realestate getTypeDetailById(int id) {
		logger.info("+++++ GET CATEGORY DETAIL BY ID +++++");
		Realestate c = null;
		String sql = "select realestate_id, realestate_title, realestate_code, image, description from realestate where realestate_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Realestate();				
				c.setRealestateId(rs.getInt("realestate_id"));
				c.setRealestateTitle(rs.getString("realestate_title"));
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
