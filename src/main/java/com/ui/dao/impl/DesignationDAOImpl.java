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
import com.ui.dao.DesignationDAO;
import com.ui.model.Designation;


public class DesignationDAOImpl implements DesignationDAO{
	JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	
	public static final Logger logger=LoggerFactory.getLogger(DesignationDAOImpl.class);	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String addDesignation(Designation d) {
		
		logger.info("Insert DESIGNATION");
		String sql="insert into designation(designation_name,created_by,ip_address) values(?,?,?)";
		Connection conn=null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, d.getDesignationName());
			pst.setInt(2, d.getCreatedBy());
			pst.setString(3, d.getIpAddress());
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
    public List<Designation> getDesignationsByPage(int pagesize, int startindex) {

        logger.info("+++++ GET ALL DESIGNATION BY PAGE +++++");
        List<Designation> sta = new ArrayList<Designation>();
        String sql = "select designation_id, designation_name from designation order by designation_name limit ?,?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, startindex);
            ps.setInt(2, pagesize);
            Designation d = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              
              d = new Designation();
              d.setDesignationId(rs.getInt("designation_id"));
              d.setDesignationName(rs.getString("designation_name"));
              sta.add(d);
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
    public List<Designation> searchDesignations(String keyword) {

        logger.info("+++++ SEARCH DESIGNATIONS +++++");
        List<Designation> sta = new ArrayList<Designation>();
        String sql = "select designation_id, designation_name from designation where concat(designation_name) like ? order by designation_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, '%' + keyword + '%');
            Designation d = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              
              d = new Designation();
              d.setDesignationId(rs.getInt("designation_id"));
              d.setDesignationName(rs.getString("designation_name"));
              sta.add(d);
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
    public Designation getDesignationDetailById(int designationid) {
        logger.info("+++++ GET DEPARTMENT DETAIL BY ID +++++");
        Designation d = null;
        String sql = "select designation_id, designation_name from designation where designation_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, designationid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                d = new Designation();
                d.setDesignationId(rs.getInt("designation_id"));
                d.setDesignationName(rs.getString("designation_name"));
            }
            rs.close();
            ps.close();
            return d;
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
    public String editDesignation(Designation d) {
        logger.info("+++++ EDIT DESIGNATION +++++");
        String sql = "update designation set designation_name=?, created_by=?, ip_address=? where designation_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getDesignationName());
            ps.setInt(2, d.getCreatedBy());
            ps.setString(3, d.getIpAddress());
            ps.setInt(4, d.getDesignationId());
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
    public void deleteDesignation(int designationid) {
        logger.info("Delete Designation ");
        String sql="delete from designation where designation_id=?";
        Connection conn=null;
        try
        {
        	conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, designationid);
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
	public List<Designation> getAllDesignation() {

		logger.info("+++++ Get All DESIGNATIONs +++++");
		List<Designation> sta = new ArrayList<Designation>();
		String sql = "select designation_id, designation_name from designation order by designation_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Designation d = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			  d = new Designation();
              d.setDesignationId(rs.getInt("designation_id"));
              d.setDesignationName(rs.getString("designation_name"));
              sta.add(d);
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
