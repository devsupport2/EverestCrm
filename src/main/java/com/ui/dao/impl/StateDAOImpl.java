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

import com.ui.dao.StateDAO;
import com.ui.model.State;

public class StateDAOImpl implements StateDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(StateDAOImpl.class);

	@Override
	public List<State> getAllStates() {
		logger.info("+++++ GET ALL STATES +++++");
		List<State> sta = new ArrayList<State>();
		String s = "y";
		String sql = "select s.state_id, s.state_name, s.state_code, s.country_id, c.country_name from state s, country c where s.country_id=c.country_id and s.status=? order by s.state_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			State state = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				state = new State(rs.getInt("state_id"), rs.getString("state_name"), rs.getString("state_code"),
						rs.getInt("country_id"), rs.getString("country_name"));
				sta.add(state);
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
	public List<State> getAllStatesByPage(int pagesize, int startindex) {
		logger.info("+++++ GET STATES BY PAGE +++++");
		List<State> sta = new ArrayList<State>();
		String s = "y";
		String sql = "select s.state_id, s.state_name, s.state_code, s.country_id, c.country_name from state s, country c where s.country_id=c.country_id and s.status=? order by s.state_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			State state = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				state = new State(rs.getInt("state_id"), rs.getString("state_name"), rs.getString("state_code"),
						rs.getInt("country_id"), rs.getString("country_name"));

				sta.add(state);
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
	public List<State> searchStates(String keyword) {
		logger.info("+++++ SERACH STATES +++++");
		List<State> sta = new ArrayList<State>();
		String s = "y";
		String sql = "select s.state_id, s.state_name, s.state_code, s.country_id, c.country_name from state s, country c where s.country_id=c.country_id and s.status=? and Concat(s.state_name, '', s.state_code, '', c.country_name) like ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			State state = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				state = new State(rs.getInt("state_id"), rs.getString("state_name"), rs.getString("state_code"),
						rs.getInt("country_id"), rs.getString("country_name"));
				sta.add(state);
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
	public List<State> getStateByCountryId(int countryId) {
		logger.info("+++++ GET STATES BY COUNTRY ID +++++");
		List<State> State = new ArrayList<State>();
		String s = "y";
		String sql = "select s.state_id, s.state_name, s.state_code, s.country_id, c.country_name from state s, country c where s.country_id=c.country_id and s.status=? and s.country_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setLong(2, countryId);
			State state = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				state = new State(rs.getInt("state_id"), rs.getString("state_name"), rs.getString("state_code"),
						rs.getInt("country_id"), rs.getString("country_name"));

				State.add(state);
			}
			rs.close();
			ps.close();

			return State;
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
	public void addState(State s) {
		logger.info("+++++ ADD STATE +++++");
		String sql = "insert into state (state_name, state_code, country_id, status, created_by, ip_address) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getStateName());
			ps.setString(2, s.getStateCode());
			ps.setInt(3, s.getCountryId());
			ps.setString(4, s.getStatus());
			ps.setInt(5, s.getCreatedBy());
			ps.setString(6, s.getIpAddress());
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
	public void editState(State s) {
		logger.info("+++++ EDIT STATE +++++");
		String sql = "update state set state_name=?, state_code=?, country_id=?, created_by=?, ip_address=? where state_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getStateName());
			ps.setString(2, s.getStateCode());
			ps.setInt(3, s.getCountryId());
			ps.setInt(4, s.getCreatedBy());
			ps.setString(5, s.getIpAddress());
			ps.setInt(6, s.getStateId());
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
	public void deleteState(int stateid) {
		logger.info("+++++ DELETE STATE +++++");
		String sql = "delete from state where state_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, stateid);
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
	public State getStateDetailById(int stateid) {
		logger.info("+++++ GET STATE DETAIL BY ID +++++");
		State state = null;
		String sql = "select state_name, state_code, country_id from state where state_id = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, stateid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				state = new State(rs.getString("state_name"), rs.getString("state_code"), rs.getInt("country_id"));
			}
			rs.close();
			ps.close();

			return state;
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
	public State getStateDetailByName(String statename) {
		logger.info("+++++ GET STATE DETAIL BY NAME +++++");
		State state = null;		
		String sql = "select state_id, state_code, country_id from state where state_name = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, statename);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				state = new State(rs.getInt("state_id"), rs.getString("state_code"),
						rs.getInt("country_id"));			
			}
			rs.close();
			ps.close();

			return state;
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
