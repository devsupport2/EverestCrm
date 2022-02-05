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

import com.ui.dao.SubcategoryDAO;
import com.ui.model.RealestateSubcategory;

public class SubcategoryDAOImpl implements SubcategoryDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(SubcategoryDAOImpl.class);
	
	@Override
	public String addRealestateSubcategoryType(RealestateSubcategory r) {
		logger.info("+++++ ADD CATEGORY +++++");
		String sql = "insert into realestate_subcategory (realestate_type_id, subcategory_title, subcategory_code, image, description, status, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getRealestateTypeId());
			ps.setString(2, r.getSubcategoryTitle());
			ps.setString(3, r.getSubcategoryCode());
			ps.setString(4, r.getImage());
			ps.setString(5, r.getDescription());
			ps.setString(6, r.getStatus());
			ps.setInt(7, r.getCreatedBy());
			ps.setString(8, r.getIpAddress());
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
	public List<RealestateSubcategory> getSubcategory() {
		logger.info("+++++ GET ALL SUBCATEGORIES +++++");
		List<RealestateSubcategory> sta = new ArrayList<RealestateSubcategory>();
		String s = "y";
		String sql = "select s.realestate_subcategory_id, s.realestate_type_id, s.subcategory_title, s.subcategory_code, s.image, s.description, c.realestate_type_name from realestate_subcategory s left join realestate_type c on s.realestate_type_id = c.realestate_type_id where s.status=? order by s.subcategory_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			RealestateSubcategory sc = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sc = new RealestateSubcategory();				
				sc.setRealestateSubcategoryId(rs.getInt("realestate_subcategory_id"));
				sc.setRealestateTypeId(rs.getInt("realestate_type_id"));
				sc.setSubcategoryTitle(rs.getString("subcategory_title"));
				sc.setSubcategoryCode(rs.getString("subcategory_code"));
				sc.setImage(rs.getString("image"));
				sc.setDescription(rs.getString("description"));
				sc.setRealestateTypeName(rs.getString("realestate_type_name"));
				sta.add(sc);
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
	public List<RealestateSubcategory> getSubcategoryByPage(int pagesize, int startindex) {
		logger.info("+++++ GET ALL SUBCATEGORIES BY PAGE +++++");
		List<RealestateSubcategory> sta = new ArrayList<RealestateSubcategory>();
		String s = "y";
		String sql = "select s.realestate_subcategory_id, s.realestate_type_id, s.subcategory_title, s.subcategory_code, s.image, s.description, c.realestate_type_name from realestate_subcategory s left join realestate_type c on s.realestate_type_id = c.realestate_type_id where s.status=? order by s.subcategory_title limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			RealestateSubcategory sc = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sc = new RealestateSubcategory();				
				sc.setRealestateSubcategoryId(rs.getInt("realestate_subcategory_id"));
				sc.setRealestateTypeId(rs.getInt("realestate_type_id"));
				sc.setSubcategoryTitle(rs.getString("subcategory_title"));
				sc.setSubcategoryCode(rs.getString("subcategory_code"));
				sc.setImage(rs.getString("image"));
				sc.setDescription(rs.getString("description"));
				sc.setRealestateTypeName(rs.getString("realestate_type_name"));
				sta.add(sc);
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
	public List<RealestateSubcategory> searchSubcategory(String keyword) {
		logger.info("+++++ SEARCH SUBCATEGORY +++++");
		List<RealestateSubcategory> sta = new ArrayList<RealestateSubcategory>();
		String s = "y";
		String sql = "select s.realestate_subcategory_id, s.realestate_type_id, s.subcategory_title, s.subcategory_code, s.image, s.description, c.realestate_type_name from realestate_subcategory s left join realestate_type c on s.realestate_type_id = c.realestate_type_id where s.status=? and concat(s.subcategory_title, '', s.subcategory_code, '', c.realestate_type_name) like ?  order by s.subcategory_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			RealestateSubcategory sc = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sc = new RealestateSubcategory();				
				sc.setRealestateSubcategoryId(rs.getInt("realestate_subcategory_id"));
				sc.setRealestateTypeId(rs.getInt("realestate_type_id"));
				sc.setSubcategoryTitle(rs.getString("subcategory_title"));
				sc.setSubcategoryCode(rs.getString("subcategory_code"));
				sc.setImage(rs.getString("image"));
				sc.setDescription(rs.getString("description"));
				sc.setRealestateTypeName(rs.getString("realestate_type_name"));
				sta.add(sc);
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
	public String editRealestateSubcategoryType(RealestateSubcategory r) {
		logger.info("+++++ EDIT SUBCATEGORY +++++");
		String sql = "update realestate_subcategory set realestate_type_id=?, subcategory_title=?, subcategory_code=?, image=?, description=?, created_by=?, ip_address=? where realestate_subcategory_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getRealestateTypeId());
			ps.setString(2, r.getSubcategoryTitle());
			ps.setString(3, r.getSubcategoryCode());
			ps.setString(4, r.getImage());
			ps.setString(5, r.getDescription());			
			ps.setInt(6, r.getCreatedBy());
			ps.setString(7, r.getIpAddress());
			ps.setInt(8, r.getRealestateSubcategoryId());
			
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
	public void deleteSubcategory(int id) {
		logger.info("+++++ DELETE CATEGORY +++++");		
		String sql = "delete from realestate_subcategory where realestate_subcategory_id=?";
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
	public RealestateSubcategory getSubcategoryDetailById(int id) {
		logger.info("+++++ GET SUBCATEGORY DETAIL BY ID +++++");
		RealestateSubcategory s = null;
		String sql = "select realestate_subcategory_id, realestate_type_id, subcategory_title, subcategory_code, image, description from realestate_subcategory where realestate_subcategory_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new RealestateSubcategory();
				s.setRealestateSubcategoryId(rs.getInt("realestate_subcategory_id"));
				s.setRealestateTypeId(rs.getInt("realestate_type_id"));
				s.setSubcategoryTitle(rs.getString("subcategory_title"));
				s.setSubcategoryCode(rs.getString("subcategory_code"));
				s.setImage(rs.getString("image"));
				s.setDescription(rs.getString("description"));
			}
			rs.close();
			ps.close();
			return s;
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
