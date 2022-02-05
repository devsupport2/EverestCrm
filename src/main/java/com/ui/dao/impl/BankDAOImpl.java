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

import com.ui.dao.BankDAO;
import com.ui.model.Bank;
import com.ui.model.RealestateType;

public class BankDAOImpl implements BankDAO{

  JdbcTemplate jdbcTemplate;
  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
  }

  private static final Logger logger = LoggerFactory.getLogger(BankDAOImpl.class);
  
  
  @Override
  public List<Bank> getAllBank() {
      logger.info("+++++ GET ALL BANK +++++");
      List<Bank> sta = new ArrayList<Bank>();
      String s = "y";
      String sql = "select bank_id, bank_name, bank_code, image, description from bank where status=? order by bank_name";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          Bank c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              c = new Bank();               
              c.setBankId(rs.getInt("bank_id"));
              c.setBankName(rs.getString("bank_name"));
              c.setBankCode(rs.getString("bank_code"));
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
  public List<Bank> getBankByPage(int pagesize, int startindex) {
      logger.info("+++++ GET BANK BY PAGE +++++");
      List<Bank> sta = new ArrayList<Bank>();
      String s = "y";
      String sql = "select bank_id, bank_name, bank_code, image, description from bank where status=? order by bank_name limit ?,?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setInt(2, startindex);
          ps.setInt(3, pagesize);
          Bank c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            c = new Bank();               
            c.setBankId(rs.getInt("bank_id"));
            c.setBankName(rs.getString("bank_name"));
            c.setBankCode(rs.getString("bank_code"));
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
  public List<Bank> searchBank(String keyword) {
      logger.info("+++++ SEARCH BANK +++++");
      List<Bank> sta = new ArrayList<Bank>();
      String s = "y";
      String sql = "select bank_id, bank_name, bank_code, image, description from bank where status=? and concat(bank_name, '', bank_code) like ? order by bank_name";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setString(2, '%' + keyword + '%');
          Bank c = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            c = new Bank();               
            c.setBankId(rs.getInt("bank_id"));
            c.setBankName(rs.getString("bank_name"));
            c.setBankCode(rs.getString("bank_code"));
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
  public Bank getBankDetailById(int id) {
      logger.info("+++++ GET BANK DETAIL BY ID +++++");
      Bank c = null;
      String sql = "select bank_id, bank_name, bank_code, image, description from bank where bank_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, id);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              c = new Bank();               
              c.setBankId(rs.getInt("bank_id"));
              c.setBankName(rs.getString("bank_name"));
              c.setBankCode(rs.getString("bank_code"));
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
  public String addBank(Bank c) {
      logger.info("+++++ ADD BANK +++++");
      String sql = "insert into bank (bank_name, bank_code, image, description, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, c.getBankName());
          ps.setString(2, c.getBankCode());
          ps.setString(3, c.getImage());
          ps.setString(4, c.getDescription());
          ps.setString(5, c.getStatus());
          ps.setInt(6, c.getCreatedBy());
          ps.setString(7, c.getIpAddress());
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
  public String editBank(Bank c) {
      logger.info("+++++ EDIT BANK +++++");
      String sql = "update bank set bank_name=?, bank_code=?, image=?, description=?, created_by=?, ip_address=? where bank_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, c.getBankName());
          ps.setString(2, c.getBankCode());
          ps.setString(3, c.getImage());
          ps.setString(4, c.getDescription());
          ps.setInt(5, c.getCreatedBy());
          ps.setString(6, c.getIpAddress());
          ps.setInt(7, c.getBankId());
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
  public void deleteBank(int id) {
      logger.info("+++++ DELETE Bank +++++");     
      String sql = "delete from bank where bank_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);          
          ps.setInt(1, id);
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
