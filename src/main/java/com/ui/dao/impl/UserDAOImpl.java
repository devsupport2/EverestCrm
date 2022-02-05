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

import com.ui.dao.UserDAO;
import com.ui.model.EmployeeProject;
import com.ui.model.User;
import com.ui.model.UserType;

public class UserDAOImpl implements UserDAO {
    JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public List<UserType> getUserTypes() {
        logger.info("+++++ GET USER TYPE +++++");
        List<UserType> sta = new ArrayList<UserType>();
        String s = "y";
        String sql = "select user_type_id, user_type_name from user_type where status=? order by user_type_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            UserType userType = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userType = new UserType(rs.getInt("user_type_id"), rs.getString("user_type_name"));
                sta.add(userType);
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
    public List<User> getAllUsers() {
        logger.info("+++++ GET ALL USERS +++++");
        List<User> sta = new ArrayList<User>();
        String s = "y";
        String sql = "select user_id, user_company_name, first_name, middle_name, last_name, mobile_number, email from user where status=? order by first_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            User user = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
                        rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"),
                        rs.getString("email"));
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
    public List<User> getUserByType(int usertypeid) {
        logger.info("+++++ GET USER BY USER TYPE +++++");
        List<User> sta = new ArrayList<User>();
        String s = "y";
        String sql = "select user_id, user_company_name, first_name, middle_name, last_name, mobile_number, email from user where status=? and user_type_id=? order by first_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, usertypeid);
            User user = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
                        rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"),
                        rs.getString("email"));
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
    public List<User> getAllUsersByPage(int pagesize, int startindex) {
        logger.info("+++++ GET USERS BY PAGE +++++");
        List<User> sta = new ArrayList<User>();
        String s = "y";
        String sql = "select user_id, user_company_name, first_name, middle_name, last_name, mobile_number, email from user where status=? order by user_company_name limit ?,?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, startindex);
            ps.setInt(3, pagesize);
            User user = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
                        rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"),
                        rs.getString("email"));
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
    public List<User> getUserNameByUserType(int usertypeid) {
        logger.info("+++++ GET CLIENT NAME +++++");
        List<User> sta = new ArrayList<User>();
        String s = "y";
        String sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? and user_type_id = ? order by first_name";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, usertypeid);
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
    public List<User> searchUsers(String keyword) {
        logger.info("+++++ SERACH USERS +++++");
        List<User> sta = new ArrayList<User>();
        String s = "y";
        String sql = "select user_id, user_company_name, first_name, middle_name, last_name, mobile_number, email from user where status=? and concat(user_company_name, '', first_name, '', middle_name, '', last_name) like ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setString(2, '%' + keyword + '%');
            User user = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
                        rs.getString("middle_name"), rs.getString("last_name"), rs.getString("mobile_number"),
                        rs.getString("email"));
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
    public void addUserType(UserType d) {
        logger.info("+++++ ADD USER TYPE +++++");
        String sql = "insert into user_type (user_type_name, status, created_by, ip_address) values (?,?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getUserTypeName());
            ps.setString(2, d.getStatus());
            ps.setInt(3, d.getCreatedBy());
            ps.setString(4, d.getIpAddress());
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
    public void addUser(User u) {
        logger.info("+++++ ADD USER IMPL +++++");
        String sql = "insert into user (user_company_name, first_name, middle_name, last_name, gender, date_of_birth, aadhar_number, passport_number, pan_number, profile_picture, address1, address2, address3, country_id, state_id, city_name, pincode, mobile_number, landline_number, email, password, gst_number, user_type_id, access_project, access_property, access_enquiry, access_payment, access_user, status, created_by, ip_address) values (?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
        	System.out.println("---------------------- ps"+sql);
            
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUserCompanyName());
            ps.setString(2, u.getFirstName());
            ps.setString(3, u.getMiddleName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getGender());
            ps.setString(6, u.getDateOfBirth());
            ps.setString(7, u.getAadharNumber());
            ps.setString(8, u.getPassportNumber());
            ps.setString(9, u.getPanNumber());
            ps.setString(10, u.getProfilePicture());
            ps.setString(11, u.getAddress1());
            ps.setString(12, u.getAddress2());
            ps.setString(13, u.getAddress3());
            ps.setInt(14, u.getCountryId());
            ps.setInt(15, u.getStateId());
            ps.setString(16, u.getCityName());
            ps.setString(17, u.getPincode());
            ps.setString(18, u.getMobileNumber());
            ps.setString(19, u.getLandlineNumber());
            ps.setString(20, u.getEmail());
            ps.setString(21, u.getPassword());
            ps.setString(22, u.getGstNumber());
            ps.setInt(23, u.getUserTypeId());
            ps.setString(24, u.getAccessProject());
            ps.setString(25, u.getAccessProperty());
            ps.setString(26, u.getAccessEnquiry());
            ps.setString(27, u.getAccessPayment());
            ps.setString(28, u.getAccessUser());
            ps.setString(29, u.getStatus());
            ps.setInt(30, u.getCreatedBy());
            ps.setString(31, u.getIpAddress());
            
            System.out.println("---------------------- ps"+ps);
            
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
    public void editUser(User u) {
        logger.info("+++++ EDIT USER IMPL +++++");
        String sql = "update user set user_company_name=?, first_name=?, middle_name=?, last_name=?, gender=?, date_of_birth=STR_TO_DATE(?,'%d/%m/%Y'), aadhar_number=?, passport_number=?, pan_number=?, profile_picture=?, address1=?, address2=?, address3=?, country_id=?, state_id=?, city_name=?, pincode=?, mobile_number=?, landline_number=?, email=?, password=?, user_type_id=?, access_project=?, access_property=?, access_enquiry=?, access_payment=?, access_user=?, gst_number=?, created_by=?, ip_address=? where user_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUserCompanyName());
            ps.setString(2, u.getFirstName());
            ps.setString(3, u.getMiddleName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getGender());
            ps.setString(6, u.getDateOfBirth());
            ps.setString(7, u.getAadharNumber());
            ps.setString(8, u.getPassportNumber());
            ps.setString(9, u.getPanNumber());
            ps.setString(10, u.getProfilePicture());
            ps.setString(11, u.getAddress1());
            ps.setString(12, u.getAddress2());
            ps.setString(13, u.getAddress3());
            ps.setInt(14, u.getCountryId());
            ps.setInt(15, u.getStateId());
            ps.setString(16, u.getCityName());
            ps.setString(17, u.getPincode());
            ps.setString(18, u.getMobileNumber());
            ps.setString(19, u.getLandlineNumber());
            ps.setString(20, u.getEmail());
            ps.setString(21, u.getPassword());
            ps.setInt(22, u.getUserTypeId());
            ps.setString(23, u.getAccessProject());
            ps.setString(24, u.getAccessProperty());
            ps.setString(25, u.getAccessEnquiry());
            ps.setString(26, u.getAccessPayment());
            ps.setString(27, u.getAccessUser());
            ps.setString(28, u.getGstNumber());
            ps.setInt(29, u.getCreatedBy());
            ps.setString(30, u.getIpAddress());
            ps.setInt(31, u.getUserId());
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
    public void deleteUser(int userid) {
        logger.info("+++++ DELETE USER IMPL +++++");
        String status = "n";
        String sql = "update user set status=? where user_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, userid);
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
    public User getUserDetailById(int userid) {
        logger.info("+++++ GET USER DETAIL BY ID +++++");
        User user = null;

        String sql = "select user_id, user_company_name, first_name, middle_name, last_name, gender, DATE_FORMAT(date_of_birth,'%d/%m/%Y') as date_of_birth, aadhar_number, passport_number, pan_number, profile_picture, address1, address2, address3, country_id, state_id, city_name, pincode, mobile_number, landline_number, email, password, gst_number, user_type_id, access_project, access_property, access_enquiry, access_payment, access_user from user where user_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
                        rs.getString("middle_name"), rs.getString("last_name"), rs.getString("gender"),
                        rs.getString("date_of_birth"), rs.getString("aadhar_number"), rs.getString("passport_number"),
                        rs.getString("pan_number"), rs.getString("profile_picture"), rs.getString("address1"),
                        rs.getString("address2"), rs.getString("address3"), rs.getInt("country_id"),
                        rs.getInt("state_id"), rs.getString("city_name"), rs.getString("pincode"),
                        rs.getString("mobile_number"), rs.getString("landline_number"), rs.getString("email"),
                        rs.getString("password"), rs.getString("gst_number"), rs.getInt("user_type_id"), rs.getString("access_project"),
                        rs.getString("access_property"), rs.getString("access_enquiry"), rs.getString("access_payment"),
                        rs.getString("access_user"));
            }
            rs.close();
            ps.close();
            return user;
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
    public int isEmailUnique(String email) {
        logger.info("+++++ IS EMAIL UNIQUE +++++");
        String sql = "select user_id from user where status=? and email=?";
        int id = 0;
        String s = "y";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("user_id");
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
    public int isEmailUniqueWithId(String email, int userid) {
        logger.info("+++++ IS EMAIL UNIQUE +++++");
        String sql = "select user_id from user where status=? and email=? and user_id!=?";
        int id = 0;
        String s = "y";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setString(2, email);
            ps.setInt(3, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("user_id");
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
    public List<User> getUserName() {
        logger.info("+++++ GET USER NAME +++++");
        List<User> sta = new ArrayList<User>();
        String s = "y";
        String sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? order by first_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
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
    public List<User> getAllEmployees() {
        logger.info("Inside Get All Employees Impl");
        List<User> sta = new ArrayList<User>();
        String s = "y";
        int usertypeid = 2;
        String sql = "select user_id, user_company_name, first_name, middle_name, last_name from user where status=? and user_type_id=? order by first_name";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            ps.setInt(2, usertypeid);
            User user = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("user_company_name"), rs.getString("first_name"),
                        rs.getString("middle_name"), rs.getString("last_name"));
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
    public int getLastUserId() {
        logger.info("+++++ GET LAST USER ID +++++");
        String sql = "select user_id from user order by user_id desc limit 0,1";
        int id = 0;
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("user_id");
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
    public UserType getUserTypeDetailByName(String usertypename) {
        logger.info("+++++ GET USER TYPE DETAIL BY NAME +++++");
        UserType userType = null;

        String sql = "select user_type_id, user_type_name from user_type where user_type_name=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usertypename);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userType = new UserType(rs.getInt("user_type_id"), rs.getString("user_type_name"));
            }
            rs.close();
            ps.close();
            return userType;
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
    public int getLastUserTypeId() {
        logger.info("+++++ GET LAST USER TYPE ID +++++");
        String sql = "select user_type_id from user_type order by user_type_id desc limit 0,1";
        int id = 0;
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("user_type_id");
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
    public User getUserDetailByFirstName(String firstname) {
        logger.info("+++++ GET USER DETAIL BY FIRST NAME +++++");
        User user = null;

        String sql = "select user_id from use where first_name=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
            }
            rs.close();
            ps.close();
            return user;
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
    public void addEmployeeProject(EmployeeProject ep) {
        logger.info("+++++ ADD EMPLOYEE PROJECT +++++");
        String sql = "insert into employee_project (user_id, project_id, created_by, ip_address) values (?,?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ep.getUserId());
            ps.setInt(2, ep.getProjectId());
            ps.setInt(3, ep.getCreatedBy());
            ps.setString(4, ep.getIpAddress());
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
    public List<EmployeeProject> getEmployeeProjectListById(int userid) {
        logger.info("+++++ GET EMPLOYEE PROJECT LIST BY ID +++++");
        List<EmployeeProject> sta = new ArrayList<EmployeeProject>();

        String sql = "select ep.employee_project_id, ep.user_id, ep.project_id, p.project_title from employee_project ep left join project p on ep.project_id = p.project_id where user_id=? order by ep.employee_project_id desc";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            EmployeeProject ep = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ep = new EmployeeProject();

                ep.setEmployeeProjectId(rs.getInt("employee_project_id"));
                ep.setUserId(rs.getInt("user_id"));
                ep.setProjectId(rs.getInt("project_id"));
                ep.setProjectTitle(rs.getString("project_title"));

                sta.add(ep);
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
    public void deleteEmployeeProject(int epid) {
        logger.info("+++++ DELETE EMPLOYEE PROJECT +++++");

        String sql = "delete from employee_project where employee_project_id=?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, epid);
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
