package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.AllCountDAO;
import com.ui.model.AllCount;

public class AllCountDAOImpl implements AllCountDAO {
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(AllCountDAOImpl.class);

	public AllCount getAllCounts(HttpSession session) {
		logger.info("+++++ GET ALL COUNTER IMPL +++++");
		AllCount allCount = null;
		Connection con = null;
		int typeid = Integer.parseInt(session.getAttribute("usertypeidadmin").toString());
		int userid = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String sql = "";
		if (typeid == 2) {
			sql = "select (select count(country_id) from country where status = 'y') as country_row_count, (select count(currency_id) from currency where status = 'y') as currency_row_count, (select count(district_id) from district where status = 'y') as district_row_count, (select count(financial_year_id) from financial_year where status = 'y') as financial_year_row_count, (select count(location_id) from location where status = 'y') as location_row_count, (select count(measurement_unit_id) from measurement_unit where status = 'y') as measurement_row_count, (select count(state_id) from state where status = 'y') as state_row_count, (select count(taluka_id) from taluka where status = 'y') as taluka_row_count, (select count(tax_id) from tax where status = 'y') as tax_row_count, (select count(project_id) from project where status = 'y') as project_row_count, (select count(user_id) from user where status = 'y') as user_row_count, (select count(property_id) from property where status = 'y') as property_row_count, (select count(payment_id) from payment where status = 'y') as payment_count, (select count(e.enquiry_id) from enquiry e left join enquiry_assign ea on e.enquiry_id=ea.enquiry_id where e.status!='n' && e.status!='cl' && e.status!='cw' and ea.user_id = "
					+ userid
					+ ") as enquiry_row_count, (select count(e.enquiry_id) from enquiry e left join enquiry_assign ea on e.enquiry_id=ea.enquiry_id where e.status='y' and ea.user_id ="
					+ userid
					+ ") as new_enquiry_row_count, (select count(e.enquiry_id) from enquiry e left join enquiry_assign ea on e.enquiry_id=ea.enquiry_id where e.status='l' and ea.user_id ="
					+ userid
					+ ") as lost_enquiry_row_count, (select count(e.enquiry_id) from enquiry e left join enquiry_assign ea on e.enquiry_id=ea.enquiry_id where e.status='o' and ea.user_id ="
					+ userid
					+ ") as working_enquiry_row_count, (select count(e.enquiry_id) from enquiry e left join enquiry_assign ea on e.enquiry_id=ea.enquiry_id where e.status='w' and ea.user_id ="
					+ userid
					+ ") as won_enquiry_row_count, (select count(e.enquiry_id) from enquiry e left join enquiry_assign ea on e.enquiry_id=ea.enquiry_id where (e.status='cl' or e.status='cw') and ea.user_id ="
					+ userid
					+ ") as close_enquiry_row_count, (select count(e.enquiry_id) from enquiry e left join enquiry_assign ea on e.enquiry_id=ea.enquiry_id where e.status='h' and ea.user_id ="
					+ userid
					+ ") as hold_enquiry_row_count, (select count(realestate_type_id) from realestate_type where status = 'y') as category_row_count, (select count(realestate_subcategory_id) from realestate_subcategory where status = 'y') as subcategory_row_count, (select count(realestate_id) from realestate where status = 'y') as type_row_count, (select count(p.project_id) as product_count from employee_project ep left join  project p on ep.project_id=p.project_id where p.status='y' and p.work_status='ongoing' and ep.user_id="
					+ userid
					+ ") as ongoing_project_row_count, (select count(p.project_id) as product_count from employee_project ep left join  project p on ep.project_id=p.project_id where p.status='y' and p.work_status='Completed' and ep.user_id="
					+ userid + ") as completed_project_row_count, (select count(bank_id) from bank where status = 'y') as bank_row_count, (select count(workstatus_id) from workstatus where status = 'y') as workstatus_row_count";
		} else {
			sql = "select (select count(country_id) from country where status = 'y') as country_row_count, (select count(currency_id) from currency where status = 'y') as currency_row_count, (select count(district_id) from district where status = 'y') as district_row_count, (select count(financial_year_id) from financial_year where status = 'y') as financial_year_row_count, (select count(location_id) from location where status = 'y') as location_row_count, (select count(measurement_unit_id) from measurement_unit where status = 'y') as measurement_row_count, (select count(state_id) from state where status = 'y') as state_row_count, (select count(taluka_id) from taluka where status = 'y') as taluka_row_count, (select count(tax_id) from tax where status = 'y') as tax_row_count, (select count(project_id) from project where status = 'y') as project_row_count, (select count(user_id) from user where status = 'y') as user_row_count, (select count(property_id) from property where status = 'y') as property_row_count, (select count(payment_id) from payment where status = 'y') as payment_count, (select count(enquiry_id) from enquiry where status!='n' && status!='cl' && status!='cw') as enquiry_row_count, (select count(enquiry_id) from enquiry where status='y') as new_enquiry_row_count, (select count(enquiry_id) from enquiry where status='l') as lost_enquiry_row_count, (select count(enquiry_id) from enquiry where status='o') as working_enquiry_row_count, (select count(enquiry_id) from enquiry where status='w') as won_enquiry_row_count, (select count(enquiry_id) from enquiry where status in('cl','cw')) as close_enquiry_row_count, (select count(enquiry_id) from enquiry where status='h') as hold_enquiry_row_count, (select count(realestate_type_id) from realestate_type where status = 'y') as category_row_count, (select count(realestate_subcategory_id) from realestate_subcategory where status = 'y') as subcategory_row_count, (select count(realestate_id) from realestate where status = 'y') as type_row_count, (select count(project_id) from project where status = 'y' and work_status='Ongoing') as ongoing_project_row_count, (select count(project_id) from project where status = 'y' and work_status='Completed') as completed_project_row_count, (select count(bank_id) from bank where status = 'y') as bank_row_count, (select count(workstatus_id) from workstatus where status = 'y') as workstatus_row_count";
		}

		try {
			con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				allCount = new AllCount(rs.getInt("country_row_count"), rs.getInt("currency_row_count"),
						rs.getInt("district_row_count"), rs.getInt("financial_year_row_count"),
						rs.getInt("location_row_count"), rs.getInt("measurement_row_count"),
						rs.getInt("state_row_count"), rs.getInt("taluka_row_count"), rs.getInt("tax_row_count"),
						rs.getInt("project_row_count"), rs.getInt("user_row_count"), rs.getInt("property_row_count"),
						rs.getInt("payment_count"), rs.getInt("enquiry_row_count"), rs.getInt("new_enquiry_row_count"),
						rs.getInt("lost_enquiry_row_count"), rs.getInt("working_enquiry_row_count"),
						rs.getInt("won_enquiry_row_count"), rs.getInt("close_enquiry_row_count"),
						rs.getInt("hold_enquiry_row_count"), rs.getInt("category_row_count"),
						rs.getInt("subcategory_row_count"), rs.getInt("type_row_count"),
						rs.getInt("ongoing_project_row_count"), rs.getInt("completed_project_row_count"), rs.getInt("bank_row_count"), rs.getInt("workstatus_row_count"));
			}
			rs.close();
			ps.close();

			return allCount;
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
				}
			}
		}
	}

}
