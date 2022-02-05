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

import com.ui.dao.SliderDAO;
import com.ui.dao.SiteSliderDAO;
import com.ui.model.Slider;
public class SiteSliderDAOImpl implements SiteSliderDAO
{
	@Autowired
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(SiteSliderDAOImpl.class);
	
	
	@Override
	public List<Slider> getAllSliders()
	{
		logger.info("Inside Get All Site Slider Impl");
		
		List<Slider> Slider = new ArrayList<Slider>();
		
		String s = "y";
		
		String sql = "select ps.slider_id, ps.project_id, ps.slider_title, ps.image, ps.active, ps.status, ps.created_by, ps.created_date, ps.ip_address, p.project_title from project_slider ps left join project p on ps.project_id=p.project_id where ps.status=? order by p.project_title";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Slider slider = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				slider = new Slider(
                rs.getInt("slider_id"),
                rs.getString("slider_title"),
                rs.getString("image"),
                rs.getString("active"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getInt("project_id"),
                rs.getString("project_title")
				);
				
				Slider.add(slider);
           }
           rs.close();
           ps.close();
          
           return Slider;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<Slider> getAllSlidersByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Site Slider By Page Impl");
		
		List<Slider> Slider = new ArrayList<Slider>();
		
		String s = "y";
		
		String sql = "select ps.slider_id, ps.project_id, ps.slider_title, ps.image, ps.active, ps.status, ps.created_by, ps.created_date, ps.ip_address, p.project_title from project_slider ps left join project p on ps.project_id=p.project_id where ps.status=? order by p.project_title limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Slider slider = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				slider = new Slider(
                rs.getInt("slider_id"),
                rs.getString("slider_title"),
                rs.getString("image"),
                rs.getString("active"),
                rs.getString("status"),
                rs.getInt("created_by"),
                rs.getString("created_date"),
                rs.getString("ip_address"),
                rs.getInt("project_id"),
                rs.getString("project_title")
				);
				
				Slider.add(slider);
           }
           rs.close();
           ps.close();
          
           return Slider;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<Slider> getActiveSliders()
	{
		logger.info("Inside Get Active Sliders Impl");
		
		List<Slider> Slider = new ArrayList<Slider>();
		
		String s = "y";
		
		String sql = "select slider_title, project_id, image from project_slider where status=? and active=? order by slider_id desc";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setString(2, s);

			Slider slider = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				slider = new Slider(
                rs.getString("slider_title"),
                rs.getString("image")
				);
				
				Slider.add(slider);
           }
           rs.close();
           ps.close();
          
           return Slider;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public void addSlider(Slider s)
	{
		logger.info("Inside Add Site Slider Impl");
		
		String sql = "insert into project_slider (slider_title, project_id, image, active, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getSliderTitle());
			ps.setInt(2, s.getSiteId());
			ps.setString(3, s.getImage());
			ps.setString(4, s.getActive());
			ps.setString(5, s.getStatus());
			ps.setInt(6, s.getCreatedBy());
			ps.setString(7, s.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public void editSlider(Slider s)
	{
		logger.info("Inside Edit Slider Impl");
		
		String sql = "update project_slider set slider_title=?, project_id=?, image=?, active=?, status=?, created_by=?, ip_address=? where slider_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getSliderTitle());
			ps.setInt(2, s.getSiteId());
			ps.setString(3, s.getImage());
			ps.setString(4, s.getActive());
			ps.setString(5, s.getStatus());
			ps.setInt(6, s.getCreatedBy());
			ps.setString(7, s.getIpAddress());
			ps.setInt(8, s.getSliderId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public void deleteSiteSlider(int sliderid)
	{
		logger.info("Inside Delete Slider Impl");
		String status="n";
		String sql = "update project_slider set status=? where slider_id=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, sliderid);
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void setActiveOrInActiveSlider(Slider s)
	{		
		logger.info("Inside Set Active Or InActive Slider Impl");
		
		String sql = "update project_slider set active=?, created_by=?, ip_address=? where slider_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getActive());
			ps.setInt(2, s.getCreatedBy());
			ps.setString(3, s.getIpAddress());
			ps.setInt(4, s.getSliderId());
			
			ps.executeUpdate();
		}
		catch (SQLException ex)
		{
			throw new RuntimeException(ex);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException ex) {}
            }
        }
	}
	
	
	
}