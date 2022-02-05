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

import com.ui.dao.CurrencyDAO;
import com.ui.model.Currency;

public class CurrencyDAOImpl implements CurrencyDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(CurrencyDAOImpl.class);

	@Override
	public List<Currency> getAllCurrencies() {
		logger.info("+++++ GET ALL CURRENCIES +++++");
		List<Currency> sta = new ArrayList<Currency>();
		String s = "y";
		String sql = "select currency_id, currency_name, currency_code, description from currency where status=? order by currency_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Currency currency = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				currency = new Currency(rs.getInt("currency_id"), rs.getString("currency_name"),
						rs.getString("currency_code"), rs.getString("description"));
				sta.add(currency);
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
	public List<Currency> getAllCurrenciesByPage(int pagesize, int startindex) {
		logger.info("+++++ GET CURRENCIES BY PAGE ++++");
		List<Currency> sta = new ArrayList<Currency>();
		String s = "y";
		String sql = "select currency_id, currency_name, currency_code, description from currency where status=? order by currency_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Currency currency = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				currency = new Currency(rs.getInt("currency_id"), rs.getString("currency_name"),
						rs.getString("currency_code"), rs.getString("description"));
				sta.add(currency);
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
	public List<Currency> searchCurrencies(String keyword) {
		logger.info("+++++ SERACH CURRENCIES +++++");
		List<Currency> sta = new ArrayList<Currency>();
		String s = "y";
		String sql = "select currency_id, currency_name, currency_code, description from currency where status=? and concat(currency_name, '', currency_code) like ? order by currency_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Currency currency = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				currency = new Currency(rs.getInt("currency_id"), rs.getString("currency_name"),
						rs.getString("currency_code"), rs.getString("description"));
				sta.add(currency);
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
	public String addCurrency(Currency c) {
		logger.info("+++++ ADD CURRENCY +++++");
		String sql = "insert into currency (currency_name, currency_code, description, status, created_by, ip_address) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getCurrencyName());
			ps.setString(2, c.getCurrencyCode());			
			ps.setString(3, c.getDescription());
			ps.setString(4, c.getStatus());
			ps.setInt(5, c.getCreatedBy());
			ps.setString(6, c.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate currency code!";
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
	public String editCurrency(Currency c) {
		logger.info("+++++ EDIT CURRENCY +++++");
		String sql = "update currency set currency_name=?, currency_code=?, description=?, created_by=?, ip_address=? where currency_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getCurrencyName());
			ps.setString(2, c.getCurrencyCode());			
			ps.setString(3, c.getDescription());
			ps.setInt(4, c.getCreatedBy());
			ps.setString(5, c.getIpAddress());
			ps.setInt(6, c.getCurrencyId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not updated! Duplicate currency code!";
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
	public void deleteCurrency(int currencyid) {
		logger.info("+++++ DELETE CURRENCY +++++");
		String sql = "delete from currency where currency_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, currencyid);
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
