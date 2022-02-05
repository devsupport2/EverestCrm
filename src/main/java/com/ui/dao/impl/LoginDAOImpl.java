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

import com.ui.dao.LoginDAO;
import com.ui.model.User;

public class LoginDAOImpl implements LoginDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(LoginDAOImpl.class);

	@Override
	public List<User> checkAdminLogin(String email, String password) {
		logger.info("Inside Check Admin Login Impl");

		List<User> User = new ArrayList<User>();

		String sql = "select user_id, first_name, middle_name, last_name, profile_picture, mobile_number, email, user_type_id, access_project, access_property, access_enquiry, access_payment, access_user from user where status=? and email=? and password=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, "y");
			ps.setString(2, email);
			ps.setString(3, password);

			User user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("middle_name"),
						rs.getString("last_name"), rs.getString("profile_picture"), rs.getString("mobile_number"),
						rs.getString("email"), rs.getInt("user_type_id"), rs.getString("access_project"), rs.getString("access_property"), rs.getString("access_enquiry"), rs.getString("access_payment"), rs.getString("access_user"));
				User.add(user);
			}
			rs.close();
			ps.close();
			return User;
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
	public User isRegistered(String email) {
		logger.info("+++++ IS USER REGISTERED +++++");
		User user = null;
		String sql = "select user_id, first_name, last_name, email from user where status=? and email=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "y");
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"));
			}
			rs.close();
			ps.close();
			return user;
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

	public String resetPassword(User u) {
		logger.info("+++++ RESET PASSWORD IMPL +++++");
		String sql = "update user set password=?, ip_address=? where email=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getIpAddress());
			ps.setString(3, u.getEmail());
			ps.executeUpdate();
			return "Success";
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
	public User checkPassword(int userid, String currentpassword) {
		logger.info("+++++ IS USER REGISTERED +++++");
		User user = null;
		String sql = "select user_id, first_name, last_name, email from user where status=? and user_id=? and password=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "y");
			ps.setInt(2, userid);
			ps.setString(3, currentpassword);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"));
			}
			rs.close();
			ps.close();
			return user;
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