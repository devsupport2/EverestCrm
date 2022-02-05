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

import com.ui.dao.PropertyDAO;
import com.ui.model.AreaTitle;
import com.ui.model.PaymentLabel;
import com.ui.model.PaymentSchedule;
import com.ui.model.ProjectPaymentSchedule;
import com.ui.model.ProjectPriceInfo;
import com.ui.model.Property;
import com.ui.model.PropertyArea;
import com.ui.model.PropertyLayout;
import com.ui.model.PropertyPaymentSchedule;
import com.ui.model.PropertyPriceInfo;
import com.ui.model.PropertyRoom;
import com.ui.model.PropertyType;
import com.ui.model.Realestate;
import com.ui.model.RealestateSubcategory;
import com.ui.model.RealestateType;
import com.ui.model.RoomTitle;

public class PropertyDAOImpl implements PropertyDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public List<Property> getPropertyByPage(int pagesize, int startindex) {
		logger.info("+++++ GET PROPERTY BY PAGE +++++");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select p.property_id, p.property_title, pj.project_title, rs.realestate_type_name, rss.subcategory_title, rst.realestate_title, um.unit_name, p.floor, p.property_status from property p left join unit_master um on p.property_unit_master_id = um.unit_master_id left join project pj on p.project_type_id = pj.project_id left join realestate_type rs on p.category_id = rs.realestate_type_id left join realestate_subcategory rss on p.subcategory_id = rss.realestate_subcategory_id left join realestate rst on p.realestate_id = rst.realestate_id where p.status=? order by property_title limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getInt("property_id"), rs.getString("property_title"),
						rs.getString("project_title"), rs.getString("realestate_type_name"),
						rs.getString("subcategory_title"), rs.getString("realestate_title"), rs.getString("unit_name"), rs.getString("floor"), rs.getString("property_status"));
				sta.add(property);
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
	public List<Property> searchProperties(String keyword) {
		logger.info("+++++ SERACH PROJECT +++++");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select p.property_id, p.property_title, pj.project_title, rs.realestate_type_name, rss.subcategory_title, rst.realestate_title, um.unit_name, p.floor, p.property_status from property p left join unit_master um on p.property_unit_master_id = um.unit_master_id left join project pj on p.project_type_id = pj.project_id left join realestate_type rs on p.category_id = rs.realestate_type_id left join realestate_subcategory rss on p.subcategory_id = rss.realestate_subcategory_id left join realestate rst on p.realestate_id = rst.realestate_id where p.status=? and concat(p.property_title,'',pj.project_title,'',rs.realestate_type_name,'',rss.subcategory_title,'',rst.realestate_title,'',um.unit_name) like ? order by p.property_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getInt("property_id"), rs.getString("property_title"),
						rs.getString("project_title"), rs.getString("realestate_type_name"),
						rs.getString("subcategory_title"), rs.getString("realestate_title"), rs.getString("unit_name"), rs.getString("floor"), rs.getString("property_status"));
				sta.add(property);
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
	public Property getPropertyDetailById(int propertyid) {
		logger.info("+++++ GET PROPERTY BY PAGE +++++");
		String s = "y";
		String sql = "select property_id, project_type_id, category_id, subcategory_id, realestate_id, property_type_id, property_title, property_unit_master_id, floor, water_source, drainage, furnishing, reserved_parking, property_availability, property_availability_description, maintenance_charges, maintenance_amount, property_description, property_status from property where status=? and property_id =?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, propertyid);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getInt("property_id"), rs.getInt("project_type_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("realestate_id"),
						rs.getInt("property_type_id"), rs.getString("property_title"),
						rs.getInt("property_unit_master_id"), rs.getString("floor"), rs.getString("water_source"),
						rs.getString("drainage"), rs.getString("furnishing"), rs.getString("reserved_parking"),
						rs.getString("property_availability"), rs.getString("property_availability_description"),
						rs.getString("maintenance_charges"), rs.getFloat("maintenance_amount"),
						rs.getString("property_description"), rs.getString("property_status"));

			}
			rs.close();
			ps.close();
			return property;
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
	public Property getCategoryNameById(int realecateid) {
		logger.info("+++++ GET Category BY ID +++++");
		String s = "y";
		String sql = "select realestate_type_name from realestate_type where status=? and realestate_type_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, realecateid);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getString("realestate_type_name"));

			}
			rs.close();
			ps.close();
			return property;
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
	public Property getSubcategoryNameById(int realestatesubid) {
		logger.info("+++++ GET Sub-category BY ID +++++");
		/* List<Property> sta = new ArrayList<Property>(); */
		String s = "y";
		String sql = "select realestate_subcategory_id, subcategory_title from realestate_subcategory where status=? and realestate_subcategory_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, realestatesubid);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getInt("realestate_subcategory_id"), rs.getString("subcategory_title"));

			}
			rs.close();
			ps.close();
			return property;
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
	public Property getRealestateNameById(int realestatetypeid) {
		logger.info("+++++ GET Realestate BY ID +++++");
		String s = "y";
		String sql = "select realestate_title, realestate_code, realestate_id from realestate where status=? and realestate_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, realestatetypeid);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getString("realestate_title"), rs.getString("realestate_code"),
						rs.getInt("realestate_id"));

			}
			rs.close();
			ps.close();
			return property;
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
	public List<RealestateSubcategory> getAllRealestateSubcategoryTitleByRealestateId(int realestateid) {
		logger.info("+++++ GET ALL SUBCATEGORY TITILES +++++");
		List<RealestateSubcategory> sta = new ArrayList<RealestateSubcategory>();
		String s = "y";
		String sql = "select realestate_subcategory_id, subcategory_title from realestate_subcategory where status=? and realestate_type_id=? order by subcategory_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, realestateid);
			RealestateSubcategory realestateSubcategory = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				realestateSubcategory = new RealestateSubcategory(rs.getInt("realestate_subcategory_id"),
						rs.getString("subcategory_title"));
				sta.add(realestateSubcategory);
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
	public void deleteAreaDetail(int propertyareaid) {
		logger.info("+++++ DELETE AREA +++++");
		String status = "n";
		String sql = "update property_area set status=? where property_area_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, propertyareaid);
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
	public void deleteRoomDetail(int propertyroomid) {
		logger.info("+++++ DELETE ROOM +++++");
		String status = "n";
		String sql = "update property_room set status=? where property_room_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, propertyroomid);
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
	public void deletePriceDetail(int paymentscheduleid) {
		logger.info("+++++ DELETE PRICE +++++");
		String status = "n";
		String sql = "update payment_schedule set status=? where payment_schedule_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, paymentscheduleid);
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
	public void deletePropertyPriceDetail(int propertypriceinfoid) {
		logger.info("+++++ DELETE PRICE +++++");
		String status = "n";
		String sql = "update property_price_info set status=? where property_price_info_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, propertypriceinfoid);
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
    public void updatePropertyStatus(int projectid) {
        logger.info("+++++ REMOVE PROPERTY +++++");
        String status = "n";
        String sql = "update property set status=? where project_type_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, projectid);
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
	public void deleteTowerName(int towernameid) {
		logger.info("+++++ DELETE TOWER NAME +++++");
		String status = "n";
		String sql = "update tower_name set status=? where tower_name_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, towernameid);
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
	public String editProperty(Property pro) {
		logger.info("+++++ EDIT PROPERTY +++++");
		String sql = "update property set project_type_id=?, category_id=?, subcategory_id=?, realestate_id=?, property_type_id=?, property_title=?, property_unit_master_id=?, floor=?, water_source=?, drainage=?, furnishing=?, reserved_parking=?, property_availability=?, property_availability_description=?, maintenance_charges=?, maintenance_amount=?, property_description=?, created_by=?, ip_address=?, property_status=? where property_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pro.getProjectTypeId());
			ps.setInt(2, pro.getCategoryId());
			ps.setInt(3, pro.getSubcategoryId());
			ps.setInt(4, pro.getRealestateId());
			ps.setInt(5, pro.getPropertyTypeId());
			ps.setString(6, pro.getPropertyTitle());
			ps.setInt(7, pro.getPropertyUnitMasterId());
			ps.setString(8, pro.getFloor());
			ps.setString(9, pro.getWaterSource());
			ps.setString(10, pro.getDrainage());
			ps.setString(11, pro.getFurnishing());
			ps.setString(12, pro.getReservedParking());
			ps.setString(13, pro.getPropertyAvailability());
			ps.setString(14, pro.getPropertyAvailabilityDescription());
			ps.setString(15, pro.getMaintenanceCharges());
			ps.setFloat(16, pro.getMaintenanceAmount());
			ps.setString(17, pro.getPropertyDescription());
			ps.setInt(18, pro.getCreatedBy());
			ps.setString(19, pro.getIpAddress());
			ps.setString(20, pro.getPropertyStatus());
			ps.setInt(21, pro.getPropertyId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not updated! Duplicate project code!";
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
	public int getLastPropertyId() {
		logger.info("+++++ GET LAST PROPERTY ID +++++");
		String sql = "select property_id from property order by property_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("property_id");
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
	public List<PropertyArea> getPropertyAreaById(int propertyid) {
		logger.info("+++++ GET PROPERTY BY PAGE +++++");
		List<PropertyArea> sta = new ArrayList<PropertyArea>();
		String s = "y";
		String sql = "select a.property_area_id, a.area_type_id, a.area_value, a.unit_id, a.property_id, at.area_type_title, ut.measurement_unit_name from property_area a, area_type at, measurement_unit ut where a.status=? and a.area_type_id = at.area_type_id and a.unit_id=ut.measurement_unit_id and property_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, propertyid);
			PropertyArea propertyArea = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				propertyArea = new PropertyArea(rs.getInt("property_area_id"), rs.getInt("area_type_id"),
						rs.getFloat("area_value"), rs.getInt("unit_id"), rs.getInt("property_id"),
						rs.getString("area_type_title"), rs.getString("measurement_unit_name"));
				sta.add(propertyArea);
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
	public List<PropertyRoom> getPropertyRoomById(int propertyid) {
		logger.info("+++++ GET PROPERTY BY PAGE +++++");
		List<PropertyRoom> sta = new ArrayList<PropertyRoom>();
		String s = "y";
		String sql = "select r.property_room_id, r.room_title_id, r.property_id, r.room_length, r.length_unit_id, r.room_breadth, r.breadth_unit_id, r.room_height, r.height_unit_id, rm.room_title, (ut.measurement_unit_name) as length_unit, (mut.measurement_unit_name) as breadth_unit, (uit.measurement_unit_name) as height_unit  from property_room r, room_title rm, measurement_unit ut, measurement_unit mut, measurement_unit uit where r.status=? and r.room_title_id = rm.room_title_id and r.length_unit_id=ut.measurement_unit_id and r.breadth_unit_id = mut.measurement_unit_id and r.height_unit_id = uit.measurement_unit_id and property_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, propertyid);
			PropertyRoom propertyRoom = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				propertyRoom = new PropertyRoom(rs.getInt("property_room_id"), rs.getInt("room_title_id"),
						rs.getInt("property_id"), rs.getFloat("room_length"), rs.getInt("length_unit_id"),
						rs.getFloat("room_breadth"), rs.getInt("breadth_unit_id"), rs.getFloat("room_height"),
						rs.getInt("height_unit_id"), rs.getString("room_title"), rs.getString("length_unit"),
						rs.getString("breadth_unit"), rs.getString("height_unit"));
				sta.add(propertyRoom);
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
	public List<PaymentSchedule> getPropertyPriceById(int propertyid) {
		logger.info("+++++ GET PROPERTY PRICE +++++");
		List<PaymentSchedule> sta = new ArrayList<PaymentSchedule>();
		String s = "y";
		String sql = "select payment_schedule_id, property_id, sale_deed, gst, stamp_duty, development, maintenance_deposit, total from payment_schedule where status=? and property_id=? order by payment_schedule_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, propertyid);
			PaymentSchedule paymentSchedule = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				paymentSchedule = new PaymentSchedule(rs.getInt("payment_schedule_id"), rs.getInt("property_id"),
						rs.getFloat("sale_deed"), rs.getFloat("gst"), rs.getFloat("stamp_duty"),
						rs.getFloat("development"), rs.getFloat("maintenance_deposit"), rs.getFloat("total"));
				sta.add(paymentSchedule);
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
	public List<PropertyPriceInfo> getPropertyPriceInfoById(int propertyid) {
		logger.info("+++++ GET PROPERTY PRICE INFO +++++");
		List<PropertyPriceInfo> sta = new ArrayList<PropertyPriceInfo>();
		String s = "y";
		String sql = "select p.property_price_info_id, p.property_price_lable, p.property_price_value, p.unit_id, m.measurement_unit_name from property_price_info p, measurement_unit m where p.status=? and p.unit_id = m.measurement_unit_id and property_id=? order by property_price_info_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, propertyid);
			PropertyPriceInfo propertyPriceInfo = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				propertyPriceInfo = new PropertyPriceInfo(rs.getInt("property_price_info_id"),
						rs.getString("property_price_lable"), rs.getFloat("property_price_value"), rs.getInt("unit_id"),
						rs.getString("measurement_unit_name"));
				sta.add(propertyPriceInfo);
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
	public List<ProjectPaymentSchedule> getPropertyPaymentScheduleById(int projectid) {
		logger.info("+++++ GET PROPERTY PRICE INFO +++++");
		List<ProjectPaymentSchedule> sta = new ArrayList<ProjectPaymentSchedule>();
		String s = "y";
		String sql = "select p.project_payment_schedule_id, p.sequence, p.sequence_title, p.payment_lable, p.payment_value, p.unit_id, m.measurement_unit_name from project_payment_schedule p, measurement_unit m where p.status=? and p.unit_id = m.measurement_unit_id and project_id=? order by sequence";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, projectid);
			ProjectPaymentSchedule projectPaymentSchedule = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectPaymentSchedule = new ProjectPaymentSchedule(rs.getInt("project_payment_schedule_id"),
						rs.getString("sequence"), rs.getString("sequence_title"), rs.getString("payment_lable"),
						rs.getFloat("payment_value"), rs.getInt("unit_id"), rs.getString("measurement_unit_name"));
				sta.add(projectPaymentSchedule);
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
	public List<PropertyPaymentSchedule> getPropertyPaymentsScheduleById(int propertyid) {
		logger.info("+++++ GET PROPERTY PRICE INFO +++++");
		List<PropertyPaymentSchedule> sta = new ArrayList<PropertyPaymentSchedule>();
		String s = "y";
		String sql = "select p.property_payment_schedule_id, p.property_id, p.sequence, p.sequence_title, p.payment_lable, p.payment_value, p.unit_id, m.measurement_unit_name from property_payment_schedule p, measurement_unit m where p.status=? and p.unit_id = m.measurement_unit_id and property_id=? order by sequence";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, propertyid);
			PropertyPaymentSchedule propertyPaymentSchedule = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				propertyPaymentSchedule = new PropertyPaymentSchedule(rs.getInt("property_payment_schedule_id"),
						rs.getInt("property_id"), rs.getString("sequence"), rs.getString("sequence_title"),
						rs.getString("payment_lable"), rs.getFloat("payment_value"), rs.getInt("unit_id"),
						rs.getString("measurement_unit_name"));
				sta.add(propertyPaymentSchedule);
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
	public List<ProjectPriceInfo> getPriceInfoById(int projectid, int categoryid, int subcategoryid, int realestateid) {
		logger.info("+++++ GET PROPERTY PRICE INFO +++++");
		List<ProjectPriceInfo> sta = new ArrayList<ProjectPriceInfo>();
		String s = "y";
		String sql = "select p.project_price_info_id, p.project_id, p.price_lable, p.price_value, m.measurement_unit_name from project_price_info p, measurement_unit m where p.status=? and p.unit_type_id = m.measurement_unit_id and project_id=? and category_id=? and subcategory_id=? and realestate_id=? order by project_price_info_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, projectid);
			ps.setInt(3, categoryid);
			ps.setInt(4, subcategoryid);
			ps.setInt(5, realestateid);
			ProjectPriceInfo projectPriceInfo = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectPriceInfo = new ProjectPriceInfo(rs.getInt("project_price_info_id"), rs.getInt("project_id"),
						rs.getString("price_lable"), rs.getString("price_value"),
						rs.getString("measurement_unit_name"));
				sta.add(projectPriceInfo);
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
	public List<RealestateType> getRealestateName() {
		logger.info("***** GET REALESTATE NAME *****");
		List<RealestateType> sta = new ArrayList<RealestateType>();
		String s = "y";
		String sql = "select realestate_type_id, realestate_type_name from realestate_type where status=? order by realestate_type_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			RealestateType realestateType = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				realestateType = new RealestateType(rs.getInt("realestate_type_id"),
						rs.getString("realestate_type_name"));
				sta.add(realestateType);
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
	public List<PropertyType> getPropertyName() {
		logger.info("***** GET PROPERTY NAME *****");
		List<PropertyType> sta = new ArrayList<PropertyType>();
		String s = "y";
		String sql = "select property_type_id, property_type_title from property_type where status=? order by property_type_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			PropertyType propertyType = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				propertyType = new PropertyType(rs.getInt("property_type_id"), rs.getString("property_type_title"));
				sta.add(propertyType);
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
	public List<AreaTitle> getAreaName() {
		logger.info("***** GET PROPERTY NAME *****");
		List<AreaTitle> sta = new ArrayList<AreaTitle>();
		String s = "y";
		String sql = "select area_type_id, area_type_title from area_type where status=? order by area_type_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			AreaTitle areaTitle = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				areaTitle = new AreaTitle(rs.getInt("area_type_id"), rs.getString("area_type_title"));
				sta.add(areaTitle);
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
	public List<RoomTitle> getRoomName() {
		logger.info("***** GET PROPERTY NAME *****");
		List<RoomTitle> sta = new ArrayList<RoomTitle>();
		String s = "y";
		String sql = "select room_title_id, room_title from room_title where status=? order by room_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			RoomTitle roomTitle = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roomTitle = new RoomTitle(rs.getInt("room_title_id"), rs.getString("room_title"));
				sta.add(roomTitle);
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
	public List<Property> getPropertyTitle() {
		logger.info("***** GET PROPERTY NAME *****");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select property_title, property_unit_name from property where status=? order by property_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getString("property_title"), rs.getString("property_unit_name"));
				sta.add(property);
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
	public String addPropertyType(PropertyType p) {
		logger.info("+++++ ADD CATEGORY +++++");
		String sql = "insert into property_type (property_type_title, property_code, image, description, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getPropertyTypeTitle());
			ps.setString(2, p.getPropertyCode());
			ps.setString(3, p.getImage());
			ps.setString(4, p.getDescription());
			ps.setString(5, p.getStatus());
			ps.setInt(6, p.getCreatedBy());
			ps.setString(7, p.getIpAddress());
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
	public String addProperty(Property pr) {
		logger.info("+++++ ADD PROPERTY +++++");
		String sql = "insert into property (sku, sequence, project_type_id, category_id, subcategory_id, realestate_id, property_type_id, property_title, property_unit_master_id, floor, water_source, drainage, furnishing, reserved_parking, property_availability, property_availability_description, maintenance_charges, maintenance_amount, property_description, property_status, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pr.getSku());
			ps.setInt(2, pr.getSequence());
			ps.setInt(3, pr.getProjectTypeId());
			ps.setInt(4, pr.getCategoryId());
			ps.setInt(5, pr.getSubcategoryId());
			ps.setInt(6, pr.getRealestateId());
			ps.setInt(7, pr.getPropertyTypeId());
			ps.setString(8, pr.getPropertyTitle());
			ps.setInt(9, pr.getPropertyUnitMasterId());
			ps.setString(10, pr.getFloor());
			ps.setString(11, pr.getWaterSource());
			ps.setString(12, pr.getDrainage());
			ps.setString(13, pr.getFurnishing());
			ps.setString(14, pr.getReservedParking());
			ps.setString(15, pr.getPropertyAvailability());
			ps.setString(16, pr.getPropertyAvailabilityDescription());
			ps.setString(17, pr.getMaintenanceCharges());
			ps.setFloat(18, pr.getMaintenanceAmount());
			ps.setString(19, pr.getPropertyDescription());
			ps.setString(20, pr.getPropertyStatus());
			ps.setString(21, pr.getStatus());
			ps.setInt(22, pr.getCreatedBy());
			ps.setString(23, pr.getIpAddress());
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
	public List<Realestate> getAllRealestateTypeTitleByRealestateSubcategoryId(int realestatesubcategoryid) {
		logger.info("+++++ GET ALL REALESTATE TITILES +++++");
		List<Realestate> sta = new ArrayList<Realestate>();
		String s = "y";
		String sql = "select realestate_id, realestate_title from realestate where status=? and realestate_subcategory_id=? order by realestate_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, realestatesubcategoryid);
			Realestate realestate = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				realestate = new Realestate(rs.getInt("realestate_id"), rs.getString("realestate_title"));
				sta.add(realestate);
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
	public String addAreaType(AreaTitle a) {
		logger.info("+++++ ADD CATEGORY +++++");
		String sql = "insert into area_type (area_type_title, status, created_by, ip_address) values (?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getAreaTypeTitle());
			ps.setString(2, a.getStatus());
			ps.setInt(3, a.getCreatedBy());
			ps.setString(4, a.getIpAddress());
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
	public String addPriceDetailInfo(PropertyPriceInfo ppi) {
		logger.info("+++++ ADD PROPERTY PRICE INFO +++++");
		String sql = "insert into property_price_info (property_id, property_price_lable, property_price_value, unit_id, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ppi.getPropertyId());
			ps.setString(2, ppi.getPropertyPriceLable());
			ps.setFloat(3, ppi.getPropertyPriceValue());
			ps.setInt(4, ppi.getUnitId());
			ps.setString(5, ppi.getStatus());
			ps.setInt(6, ppi.getCreatedBy());
			ps.setString(7, ppi.getIpAddress());
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
	public String addPriceType(PaymentLabel a) {
		logger.info("+++++ ADD PRICE LABEL +++++");
		String sql = "insert into payment_label (price_type_label, status, created_by, ip_address) values (?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getPriceTypeLabel());
			ps.setString(2, a.getStatus());
			ps.setInt(3, a.getCreatedBy());
			ps.setString(4, a.getIpAddress());
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
	public String addRoomType(RoomTitle r) {
		logger.info("+++++ ADD CATEGORY +++++");
		String sql = "insert into room_title (room_title, description, status, created_by, ip_address) values (?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, r.getRoomTitle());
			ps.setString(2, r.getDescription());
			ps.setString(3, r.getStatus());
			ps.setInt(4, r.getCreatedBy());
			ps.setString(5, r.getIpAddress());
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
	public String addAreaDetails(PropertyArea pa) {
		logger.info("+++++ ADD CATEGORY +++++");
		String sql = "insert into property_area (area_type_id, area_value, unit_id, property_id, status, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pa.getAreaTypeId());
			ps.setFloat(2, pa.getAreaValue());
			ps.setInt(3, pa.getUnitId());
			ps.setInt(4, pa.getPropertyId());
			ps.setString(5, pa.getStatus());
			ps.setInt(6, pa.getCreatedBy());
			ps.setString(7, pa.getIpAddress());
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
	public String addPriceDetails(PaymentSchedule pss) {
		logger.info("+++++ ADD PRICE DETAILS +++++");
		String sql = "insert into payment_schedule (property_id, sale_deed, gst, stamp_duty, development, maintenance_deposit, total, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pss.getPropertyId());
			ps.setFloat(2, pss.getSaleDeed());
			ps.setFloat(3, pss.getGst());
			ps.setFloat(4, pss.getStampDuty());
			ps.setFloat(5, pss.getDevelopment());
			ps.setFloat(6, pss.getMaintenanceDeposit());
			ps.setFloat(7, pss.getTotal());
			ps.setString(8, pss.getStatus());
			ps.setInt(9, pss.getCreatedBy());
			ps.setString(10, pss.getIpAddress());
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
	public String addRoomDetails(PropertyRoom pr) {
		logger.info("+++++ ADD ROOM DETAILS +++++");
		String sql = "insert into property_room (room_title_id, property_id, room_length, length_unit_id, room_breadth, breadth_unit_id, room_height, height_unit_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pr.getRoomTitleId());
			ps.setInt(2, pr.getPropertyId());
			ps.setFloat(3, pr.getRoomLength());
			ps.setInt(4, pr.getLengthUnitId());
			ps.setFloat(5, pr.getRoomBreadth());
			ps.setInt(6, pr.getBreadthUnitId());
			ps.setFloat(7, pr.getRoomHeight());
			ps.setInt(8, pr.getHeightUnitId());
			ps.setString(9, pr.getStatus());
			ps.setInt(10, pr.getCreatedBy());
			ps.setString(11, pr.getIpAddress());
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
	public String addPropertyPaymentSchedule(PropertyPaymentSchedule pps) {
		logger.info("+++++ ADD PROPERTY PAYMENT SCHEDULE +++++");
		String sql = "insert into property_payment_schedule (property_id, sequence, sequence_title, payment_lable, payment_value, unit_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pps.getPropertyId());
			ps.setString(2, pps.getSequence());
			ps.setString(3, pps.getSequenceTitle());
			ps.setString(4, pps.getPaymentLable());
			ps.setFloat(5, pps.getPaymentValue());
			ps.setInt(6, pps.getUnitId());
			ps.setString(7, pps.getStatus());
			ps.setInt(8, pps.getCreatedBy());
			ps.setString(9, pps.getIpAddress());
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
	public List<Property> getAllProperty() {
		logger.info("***** GET ALL PROPERTY *****");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select p.property_id, p.property_title, pj.project_title, rs.realestate_type_name, rss.subcategory_title, rst.realestate_title, um.unit_name, p.floor, p.property_status from property p left join unit_master um on p.property_unit_master_id = um.unit_master_id left join project pj on p.project_type_id = pj.project_id left join realestate_type rs on p.category_id = rs.realestate_type_id left join realestate_subcategory rss on p.subcategory_id = rss.realestate_subcategory_id left join realestate rst on p.realestate_id = rst.realestate_id where p.status=? order by property_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			  property = new Property(rs.getInt("property_id"), rs.getString("property_title"),
                    rs.getString("project_title"), rs.getString("realestate_type_name"),
                    rs.getString("subcategory_title"), rs.getString("realestate_title"), rs.getString("unit_name"), rs.getString("floor"), rs.getString("property_status"));
            sta.add(property);
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
	public void deleteProperty(int propertyid) {
		logger.info("+++++ DELETE AREA +++++");
		String status = "n";
		String sql = "update property set status=? where property_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, propertyid);
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
    public void deletePropertyLayout(int layoutid) {
        logger.info("+++++ DELETE LAYOUT +++++");
        String sql = "delete from property_layout where property_layout_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, layoutid);
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
	public void deletePropertyPaymentSchedule(int id) {
		logger.info("+++++ DELETE PROPERTY PAYMENT SCHEDULE +++++");
		String status = "n";
		String sql = "update property_payment_schedule set status=? where property_payment_schedule_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
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
	public Property getLastPropertyDetailByProjectId(int projectid) {
		logger.info("+++++ GET LAST PROPERTY DETAIL BY PROJECT ID +++++");
		String s = "y";
		String sql = "select property_id, sequence, sku, project_type_id, category_id, subcategory_id, realestate_id, property_type_id, property_title, property_unit_master_id, floor, water_source, drainage, furnishing, reserved_parking, property_availability, property_availability_description, maintenance_charges, maintenance_amount, property_description from property where status=? and project_type_id=? order by property_id desc limit 0,1";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, projectid);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getInt("property_id"), rs.getInt("sequence"), rs.getString("sku"),
						rs.getInt("project_type_id"), rs.getInt("category_id"), rs.getInt("subcategory_id"),
						rs.getInt("realestate_id"), rs.getInt("property_type_id"), rs.getString("property_title"),
						rs.getInt("property_unit_master_id"), rs.getString("floor"), rs.getString("water_source"),
						rs.getString("drainage"), rs.getString("furnishing"), rs.getString("reserved_parking"),
						rs.getString("property_availability"), rs.getString("property_availability_description"),
						rs.getString("maintenance_charges"), rs.getFloat("maintenance_amount"),
						rs.getString("property_description"));

			}
			rs.close();
			ps.close();
			return property;
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
	public List<Property> getPropertyNumberListById(int projectid, int categoryid, int subcategoryid, int typeid) {
		logger.info("+++++ GET PROPERTY NUMBER LIST BY ID +++++");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select property_id, project_type_id, category_id, subcategory_id, realestate_id, property_title from property where status=? and project_type_id = ? and category_id = ? and subcategory_id = ? and realestate_id = ? order by property_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, projectid);
			ps.setInt(3, categoryid);
			ps.setInt(4, subcategoryid);
			ps.setInt(5, typeid);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getInt("property_id"), rs.getInt("project_type_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("realestate_id"),
						rs.getString("property_title"));
				sta.add(property);
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
	public List<Property> getPropertyNumberListById(int projectid, int categoryid, int subcategoryid, int typeid,
			int unitmasterid) {
		logger.info("+++++ GET PROPERTY NUMBER LIST BY ID +++++");
		List<Property> sta = new ArrayList<Property>();
		String s = "y";
		String sql = "select property_id, project_type_id, category_id, subcategory_id, realestate_id, property_title from property where status=? and project_type_id = ? and category_id = ? and subcategory_id = ? and realestate_id = ? and property_unit_master_id=? order by property_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, projectid);
			ps.setInt(3, categoryid);
			ps.setInt(4, subcategoryid);
			ps.setInt(5, typeid);
			ps.setInt(6, unitmasterid);
			Property property = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				property = new Property(rs.getInt("property_id"), rs.getInt("project_type_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("realestate_id"),
						rs.getString("property_title"));
				sta.add(property);
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
	public int getLastRealestateTypeId() {
		logger.info("+++++ GET LAST REALESTATE TYPE ID +++++");
		String sql = "select realestate_type_id from realestate_type order by realestate_type_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("realestate_type_id");
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
	public int getLastRealestateSubcategoryId() {
		logger.info("+++++ GET LAST REALESTATE SUBCATEGORY ID +++++");
		String sql = "select realestate_subcategory_id from realestate_subcategory order by realestate_subcategory_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("realestate_subcategory_id");
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
    public List<Property> getAllPropertyByProjectId(int projectid) {
        logger.info("+++++ GET PROPERTY BY PROJECT ID +++++");
        List<Property> sta = new ArrayList<Property>();
        String s = "y";
        String sql = "select p.property_id, p.property_title, pj.project_title, rs.realestate_type_name, rss.subcategory_title, rst.realestate_title, um.unit_name, p.floor, p.property_status from property p left join unit_master um on p.property_unit_master_id = um.unit_master_id, project pj, realestate_type rs, realestate_subcategory rss, realestate rst where p.status=? and pj.project_id=? and p.project_type_id = pj.project_id and p.category_id = rs.realestate_type_id and p.subcategory_id = rss.realestate_subcategory_id and p.realestate_id = rst.realestate_id order by property_title";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, projectid);
            Property property = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                property = new Property(rs.getInt("property_id"), rs.getString("property_title"),
                        rs.getString("project_title"), rs.getString("realestate_type_name"),
                        rs.getString("subcategory_title"), rs.getString("realestate_title"), rs.getString("unit_name"), rs.getString("floor"), rs.getString("property_status"));
                sta.add(property);
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
    public List<Property> getAllPropertyByProjectIdAndUnitID(int projectid, int projectunitid) {
        logger.info("+++++ GET PROPERTY BY PROJECT ID AND UNIT ID +++++");
        List<Property> sta = new ArrayList<Property>();
        String s = "y";
        String sql = "select p.property_id, p.property_title, pj.project_title, rs.realestate_type_name, rss.subcategory_title, rst.realestate_title, um.unit_name, p.floor, p.property_status from property p left join unit_master um on p.property_unit_master_id = um.unit_master_id, project pj, realestate_type rs, realestate_subcategory rss, realestate rst where p.status=? and pj.project_id=? and p.property_unit_master_id=? and p.project_type_id = pj.project_id and p.category_id = rs.realestate_type_id and p.subcategory_id = rss.realestate_subcategory_id and p.realestate_id = rst.realestate_id order by property_title";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, projectid);
            ps.setInt(3, projectunitid);
            Property property = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                property = new Property(rs.getInt("property_id"), rs.getString("property_title"),
                        rs.getString("project_title"), rs.getString("realestate_type_name"),
                        rs.getString("subcategory_title"), rs.getString("realestate_title"), rs.getString("unit_name"), rs.getString("floor"), rs.getString("property_status"));
                sta.add(property);
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
    public List<Realestate> getAllRealestateType() {
        logger.info("***** GET REALESTATE NAME *****");
        List<Realestate> sta = new ArrayList<Realestate>();
        String s = "y";
        String sql = "select realestate_id, realestate_title from realestate where status=? group by realestate_id,realestate_title";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            Realestate realestate = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                realestate = new Realestate(rs.getInt("realestate_id"),
                        rs.getString("realestate_title"));
                sta.add(realestate);
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
    public List<Property> getPropertyNumberListForEnquiryById(int projectid, int categoryid, int subcategoryid) {
        logger.info("+++++ GET PROPERTY NUMBER LIST FOR ENQUIRY BY ID +++++");
        List<Property> sta = new ArrayList<Property>();
        String s = "y";
        String sql = "select property_id, project_type_id, category_id, subcategory_id, realestate_id, property_title from property where status=? and project_type_id = ? and category_id = ? and subcategory_id = ? order by property_title";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, projectid);
            ps.setInt(3, categoryid);
            ps.setInt(4, subcategoryid);
            Property property = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                property = new Property(rs.getInt("property_id"), rs.getInt("project_type_id"),
                        rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("realestate_id"),
                        rs.getString("property_title"));
                sta.add(property);
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
    public String addPropertyLayout(PropertyLayout pl) {
        logger.info("+++++ ADD PROPERTY LAYOUT +++++");
        String sql = "insert into property_layout (property_id, title, image, created_by, ip_address) values (?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pl.getPropertyId());
            ps.setString(2, pl.getLayoutTitle());
            ps.setString(3, pl.getImage());
            ps.setInt(4, pl.getCreatedBy());
            ps.setString(5, pl.getIpAddress());
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
    public List<PropertyLayout> getPropertyLayoutById(int propertyid) {
        logger.info("+++++ GET PROPERTY LAYOUT BY PAGE +++++");
        List<PropertyLayout> sta = new ArrayList<PropertyLayout>();
        String s = "y";
        String sql = "select property_layout_id, title, image from property_layout where property_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, propertyid);
            PropertyLayout pl = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pl = new PropertyLayout();
                pl.setPropertyLayoutId(rs.getInt("property_layout_id"));
                pl.setLayoutTitle(rs.getString("title"));
                pl.setImage(rs.getString("image"));
                
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
	
	
}
