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

import com.ui.dao.CountryDAO;
import com.ui.model.Country;

public class CountryDAOImpl implements CountryDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(CountryDAOImpl.class);

	@Override
	public List<Country> getAllCountries() {
		logger.info("+++++ GET ALL COUNTRIES +++++");
		List<Country> sta = new ArrayList<Country>();
		String s = "y";
		String sql = "select country_id, country_name, country_code, country_dialing_code from country where status=? order by country_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Country country = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				country = new Country(rs.getInt("country_id"), rs.getString("country_name"),
						rs.getString("country_code"), rs.getString("country_dialing_code"));

				sta.add(country);
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
	public List<Country> getAllCountriesByPage(int pagesize, int startindex) {
		logger.info("+++++ GET ALL COUNTRIES BY PAGE +++++");
		List<Country> sta = new ArrayList<Country>();
		String s = "y";
		String sql = "select country_id, country_name, country_code, country_dialing_code from country where status=? order by country_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Country country = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				country = new Country(rs.getInt("country_id"), rs.getString("country_name"),
						rs.getString("country_code"), rs.getString("country_dialing_code"));
				sta.add(country);
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
	public List<Country> searchCountries(String keyword) {
		logger.info("Inside Search Country Impl");
		List<Country> sta = new ArrayList<Country>();
		String s = "y";
		String sql = "select country_id, country_name, country_code, country_dialing_code from country where status=? and concat(country_name, '', country_code, '', country_dialing_code) like ? order by country_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Country country = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				country = new Country(rs.getInt("country_id"), rs.getString("country_name"),
						rs.getString("country_code"), rs.getString("country_dialing_code"));
				sta.add(country);
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
	public String addCountry(Country c) {
		logger.info("+++++ ADD COUNTRY +++++");
		String sql = "insert into country (country_name, country_code, country_dialing_code, status, created_by, ip_address) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getCountryName());
			ps.setString(2, c.getCountryCode());
			ps.setString(3, c.getCountryDialingCode());
			ps.setString(4, c.getStatus());
			ps.setInt(5, c.getCreatedBy());
			ps.setString(6, c.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not saved! Duplicate entry of country code or dialing code!";
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
	public String editCountry(Country c) {
		logger.info("+++++ EDIT COUNTRY +++++");
		String sql = "update country set country_name=?, country_code=?, country_dialing_code=?, created_by=?, ip_address=? where country_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getCountryName());
			ps.setString(2, c.getCountryCode());
			ps.setString(3, c.getCountryDialingCode());
			ps.setInt(4, c.getCreatedBy());
			ps.setString(5, c.getIpAddress());
			ps.setInt(6, c.getCountryId());
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
	public void deleteCountry(int countryid) {
		logger.info("+++++ DELETE COUNTRY +++++");		
		String sql = "delete from country where country_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, countryid);
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
	public Country getCountryDetailById(int countryid) {
		logger.info("+++++ GET COUNTRY DETAIL BY ID +++++");
		Country country = null;
		String sql = "select country_id, country_name, country_code, country_dialing_code from country where country_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, countryid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				country = new Country(rs.getInt("country_id"), rs.getString("country_name"), rs.getString("country_code"),
						rs.getString("country_dialing_code"));
			}
			rs.close();
			ps.close();
			return country;
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
