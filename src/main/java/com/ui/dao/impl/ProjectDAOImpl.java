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

import com.ui.dao.ProjectDAO;
import com.ui.model.Project;
import com.ui.model.ProjectAmenity;
import com.ui.model.ProjectArea;
import com.ui.model.ProjectBank;
import com.ui.model.ProjectDetail;
import com.ui.model.ProjectFloorLayout;
import com.ui.model.ProjectPaymentSchedule;
import com.ui.model.ProjectPriceInfo;
import com.ui.model.ProjectSpecification;
import com.ui.model.Realestate;
import com.ui.model.RealestateSubcategory;
import com.ui.model.RealestateType;
import com.ui.model.UnitMaster;

public class ProjectDAOImpl implements ProjectDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);

	@Override
	public List<Project> getAllProjects() {
		logger.info("***** GET PROJECT *****");
		List<Project> sta = new ArrayList<Project>();
		String s = "y";
		String sql = "select project_id, project_title, project_subtitle, project_code, sequence from project where status=? order by sequence";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Project project = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				project = new Project(rs.getInt("project_id"), rs.getString("project_title"),
						rs.getString("project_subtitle"), rs.getString("project_code"), rs.getInt("sequence"));
				sta.add(project);
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
	public List<Project> getAllProjectsByPage(int pagesize, int startindex) {
		logger.info("+++++ GET PROJECT BY PAGE +++++");
		List<Project> sta = new ArrayList<Project>();
		String s = "y";
		String sql = "select project_id, project_title, project_subtitle, project_code, sequence from project where status=? order by sequence limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Project project = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				project = new Project(rs.getInt("project_id"), rs.getString("project_title"),
						rs.getString("project_subtitle"), rs.getString("project_code"), rs.getInt("sequence"));
				sta.add(project);
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
	public List<Project> searchProject(String keyword) {
		logger.info("+++++ SERACH PROJECT +++++");
		List<Project> sta = new ArrayList<Project>();
		String s = "y";
		String sql = "select project_id, project_title, project_subtitle, project_code, sequence from project where status=? and concat(project_title,'',project_subtitle,'',project_code) like ? order by sequence";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Project project = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				project = new Project(rs.getInt("project_id"), rs.getString("project_title"),
						rs.getString("project_subtitle"), rs.getString("project_code"), rs.getInt("sequence"));
				sta.add(project);
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
	public String addProject(Project t) {
		logger.info("+++++ ADD PROJECT +++++");
		String sql = "insert into project (project_title, project_subtitle, project_code, project_logo, location_id, architect_id, structural_designer_id, legal_advisor_id, developer_company_id, property_type_id, rera_approve, rera_no, layout_map, water_source, drainage, work_status, description, status, created_by, ip_address,project_Image, project_pdf, site_link, sequence,shown_project) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getProjectTitle());
			ps.setString(2, t.getProjectSubtitle());
			ps.setString(3, t.getProjectCode());
			ps.setString(4, t.getProjectLogo());
			ps.setInt(5, t.getLocationId());
			ps.setInt(6, t.getArchitectId());
			ps.setInt(7, t.getStructuralDesignerId());
			ps.setInt(8, t.getLegalAdvisorId());
			ps.setInt(9, t.getDeveloperCompanyId());
			ps.setInt(10, t.getPropertyTypeId());
			ps.setString(11, t.getReraApprove());
			ps.setString(12, t.getReraNo());
			ps.setString(13, t.getLayoutMap());
			ps.setString(14, t.getWaterSource());
			ps.setString(15, t.getDrainage());
			ps.setString(16, t.getWorkStatus());
			ps.setString(17, t.getDescription());
			ps.setString(18, t.getStatus());
			ps.setInt(19, t.getCreatedBy());
			ps.setString(20, t.getIpAddress());
			ps.setString(21, t.getProjmainimg());
			ps.setString(22, t.getProjectPDF());
			ps.setString(23, t.getSiteLink());
			ps.setInt(24, t.getSequence());
			ps.setString(25, t.getShown_project());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate project code!";
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
	public String editProject(Project t) {
		logger.info("+++++ EDIT PROJECT +++++");
		String sql = "update project set project_title=?, project_subtitle=?, project_code=?, project_logo=?, location_id=?, architect_id=?, structural_designer_id=?, legal_advisor_id=?, developer_company_id=?, property_type_id=?, rera_approve=?, rera_no=?, layout_map=?, water_source=?, drainage=?, work_status=?, description=?, created_by=?, ip_address=?,project_Image=?, project_pdf=?, site_link=?, sequence=?,shown_project=? where project_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getProjectTitle());
			ps.setString(2, t.getProjectSubtitle());
			ps.setString(3, t.getProjectCode());
			ps.setString(4, t.getProjectLogo());
			ps.setInt(5, t.getLocationId());
			ps.setInt(6, t.getArchitectId());
			ps.setInt(7, t.getStructuralDesignerId());
			ps.setInt(8, t.getLegalAdvisorId());
			ps.setInt(9, t.getDeveloperCompanyId());
			ps.setInt(10, t.getPropertyTypeId());
			ps.setString(11, t.getReraApprove());
			ps.setString(12, t.getReraNo());
			ps.setString(13, t.getLayoutMap());
			ps.setString(14, t.getWaterSource());
			ps.setString(15, t.getDrainage());
			ps.setString(16, t.getWorkStatus());
			ps.setString(17, t.getDescription());
			ps.setInt(18, t.getCreatedBy());
			ps.setString(19, t.getIpAddress());
			ps.setString(20, t.getProjmainimg());
			ps.setString(21, t.getProjectPDF());
			ps.setString(22, t.getSiteLink());
			ps.setInt(23, t.getSequence());
			ps.setString(24, t.getShown_project());
			ps.setInt(25, t.getProjectId());
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
	public void deleteProject(int projectid) {
		logger.info("+++++ DELETE PROJECT +++++");
		String status = "n";
		String sql = "update project set status=? where project_id=?";
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
	public void deleteProjectDetail(int projectdetailid) {
		logger.info("+++++ DELETE PROJECT DETAILS +++++");
		String status = "n";
		String sql = "update project_detail set status=? where project_detail_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, projectdetailid);
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
	public void deleteProjectPriceDetail(int projectpriceinfoid) {
		logger.info("+++++ DELETE PROJECT DETAILS +++++");
		String status = "n";
		String sql = "update project_price_info set status=? where project_price_info_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, projectpriceinfoid);
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
	public void deleteProjectPaymentSchedule(int id) {
		logger.info("+++++ DELETE PROJECT PAYMENT SCHEDULE +++++");
		String status = "n";
		String sql = "update project_payment_schedule set status=? where project_payment_schedule_id=?";
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
    public void deleteProjectFloorLayout(int floorlayoutid) {
        logger.info("+++++ DELETE PROJECT FLOOR LAYOUT +++++");
        
        String sql = "delete from project_floor_layout where project_floor_layout_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, floorlayoutid);
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
	public Project getProjectDetailById(int projectid) {
		logger.info("+++++ PROJECT DETAIL BY ID +++++");
		Project project = null;//project_Image
		
		String sql = "select shown_project,project_Image, project_id, project_title, project_subtitle, project_code, project_logo, location_id, architect_id, structural_designer_id, legal_advisor_id, developer_company_id, property_type_id, rera_approve, rera_no, layout_map, water_source, drainage, work_status, description, project_pdf, site_link, sequence from project where project_id=?";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				project = new Project(rs.getInt("project_id"), rs.getString("project_title"),
						rs.getString("project_subtitle"), rs.getString("project_code"), rs.getString("project_logo"),
						rs.getInt("location_id"), rs.getInt("architect_id"), rs.getInt("structural_designer_id"),
						rs.getInt("legal_advisor_id"), rs.getInt("developer_company_id"), rs.getInt("property_type_id"),
						rs.getString("rera_approve"), rs.getString("rera_no"), rs.getString("layout_map"),
						rs.getString("water_source"), rs.getString("drainage"), rs.getString("work_status"),
						rs.getString("description"),rs.getString("project_Image"), rs.getString("project_pdf"), rs.getString("site_link"), rs.getInt("sequence"), rs.getString("shown_project"));
			}
			rs.close();
			ps.close();
			return project;
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
	public int getLastProjectId() {
		logger.info("+++++ GET LAST PROJECT ID +++++");
		String sql = "select project_id from project order by project_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("project_id");
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
	public void addProjectSpecification(ProjectSpecification p) {
		logger.info("+++++ ADD PROJECT SPECIFICATION +++++");
		String sql = "insert into project_specification (project_id, title, subtitle, description, attachment, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getProjectId());
			ps.setString(2, p.getTitle());
			ps.setString(3, p.getSubtitle());
			ps.setString(4, p.getDescription());
			ps.setString(5, p.getAttachment());
			ps.setInt(6, p.getCreatedBy());
			ps.setString(7, p.getIpAddress());
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
	public void addProjectAmenity(ProjectAmenity p) {
		logger.info("+++++ ADD PROJECT AMENITY +++++");
		String sql = "insert into project_amenity (project_id, title, subtitle, description, attachment, created_by, ip_address) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getProjectId());
			ps.setString(2, p.getTitle());
			ps.setString(3, p.getSubtitle());
			ps.setString(4, p.getDescription());
			ps.setString(5, p.getAttachment());
			ps.setInt(6, p.getCreatedBy());
			ps.setString(7, p.getIpAddress());
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
	public List<ProjectSpecification> getProjectSpecificationById(int projectid) {
		logger.info("***** GET PROJECT SPECIFICATION BY ID *****");
		List<ProjectSpecification> sta = new ArrayList<ProjectSpecification>();
		String sql = "select project_specification_id, project_id, title, subtitle, description, attachment from project_specification where project_id=? order by project_specification_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ProjectSpecification projectSpecification = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectSpecification = new ProjectSpecification(rs.getInt("project_specification_id"),
						rs.getInt("project_id"), rs.getString("title"), rs.getString("subtitle"),
						rs.getString("description"), rs.getString("attachment"));
				sta.add(projectSpecification);
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
	public List<ProjectAmenity> getProjectAmenityById(int projectid) {
		logger.info("***** GET PROJECT AMENITY BY ID *****");
		List<ProjectAmenity> sta = new ArrayList<ProjectAmenity>();
		String sql = "select project_amenity_id, project_id, title, subtitle, description, attachment from project_amenity where project_id=? order by project_amenity_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ProjectAmenity projectAmenity = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectAmenity = new ProjectAmenity(rs.getInt("project_amenity_id"), rs.getInt("project_id"),
						rs.getString("title"), rs.getString("subtitle"), rs.getString("description"),
						rs.getString("attachment"));
				sta.add(projectAmenity);
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
	public List<ProjectArea> getProjectAreaById(int projectid) {
		logger.info("***** GET PROJECT AREA BY ID *****");
		List<ProjectArea> sta = new ArrayList<ProjectArea>();
		String sql = "select pa.project_area_id, pa.project_id, pa.category_id, pa.subcategory_id, pa.realestate_id, pa.unit_master_id, pa.area_id, pa.unit_id, pa.area_value, rt.realestate_type_name, rs.subcategory_title, r.realestate_title, a.area_type_title, mu.measurement_unit_name, um.unit_name from project_area pa left join unit_master um on pa.unit_master_id = um.unit_master_id, realestate_type rt, realestate_subcategory rs, realestate r, area_type a, measurement_unit mu where project_id=? and pa.category_id = rt.realestate_type_id and pa.subcategory_id = rs.realestate_subcategory_id and pa.realestate_id = r.realestate_id and pa.area_id = a.area_type_id and pa.unit_id = mu.measurement_unit_id order by project_area_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ProjectArea projectArea = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectArea = new ProjectArea(rs.getInt("project_area_id"), rs.getInt("project_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("realestate_id"),
						rs.getInt("unit_master_id"), rs.getInt("area_id"), rs.getInt("unit_id"),
						rs.getString("area_value"), rs.getString("realestate_type_name"),
						rs.getString("subcategory_title"), rs.getString("realestate_title"),
						rs.getString("area_type_title"), rs.getString("measurement_unit_name"),
						rs.getString("unit_name"));
				sta.add(projectArea);
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
	public List<ProjectDetail> getProjectDetailsById(int projectid) {
		logger.info("***** GET PROJECT DETAILS BY ID *****");
		List<ProjectDetail> sta = new ArrayList<ProjectDetail>();
		String s = "y";
		String sql = "select pd.project_detail_id, pd.project_id, pd.category_id, pd.subcategory_id, pd.unit_master_id, pd.realestate_id, pd.number_of_units, rs.realestate_type_name, rss.subcategory_title, rst.realestate_title, um.unit_name from project_detail pd left join unit_master um on pd.unit_master_id = um.unit_master_id, realestate_type rs, realestate_subcategory rss, realestate rst  where pd.status=? and pd.category_id = rs.realestate_type_id and pd.subcategory_id = rss.realestate_subcategory_id and pd.realestate_id = rst.realestate_id and project_id=? order by project_detail_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, projectid);
			ProjectDetail projectDetail = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectDetail = new ProjectDetail(rs.getInt("project_detail_id"), rs.getInt("project_id"),
						rs.getInt("category_id"), rs.getInt("subcategory_id"), rs.getInt("unit_master_id"),
						rs.getInt("realestate_id"), rs.getString("number_of_units"),
						rs.getString("realestate_type_name"), rs.getString("subcategory_title"),
						rs.getString("realestate_title"), rs.getString("unit_name"));
				sta.add(projectDetail);
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
	public List<ProjectPriceInfo> getProjectPriceDetailsById(int projectid) {
		logger.info("***** GET PROJECT PRICE DETAILS BY ID *****");
		List<ProjectPriceInfo> sta = new ArrayList<ProjectPriceInfo>();
		String s = "y";
		String sql = "select pp.project_price_info_id, pp.project_id, pp.price_lable, pp.price_value, rs.realestate_type_name, rss.subcategory_title, rst.realestate_title, um.unit_name, mu.measurement_unit_name from project_price_info pp left join unit_master um on pp.unit_master_id = um.unit_master_id, realestate_type rs, realestate_subcategory rss, realestate rst,measurement_unit mu  where pp.status=? and pp.category_id = rs.realestate_type_id and pp.subcategory_id = rss.realestate_subcategory_id and pp.realestate_id = rst.realestate_id and pp.unit_type_id = mu.measurement_unit_id and project_id=? order by project_price_info_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, projectid);
			ProjectPriceInfo projectPriceInfo = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectPriceInfo = new ProjectPriceInfo(rs.getInt("project_price_info_id"), rs.getInt("project_id"),
						rs.getString("price_lable"), rs.getString("price_value"), rs.getString("realestate_type_name"),
						rs.getString("subcategory_title"), rs.getString("realestate_title"), rs.getString("unit_name"),
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
	public List<ProjectPaymentSchedule> getProjectPaymentScheduleById(int projectid) {
		logger.info("***** GET PROJECT PRICE DETAILS BY ID *****");
		List<ProjectPaymentSchedule> sta = new ArrayList<ProjectPaymentSchedule>();
		String s = "y";
		String sql = "select pp.project_payment_schedule_id, pp.project_id, pp.sequence, pp.sequence_title, pp.payment_lable, pp.payment_value, pp.unit_id, mu.measurement_unit_name from project_payment_schedule pp,measurement_unit mu  where pp.status=? and pp.unit_id = mu.measurement_unit_id and project_id=? order by pp.sequence";
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
						rs.getInt("project_id"), rs.getString("sequence"), rs.getString("sequence_title"),
						rs.getString("payment_lable"), rs.getFloat("payment_value"), rs.getInt("unit_id"),
						rs.getString("measurement_unit_name"));
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
	public void deleteSpecification(int projectspecificationid) {
		logger.info("+++++ DELETE SPECIFICATION +++++");
		String sql = "delete from project_specification where project_specification_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectspecificationid);
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
	public void deleteAmenity(int projectamenityid) {
		logger.info("+++++ DELETE AMENITY +++++");
		String sql = "delete from project_amenity where project_amenity_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectamenityid);
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
    public void deleteProjectBank(int projectbankid) {
        logger.info("+++++ DELETE PROJECT BANK +++++");
        String sql = "delete from project_bank where project_bank_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, projectbankid);
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
	public List<Project> getProjectName() {
		logger.info("***** GET PROJECT *****");
		List<Project> sta = new ArrayList<Project>();
		String s = "y";
		String sql = "select project_id, project_title from project where status=? order by project_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Project project = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				project = new Project(rs.getInt("project_id"), rs.getString("project_title"));
				sta.add(project);
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
	public String addProjectArea(ProjectArea pa) {
		logger.info("+++++ ADD PROJECT AREA +++++");
		String sql = "insert into project_area (project_id, category_id, subcategory_id, realestate_id, unit_master_id, area_id, unit_id, area_value, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pa.getProjectId());
			ps.setInt(2, pa.getCategoryId());
			ps.setInt(3, pa.getSubcategoryId());
			ps.setInt(4, pa.getRealestateId());
			ps.setInt(5, pa.getUnitMasterId());
			ps.setInt(6, pa.getAreaId());
			ps.setInt(7, pa.getUnitId());
			ps.setString(8, pa.getAreaValue());
			ps.setInt(9, pa.getCreatedBy());
			ps.setString(10, pa.getIpAddress());
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
	public String addProjectDetails(ProjectDetail td) {
		logger.info("+++++ ADD PROJECT DETAILS+++++");
		String sql = "insert into project_detail (project_id, category_id, subcategory_id, unit_master_id, realestate_id, number_of_units, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, td.getProjectId());
			ps.setInt(2, td.getCategoryId());
			ps.setInt(3, td.getSubcategoryId());
			ps.setInt(4, td.getUnitMasterId());
			ps.setInt(5, td.getRealestaeId());
			ps.setString(6, td.getNumberOfUnits());
			ps.setString(7, td.getStatus());
			ps.setInt(8, td.getCreatedBy());
			ps.setString(9, td.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate project code!";
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
	public String addProjectPriceDetails(ProjectPriceInfo pp) {
		logger.info("+++++ ADD PROJECT PRICE DETAILS+++++");
		String sql = "insert into project_price_info (project_id, category_id, subcategory_id, realestate_id, unit_master_id, price_lable, price_value, unit_type_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pp.getProjectId());
			ps.setInt(2, pp.getCategoryId());
			ps.setInt(3, pp.getSubcategoryId());
			ps.setInt(4, pp.getRealestaeId());
			ps.setInt(5, pp.getUnitMasterId());
			ps.setString(6, pp.getPriceLable());
			ps.setString(7, pp.getPriceValue());
			ps.setInt(8, pp.getUnitId());
			ps.setString(9, pp.getStatus());
			ps.setInt(10, pp.getCreatedBy());
			ps.setString(11, pp.getIpAddress());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException e) {
			return "Data not added! Duplicate project code!";
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
	public String addProjectPaymentSchedule(ProjectPaymentSchedule pps) {
		logger.info("+++++ ADD PROJECT PAYMENT SCHEDULE +++++");
		String sql = "insert into project_payment_schedule (project_id, sequence, sequence_title, payment_lable, payment_value, unit_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pps.getProjectId());
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
			return "Data not added! Duplicate project code!";
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
	public void deleteProjectAreaRow(int projectareaid) {
		logger.info("+++++ DELETE PROJECT AREA +++++");
		String sql = "delete from project_area where project_area_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectareaid);
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
	public List<ProjectDetail> getProjectSubcategoryById(int projectid, int categoryid) {
		logger.info("+++++ GET PROJECT SUBCATEGORY BY ID +++++");
		List<ProjectDetail> sta = new ArrayList<ProjectDetail>();
		String sql = "select pd.subcategory_id, rs.subcategory_title from project_detail pd, realestate_subcategory rs where pd.project_id=? and pd.category_id=? and pd.subcategory_id = rs.realestate_subcategory_id group by pd.subcategory_id order by rs.subcategory_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ps.setInt(2, categoryid);
			ProjectDetail projectDetail = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectDetail = new ProjectDetail(rs.getInt("subcategory_id"), rs.getString("subcategory_title"));
				sta.add(projectDetail);
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
	public List<ProjectDetail> getProjectRealestateTypeById(int projectid, int categoryid, int subcategoryid) {
		logger.info("+++++ GET PROJECT REAL ESTATE TYPE BY ID +++++");
		List<ProjectDetail> sta = new ArrayList<ProjectDetail>();
		String sql = "select pd.subcategory_id, pd.realestate_id, r.realestate_title from project_detail pd, realestate r where pd.project_id=? and pd.category_id=? and pd.subcategory_id=? and pd.realestate_id = r.realestate_id group by pd.realestate_id order by r.realestate_title";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ps.setInt(2, categoryid);
			ps.setInt(3, subcategoryid);
			ProjectDetail projectDetail = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectDetail = new ProjectDetail(rs.getInt("subcategory_id"), rs.getInt("realestate_id"),
						rs.getString("realestate_title"));
				sta.add(projectDetail);
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
	public List<ProjectDetail> getProjectUnitById(int projectid, int categoryid, int subcategoryid, int typeid) {
		logger.info("+++++ PROJECT UNIT BY ID +++++");
		List<ProjectDetail> sta = new ArrayList<ProjectDetail>();

		String sql = "select um.unit_name, pd.unit_master_id from project_detail pd left join unit_master um on pd.unit_master_id = um.unit_master_id where project_id=? and category_id=? and subcategory_id=? and realestate_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ps.setInt(2, categoryid);
			ps.setInt(3, subcategoryid);
			ps.setInt(4, typeid);
			ResultSet rs = ps.executeQuery();
			ProjectDetail projectDetail = null;
			while (rs.next()) {
				projectDetail = new ProjectDetail(rs.getString("unit_name"), rs.getInt("unit_master_id"));
				sta.add(projectDetail);
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
	public List<ProjectArea> getProjectAreaForPropertyById(int projectid, int categoryid, int subcategoryid,
			int typeid) {
		logger.info("+++++ GET PROJECT REAL ESTATE TYPE BY ID +++++");
		List<ProjectArea> sta = new ArrayList<ProjectArea>();
		String sql = "select pa.area_id, pa.unit_id, pa.area_value, a.area_type_title, mu.measurement_unit_name from project_area pa, area_type a, measurement_unit mu where pa.project_id=? and pa.category_id=? and pa.subcategory_id=? and pa.realestate_id=? and pa.area_id = a.area_type_id and pa.unit_id = mu.measurement_unit_id order by project_area_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ps.setInt(2, categoryid);
			ps.setInt(3, subcategoryid);
			ps.setInt(4, typeid);
			ProjectArea projectArea = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectArea = new ProjectArea(rs.getInt("area_id"), rs.getInt("unit_id"), rs.getString("area_value"),
						rs.getString("area_type_title"), rs.getString("measurement_unit_name"));
				sta.add(projectArea);
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
	public List<ProjectArea> getProjectAreaForPropertyById(int projectid, int categoryid, int subcategoryid, int typeid,
			int unitmasterid) {
		logger.info("+++++ GET PROJECT REAL ESTATE TYPE BY ID +++++");
		List<ProjectArea> sta = new ArrayList<ProjectArea>();
		String sql = "select pa.area_id, pa.unit_id, pa.area_value, a.area_type_title, mu.measurement_unit_name from project_area pa, area_type a, measurement_unit mu where pa.project_id=? and pa.category_id=? and pa.subcategory_id=? and pa.realestate_id=? and pa.unit_master_id=? and pa.area_id = a.area_type_id and pa.unit_id = mu.measurement_unit_id order by project_area_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, projectid);
			ps.setInt(2, categoryid);
			ps.setInt(3, subcategoryid);
			ps.setInt(4, typeid);
			ps.setInt(5, unitmasterid);
			ProjectArea projectArea = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectArea = new ProjectArea(rs.getInt("area_id"), rs.getInt("unit_id"), rs.getString("area_value"),
						rs.getString("area_type_title"), rs.getString("measurement_unit_name"));
				sta.add(projectArea);
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
	public List<ProjectPriceInfo> getProjectUnitPriceForPropertyById(int projectid, int categoryid, int subcategoryid,
			int typeid) {
		logger.info("***** GET PROJECT UNIT PRICE FOR PROPERTY BY ID *****");
		List<ProjectPriceInfo> sta = new ArrayList<ProjectPriceInfo>();
		String s = "y";
		String sql = "select pp.price_lable, pp.price_value, pp.unit_type_id, mu.measurement_unit_name from project_price_info pp, measurement_unit mu where pp.status=? and pp.project_id=? and pp.category_id=? and pp.subcategory_id=? and pp.realestate_id=? and pp.unit_type_id = mu.measurement_unit_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, projectid);
			ps.setInt(3, categoryid);
			ps.setInt(4, subcategoryid);
			ps.setInt(5, typeid);
			ProjectPriceInfo projectPriceInfo = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectPriceInfo = new ProjectPriceInfo(rs.getString("price_lable"), rs.getString("price_value"),
						rs.getInt("unit_type_id"), rs.getString("measurement_unit_name"));
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
	public List<ProjectPriceInfo> getProjectUnitPriceForPropertyById(int projectid, int categoryid, int subcategoryid,
			int typeid, int unitmasterid) {
		logger.info("***** GET PROJECT UNIT PRICE FOR PROPERTY BY ID *****");
		List<ProjectPriceInfo> sta = new ArrayList<ProjectPriceInfo>();
		String s = "y";
		String sql = "select pp.price_lable, pp.price_value, pp.unit_type_id, mu.measurement_unit_name from project_price_info pp, measurement_unit mu where pp.status=? and pp.project_id=? and pp.category_id=? and pp.subcategory_id=? and pp.realestate_id=? and pp.unit_master_id=? and pp.unit_type_id = mu.measurement_unit_id";
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
			ProjectPriceInfo projectPriceInfo = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				projectPriceInfo = new ProjectPriceInfo(rs.getString("price_lable"), rs.getString("price_value"),
						rs.getInt("unit_type_id"), rs.getString("measurement_unit_name"));
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
	public RealestateType getRealestateTypeDetailById(int realecateid) {
		logger.info("+++++ GET REALESTATE TYPE DETAIL BY ID +++++");
		RealestateType realestateType = null;
		String sql = "select realestate_type_id, realestate_type_name, realestate_code, image, description from realestate_type where realestate_type_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, realecateid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				realestateType = new RealestateType(rs.getInt("realestate_type_id"),
						rs.getString("realestate_type_name"), rs.getString("realestate_code"), rs.getString("image"),
						rs.getString("description"));
			}
			rs.close();
			ps.close();
			return realestateType;
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
	public RealestateSubcategory getRealestateSubcategoryDetailById(int realestatesubid) {
		logger.info("+++++ GET REALESTATE SUBCATEGORY DETAIL BY ID +++++");
		RealestateSubcategory realestateSubcategory = null;
		String sql = "select realestate_subcategory_id, realestate_type_id, subcategory_title, subcategory_code, image, description from realestate_subcategory where realestate_subcategory_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, realestatesubid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				realestateSubcategory = new RealestateSubcategory(rs.getInt("realestate_subcategory_id"),
						rs.getInt("realestate_type_id"), rs.getString("subcategory_title"),
						rs.getString("subcategory_code"), rs.getString("image"), rs.getString("description"));
			}
			rs.close();
			ps.close();
			return realestateSubcategory;
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
	public Realestate getRealestateDetailById(int realestatetypeid) {
		logger.info("+++++ GET REALESTATE TYPE DETAIL BY ID +++++");
		Realestate realestate = null;
		String sql = "select realestate_id, realestate_type_id, realestate_subcategory_id, realestate_title, realestate_code, image, description from realestate where realestate_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, realestatetypeid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				realestate = new Realestate(rs.getInt("realestate_id"), rs.getInt("realestate_type_id"),
						rs.getInt("realestate_subcategory_id"), rs.getString("realestate_title"),
						rs.getString("realestate_code"), rs.getString("image"), rs.getString("description"));
			}
			rs.close();
			ps.close();
			return realestate;
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
	public List<UnitMaster> getUnitNameList() {
		logger.info("***** GET UNIT NAME LIST *****");
		List<UnitMaster> sta = new ArrayList<UnitMaster>();
		String s = "y";
		String sql = "select unit_master_id, unit_name from unit_master where status=? order by unit_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			UnitMaster unitMaster = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				unitMaster = new UnitMaster(rs.getInt("unit_master_id"), rs.getString("unit_name"));
				sta.add(unitMaster);
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
	public void addUnitMaster(UnitMaster u) {
		logger.info("+++++ ADD UNIT MASTER +++++");
		String sql = "insert into unit_master (unit_name, status, created_by, ip_address) values (?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUnitName());
			ps.setString(2, u.getStatus());
			ps.setInt(3, u.getCreatedBy());
			ps.setString(4, u.getIpAddress());
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
	public Project getProjectDetailByName(String projectname) {
		logger.info("+++++ PROJECT DETAIL BY NAME +++++");
		Project p = null;
		String sql = "select project_id, project_title, project_subtitle, project_code from project where project_title=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, projectname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Project();
				p.setProjectId(rs.getInt("project_id"));
				p.setProjectTitle(rs.getString("project_title"));
				p.setProjectSubtitle(rs.getString("project_subtitle"));
				p.setProjectCode(rs.getString("project_code"));
			}
			rs.close();
			ps.close();
			return p;
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
	public Project getProjectDetailByCode(String projectcode) {
		logger.info("+++++ PROJECT DETAIL BY CODE +++++");
		Project p = null;
		String sql = "select project_id, project_title, project_subtitle, project_code from project where project_code=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, projectcode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Project();
				p.setProjectId(rs.getInt("project_id"));
				p.setProjectTitle(rs.getString("project_title"));
				p.setProjectSubtitle(rs.getString("project_subtitle"));
				p.setProjectCode(rs.getString("project_code"));
			}
			rs.close();
			ps.close();
			return p;
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
	public RealestateType getRealestateTypeDetailByName(String categoryname) {
		logger.info("+++++ REALESTATE TYPE DETAIL BY NAME +++++");
		RealestateType rt = null;
		String sql = "select realestate_type_id, realestate_type_name, realestate_code from realestate_type where realestate_type_name=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categoryname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rt = new RealestateType();
				rt.setRealestateTypeId(rs.getInt("realestate_type_id"));
				rt.setRealestateTypeName(rs.getString("realestate_type_name"));
				rt.setRealestateCode(rs.getString("realestate_code"));
			}
			rs.close();
			ps.close();
			return rt;
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
	public RealestateType getRealestateTypeDetailByCode(String categorycode) {
		logger.info("+++++ REALESTATE TYPE DETAIL BY CODE +++++");
		RealestateType rt = null;
		String sql = "select realestate_type_id, realestate_type_name, realestate_code from realestate_type where realestate_code=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, categorycode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rt = new RealestateType();
				rt.setRealestateTypeId(rs.getInt("realestate_type_id"));
				rt.setRealestateTypeName(rs.getString("realestate_type_name"));
				rt.setRealestateCode(rs.getString("realestate_code"));
			}
			rs.close();
			ps.close();
			return rt;
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
	public RealestateSubcategory getRealestateSubcategoryDetailByName(String subcategoryname) {
		logger.info("+++++ REALESTATE SUBCATEGORY DETAIL BY NAME +++++");
		RealestateSubcategory rsc = null;
		String sql = "select realestate_subcategory_id, subcategory_title, subcategory_code from realestate_subcategory where subcategory_title=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, subcategoryname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rsc = new RealestateSubcategory();
				rsc.setRealestateSubcategoryId(rs.getInt("realestate_subcategory_id"));
				rsc.setSubcategoryTitle(rs.getString("subcategory_title"));
				rsc.setSubcategoryCode(rs.getString("subcategory_code"));
			}
			rs.close();
			ps.close();
			return rsc;
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
	public RealestateSubcategory getRealestateSubcategoryDetailByCode(String subcategorycode) {
		logger.info("+++++ REALESTATE SUBCATEGORY DETAIL BY NAME +++++");
		RealestateSubcategory rsc = null;
		String sql = "select realestate_subcategory_id, subcategory_title, subcategory_code from realestate_subcategory where subcategory_code=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, subcategorycode);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rsc = new RealestateSubcategory();
				rsc.setRealestateSubcategoryId(rs.getInt("realestate_subcategory_id"));
				rsc.setSubcategoryTitle(rs.getString("subcategory_title"));
				rsc.setSubcategoryCode(rs.getString("subcategory_code"));
			}
			rs.close();
			ps.close();
			return rsc;
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
    public List<ProjectDetail> getProjectUnitByProjectId(int projectid) {
        logger.info("+++++ PROJECT UNIT BY PROJECT ID +++++");
        List<ProjectDetail> sta = new ArrayList<ProjectDetail>();

        String sql = "select um.unit_name, pd.unit_master_id from project_detail pd left join unit_master um on pd.unit_master_id = um.unit_master_id where project_id=? and pd.unit_master_id != 0 group by um.unit_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, projectid);
            ResultSet rs = ps.executeQuery();
            ProjectDetail projectDetail = null;
            while (rs.next()) {
                projectDetail = new ProjectDetail(rs.getString("unit_name"), rs.getInt("unit_master_id"));
                sta.add(projectDetail);
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
    public List<Project> getProjectNameAndEnquiryCount() {
        logger.info("***** GET PROJECT NAME AND ENQUIRY COUNT *****");
        List<Project> sta = new ArrayList<Project>();
        String s = "n";
        String s1="y";
        String sql = "select p.project_id, p.project_title, p.project_logo, count(e.enquiry_id) as project_enquiry_count from project p left join enquiry_property ep on p.project_id=ep.project_id left join enquiry e on ep.enquiry_id=e.enquiry_id where e.status!=? and p.status=? group by p.project_id order by p.project_title";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setString(2, s1);
            Project p = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              p = new Project();
              p.setProjectId(rs.getInt("project_id"));
              p.setProjectTitle(rs.getString("project_title"));
              p.setProjectLogo(rs.getString("project_logo"));
              p.setProjectEnquiryCount(rs.getInt("project_enquiry_count"));
              sta.add(p);
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
    public List<Project> getProjectStatusWiseCount() {
        logger.info("***** GET PROJECT COUNT BY STATUS *****");
        List<Project> sta = new ArrayList<Project>();
        String s = "y";
        String sql = "select p.project_id, p.project_title, p.project_logo, count(ep.enquiry_id) as project_enquiry_count from project p left join enquiry_property ep on p.project_id=ep.project_id where p.status=? group by p.project_id order by p.project_title";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            Project p = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              p = new Project();
              p.setProjectId(rs.getInt("project_id"));
              p.setProjectTitle(rs.getString("project_title"));
              p.setProjectLogo(rs.getString("project_logo"));
              p.setProjectEnquiryCount(rs.getInt("project_enquiry_count"));
              sta.add(p);
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
    public void addProjectFloorLayout(ProjectFloorLayout pfl) {
        logger.info("+++++ ADD PROJECT FLOOR LAYOUT +++++");
        String sql = "insert into project_floor_layout (project_id, category_id, unit_id, floor_number, image, total_unit, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pfl.getProjectId());
            ps.setInt(2, pfl.getCategoryId());
            ps.setInt(3, pfl.getUnitId());
            ps.setString(4, pfl.getFloorNumber());
            ps.setString(5, pfl.getImage());
            ps.setString(6, pfl.getTotalUnit());
            ps.setInt(7, pfl.getCreatedBy());
            ps.setString(8, pfl.getIpAddress());
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
    public List<ProjectFloorLayout> getProjectFloorLayoutById(int projectid) {
        logger.info("***** GET PROJECT FLOOR LAYOUT BY ID *****");
        List<ProjectFloorLayout> sta = new ArrayList<ProjectFloorLayout>();
        String s = "y";
        String sql = "select pp.project_floor_layout_id, pp.floor_number, pp.image, pp.total_unit, p.project_title, um.unit_name, r.realestate_type_name from project_floor_layout pp left join project p on pp.project_id=p.project_id left join unit_master um on pp.unit_id=um.unit_master_id left join realestate_type r on pp.category_id=r.realestate_type_id where pp.project_id=? order by pp.project_floor_layout_id";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, projectid);
            ProjectFloorLayout projectFloorLayout = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              projectFloorLayout = new ProjectFloorLayout();
              projectFloorLayout.setProjectFloorLayoutId(rs.getInt("project_floor_layout_id"));
              projectFloorLayout.setFloorNumber(rs.getString("floor_number"));
              projectFloorLayout.setImage(rs.getString("image"));
              projectFloorLayout.setTotalUnit(rs.getString("total_unit"));
              projectFloorLayout.setProjectTitle(rs.getString("project_title"));
              projectFloorLayout.setUnitName(rs.getString("unit_name"));
              projectFloorLayout.setCategoryName(rs.getString("realestate_type_name"));
              
                sta.add(projectFloorLayout);
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
    public List<ProjectBank> getAllProjectBankById(int projectid) {
        logger.info("***** GET PROJECT BANK BY ID *****");
        List<ProjectBank> sta = new ArrayList<ProjectBank>();
        String s = "y";
        String sql = "select pp.project_bank_id, b.bank_name, p.project_title from project_bank pp left join project p on pp.project_id=p.project_id left join bank b on pp.bank_id=b.bank_id where pp.project_id=? order by b.bank_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, projectid);
            ProjectBank b = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              
              b = new ProjectBank();
              b.setProjectBankId(rs.getInt("project_bank_id"));
              b.setTitle(rs.getString("bank_name"));
              b.setProjectTitle(rs.getString("project_title"));
              
                sta.add(b);
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
    public String addProjectBank(ProjectBank b) {
        logger.info("+++++ ADD PROJECT BANKS +++++");
        String sql = "insert into project_bank (project_id, bank_id, title, created_by, ip_address) values (?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, b.getProjectId());
            ps.setInt(2, b.getBankId());
            ps.setString(3, b.getTitle());
            ps.setInt(4, b.getCreatedBy());
            ps.setString(5, b.getIpAddress());
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

}
