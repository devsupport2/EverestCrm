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
import com.ui.dao.OccupationDao;
import com.ui.model.Country;
import com.ui.model.Occupation;

public class OccupationDAOImpl implements OccupationDao{

	JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	
	public static final Logger logger=LoggerFactory.getLogger(OccupationDAOImpl.class);
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public String addOccupation(Occupation o) {
		
		logger.info("Insert OCCUPATION");
		String sql="insert into occupation(occupation_name,created_by,ip_address) values(?,?,?)";
		Connection conn=null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, o.getOccupationName());
			pst.setInt(2, o.getCreatedBy());
			pst.setString(3, o.getIpAddress());
			pst.executeUpdate();
			return "Success";
		} catch (Exception e) {
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
    public List<Occupation> getOccupationsByPage(int pagesize, int startindex) {

        logger.info("+++++ GET ALL OCCUPATION BY PAGE +++++");
        List<Occupation> sta = new ArrayList<Occupation>();
        String sql = "select occupation_id, occupation_name from occupation order by occupation_name limit ?,?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, startindex);
            ps.setInt(2, pagesize);
            Occupation o = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              
              o = new Occupation();
              o.setOccupationId(rs.getInt("occupation_id"));
              o.setOccupationName(rs.getString("occupation_name"));
              sta.add(o);
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
    public Occupation getOccupationDetailById(int occupationid) {
        logger.info("+++++ GET DEPARTMENT DETAIL BY ID +++++");
        Occupation o = null;
        String sql = "select occupation_id, occupation_name from occupation where occupation_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, occupationid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                o = new Occupation();
                o.setOccupationId(rs.getInt("occupation_id"));
                o.setOccupationName(rs.getString("occupation_name"));
            }
            rs.close();
            ps.close();
            return o;
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
    public String editOccupation(Occupation o) {
        logger.info("+++++ EDIT OCCUPATION +++++");
        String sql = "update occupation set occupation_name=?, created_by=?, ip_address=? where occupation_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, o.getOccupationName());
            ps.setInt(2, o.getCreatedBy());
            ps.setString(3, o.getIpAddress());
            ps.setInt(4, o.getOccupationId());
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
    public void deleteOccupation(int occupationid) {
        logger.info("Delete Occupation ");
        String sql="delete from occupation where occupation_id=?";
        Connection conn=null;
        try
        {
            conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, occupationid);
            pst.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            // TODO: handle exception
        }
        finally {
            if(conn !=null)
            {
                try {
                    conn.close();
                    
                } catch (SQLException  e) {
                    // TODO: handle exception
                }
            }
        }
    }

	@Override
	public List<Occupation> getAllOccupation() {

		logger.info("+++++ Get All OCCUPATIONs +++++");
		List<Occupation> sta = new ArrayList<Occupation>();
		String sql = "select occupation_id, occupation_name from occupation order by occupation_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Occupation o = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			  o = new Occupation();
              o.setOccupationId(rs.getInt("occupation_id"));
              o.setOccupationName(rs.getString("occupation_name"));
              sta.add(o);
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



}
