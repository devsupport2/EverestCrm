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

import com.ui.dao.RangeDAO;
import com.ui.model.RangeMaster;

public class RangeDAOImpl implements RangeDAO{
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(RangeDAOImpl.class);

	@Override
	public String addRange(RangeMaster r) {
		logger.info("+++++ ADD RANGE +++++");
		String sql = "insert into range_master (range_from, range_to, unit_id, description, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, r.getRangeFrom());
			ps.setFloat(2, r.getRangeTo());			
			ps.setInt(3, r.getUnitId());
			ps.setString(4, r.getDescription());
			ps.setString(5, r.getStatus());
			ps.setInt(6, r.getCreatedBy());
			ps.setString(7, r.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Something went wrong!";
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
	public String editRange(RangeMaster r) {
		logger.info("+++++ EDIT RANGE +++++");
		String sql = "update range_master set range_from=?, range_to=?, unit_id=?, description=?, created_by=?, ip_address=? where range_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, r.getRangeFrom());
			ps.setFloat(2, r.getRangeTo());			
			ps.setInt(3, r.getUnitId());
			ps.setString(4, r.getDescription());
			ps.setInt(5, r.getCreatedBy());
			ps.setString(6, r.getIpAddress());
			ps.setInt(7, r.getRangeId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not updated! Duplicate range!";
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
	public List<RangeMaster> getRangeByPage(int pagesize, int startindex) {
		logger.info("+++++ GET RANGE BY PAGE ++++");
		List<RangeMaster> rangeList = new ArrayList<RangeMaster>();
		String s = "y";
		String sql = "select r.range_id, r.range_from, r.range_to, r.unit_id, u.measurement_unit_name from range_master r left join measurement_unit u on r.unit_id=u.measurement_unit_id where r.status=? order by range_from limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			RangeMaster r = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = new RangeMaster();				
				r.setRangeId(rs.getInt("range_id"));
				r.setRangeFrom(rs.getFloat("range_from"));
				r.setRangeTo(rs.getFloat("range_to"));
				r.setUnitId(rs.getInt("unit_id"));
				r.setUnitName(rs.getString("measurement_unit_name"));
				rangeList.add(r);
			}
			rs.close();
			ps.close();
			return rangeList;
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
    public List<RangeMaster> getAllRanges() {
        logger.info("+++++ GET ALL RANGE ++++");
        List<RangeMaster> rangeList = new ArrayList<RangeMaster>();
        String s = "y";
        String sql = "select r.range_id, r.range_from, r.range_to, r.unit_id, u.measurement_unit_name from range_master r left join measurement_unit u on r.unit_id=u.measurement_unit_id where r.status=? order by range_from";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            RangeMaster r = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r = new RangeMaster();              
                r.setRangeId(rs.getInt("range_id"));
                r.setRangeFrom(rs.getFloat("range_from"));
                r.setRangeTo(rs.getFloat("range_to"));
                r.setUnitId(rs.getInt("unit_id"));
                r.setUnitName(rs.getString("measurement_unit_name"));
                rangeList.add(r);
            }
            rs.close();
            ps.close();
            return rangeList;
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
    public RangeMaster getRangeDetailById(int rangeid) {
        logger.info("+++++ GET RANGE DETAILS BY ID ++++");
        String s = "y";
        String sql = "select range_id, range_from, range_to, unit_id, description from range_master where status=? and range_id=? ";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, rangeid);
            RangeMaster r = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                r = new RangeMaster();              
                r.setRangeId(rs.getInt("range_id"));
                r.setRangeFrom(rs.getFloat("range_from"));
                r.setRangeTo(rs.getFloat("range_to"));
                r.setUnitId(rs.getInt("unit_id"));
                r.setDescription(rs.getString("description"));
            }
            rs.close();
            ps.close();
            return r;
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
    public void deleteRange(int rangeid) {
        logger.info("Delete RANGE ");
        String sql="delete from range_master where range_id=?";
        Connection conn=null;
        try
        {
            conn=dataSource.getConnection();
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, rangeid);
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

}
