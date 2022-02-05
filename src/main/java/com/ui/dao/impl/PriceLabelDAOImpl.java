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

import com.ui.dao.PriceLabelDAO;
import com.ui.model.PriceLabel;

public class PriceLabelDAOImpl implements PriceLabelDAO{

  JdbcTemplate jdbcTemplate;
  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
  }

  private static final Logger logger = LoggerFactory.getLogger(PriceLabelDAOImpl.class);

  @Override
  public List<PriceLabel> getAllPrices() {
      logger.info("***** GET ALL PRICES *****");
      List<PriceLabel> sta = new ArrayList<PriceLabel>();
      String s = "y";
      String sql = "select price_label_id, price_label, description from price_label where status=? order by price_label";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          PriceLabel pl = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              pl = new PriceLabel();
              pl.setPriceLabelId(rs.getInt("price_label_id"));
              pl.setPriceLabel(rs.getString("price_label"));
              pl.setDescription(rs.getString("description"));
              
              sta.add(pl);
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
  public List<PriceLabel> getPricesByPage(int pagesize, int startindex) {
      logger.info("+++++ GET PRICE LABEL BY PAGE +++++");
      List<PriceLabel> sta = new ArrayList<PriceLabel>();
      String s = "y";
      String sql = "select price_label_id, price_label, description from price_label where status=? order by price_label limit ?,?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setInt(2, startindex);
          ps.setInt(3, pagesize);
          PriceLabel pl = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            pl = new PriceLabel();
            pl.setPriceLabelId(rs.getInt("price_label_id"));
            pl.setPriceLabel(rs.getString("price_label"));
            pl.setDescription(rs.getString("description"));
            
            sta.add(pl);
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
  public List<PriceLabel> searchPrices(String keyword) {
      logger.info("+++++ SERACH PRICE LABEL +++++");
      List<PriceLabel> sta = new ArrayList<PriceLabel>();
      String s = "y";
      String sql = "select price_label_id, price_label, description from price_label where status=? and concat(price_label) like ? order by price_label";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);
          ps.setString(2, '%' + keyword + '%');
          PriceLabel pl = null;
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
            pl = new PriceLabel();
            pl.setPriceLabelId(rs.getInt("price_label_id"));
            pl.setPriceLabel(rs.getString("price_label"));
            pl.setDescription(rs.getString("description"));
            
            sta.add(pl);
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
  public String addPriceLabel(PriceLabel t) {
      logger.info("+++++ ADD PRICE LABEL +++++");
      String sql = "insert into price_label (price_label, description, status, created_by, ip_address) values (?,?,?,?,?)";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, t.getPriceLabel());
          ps.setString(2, t.getDescription());
          ps.setString(3, t.getStatus());
          ps.setInt(4, t.getCreatedBy());
          ps.setString(5, t.getIpAddress());
          ps.executeUpdate();
          return "Success";
      } catch (SQLException e) {
          return "Data not added! Duplicate Price Label name!";
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
  public String editPriceLabel(PriceLabel t) {
      logger.info("+++++ EDIT PRICE LABEL +++++");
      String sql = "update price_label set price_label=?, description=?, created_by=?, ip_address=? where price_label_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, t.getPriceLabel());
          ps.setString(2, t.getDescription());
          ps.setInt(3, t.getCreatedBy());
          ps.setString(4, t.getIpAddress());
          ps.setInt(5, t.getPriceLabelId());
          ps.executeUpdate();
          return "Success";
      } catch (SQLException e) {
          return "Data not added! Duplicate Price Label!";
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
  public void deletePrice(int priceid) {
      logger.info("+++++ DELETE PRICE LABEL +++++");      
      String sql = "delete from price_label where price_label_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);          
          ps.setInt(1, priceid);
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
