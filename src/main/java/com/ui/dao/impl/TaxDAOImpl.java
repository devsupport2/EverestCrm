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

import com.ui.dao.TaxDAO;
import com.ui.model.Tax;

public class TaxDAOImpl implements TaxDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(TaxDAOImpl.class);

	@Override
	public List<Tax> getAllTaxes() {
		logger.info("***** GET TAX *****");
		List<Tax> sta = new ArrayList<Tax>();
		String s = "y";
		String sql = "select tax_id, tax_name, description from tax where status=? order by tax_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Tax tax = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tax = new Tax(rs.getInt("tax_id"), rs.getString("tax_name"), rs.getString("description"));
				sta.add(tax);
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
	public List<Tax> getAllTaxesByPage(int pagesize, int startindex) {
		logger.info("+++++ GET TAX BY PAGE +++++");
		List<Tax> sta = new ArrayList<Tax>();
		String s = "y";
		String sql = "select tax_id, tax_name, description from tax where status=? order by tax_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Tax tax = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tax = new Tax(rs.getInt("tax_id"), rs.getString("tax_name"), rs.getString("description"));
				sta.add(tax);
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
	public List<Tax> searchTaxes(String keyword) {
		logger.info("+++++ SERACH TEX +++++");
		List<Tax> sta = new ArrayList<Tax>();
		String s = "y";
		String sql = "select tax_id, tax_name, description from tax where status=? and concat(tax_name) like ? order by tax_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Tax tax = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tax = new Tax(rs.getInt("tax_id"), rs.getString("tax_name"), rs.getString("description"));
				sta.add(tax);
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
	public String addTax(Tax t) {
		logger.info("+++++ ADD TAX +++++");
		String sql = "insert into tax (tax_name, description, status, created_by, ip_address) values (?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getTaxName());
			ps.setString(2, t.getDescription());
			ps.setString(3, t.getStatus());
			ps.setInt(4, t.getCreatedBy());
			ps.setString(5, t.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate tax name!";
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
	public String editTax(Tax t) {
		logger.info("+++++ EDIT TAX +++++");
		String sql = "update tax set tax_name=?, description=?, created_by=?, ip_address=? where tax_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getTaxName());
			ps.setString(2, t.getDescription());
			ps.setInt(3, t.getCreatedBy());
			ps.setString(4, t.getIpAddress());
			ps.setInt(5, t.getTaxId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate tax name!";
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
	public void deleteTax(int taxid) {
		logger.info("+++++ DELETE TAX +++++");		
		String sql = "delete from tax where tax_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, taxid);
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
}
