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

import com.ui.dao.AchievementDAO;
import com.ui.model.Achievement;
import com.ui.model.RealestateType;

public class AchievementDAOImpl implements AchievementDAO {
  JdbcTemplate jdbcTemplate;
  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
  }

  private static final Logger logger = LoggerFactory.getLogger(AchievementDAOImpl.class);
  
  
  @Override
  public List<Achievement> getAchievement() {
      logger.info("+++++ GET ALL Achievement +++++");
      List<Achievement> sta = new ArrayList<Achievement>();
      String s = "y";
      String sql = "select achievement_id, achievement_title, subtitle, image, description from achievement where status=? order by achievement_title";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          Achievement c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              c = new Achievement();
              
              c.setAchievementId(rs.getInt("achievement_id"));
              c.setTitle(rs.getString("achievement_title"));
              c.setSubtitle(rs.getString("subtitle"));
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
  public List<Achievement> getAchievementByPage(int pagesize, int startindex) {
      logger.info("+++++ GET ALL ACHIEVEMENT BY PAGE +++++");
      List<Achievement> sta = new ArrayList<Achievement>();
      String s = "y";
      String sql = "select achievement_id, achievement_title, subtitle, image, description from achievement where status=? order by achievement_title limit ?,?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setInt(2, startindex);
          ps.setInt(3, pagesize);
          Achievement c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            c = new Achievement();
            
            c.setAchievementId(rs.getInt("achievement_id"));
            c.setTitle(rs.getString("achievement_title"));
            c.setSubtitle(rs.getString("subtitle"));
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
  public List<Achievement> searchAchievement(String keyword) {
      logger.info("+++++ SEARCH CATEGORY +++++");
      List<Achievement> sta = new ArrayList<Achievement>();
      String s = "y";
      String sql = "select achievement_id, achievement_title, subtitle, image, description from achievement where status=? and concat(achievement_title, '', subtitle, '',description) like ? order by achievement_title";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setString(2, '%' + keyword + '%');
          Achievement c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            c = new Achievement();
            
            c.setAchievementId(rs.getInt("achievement_id"));
            c.setTitle(rs.getString("achievement_title"));
            c.setSubtitle(rs.getString("subtitle"));
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
  public String addAchivement(Achievement a) {
      logger.info("+++++ ADD ACHIEVEMENT +++++");
      String sql = "insert into achievement (achievement_title, subtitle, image, description, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, a.getTitle());
          ps.setString(2, a.getSubtitle());
          ps.setString(3, a.getImage());
          ps.setString(4, a.getDescription());
          ps.setString(5, a.getStatus());
          ps.setInt(6, a.getCreatedBy());
          ps.setString(7, a.getIpAddress());
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
  public String editAchivement(Achievement c) {
      logger.info("+++++ EDIT ACHIEVEMENT +++++");
      String sql = "update achievement set achievement_title=?, subtitle=?, image=?, description=?, created_by=?, ip_address=? where achievement_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, c.getTitle());
          ps.setString(2, c.getSubtitle());
          ps.setString(3, c.getImage());
          ps.setString(4, c.getDescription());            
          ps.setInt(5, c.getCreatedBy());
          ps.setString(6, c.getIpAddress());
          ps.setInt(7, c.getAchievementId());
          ps.executeUpdate();
          return "Success";
      } catch (SQLException e) {
          return "Data not updated!";
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
  public Achievement getAchievementDetailById(int id) {
      logger.info("+++++ GET ACHIEVEMENT DETAIL BY ID +++++");
      Achievement c = null;
      String sql = "select achievement_id, achievement_title, subtitle, image, description from achievement where achievement_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, id);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            c = new Achievement();
            
            c.setAchievementId(rs.getInt("achievement_id"));
            c.setTitle(rs.getString("achievement_title"));
            c.setSubtitle(rs.getString("subtitle"));
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
  
  
  @Override
  public void deleteAchievement(int id) {
      logger.info("+++++ DELETE ACHIEVEMENT +++++");     
      String sql = "update achievement set status=? where achievement_id=?";
      Connection conn = null;
      String s = "n";
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);          
          ps.setString(1, s);
          ps.setInt(2, id);
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
  
}//end
