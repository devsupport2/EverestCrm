package com.ui.dao;

import java.util.List;

import com.ui.model.EmployeeProject;
import com.ui.model.User;
import com.ui.model.UserType;

public interface UserDAO {
	public List<UserType> getUserTypes();
	public List<User> getAllUsers();
	public List<User> getUserByType(int usertypeid);
	public List<User> getAllEmployees();
	public void addUserType(UserType u);
	public void addUser(User u);
	public void editUser(User u);
	public void deleteUser(int userid);
	public List<User> getAllUsersByPage(int pagesize, int startindex);
	public List<User> searchUsers(String keyword);		
	public User getUserDetailById(int userid);
	public int isEmailUnique(String email);
	public int isEmailUniqueWithId(String email, int userid);
	public List<User> getUserName();
	public List<User> getUserNameByUserType(int usertypeid);
	public int getLastUserId();
	public UserType getUserTypeDetailByName(String usertypename);
	public int getLastUserTypeId();
	public User getUserDetailByFirstName(String firstname);
	public void addEmployeeProject(EmployeeProject ep);
	public List<EmployeeProject> getEmployeeProjectListById(int userid);
	public void deleteEmployeeProject(int epid);
}
