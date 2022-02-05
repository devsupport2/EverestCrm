package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.EnquiryDAO;
import com.ui.model.AllEnquiryStatus;
import com.ui.model.Enquiry;
import com.ui.model.EnquiryAssign;
import com.ui.model.EnquiryFollowup;
import com.ui.model.EnquiryProperty;
import com.ui.model.EnquiryStatus;
import com.ui.model.EnquiryStatusReason;
import com.ui.model.FollowupEmployee;
import com.ui.model.User;

public class EnquiryDAOImpl implements EnquiryDAO {
	JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(EnquiryDAOImpl.class);

	
	@Override
    public List<Enquiry> getLastHundredEnquiryList(HttpSession session) {
        logger.info("+++++ GET Last Hundred ENQUIRIES +++++");
        List<Enquiry> sta = new ArrayList<Enquiry>();
        String s = "n";
        String s1 = "cl";
        String s2 = "cw";
        String sql = null;
        int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
        int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
        if(typeid==1){
          sql = "select e.enquiry_id, e.enquiry_no, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name, p.project_title, um.unit_name, pr.property_title, rs.subcategory_title, r.realestate_title, concat(ref.user_company_name,' - ',ref.first_name,' ',ref.middle_name,' ',ref.last_name) as reference_by from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id left join enquiry_property ep on e.enquiry_id = ep.enquiry_id left join project p on ep.project_id = p.project_id left join unit_master um on ep.property_unit_master_id = um.unit_master_id left join property pr on ep.property_id = pr.property_id left join realestate_subcategory rs on ep.subcategory_id = rs.realestate_subcategory_id left join realestate r on ep.realestate_id = r.realestate_id left join user ref on e.reference_id = ref.user_id where e.status!=? and e.status!=? and e.status!=? order by e.enquiry_id desc limit 0,100";
        }
        if(typeid==2){
          sql = "select e.enquiry_id, e.enquiry_no, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name, p.project_title, um.unit_name, pr.property_title, rs.subcategory_title, r.realestate_title, concat(ref.user_company_name,' - ',ref.first_name,' ',ref.middle_name,' ',ref.last_name) as reference_by from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id left join enquiry_property ep on e.enquiry_id = ep.enquiry_id left join project p on ep.project_id = p.project_id left join unit_master um on ep.property_unit_master_id = um.unit_master_id left join property pr on ep.property_id = pr.property_id left join realestate_subcategory rs on ep.subcategory_id = rs.realestate_subcategory_id left join realestate r on ep.realestate_id = r.realestate_id left join user ref on e.reference_id = ref.user_id where e.status!=? and e.status!=? and e.status!=? order by e.enquiry_id desc limit 0,200";
        }
        

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setString(2, s1);
            ps.setString(3, s2);
            Enquiry e = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e = new Enquiry();
                
                e.setEnquiryId(rs.getInt("enquiry_id"));
                e.setEnquiryNo(rs.getString("enquiry_no"));
                e.setEnquiryDate(rs.getString("enquiry_date"));
                e.setStatus(rs.getString("status"));
                e.setUserCompanyName(rs.getString("user_company_name"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setMobileNumber(rs.getString("mobile_number"));
                e.setEmail(rs.getString("email"));
                e.setCountryName(rs.getString("country_name"));
                e.setProjectTitle(rs.getString("project_title"));
                e.setUnitName(rs.getString("unit_name"));
                e.setPropertyTitle(rs.getString("property_title"));
                e.setSubcategoryTitle(rs.getString("subcategory_title"));
                e.setRealestateTitle(rs.getString("realestate_title"));
                e.setReferenceBy(rs.getString("reference_by"));
                
                sta.add(e);
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
	public List<Enquiry> getEnquiryListByDate(String startdate, String enddate) {
		logger.info("+++++ GET ALL OPEN ENQUIRIES BY DATE +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		String s1 = "cl";
		String s2 = "cw";

		String sql = "select e.enquiry_id, e.enquiry_no, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name, p.project_title, um.unit_name, pr.property_title, rs.subcategory_title, r.realestate_title, concat(ref.user_company_name,' - ',ref.first_name,' ',ref.middle_name,' ',ref.last_name) as reference_by from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id left join enquiry_property ep on e.enquiry_id = ep.enquiry_id left join project p on ep.project_id = p.project_id left join unit_master um on ep.property_unit_master_id = um.unit_master_id left join property pr on ep.property_id = pr.property_id left join realestate_subcategory rs on ep.subcategory_id = rs.realestate_subcategory_id left join realestate r on ep.realestate_id = r.realestate_id left join user ref on e.reference_id = ref.user_id where e.status!=? and e.status!=? and e.status!=? and e.enquiry_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') order by e.enquiry_date desc";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, s1);
			ps.setString(3, s2);
			ps.setString(4, startdate);
			ps.setString(5, enddate);
			Enquiry e = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				e = new Enquiry();
				
				e.setEnquiryId(rs.getInt("enquiry_id"));
				e.setEnquiryNo(rs.getString("enquiry_no"));
				e.setEnquiryDate(rs.getString("enquiry_date"));
				e.setStatus(rs.getString("status"));
				e.setUserCompanyName(rs.getString("user_company_name"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setMobileNumber(rs.getString("mobile_number"));
				e.setEmail(rs.getString("email"));
				e.setCountryName(rs.getString("country_name"));
				e.setProjectTitle(rs.getString("project_title"));
				e.setUnitName(rs.getString("unit_name"));
				e.setPropertyTitle(rs.getString("property_title"));
				e.setSubcategoryTitle(rs.getString("subcategory_title"));
				e.setRealestateTitle(rs.getString("realestate_title"));
				e.setReferenceBy(rs.getString("reference_by"));
				
				sta.add(e);
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
	public List<Enquiry> getAllOpenEnquiries(HttpSession session) {
		logger.info("+++++ GET ALL OPEN ENQUIRIES IMPL +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		String s1 = "cl";
		String s2 = "cw";

		String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id where e.status!=? and e.status!=? and e.status!=? order by e.enquiry_date desc";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, s1);
			ps.setString(3, s2);
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("mobile_number"), rs.getString("email"),
						rs.getString("country_name"));
				sta.add(enquiry);
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
	public List<Enquiry> searchEnquiry(String keyword, HttpSession session) {
		logger.info("+++++ SEARCH ENQUIRIES +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		String s1 = "cl";
		String s2 = "cw";
		
		/*String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name, s.state_name from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id left join state s on u.state_id = s.state_id left join enquiry_property ep on e.enquiry_id=ep.enquiry_id left join unit_master um on ep.property_unit_master_id=um.unit_master_id left join property p on ep.property_id=p.property_id left join project pp on ep.project_id = pp.project_id left join realestate_type rt on ep.category_id=rt.realestate_type_id left join realestate_subcategory rs on ep.subcategory_id = rs.realestate_subcategory_id where e.status!=? and e.status!=? and e.status!=? and (concat(e.enquiry_no) like ? or concat(u.first_name) like ? or concat(u.last_name) like ? or concat(u.city_name) like ? or concat(u.mobile_number) like ? or concat(u.email) like ? or concat(e.enquiry_remarks) like ? or concat(um.unit_name) like ? or concat(p.property_title) like ? or concat(c.country_name) like ? or concat(s.state_name) like ? or concat(pp.project_title) like ? or concat(rt.realestate_type_name) like ? or concat(rs.subcategory_title) like ?) group by e.enquiry_id order by e.enquiry_date desc";*/
		String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name, s.state_name, concat(r.first_name,' ',r.last_name) as reference_by, concat(u.first_name,' ',u.last_name) as client_name from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id left join state s on u.state_id = s.state_id left join enquiry_property ep on e.enquiry_id=ep.enquiry_id left join unit_master um on ep.property_unit_master_id=um.unit_master_id left join property p on ep.property_id=p.property_id left join project pp on ep.project_id = pp.project_id left join realestate_type rt on ep.category_id=rt.realestate_type_id left join realestate_subcategory rs on ep.subcategory_id = rs.realestate_subcategory_id left join user r on e.reference_id = r.user_id where e.status!=? and e.status!=? and e.status!=? and (concat(e.enquiry_no) like ? or concat(u.first_name) like ? or concat(u.last_name) like ? or concat(u.city_name) like ? or concat(u.mobile_number) like ? or concat(u.email) like ? or concat(e.enquiry_remarks) like ? or concat(um.unit_name) like ? or concat(p.property_title) like ? or concat(c.country_name) like ? or concat(s.state_name) like ? or concat(pp.project_title) like ? or concat(rt.realestate_type_name) like ? or concat(rs.subcategory_title) like ? or concat(r.first_name) like ? or concat(r.last_name) like ? or concat(r.user_company_name) like ? or concat(r.first_name,' ',r.last_name) like ? or concat(u.first_name,' ',u.last_name) like ?) group by e.enquiry_id order by e.enquiry_date desc";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, s1);
			ps.setString(3, s2);
			ps.setString(4, "%"+keyword+"%");
			ps.setString(5, "%"+keyword+"%");
			ps.setString(6, "%"+keyword+"%");
			ps.setString(7, "%"+keyword+"%");
			ps.setString(8, "%"+keyword+"%");
			ps.setString(9, "%"+keyword+"%");
			ps.setString(10, "%"+keyword+"%");
			ps.setString(11, "%"+keyword+"%");
			ps.setString(12, "%"+keyword+"%");
			ps.setString(13, "%"+keyword+"%");
			ps.setString(14, "%"+keyword+"%");
			ps.setString(15, "%"+keyword+"%");
            ps.setString(16, "%"+keyword+"%");
            ps.setString(17, "%"+keyword+"%");
            ps.setString(18, "%"+keyword+"%");
            ps.setString(19, "%"+keyword+"%");
            ps.setString(20, "%"+keyword+"%");
            ps.setString(21, "%"+keyword+"%");
            ps.setString(22, "%"+keyword+"%");
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("mobile_number"), rs.getString("email"),
						rs.getString("country_name"), rs.getString("reference_by"));
				sta.add(enquiry);
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
	public List<Enquiry> getEnquiriesByStatus(String status, HttpSession session) {
		logger.info("+++++ GET ENQUIRIES BY STATUS +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String sql = "";
		if (!status.equals("c") && !status.equals("a")) {

			sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id where e.status=? order by e.enquiry_date desc";
		}

		if (status.equals("c")) {

			sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id where (e.status=? or e.status=?) order by e.enquiry_date desc";

		}

		if (status.equals("a")) {

			sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id where e.status!=? order by e.enquiry_date desc";

		}
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (!status.equals("c") && !status.equals("a")) {
				ps.setString(1, status);
			}
			if (status.equals("c")) {
				ps.setString(1, "cl");
				ps.setString(2, "cw");
			}
			if (status.equals("a")) {
				ps.setString(1, "n");
			}

			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("mobile_number"), rs.getString("email"),
						rs.getString("country_name"));
				sta.add(enquiry);
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
	public List<Enquiry> getEnquiriesByPage(int pagesize, int startindex, HttpSession session) {
		logger.info("+++++ GET ENQUIRIES BY PAGE IMPL +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name, concat(r.first_name,' ',r.last_name) as reference_by from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id left join user r on e.reference_id = r.user_id where e.status!=? order by e.enquiry_date desc limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("mobile_number"), rs.getString("email"),
						rs.getString("country_name"), rs.getString("reference_by"));
				sta.add(enquiry);
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
	public List<User> getReferenceTitleByUserTypeId(int usertypeid) {
		logger.info("+++++ REFERENCE BY USER TYPE ID IMPL +++++");
		List<User> sta = new ArrayList<User>();
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name,mobile_number from user where user_type_id=? and status='y' order by first_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, usertypeid);
			User user = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"));
				sta.add(user);
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
	public List<User> getClientAndProspectTitle() {
		logger.info("+++++ REFERENCE BY USER TYPE ID IMPL +++++");
		List<User> sta = new ArrayList<User>();
		String s = "y";
		int usertypeid1 = 3;
		int usertypeid2 = 5;
		String sql = "select user_id, user_company_name, first_name, middle_name, last_name , mobile_number from user where status=? and user_type_id=? or user_type_id=? order by first_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, usertypeid1);
			ps.setInt(3, usertypeid2);
			User user = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("middle_name"), rs.getString("last_name"),rs.getString("mobile_number"));
				sta.add(user);
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
	public List<EnquiryFollowup> getEnquiryFollowupByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY FOLLOW-UP BY ENQUIRY ID IMPL +++++");
		List<EnquiryFollowup> sta = new ArrayList<EnquiryFollowup>();
		String sql = "select ef.followup_id, ef.enquiry_id, DATE_FORMAT(ef.followup_date_time,'%d/%m/%Y %h:%i %p') as followup_date_time, ef.remark, ef.created_by, u.first_name, u.last_name from enquiry_followup ef, user u where ef.enquiry_id=? and ef.created_by = u.user_id order by ef.followup_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			EnquiryFollowup enquiryFollowup = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiryFollowup = new EnquiryFollowup(rs.getInt("followup_id"), rs.getInt("enquiry_id"),
						rs.getString("followup_date_time"), rs.getString("remark"), rs.getInt("created_by"),
						rs.getString("first_name"), rs.getString("last_name"));
				sta.add(enquiryFollowup);
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
	public void addStatusReason(EnquiryStatusReason esr) {
		logger.info("+++++ ADD STATUS REASON +++++");
		String sql = "insert into enquiry_status_reason(status_reason, status, created_by, ip_address) values (?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, esr.getStatusReason());
			ps.setString(2, esr.getStatus());
			ps.setInt(3, esr.getCreatedBy());
			ps.setString(4, esr.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void insertEnquiryStatus(AllEnquiryStatus aes) {
		logger.info("+++++ INSERT ENQUIRY STATUS +++++");
		String sql = "insert into all_enquiry_status(enquiry_id, enquiry_status, reason_id, status, created_by, ip_address) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aes.getEnquiryId());
			ps.setString(2, aes.getEnquiryStatus());
			ps.setInt(3, aes.getReasonId());
			ps.setString(4, aes.getStatus());
			ps.setInt(5, aes.getCreatedBy());
			ps.setString(6, aes.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public String markCompleteFollowup(EnquiryFollowup e) {
		logger.info("+++++ MARK FOLLOWUP TO COMPLETE +++++");
		String sql = "update enquiry_followup set status=?, created_by=?, ip_address=? where followup_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getStatus());
			ps.setInt(2, e.getCreatedBy());
			ps.setString(3, e.getIpAddress());
			ps.setInt(4, e.getFollowupId());
			ps.executeUpdate();
			return "Success";
		} catch (SQLException ex) {
			return "Something wrong!";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void deleteFollowup(int followupid) {
		logger.info("+++++ DELETE FOLLOWUP +++++");
		String sql = "delete from enquiry_followup where followup_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, followupid);
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
	public void changeEnquiryStatus(Enquiry e) {
		logger.info("+++++ CHANGE ENQUIRY STATUS +++++");
		String sql = "update enquiry set status=?, reason_id=? where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getStatus());
			ps.setInt(2, e.getReasonId());
			ps.setInt(3, e.getEnquiryId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void addEnquiry(Enquiry e) {
		logger.info("+++++ ADD ENQUIRY IMPL +++++");
		String sql = "insert into enquiry(sequence, enquiry_no, reference_id, client_id, employee_id, enquiry_date, enquiry_via, enquiry_remarks, occupation_id, designation_id, project_type, budget_id, important_features, considering_project, finalized_expecting_time, status, created_by, ip_address) values (?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, e.getSequence());
			ps.setString(2, e.getEnquiryNo());
			ps.setInt(3, e.getReferenceId());
			ps.setInt(4, e.getClientId());
			ps.setInt(5, e.getEmployeeId());
			ps.setString(6, e.getEnquiryDate());
			ps.setString(7, e.getEnquiryVia());
			ps.setString(8, e.getEnquriRemarks());
			ps.setInt(9, e.getOccupationId());
			ps.setInt(10, e.getDesignationId());
			ps.setString(11, e.getProjectType());
			ps.setInt(12, e.getBudgetId());
			ps.setString(13, e.getImportantFeatures());
			ps.setString(14, e.getConsideringProject());
			ps.setInt(15, e.getFinalizedExpectingTime());
			ps.setString(16, e.getStatus());
			ps.setInt(17, e.getCreatedBy());
			ps.setString(18, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void editEnquiry(Enquiry e) {
		logger.info("+++++ EDIT ENQUIRY IMPL +++++");
		String sql = "update enquiry set reference_id=?, client_id=?, employee_id=?, enquiry_date=STR_TO_DATE(?,'%d/%m/%Y'), enquiry_via=?, enquiry_remarks=?, occupation_id=?, designation_id=?, project_type=?, budget_id=?, important_features=?, considering_project=?, finalized_expecting_time=?, created_by=?, ip_address=? where enquiry_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, e.getReferenceId());
			ps.setInt(2, e.getClientId());
			ps.setInt(3, e.getEmployeeId());
			ps.setString(4, e.getEnquiryDate());
			ps.setString(5, e.getEnquiryVia());
			ps.setString(6, e.getEnquriRemarks());
			ps.setInt(7, e.getOccupationId());
			ps.setInt(8, e.getDesignationId());
			ps.setString(9, e.getProjectType());
			ps.setInt(10, e.getBudgetId());
			ps.setString(11, e.getImportantFeatures());
			ps.setString(12, e.getConsideringProject());
			ps.setInt(13, e.getFinalizedExpectingTime());
			ps.setInt(14, e.getCreatedBy());
			ps.setString(15, e.getIpAddress());
			ps.setInt(16, e.getEnquiryId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void openEnquiry(int enquiryid) {
		logger.info("+++++ CHANGE ENQUIRY IMPL +++++");
		String status = "o";
		String sql = "update enquiry set status=? where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, enquiryid);
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
	public void addEnquiryLog(EnquiryStatus e) {
		logger.info("+++++ ADD ENQUIRY LOG +++++");
		String sql = "insert into enquiry_status(enquiry_id, status_id, remark, log_date_time, status, created_by, ip_address) values (?,?,?,STR_TO_DATE(?,'%d/%m/%Y %h:%i %p'),?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setInt(2, e.getStatusId());
			ps.setString(3, e.getRemark());
			ps.setString(4, e.getLogDateTime());
			ps.setString(5, e.getStatus());
			ps.setInt(6, e.getCreatedBy());
			ps.setString(7, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public List<EnquiryStatus> getEnquiryLogByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY LOG BY ENQUIRY ID IMPL +++++");
		List<EnquiryStatus> sta = new ArrayList<EnquiryStatus>();
		String sql = "select es.enquiry_status_id, es.enquiry_id, es.quotation_id, es.status_id, DATE_FORMAT(es.log_date_time,'%d/%m/%Y %h:%i %p') as log_date_time, es.created_by, DATE_FORMAT(CONVERT_TZ(es.created_date,'+00:00','+09:29'),'%d/%m/%Y %h:%i %p') as created_date, e.enquiry_no, s.status_name, u.first_name, u.last_name from enquiry_status es, enquiry e, user u, status s where es.enquiry_id=? and es.enquiry_id = e.enquiry_id and es.created_by = u.user_id and es.status_id = s.status_id union select es.enquiry_status_id, es.enquiry_id, es.quotation_id, es.status_id, DATE_FORMAT(es.log_date_time,'%d/%m/%Y %h:%i %p') as log_date_time, es.created_by, DATE_FORMAT(CONVERT_TZ(es.created_date,'+00:00','+09:29'),'%d/%m/%Y %h:%i %p') as created_date, e.enquiry_no, remark as status_name, u.first_name, u.last_name from enquiry_status es, enquiry e, user u where es.enquiry_id=? and es.status_id=0 and es.enquiry_id = e.enquiry_id and es.created_by = u.user_id order by enquiry_status_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			ps.setInt(2, enquiryid);
			EnquiryStatus enquiryStatus = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryStatus = new EnquiryStatus(rs.getInt("enquiry_status_id"), rs.getInt("enquiry_id"),
						rs.getInt("quotation_id"), rs.getInt("status_id"), rs.getString("log_date_time"),
						rs.getInt("created_by"), rs.getString("created_date"), rs.getString("enquiry_no"),
						rs.getString("status_name"), rs.getString("first_name"), rs.getString("last_name"));
				sta.add(enquiryStatus);
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
	public List<EnquiryProperty> getAllEnquiryPropertyName() {
		logger.info("+++++ ALL ENQUIRY PROPERTY NAME +++++");
		List<EnquiryProperty> sta = new ArrayList<EnquiryProperty>();
		String s = "y";
		String sql = "select ep.enquiry_property_id, ep.enquiry_id, um.unit_name, p.property_title, pj.project_title , rs.realestate_type_name, rss.subcategory_title, rst.realestate_title from enquiry_property ep left join unit_master um on ep.property_unit_master_id=um.unit_master_id, property p, project pj, realestate_type rs, realestate_subcategory rss, realestate rst where ep.status=? and ep.project_id = pj.project_id and  ep.category_id = rs.realestate_type_id and ep.subcategory_id = rss.realestate_subcategory_id and ep.realestate_id = rst.realestate_id and ep.property_id = p.property_id order by ep.enquiry_property_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			EnquiryProperty enquiryProperty = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiryProperty = new EnquiryProperty(rs.getInt("enquiry_property_id"), rs.getInt("enquiry_id"),
						rs.getString("unit_name"), rs.getString("property_title"), rs.getString("project_title"),
						rs.getString("realestate_type_name"), rs.getString("subcategory_title"),
						rs.getString("realestate_title"));
				sta.add(enquiryProperty);
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
	public List<EnquiryProperty> getEnquiryPropertyByEnquiryId(int enquiryid) {
		logger.info("+++++ GET ENQUIRY PROPERTY BY ENQUIRY ID +++++");
		List<EnquiryProperty> sta = new ArrayList<EnquiryProperty>();
		String sql = "select ep.enquiry_property_id, ep.enquiry_id, ep.project_id, um.unit_name, p.property_title, pj.project_title , rs.realestate_type_name, rss.subcategory_title, rst.realestate_title from enquiry_property ep left join unit_master um on ep.property_unit_master_id=um.unit_master_id left join property p on ep.property_id = p.property_id left join project pj on ep.project_id = pj.project_id left join realestate_type rs on ep.category_id = rs.realestate_type_id left join realestate_subcategory rss on ep.subcategory_id = rss.realestate_subcategory_id left join realestate rst on ep.realestate_id = rst.realestate_id where ep.status=? and ep.enquiry_id=? order by ep.enquiry_property_id";
		Connection conn = null;
		String s = "y";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, enquiryid);
			EnquiryProperty enquiryProperty = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryProperty = new EnquiryProperty(rs.getInt("enquiry_property_id"), rs.getInt("enquiry_id"),
						rs.getInt("project_id"), rs.getString("unit_name"), rs.getString("property_title"),
						rs.getString("project_title"), rs.getString("realestate_type_name"),
						rs.getString("subcategory_title"), rs.getString("realestate_title"));
				sta.add(enquiryProperty);
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
	public void addEnquiryFollowup(EnquiryFollowup e) {
		logger.info("+++++ ADD ENQUIRY FOLLOW-UP +++++");
		String sql = "insert into enquiry_followup(enquiry_id, followup_date_time, remark, status, created_by, ip_address) values (?,STR_TO_DATE(?,'%d/%m/%Y %h:%i %p'),?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setString(2, e.getFollowupDateTime());
			ps.setString(3, e.getRemark());
			ps.setString(4, e.getStatus());
			ps.setInt(5, e.getCreatedBy());
			ps.setString(6, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void addEnquiryProperty(EnquiryProperty ep) {
		logger.info("+++++ ADD ENQUIRY PROPERTY IMPL +++++");
		String sql = "insert into enquiry_property(enquiry_id, project_id, category_id, subcategory_id, realestate_id, property_unit_master_id, property_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, ep.getEnquiryId());
			ps.setInt(2, ep.getProjectId());
			ps.setInt(3, ep.getCategoryId());
			ps.setInt(4, ep.getSubcategoryId());
			ps.setInt(5, ep.getRealestaeId());
			ps.setInt(6, ep.getPropertyUnitMasterId());
			ps.setInt(7, ep.getPropertyId());
			ps.setString(8, ep.getStatus());
			ps.setInt(9, ep.getCreatedBy());
			ps.setString(10, ep.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public Enquiry getEnquiryDetailsById(int enquiryid) {
		logger.info("+++++ GET ENQUIRY DETAILS BY ID IMPL +++++");
		Enquiry enquiry = null;
		String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, e.occupation_id, e.designation_id, e.project_type, e.budget_id, e.important_features, e.considering_project, e.finalized_expecting_time from enquiry e, user u where e.client_id = u.user_id and e.enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("mobile_number"), rs.getString("email"), rs.getInt("occupation_id"), rs.getInt("designation_id"), rs.getString("project_type"), rs.getInt("budget_id"), rs.getString("important_features"), rs.getString("considering_project"), rs.getInt("finalized_expecting_time"));
			}
			rs.close();
			ps.close();
			return enquiry;
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
	public List<EnquiryStatusReason> getStatusReason() {
		logger.info("+++++ GET STATUS REASON +++++");
		List<EnquiryStatusReason> sta = new ArrayList<EnquiryStatusReason>();
		String s = "y";
		String sql = "select reason_id, status_reason from enquiry_status_reason where status=? order by status_reason";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			EnquiryStatusReason enquiryStatusReason = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiryStatusReason = new EnquiryStatusReason(rs.getInt("reason_id"), rs.getString("status_reason"));
				sta.add(enquiryStatusReason);
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
	public List<Enquiry> getTodayFollowupEnquiries(HttpSession session) {
		logger.info("+++++ GET TODAY FOLLOWUP ENQUIRIES +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (typeid == 2) {
			sql = "select * from (select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry e, enquiry_assign ea, user u, enquiry_followup ef where e.status!=? and ea.user_id = "
					+ userid
					+ " and ea.enquiry_id=e.enquiry_id and e.client_id = u.user_id and e.enquiry_id = ef.enquiry_id and date_format(ef.followup_date_time,'%Y-%m-%d') = curdate() union select null, null, null, null, null, null, null, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from followup_employee fe left join enquiry_followup ef on fe.followup_id=ef.followup_id, user u where fe.user_id=u.user_id and date_format(ef.followup_date_time,'%Y-%m-%d') = curdate() and fe.user_id="
					+ userid + ") a order by followup_time";

		} else {
			sql = "select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry_followup ef left join enquiry e on ef.enquiry_id = e.enquiry_id and e.status!=? left join user u on e.client_id = u.user_id where date_format(ef.followup_date_time,'%Y-%m-%d') = curdate() order by ef.followup_date_time";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getString("enquiry_no"), rs.getInt("client_id"),
						rs.getString("enquiry_date"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getInt("followup_id"), rs.getString("followup_time"),
						rs.getString("remark"), rs.getString("followup_status"));
				sta.add(enquiry);
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
	public List<Enquiry> getFollowupEnquiriesByDate(HttpSession session, String fromdate, String todate) {
		logger.info("+++++ GET FOLLOWUP ENQUIRIES BY DATE +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String s = "n";
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (typeid == 2) {
			sql = "select * from (select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%d/%m/%Y') as followup_date, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry e, enquiry_assign ea, user u, enquiry_followup ef where e.status!=? and ea.user_id = "
					+ userid
					+ " and ea.enquiry_id=e.enquiry_id and e.client_id = u.user_id and e.enquiry_id = ef.enquiry_id and ef.followup_date_time between str_to_date(?, '%d/%m/%Y') and str_to_date(?, '%d/%m/%Y') union select null, null, null, null, null, null, null, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%d/%m/%Y') as followup_date, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from followup_employee fe left join enquiry_followup ef on fe.followup_id=ef.followup_id, user u where fe.user_id=u.user_id and ef.followup_date_time between str_to_date(?, '%d/%m/%Y') and str_to_date(?, '%d/%m/%Y') and fe.user_id="
					+ userid + ") a order by followup_date";
		} else {
			sql = "select e.enquiry_id, e.enquiry_no, e.client_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, u.user_company_name, u.first_name, u.last_name, ef.followup_id, DATE_FORMAT(ef.followup_date_time,'%d/%m/%Y') as followup_date, DATE_FORMAT(ef.followup_date_time,'%h:%i %p') as followup_time, ef.remark, ef.status as followup_status from enquiry_followup ef left join enquiry e on ef.enquiry_id = e.enquiry_id and e.status!=? left join user u on e.client_id = u.user_id where ef.followup_date_time between str_to_date(?, '%d/%m/%Y') and str_to_date(?, '%d/%m/%Y') order by ef.followup_date_time";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (typeid == 2) {
				ps.setString(1, s);
				ps.setString(2, fromdate);
				ps.setString(3, todate);
				ps.setString(4, fromdate);
				ps.setString(5, todate);
			} else {
				ps.setString(1, s);
				ps.setString(2, fromdate);
				ps.setString(3, todate);
			}

			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getString("enquiry_no"), rs.getInt("client_id"),
						rs.getString("enquiry_date"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getInt("followup_id"), rs.getString("followup_date"),
						rs.getString("followup_time"), rs.getString("remark"), rs.getString("followup_status"));
				sta.add(enquiry);
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
	public int getLastEnquiryId() {
		logger.info("+++++ GET LAST ENQUIRY ID IMPL +++++");
		String sql = "select enquiry_id from enquiry order by enquiry_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("enquiry_id");
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

	public int getLastEnquirySequence() {
		logger.info("++++++++++ GET LAST ENQUIRY SEQUENCE IMPL ++++++++++");
		String sql = "select sequence from enquiry order by enquiry_id desc limit 0,1";
		int sequence = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sequence = rs.getInt("sequence");
			}
			rs.close();
			ps.close();
			return sequence;
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
	public Enquiry getLastEnquiryDetail() {
		logger.info("+++++ GET LAST ENQUIRY DETAIL IMPL +++++");
		Enquiry enquiry = null;
		String sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email from enquiry e, user u where e.client_id = u.user_id order by e.enquiry_id desc limit 0,1";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
						rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
						rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
						rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("mobile_number"), rs.getString("email"));
			}
			rs.close();
			ps.close();
			return enquiry;
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
	public void addEnquiryStatus(EnquiryStatus e) {
		logger.info("+++++ ADD ENQUIRY STATUS IMPL +++++");
		String sql = "insert into enquiry_status(enquiry_id, quotation_id, status_id, status, created_by, ip_address) values (?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setInt(2, e.getQuotationId());
			ps.setInt(3, e.getStatusId());
			ps.setString(4, e.getStatus());
			ps.setInt(5, e.getCreatedBy());
			ps.setString(6, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void deleteEnquiryProperty(int enquirypropertyid) {
		logger.info("+++++ DELETE ENQUIRY PROPERTY +++++");
		String status = "n";
		String sql = "update enquiry_property set status=? where enquiry_property_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, enquirypropertyid);
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
	public int getLastFollowupId() {
		logger.info("+++++ GET LAST FOLLOWUP ID +++++");
		String sql = "select followup_id from enquiry_followup order by followup_id desc limit 0,1";
		int id = 0;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("followup_id");
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
	public void addEmpFollowup(FollowupEmployee e) {
		logger.info("+++++ ADD FOLLOWUP EMPLOYEE +++++");
		String sql = "insert into followup_employee(followup_id, user_id, created_by, ip_address) values (?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getFollowupId());
			ps.setInt(2, e.getUserId());
			ps.setInt(3, e.getCreatedBy());
			ps.setString(4, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public void addEnquiryAssign(EnquiryAssign e) {
		logger.info("+++++ ADD ENQUIRY ASSIGN +++++");
		String sql = "insert into enquiry_assign(enquiry_id, user_id, created_by, ip_address) values (?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getEnquiryId());
			ps.setInt(2, e.getUserId());
			ps.setInt(3, e.getCreatedBy());
			ps.setString(4, e.getIpAddress());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
	}

	@Override
	public List<EnquiryAssign> getEnquiryAssignByEnquiryId(int enquiryid) {
		logger.info("+++++ ENQUIRY ASSIGN BY ENQUIRY ID +++++");
		List<EnquiryAssign> sta = new ArrayList<EnquiryAssign>();
		String sql = "select ea.enquiry_assign_id, ea.enquiry_id, ea.user_id, u.first_name, u.last_name from enquiry_assign ea left join user u on ea.user_id = u.user_id where ea.enquiry_id=? order by ea.enquiry_assign_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, enquiryid);
			EnquiryAssign enquiryAssign = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				enquiryAssign = new EnquiryAssign(rs.getInt("enquiry_assign_id"), rs.getInt("enquiry_id"),
						rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"));
				sta.add(enquiryAssign);
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
	public void deleteEnquiryAssignRow(int id) {
		logger.info("+++++ DELETE ENQUIRY ASSIGN +++++");
		String sql = "delete from enquiry_assign where enquiry_assign_id=?";
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

	@Override
	public EnquiryStatus getLastEnquiryStatusDetail() {
		logger.info("+++++ GET LAST ENQUIRY STATUS DETAIL IMPL +++++");
		EnquiryStatus enquiryStatus = null;
		String sql = "select enquiry_status_id, enquiry_id, quotation_id, sales_order_id, remark, DATE_FORMAT(log_date_time,'%d/%m/%Y %h:%i %p') as log_date_time from enquiry_status order by enquiry_status_id desc limit 0,1";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiryStatus = new EnquiryStatus(rs.getInt("enquiry_status_id"), rs.getInt("enquiry_id"),
						rs.getInt("quotation_id"), rs.getInt("sales_order_id"), rs.getString("remark"),
						rs.getString("log_date_time"));
			}
			rs.close();
			ps.close();
			return enquiryStatus;
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
	public EnquiryFollowup getLastEnquiryFollowupDetail() {
		logger.info("+++++ GET LAST ENQUIRY FOLLOWUP DETAIL IMPL +++++");
		EnquiryFollowup enquiryFollowup = null;
		String sql = "select followup_id, enquiry_id, DATE_FORMAT(followup_date_time,'%d/%m/%Y %h:%i %p') as followup_date_time, remark from enquiry_followup order by followup_id desc limit 0,1";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiryFollowup = new EnquiryFollowup(rs.getInt("followup_id"), rs.getInt("enquiry_id"),
						rs.getString("followup_date_time"), rs.getString("remark"));
			}
			rs.close();
			ps.close();
			return enquiryFollowup;
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
	public List<FollowupEmployee> getFollowupEmployeeByFollowupId(int followupid) {
		logger.info("+++++ GET FOLLOWUP EMPLOYEE BY FOLLOWUP ID +++++");
		List<FollowupEmployee> sta = new ArrayList<FollowupEmployee>();
		String sql = "select followup_emp_id, followup_id, user_id from followup_employee where followup_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, followupid);
			FollowupEmployee followupEmployee = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				followupEmployee = new FollowupEmployee(rs.getInt("followup_emp_id"), rs.getInt("followup_id"),
						rs.getInt("user_id"));
				sta.add(followupEmployee);
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
	public void deleteEnquiry(int enquiryid) {
		logger.info("+++++ DELETE ENQUIRY IMPL +++++");
		String status = "n";
		String sql = "update enquiry set status=? where enquiry_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, enquiryid);
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
	public List<Enquiry> getEnquiryListCountryWise() {
		logger.info("+++++ GET ALL ENQUIRIES COUNTRY WISE IMPL +++++");
		List<Enquiry> sta = new ArrayList<Enquiry>();
		String sql = "select c.country_name, count(e.enquiry_id) as country_enquiry_count from country c left join user u on c.country_id = u.country_id left join enquiry e on u.user_id = e.client_id group by c.country_name";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Enquiry enquiry = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enquiry = new Enquiry();
				enquiry.setCountryName(rs.getString("country_name"));
				enquiry.setCountryEnquiryCount(rs.getInt("country_enquiry_count"));
				sta.add(enquiry);
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
    public List<Enquiry> getEnquiriesByPageAndId(int pagesize, int startindex, int projectid, HttpSession session) {
        logger.info("+++++ GET ENQUIRIES BY PAGE AND ID IMPL +++++");
        List<Enquiry> sta = new ArrayList<Enquiry>();
        String s = "n";
        String sql = null;
        if(projectid==0){
          sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name, concat(r.first_name,' ',r.last_name) as reference_by from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id left join user r on e.reference_id = r.user_id where e.status!=? order by e.enquiry_date desc limit ?,?";
        } else {
          sql = "select e.enquiry_id, e.sequence, e.enquiry_no, e.reference_id, e.client_id, e.employee_id, DATE_FORMAT(e.enquiry_date,'%d/%m/%Y') as enquiry_date, e.enquiry_via, e.enquiry_remarks, e.status, u.user_company_name, u.first_name, u.last_name, u.mobile_number, u.email, c.country_name, concat(r.first_name,' ',r.last_name) as reference_by from enquiry e left join user u on e.client_id = u.user_id left join country c on u.country_id = c.country_id left join user r on e.reference_id = r.user_id left join enquiry_property ep on e.enquiry_id=ep.enquiry_id where e.status!=? and ep.project_id=? order by e.enquiry_date desc limit ?,?";
        }
        
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if(projectid==0){
              ps.setString(1, s);
              ps.setInt(2, startindex);
              ps.setInt(3, pagesize);
            } else {
              ps.setString(1, s);
              ps.setInt(2, projectid);
              ps.setInt(3, startindex);
              ps.setInt(4, pagesize);
            }
            Enquiry enquiry = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                enquiry = new Enquiry(rs.getInt("enquiry_id"), rs.getInt("sequence"), rs.getString("enquiry_no"),
                        rs.getInt("reference_id"), rs.getInt("client_id"), rs.getInt("employee_id"),
                        rs.getString("enquiry_date"), rs.getString("enquiry_via"), rs.getString("enquiry_remarks"),
                        rs.getString("status"), rs.getString("user_company_name"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("mobile_number"), rs.getString("email"),
                        rs.getString("country_name"), rs.getString("reference_by"));
                sta.add(enquiry);
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
