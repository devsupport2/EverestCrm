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

import com.ui.dao.WorkstatusDAO;
import com.ui.model.Achievement;
import com.ui.model.Workstatus;
import com.ui.model.WorkstatusImage;

public class WorkStatusDAOImpl implements WorkstatusDAO {
  
  JdbcTemplate jdbcTemplate;
  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
  }

  private static final Logger logger = LoggerFactory.getLogger(WorkStatusDAOImpl.class);

  
  @Override
  public List<Workstatus> getAllWorkstatus() {
      logger.info("+++++ GET ALL WORKSTATUS +++++");
      List<Workstatus> sta = new ArrayList<Workstatus>();
      String s = "y";
      String sql = "select w.workstatus_id, w.project_id, w.title, w.subtitle, p.project_title from workstatus w left join project p on w.project_id=p.project_id where w.status=? order by w.workstatus_id desc";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          Workstatus c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              c = new Workstatus();
              
              c.setWorkstatusId(rs.getInt("workstatus_id"));
              c.setProjectId(rs.getInt("project_id"));
              c.setTitle(rs.getString("title"));
              c.setSubtitle(rs.getString("subtitle"));
              c.setProjectTitle(rs.getString("project_title"));
              
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
  public List<Workstatus> getWorkstatusByPage(int pagesize, int startindex) {
      logger.info("+++++ GET ALL WORKSTATUS BY PAGE +++++");
      List<Workstatus> sta = new ArrayList<Workstatus>();
      String s = "y";
      String sql = "select w.workstatus_id, w.project_id, w.title, w.subtitle, p.project_title from workstatus w left join project p on w.project_id=p.project_id where w.status=? order by w.workstatus_id desc limit ?,?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setInt(2, startindex);
          ps.setInt(3, pagesize);
          Workstatus c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            c = new Workstatus();
            
            c.setWorkstatusId(rs.getInt("workstatus_id"));
            c.setProjectId(rs.getInt("project_id"));
            c.setTitle(rs.getString("title"));
            c.setSubtitle(rs.getString("subtitle"));
            c.setProjectTitle(rs.getString("project_title"));
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
  public Workstatus getWorkstatusDetailsById(int id) {
      logger.info("+++++ GET WORKSTATUS DETAILS BY ID +++++");
      String s = "y";
      String sql = "select workstatus_id, project_id, title, subtitle, date, description from workstatus where workstatus_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, id);
          Workstatus c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            c = new Workstatus();
            
            c.setWorkstatusId(rs.getInt("workstatus_id"));
            c.setProjectId(rs.getInt("project_id"));
            c.setTitle(rs.getString("title"));
            c.setSubtitle(rs.getString("subtitle"));
            c.setDate(rs.getString("date"));
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
  public List<WorkstatusImage> getAllImageById(int id) {
      logger.info("+++++ GET ALL WORKSTATUS IMAGE BY ID +++++");
      List<WorkstatusImage> sta = new ArrayList<WorkstatusImage>();
      String s = "y";
      String sql = "select workstatus_image_id, workstatus_id, title, subtitle, image from workstatus_image where workstatus_id=? order by title";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, id);
          WorkstatusImage c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            c = new WorkstatusImage();
            
            c.setWorkstatusImageId(rs.getInt("workstatus_image_id"));
            c.setWorkstatusId(rs.getInt("workstatus_id"));
            c.setTitle(rs.getString("title"));
            c.setSubtitle(rs.getString("subtitle"));
            c.setImage(rs.getString("image"));
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
  public String addWorkStatus(Workstatus sr) {
      logger.info("+++++ ADD WORK STATUS +++++");
      String sql = "insert into workstatus (project_id, title, subtitle, date, description, status, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, sr.getProjectId());
          ps.setString(2, sr.getTitle());
          ps.setString(3, sr.getSubtitle());
          ps.setString(4, sr.getDate());
          ps.setString(5, sr.getDescription());
          ps.setString(6, sr.getStatus());
          ps.setInt(7, sr.getCreatedBy());
          ps.setString(8, sr.getIpAddress());
          ps.executeUpdate();
          return "Success";
      } catch (SQLException e) {
          return "Data not added!";
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
  public String editWorkStatus(Workstatus sr) {
      logger.info("+++++ EDIT WORK STATUS +++++");
      String sql = "update workstatus set project_id=?, title=?, subtitle=?, date=?, description=?, created_by=?, ip_address=? where workstatus_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, sr.getProjectId());
          ps.setString(2, sr.getTitle());
          ps.setString(3, sr.getSubtitle());
          ps.setString(4, sr.getDate());
          ps.setString(5, sr.getDescription());
          ps.setInt(6, sr.getCreatedBy());
          ps.setString(7, sr.getIpAddress());
          ps.setInt(8, sr.getWorkstatusId());
          ps.executeUpdate();
          return "Success";
      } catch (SQLException e) {
          return "Data not added!";
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
  public int getLastWorkstatusId() {
      logger.info("+++++ GET LAST WORKSTATUS ID +++++");
      String sql = "select workstatus_id from workstatus order by workstatus_id desc limit 0,1";
      int id = 0;
      Connection conn = null;

      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              id = rs.getInt("workstatus_id");
          }
          rs.close();
          ps.close();
          return id;
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
  public String addImage(WorkstatusImage wi) {
      logger.info("+++++ ADD WORKSTATUS IMAGE +++++");
      String sql = "insert into workstatus_image (workstatus_id, title, subtitle, image, created_by, ip_address) values (?,?,?,?,?,?)";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, wi.getWorkstatusId());
          ps.setString(2, wi.getTitle());
          ps.setString(3, wi.getSubtitle());           
          ps.setString(4, wi.getImage());
          ps.setInt(5, wi.getCreatedBy());
          ps.setString(6, wi.getIpAddress());
          ps.executeUpdate();
          return "Success";
      } catch (SQLException e) {
          return "Data not added!";
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
  public void deleteWorkstatus(int id) {
      logger.info("+++++ DELETE WORKSTATUS +++++");
      String s = "n";
      String sql = "update workstatus set status=? where workstatus_id=?";
      Connection conn = null;
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
  
  
  @Override
  public void deleteWorkstatusImage(int workstatusImageId) {
      logger.info("+++++ DELETE WORKSTATUS IMAGE +++++");
      
      String sql = "delete from workstatus_image where workstatus_image_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, workstatusImageId);
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
