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

import com.ui.dao.FeedbackDAO;
import com.ui.model.Feedback;

public class FeedbackDAOImpl implements FeedbackDAO{
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(FeedbackDAOImpl.class);
	
	
	
	@Override
    public List<Feedback> getFeedbackByPage(int pagesize, int startindex) {
        logger.info("+++++ GET FEEDBACK BY PAGE +++++");
        List<Feedback> sta = new ArrayList<Feedback>();
        String s = "y";
        String sql = "select f.feedback_id, f.user_id, f.user_first_name, f.user_last_name, f.image, f.orgnaization_name, f.feedback, u.first_name, u.last_name from feedback f left join user u on f.user_id = u.user_id where f.status=? order by user_first_name limit ?,?";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, startindex);
            ps.setInt(3, pagesize);
            Feedback feedback = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              feedback = new Feedback();
              feedback.setFeedbackId(rs.getInt("feedback_id"));
              feedback.setUserId(rs.getInt("user_id"));
              feedback.setUserFirstName(rs.getString("user_first_name"));
              feedback.setUserLastName(rs.getString("user_last_name"));
              feedback.setImage(rs.getString("image"));
              feedback.setOrgnaizationName(rs.getString("orgnaization_name"));
              feedback.setFeedback(rs.getString("feedback"));
              feedback.setFirstName(rs.getString("first_name"));
              feedback.setLastName(rs.getString("last_name"));

                sta.add(feedback);
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
	public List<Feedback> getAllFeedback() {
		logger.info("+++++ GET ALL FEEDBACK +++++");
		List<Feedback> sta = new ArrayList<Feedback>();
		String s = "y";
		String sql = "select f.feedback_id, f.user_id, f.user_first_name, f.user_last_name, f.image, f.orgnaization_name, f.feedback, u.first_name, u.last_name from feedback f left join user u on f.user_id = u.user_id where f.status=? order by user_first_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Feedback feedback = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			  feedback = new Feedback();
			        feedback.setFeedbackId(rs.getInt("feedback_id"));
			        feedback.setUserId(rs.getInt("user_id"));
			        feedback.setUserFirstName(rs.getString("user_first_name"));
			        feedback.setUserLastName(rs.getString("user_last_name"));
			        feedback.setImage(rs.getString("image"));
			        feedback.setOrgnaizationName(rs.getString("orgnaization_name"));
			        feedback.setFeedback(rs.getString("feedback"));
			        feedback.setFirstName(rs.getString("first_name"));
			        feedback.setLastName(rs.getString("last_name"));

				sta.add(feedback);
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
	public List<Feedback> searchFeedback(String keyword) {
		logger.info("+++++ SEARCH FEEDBACK +++++");
		List<Feedback> sta = new ArrayList<Feedback>();
		String s = "y";
		String sql = "select f.feedback_id, f.user_id, f.user_first_name, f.user_last_name, f.image, f.orgnaization_name, f.feedback, u.first_name, u.last_name from feedback f left join user u on f.user_id = u.user_id where f.status=? and (concat(f.user_first_name) like ? or concat(f.user_last_name) like ? or concat(f.orgnaization_name) like ? or concat(u.first_name) like ? or concat(u.last_name) like ?) order by user_first_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			ps.setString(3, '%' + keyword + '%');
			ps.setString(4, '%' + keyword + '%');
			ps.setString(5, '%' + keyword + '%');
			ps.setString(6, '%' + keyword + '%');
			Feedback feedback = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			  feedback = new Feedback();
              feedback.setFeedbackId(rs.getInt("feedback_id"));
              feedback.setUserId(rs.getInt("user_id"));
              feedback.setUserFirstName(rs.getString("user_first_name"));
              feedback.setUserLastName(rs.getString("user_last_name"));
              feedback.setImage(rs.getString("image"));
              feedback.setOrgnaizationName(rs.getString("orgnaization_name"));
              feedback.setFeedback(rs.getString("feedback"));
              feedback.setFirstName(rs.getString("first_name"));
              feedback.setLastName(rs.getString("last_name"));
			
              sta.add(feedback);
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
	public String addFeedback(Feedback c) {
		logger.info("+++++ ADD FEEDBACK +++++");
		String sql = "insert into feedback (user_id, user_first_name, user_last_name, image, orgnaization_name, feedback, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getUserId());			
			ps.setString(2, c.getFirstName());
			ps.setString(3, c.getLastName());
			ps.setString(4, c.getImage());
			ps.setString(5, c.getOrgnaizationName());
			ps.setString(6, c.getFeedback());			
			ps.setString(7, c.getStatus());
			ps.setInt(8, c.getCreatedBy());
			ps.setString(9, c.getIpAddress());
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
    public Feedback getFeedbackById(int feedbackid) {
        logger.info("+++++ GET FEEDBACK BY ID +++++");
        Feedback feedback = null;
        String sql = "select feedback_id, user_id, user_first_name, user_last_name, image, orgnaization_name, feedback from feedback where feedback_id=?";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, feedbackid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              feedback = new Feedback();
              feedback.setFeedbackId(rs.getInt("feedback_id"));
              feedback.setUserId(rs.getInt("user_id"));
              feedback.setUserFirstName(rs.getString("user_first_name"));
              feedback.setUserLastName(rs.getString("user_last_name"));
              feedback.setImage(rs.getString("image"));
              feedback.setOrgnaizationName(rs.getString("orgnaization_name"));
              feedback.setFeedback(rs.getString("feedback"));
            }
            rs.close();
            ps.close();

            return feedback;
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
    public String editFeedback(Feedback c) {
        logger.info("+++++ EDIT FEEDBACK +++++");
        String sql = "update feedback set user_id=?, user_first_name=?, user_last_name=?, image=?, orgnaization_name=?, feedback=?, created_by=?, ip_address=? where feedback_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getUserId());
            ps.setString(2, c.getFirstName());           
            ps.setString(3, c.getLastName());
            ps.setString(4, c.getImage());
            ps.setString(5, c.getOrgnaizationName());
            ps.setString(6, c.getFeedback());
            ps.setInt(7, c.getCreatedBy());
            ps.setString(8, c.getIpAddress());
            ps.setInt(9, c.getFeedbackId());
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
    public void deleteFeedback(int feedbackid) {
        logger.info("+++++ DELETE FEEDBACK +++++");
        String status = "n";
        String sql = "update feedback set status=? where feedback_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, feedbackid);
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
