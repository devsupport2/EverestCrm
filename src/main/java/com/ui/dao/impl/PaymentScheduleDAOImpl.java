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

import com.ui.dao.PaymentScheduleDAO;
import com.ui.model.Payment;

public class PaymentScheduleDAOImpl implements PaymentScheduleDAO {
  JdbcTemplate jdbcTemplate;
  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
  }

  private static final Logger logger = LoggerFactory.getLogger(PaymentScheduleDAOImpl.class);

  @Override
  public List<Payment> getAllPayments() {
      logger.info("+++++ GET ALL PAYMENTS +++++");
      List<Payment> sta = new ArrayList<Payment>();
      String s = "y";
      /*String sql = "select payment_id, payment_label, payment_schedule, percentage from payment where status=? order by payment_id";*/
      String sql = "select ps.project_id, p.project_title from payment ps, project p where ps.status=? and ps.project_id = p.project_id group by ps.project_id";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          Payment payment = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            payment = new Payment(rs.getInt("project_id"), rs.getString("project_title"));
           /* payment = new Payment(rs.getInt("payment_id"), rs.getString("payment_label"),
                  rs.getString("payment_schedule"), rs.getString("percentage"));*/

              sta.add(payment);
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
  public List<Payment> getAllPaymentByPage(int pagesize, int startindex) {
      logger.info("+++++ GET ALL PAYMENT BY PAGE +++++");
      List<Payment> sta = new ArrayList<Payment>();
      String s = "y";
      String sql = "select ps.project_id, p.project_title from payment ps, project p where ps.status=? and ps.project_id = p.project_id group by ps.project_id limit ?,?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setInt(2, startindex);
          ps.setInt(3, pagesize);
          Payment payment = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            payment = new Payment(rs.getInt("project_id"), rs.getString("project_title"));
              sta.add(payment);
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
  public List<Payment> searchPayments(String keyword) {
      logger.info("Inside Search Payment Schedule Impl");
      List<Payment> sta = new ArrayList<Payment>();
      String s = "y";
      String sql = "select ps.project_id, p.project_title from payment ps, project p where ps.status=? and ps.project_id = p.project_id and concat(project_title) like ? group by ps.project_id";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setString(2, '%' + keyword + '%');
          Payment payment = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            payment = new Payment(rs.getInt("project_id"), rs.getString("project_title"));
              sta.add(payment);
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
  public String addPayment(Payment p) {
      logger.info("+++++ ADD PAYMENT SCHEDULE +++++");
      String sql = "insert into payment (project_id, payment_label, payment_schedule, percentage, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, p.getProjectId());
          ps.setString(2, p.getPaymentLabel());
          ps.setString(3, p.getPaymentSchedule());
          ps.setString(4, p.getPercentage());
          ps.setString(5, p.getStatus());
          ps.setInt(6, p.getCreatedBy());
          ps.setString(7, p.getIpAddress());
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
  public String editPayment(Payment c) {
      logger.info("+++++ EDIT PAYMENT +++++");
      String sql = "update payment set payment_label=?, payment_schedule=?, percentage=?, created_by=?, ip_address=? where payment_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, c.getPaymentLabel());
          ps.setString(2, c.getPaymentSchedule());
          ps.setString(3, c.getPercentage());
          ps.setInt(4, c.getCreatedBy());
          ps.setString(5, c.getIpAddress());
          ps.setInt(6, c.getPaymentId());
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
  public void deletePayment(int paymentid) {
      logger.info("+++++ DELETE PAYMENT SCHEDULE +++++");      
      String sql = "delete from payment where payment_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);          
          ps.setInt(1, paymentid);
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
  public List<Payment> getPaymentDetailById(int projectid) {
      logger.info("+++++ GET ALL PAYMENT BY ID +++++");
      List<Payment> sta = new ArrayList<Payment>();
      String s = "y";
      String sql = "select payment_id, project_id, payment_label, payment_schedule, percentage from payment where status=? and  project_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setInt(2, projectid);          
          Payment payment = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            payment = new Payment(rs.getInt("payment_id"), rs.getInt("project_id"), rs.getString("payment_label"), rs.getString("payment_schedule"), rs.getString("percentage"));
              sta.add(payment);
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
  public Payment getProjectIdById(int projectid) {
      logger.info("+++++ GET PROJECT ID BY ID +++++");      
      String s = "y";
      String sql = "select project_id from payment where status=? and  project_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setInt(2, projectid);          
          Payment payment = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            payment = new Payment(rs.getInt("project_id"));
             
          }
          rs.close();
          ps.close();
          return payment;
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
