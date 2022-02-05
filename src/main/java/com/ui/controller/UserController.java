package com.ui.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.UserDAO;
import com.ui.model.EmployeeProject;
import com.ui.model.User;
import com.ui.model.UserType;

@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;

    User user;
    UserType userType;
    EmployeeProject employeeProject;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/getUserTypes", method = RequestMethod.GET, produces = "application/json")
    public List<UserType> getUserTypes(HttpServletRequest request) {
        logger.info("***** GET USER TYPE *****");
        List<UserType> d = userDAO.getUserTypes();
        return d;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUsers(HttpServletRequest request) {
        logger.info("***** GET USERS *****");
        List<User> user = userDAO.getAllUsers();
        return user;
    }
    
    @RequestMapping(value = "/getUserByType", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUserByType(int usertypeid, HttpServletRequest request) {
        logger.info("***** GET USER BY USER TYPE *****");
        List<User> user = userDAO.getUserByType(usertypeid);
        return user;
    }

    @RequestMapping(value = "/getUsersByPage", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUsersByPage(int pagesize, int startindex, HttpServletRequest request) {
        logger.info("***** GET USER BY PAGE *****");
        List<User> user = userDAO.getAllUsersByPage(pagesize, startindex);
        return user;
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET, produces = "application/json")
    public List<User> searchUsers(String keyword, HttpServletRequest request) {
        logger.info("***** SEARCH USERS *****");
        List<User> user = userDAO.searchUsers(keyword);
        return user;
    }

    @RequestMapping(value = "addUserType", method = RequestMethod.POST)
    public String addUserType(String usertypename, HttpServletRequest request, HttpSession session) {
        logger.info("***** ADD USER TYPE *****");
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        String s = "y";
        userType = new UserType(usertypename, s, id, IpAddress);
        userDAO.addUserType(userType);
        return "Success";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "profilepicture", required = false) MultipartFile profilepicture,
            String companyname, String firstname, String middlename, String lastname, int usertypename, String gender,
            String dateofbirth, String aadharnumber, String passportnumber, String pannumber, String address1,
            String address2, String address3, int countryname, int statename, String cityname, String pincode,
            String mobilenumber, String landlinenumber, String email, String password, String accessproject,
            String accessproperty, String accessenquiry, String accesspayment, String accessuser, String gstno,
            HttpServletRequest request, HttpSession session) {
        logger.info("***** ADD USER CONTROLLER *****");
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        int checkEmail = 0;
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        String s = "y";
        String image1 = "";
        if (dateofbirth == "") {
            dateofbirth = null;
        }

        if (email.equals("")) {
            email = null;
            user = new User(companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
                    passportnumber, pannumber, image1, address1, address2, address3, countryname, statename, cityname,
                    pincode, mobilenumber, landlinenumber, email, password, usertypename, accessproject, accessproperty,
                    accessenquiry, accesspayment, accessuser, gstno, s, id, IpAddress);
            userDAO.addUser(user);
            return "Success";
        } else {

            checkEmail = userDAO.isEmailUnique(email);
            if (checkEmail == 0) {

                try {
                    if (profilepicture != null) {
                        try {
                            byte[] bytes = profilepicture.getBytes();
                            File dir = new File(request.getSession().getServletContext().getRealPath("")
                                    + "/resources/admin/images/" + File.separator + "user");
                            if (!dir.exists())
                                dir.mkdirs();
                            String path = request.getSession().getServletContext()
                                    .getRealPath("/resources/admin/images/user/");
                            File uploadfile = new File(path + File.separator + profilepicture.getOriginalFilename());
                            /********* Today Start **********/
                            int height = 480, width = 700;
                            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                            try {
                                BufferedImage img = ImageIO.read(in);
                                int original_width = img.getWidth();
                                int original_height = img.getHeight();
                                int bound_width = 700;
                                int bound_height = 480;
                                if (original_height / bound_height > original_width / bound_width) {
                                    bound_width = (int) (bound_height * original_width / original_height);
                                } else {
                                    bound_height = (int) (bound_width * original_height / original_width);
                                }
                                Image scaledImage = img.getScaledInstance(bound_width, bound_height,
                                        Image.SCALE_SMOOTH);
                                BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                                Graphics2D drawer = imageBuff.createGraphics();
                                drawer.setBackground(Color.WHITE);
                                drawer.clearRect(0, 0, width, height);
                                imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
                                        (height - bound_height) / 2, new Color(0, 0, 0), null);
                                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                                ImageIO.write(imageBuff, "jpg", buffer);
                                bytes = buffer.toByteArray();
                            } catch (IOException e) {

                            }
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                    new FileOutputStream(uploadfile));
                            bufferedOutputStream.write(bytes);
                            bufferedOutputStream.close();
                            image1 = request.getScheme() + "://" + request.getServerName() + ":"
                                    + request.getServerPort() + "/everest/resources/admin/images/user/"
                                    + profilepicture.getOriginalFilename();
                            user = new User(companyname, firstname, middlename, lastname, gender, dateofbirth,
                                    aadharnumber, passportnumber, pannumber, image1, address1, address2, address3,
                                    countryname, statename, cityname, pincode, mobilenumber, landlinenumber, email,
                                    password, usertypename, accessproject, accessproperty, accessenquiry, accesspayment,
                                    accessuser, gstno, s, id, IpAddress);
                            userDAO.addUser(user);
                            return "";
                        } catch (Exception e) {
                            e.printStackTrace();
                            user = new User(companyname, firstname, middlename, lastname, gender, dateofbirth,
                                    aadharnumber, passportnumber, pannumber, image1, address1, address2, address3,
                                    countryname, statename, cityname, pincode, mobilenumber, landlinenumber, email,
                                    password, usertypename, accessproject, accessproperty, accessenquiry, accesspayment,
                                    accessuser, gstno, s, id, IpAddress);
                            userDAO.addUser(user);
                            return "";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    user = new User(companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
                            passportnumber, pannumber, image1, address1, address2, address3, countryname, statename,
                            cityname, pincode, mobilenumber, landlinenumber, email, password, usertypename,
                            accessproject, accessproperty, accessenquiry, accesspayment, accessuser, gstno, s, id, IpAddress);
                    userDAO.addUser(user);
                    return "";
                }

                user = new User(companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
                        passportnumber, pannumber, image1, address1, address2, address3, countryname, statename,
                        cityname, pincode, mobilenumber, landlinenumber, email, password, usertypename, accessproject,
                        accessproperty, accessenquiry, accesspayment, accessuser, gstno, s, id, IpAddress);
                userDAO.addUser(user);

                return "Success";

            } else {
                return "Data not saved! Email already exists!";
            }
        }
    }

    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    public String editUser(@RequestParam(value = "profilepicture", required = false) MultipartFile profilepicture,
            int userid, String companyname, String firstname, String middlename, String lastname, int usertypename,
            String gender, String dateofbirth, String aadharnumber, String passportnumber, String pannumber,
            String address1, String address2, String address3, int countryname, int statename, String cityname,
            String pincode, String mobilenumber, String landlinenumber, String email, String password,
            String profilepicture1, String accessproject, String accessproperty, String accessenquiry,
            String accesspayment, String accessuser, String gstno, HttpServletRequest request, HttpSession session) {
        logger.info("***** EDIT USER CONTROLLER *****");

        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        String image1 = "";
        if (dateofbirth == "") {
            dateofbirth = null;
        }

        int checkEmail = 0;

        if (email.equals("")) {
            email = null;
            user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
                    passportnumber, pannumber, profilepicture1, address1, address2, address3, countryname, statename,
                    cityname, pincode, mobilenumber, landlinenumber, email, password, usertypename, accessproject,
                    accessproperty, accessenquiry, accesspayment, accessuser, gstno, id, IpAddress);
            userDAO.editUser(user);
            return "Success";

        } else {

            checkEmail = userDAO.isEmailUniqueWithId(email, userid);

            if (checkEmail == 0) {

                try {
                    if (profilepicture != null) {
                        try {
                            byte[] bytes = profilepicture.getBytes();
                            File dir = new File(request.getSession().getServletContext().getRealPath("")
                                    + "/resources/admin/images/" + File.separator + "user");
                            if (!dir.exists())
                                dir.mkdirs();
                            String path = request.getSession().getServletContext()
                                    .getRealPath("/resources/admin/images/user/");
                            File uploadfile = new File(path + File.separator + profilepicture.getOriginalFilename());
                            int height = 480, width = 700;
                            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                            try {
                                BufferedImage img = ImageIO.read(in);
                                int original_width = img.getWidth();
                                int original_height = img.getHeight();
                                int bound_width = 700;
                                int bound_height = 480;
                                if (original_height / bound_height > original_width / bound_width) {
                                    bound_width = (int) (bound_height * original_width / original_height);
                                } else {
                                    bound_height = (int) (bound_width * original_height / original_width);
                                }
                                Image scaledImage = img.getScaledInstance(bound_width, bound_height,
                                        Image.SCALE_SMOOTH);
                                BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                                Graphics2D drawer = imageBuff.createGraphics();
                                drawer.setBackground(Color.WHITE);
                                drawer.clearRect(0, 0, width, height);
                                imageBuff.getGraphics().drawImage(scaledImage, (width - bound_width) / 2,
                                        (height - bound_height) / 2, new Color(0, 0, 0), null);
                                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                                ImageIO.write(imageBuff, "jpg", buffer);
                                bytes = buffer.toByteArray();
                            } catch (IOException e) {

                            }
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                    new FileOutputStream(uploadfile));
                            bufferedOutputStream.write(bytes);
                            bufferedOutputStream.close();
                            image1 = request.getScheme() + "://" + request.getServerName() + ":"
                                    + request.getServerPort() + "/everest/resources/admin/images/user/"
                                    + profilepicture.getOriginalFilename();

                            user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth,
                                    aadharnumber, passportnumber, pannumber, image1, address1, address2, address3,
                                    countryname, statename, cityname, pincode, mobilenumber, landlinenumber, email,
                                    password, usertypename, accessproject, accessproperty, accessenquiry, accesspayment,
                                    accessuser, gstno, id, IpAddress);
                            userDAO.editUser(user);
                            return "Success";
                        } catch (Exception e) {
                            e.printStackTrace();

                            user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth,
                                    aadharnumber, passportnumber, pannumber, profilepicture1, address1, address2,
                                    address3, countryname, statename, cityname, pincode, mobilenumber, landlinenumber,
                                    email, password, usertypename, accessproject, accessproperty, accessenquiry,
                                    accesspayment, accessuser, gstno, id, IpAddress);
                            userDAO.editUser(user);
                            return "Success";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth,
                            aadharnumber, passportnumber, pannumber, profilepicture1, address1, address2, address3,
                            countryname, statename, cityname, pincode, mobilenumber, landlinenumber, email, password,
                            usertypename, accessproject, accessproperty, accessenquiry, accesspayment, accessuser, gstno, id,
                            IpAddress);
                    userDAO.editUser(user);
                    return "Success";
                }
                user = new User(userid, companyname, firstname, middlename, lastname, gender, dateofbirth, aadharnumber,
                        passportnumber, pannumber, profilepicture1, address1, address2, address3, countryname,
                        statename, cityname, pincode, mobilenumber, landlinenumber, email, password, usertypename,
                        accessproject, accessproperty, accessenquiry, accesspayment, accessuser, gstno, id, IpAddress);
                userDAO.editUser(user);
                return "Success";
            } else {
                return "Data not updated! Email already exists!";
            }
        }

    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE)
    public void delete(int userid) {
        logger.info("Inside Delete User Controller...");
        userDAO.deleteUser(userid);
    }

    @RequestMapping(value = "/getUserDetailById", method = RequestMethod.GET, produces = "application/json")
    public User getUserDetailById(int userid, HttpServletRequest request) {
        logger.info("***** GET USER DETAIL BY ID *****");
        User user = userDAO.getUserDetailById(userid);

        user.setEmployeeProjectList(userDAO.getEmployeeProjectListById(userid));

        return user;
    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUserName(HttpServletRequest request) {
        logger.info("***** GET USER NAME *****");
        List<User> user = userDAO.getUserName();
        return user;
    }

    @RequestMapping(value = "/getUserNameByUserType", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUserNameByUserType(int usertypeid, HttpServletRequest request) {
        logger.info("***** GET USER NAME BY USER TYPE ID *****");
        List<User> user = userDAO.getUserNameByUserType(usertypeid);
        return user;
    }

    @RequestMapping(value = "/getEmployees", method = RequestMethod.GET, produces = "application/json")
    public List<User> getEmployees(HttpServletRequest request) {
        logger.info("Inside Get All Employees Controller");
        List<User> user = userDAO.getAllEmployees();
        return user;
    }

    @RequestMapping(value = "addEmployeeProject", method = RequestMethod.POST)
    public String addEmployeeProject(int projectid, HttpServletRequest request, HttpSession session) {
        logger.info("***** ADD EMPLOYEE PROJECT *****");
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String ipaddress = request.getHeader("X-FORWARDED-FOR");
        if (ipaddress == null) {
            ipaddress = request.getRemoteAddr();
        }

        employeeProject = new EmployeeProject();

        int userid = userDAO.getLastUserId();

        employeeProject.setUserId(userid);
        employeeProject.setProjectId(projectid);
        employeeProject.setCreatedBy(id);
        employeeProject.setIpAddress(ipaddress);

        userDAO.addEmployeeProject(employeeProject);

        return "Success";
    }

    @RequestMapping(value = "editEmployeeProject", method = RequestMethod.POST)
    public String editEmployeeProject(int userid, int projectid, HttpServletRequest request, HttpSession session) {
        logger.info("***** EDIT EMPLOYEE PROJECT *****");
        int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
        String ipaddress = request.getHeader("X-FORWARDED-FOR");
        if (ipaddress == null) {
            ipaddress = request.getRemoteAddr();
        }

        employeeProject = new EmployeeProject();

        employeeProject.setUserId(userid);
        employeeProject.setProjectId(projectid);
        employeeProject.setCreatedBy(id);
        employeeProject.setIpAddress(ipaddress);

        userDAO.addEmployeeProject(employeeProject);

        return "Success";
    }

    @RequestMapping(value = "/getEmployeeProjectListById", method = RequestMethod.GET, produces = "application/json")
    public List<EmployeeProject> getEmployeeProjectListById(int userid, HttpServletRequest request) {
        logger.info("***** GET EMPLOYEE PROJECT LIST BY ID *****");
        return userDAO.getEmployeeProjectListById(userid);
    }

    @RequestMapping(value = "deleteEmployeeProject", method = RequestMethod.DELETE)
    public void deleteEmployeeProject(int epid) {
        logger.info("***** DELETE EMPLOYEE PROJECT *****");
        userDAO.deleteEmployeeProject(epid);
    }
}