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

import com.ui.dao.FinancialYearDAO;
import com.ui.model.FinancialYear;

public class FinancialYearDAOImpl implements FinancialYearDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(FinancialYearDAOImpl.class);

	@Override
	public List<FinancialYear> getAllFinancialYears() {
		logger.info("+++++ GET FINANCIAL YEAR +++++");
		List<FinancialYear> sta = new ArrayList<FinancialYear>();
		String s = "y";
		String sql = "select financial_year_id, financial_year_start_date, financial_year_end_date, financial_year_code, defaultt from financial_year where status=? order by financial_year_code";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			FinancialYear financialYear = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				financialYear = new FinancialYear(rs.getInt("financial_year_id"),
						rs.getString("financial_year_start_date"), rs.getString("financial_year_end_date"),
						rs.getString("financial_year_code"), rs.getString("defaultt"));
				sta.add(financialYear);
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
	public List<FinancialYear> getAllFinancialYearsByPage(int pagesize, int startindex) {
		logger.info("+++++ GET ALL FINANCIAL YEAR BY PAGE +++++");
		List<FinancialYear> sta = new ArrayList<FinancialYear>();
		String s = "y";
		String sql = "select financial_year_id, financial_year_start_date, financial_year_end_date, financial_year_code, defaultt from financial_year where status=? order by financial_year_code limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			FinancialYear financialYear = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				financialYear = new FinancialYear(rs.getInt("financial_year_id"),
						rs.getString("financial_year_start_date"), rs.getString("financial_year_end_date"),
						rs.getString("financial_year_code"), rs.getString("defaultt"));
				sta.add(financialYear);
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
	public List<FinancialYear> searchFinancialYears(String keyword) {
		logger.info("+++++ SERACH FINANCIAL YEAR +++++");
		List<FinancialYear> sta = new ArrayList<FinancialYear>();
		String s = "y";
		String sql = "select financial_year_id, financial_year_start_date, financial_year_end_date, financial_year_code, defaultt from financial_year where status=? and concat(financial_year_start_date, '', financial_year_end_date, '', financial_year_code) like ? order by financial_year_code";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			FinancialYear financialYear = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				financialYear = new FinancialYear(rs.getInt("financial_year_id"),
						rs.getString("financial_year_start_date"), rs.getString("financial_year_end_date"),
						rs.getString("financial_year_code"), rs.getString("defaultt"));
				sta.add(financialYear);
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
	public String addFinancialYear(FinancialYear f) {
		logger.info("+++++ ADD FINANCIAL YEAR +++++");
		String sql = "insert into financial_year (financial_year_start_date, financial_year_end_date, financial_year_code, defaultt, status, created_by, ip_address) values (STR_TO_DATE(?,'%d/%m/%Y'),STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, f.getFinancialYearStartDate());
			ps.setString(2, f.getFinancialYearEndDate());
			ps.setString(3, f.getFinancialYearCode());
			ps.setString(4, f.getDefaultt());
			ps.setString(5, f.getStatus());
			ps.setInt(6, f.getCreatedBy());
			ps.setString(7, f.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate financial year code!";
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
	public String editFinancialYear(FinancialYear f) {
		logger.info("+++++ EDIT FINANCIAL YEAR +++++");
		String sql = "update financial_year set financial_year_start_date=STR_TO_DATE(?,'%d/%m/%Y'), financial_year_end_date=STR_TO_DATE(?,'%d/%m/%Y'), financial_year_code=?, created_by=?, ip_address=? where financial_year_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, f.getFinancialYearStartDate());
			ps.setString(2, f.getFinancialYearEndDate());
			ps.setString(3, f.getFinancialYearCode());
			ps.setInt(4, f.getCreatedBy());
			ps.setString(5, f.getIpAddress());
			ps.setInt(6, f.getFinancialYearId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not updated! Duplicate financial year code!";
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
	public void deleteFinancialYear(int financialyearid) {
		logger.info("+++++ DELETE FINANCIAL YEAR CODE +++++");
		String sql = "delete from financial_year where financial_year_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(2, financialyearid);
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

	public void disableDefaultFinancialYear(FinancialYear f) {
		logger.info("+++++ DISABLE DEFAULT FINANCIAL YEAR CODE +++++");
		String sql = "update financial_year set defaultt=?, created_by=?, ip_address=? where defaultt=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "n");
			ps.setInt(2, f.getCreatedBy());
			ps.setString(3, f.getIpAddress());
			ps.setString(4, "y");
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

	public void addDefaultFinancialYear(FinancialYear f) {
		logger.info("+++++ ADD DEFAULT FINANCIAL YEAR +++++");
		String sql = "update financial_year set defaultt=?, created_by=?, ip_address=? where financial_year_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "y");
			ps.setInt(2, f.getCreatedBy());
			ps.setString(3, f.getIpAddress());
			ps.setInt(4, f.getFinancialYearId());
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
	public int getCurrentFinancialYearId() {
		logger.info("+++++ GET CURRENT FINANCIAL YEAR ID +++++");
		int financialyearid = 0;
		String sql = "select financial_year_id from financial_year where defaultt=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "y");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				financialyearid = rs.getInt("financial_year_id");
			}
			rs.close();
			ps.close();
			return financialyearid;
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

	public String getCurrentFinancialYearCode() {
		logger.info("+++++ GET CURRENT YEAR FINANCIAL YEAR CODE ++++++");
		String s = "y";
		String sql = "select financial_year_code from financial_year where status=? and defaultt=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, s);
			String f = "";
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				f = rs.getString("financial_year_code");
			}
			rs.close();
			ps.close();

			return f;
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
