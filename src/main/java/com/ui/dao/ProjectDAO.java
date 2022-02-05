package com.ui.dao;

import java.util.List;

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

public interface ProjectDAO {
	public List<Project> getAllProjects();

	public List<Project> getAllProjectsByPage(int pagesize, int startindex);
	public List<Project> searchProject(String keyword);
	public List<ProjectSpecification> getProjectSpecificationById(int projectid);
	public List<ProjectAmenity> getProjectAmenityById(int projectid);
	public List<ProjectArea> getProjectAreaById(int projectid);
	public List<ProjectDetail> getProjectDetailsById(int projectid);
	public List<ProjectPaymentSchedule> getProjectPaymentScheduleById(int projectid);
	public List<ProjectPriceInfo> getProjectPriceDetailsById(int projectid);
	public List<Project> getProjectName();
	public Project getProjectDetailById(int projectid);
	public int getLastProjectId();
	public void deleteProject(int projectid);
	public void deleteProjectDetail(int projectdetailid);
	public void deleteProjectPriceDetail(int projectpriceinfoid);
	public void deleteSpecification(int projectspecificationid);
	public void deleteAmenity(int projectamenityid);
	public void deleteProjectPaymentSchedule(int id);
	public void deleteProjectFloorLayout(int floorlayoutid);
	public String editProject(Project t);
	public String addProject(Project t);
	public void addProjectSpecification(ProjectSpecification ps);
	public void addProjectAmenity(ProjectAmenity pa);
	public String addProjectArea(ProjectArea pa);
	public String addProjectDetails(ProjectDetail td);
	public String addProjectPriceDetails(ProjectPriceInfo pp);
	public String addProjectPaymentSchedule(ProjectPaymentSchedule pps);
	public void deleteProjectAreaRow(int projectareaid);
	public List<ProjectDetail> getProjectSubcategoryById(int projectid, int categoryid);
	public List<ProjectDetail> getProjectRealestateTypeById(int projectid, int categoryid, int subcategoryid);
	public List<ProjectDetail> getProjectUnitById(int projectid, int categoryid, int subcategoryid, int typeid);
	public List<ProjectArea> getProjectAreaForPropertyById(int projectid, int categoryid, int subcategoryid,
			int typeid);
	public List<ProjectArea> getProjectAreaForPropertyById(int projectid, int categoryid, int subcategoryid,
			int typeid, int unitmasterid);
	public List<ProjectPriceInfo> getProjectUnitPriceForPropertyById(int projectid, int categoryid, int subcategoryid,
			int typeid);
	public List<ProjectPriceInfo> getProjectUnitPriceForPropertyById(int projectid, int categoryid, int subcategoryid,
			int typeid, int unitmasterid);
	public RealestateType getRealestateTypeDetailById(int realecateid);
	public RealestateSubcategory getRealestateSubcategoryDetailById(int realestatesubid);
	public Realestate getRealestateDetailById(int realestatetypeid);
	public List<UnitMaster> getUnitNameList();
	public void addUnitMaster(UnitMaster u);
	public Project getProjectDetailByName(String projectname);
	public Project getProjectDetailByCode(String projectcode);
	public RealestateType getRealestateTypeDetailByName(String categoryname);
	public RealestateType getRealestateTypeDetailByCode(String categorycode);
	public RealestateSubcategory getRealestateSubcategoryDetailByName(String subcategoryname);
	public RealestateSubcategory getRealestateSubcategoryDetailByCode(String subcategorycode);
	public List<ProjectDetail> getProjectUnitByProjectId(int projectid);
	public List<Project> getProjectNameAndEnquiryCount();
	public List<Project> getProjectStatusWiseCount();
	public List<ProjectFloorLayout> getProjectFloorLayoutById(int projectid);
	public void addProjectFloorLayout(ProjectFloorLayout pfl);
	public String addProjectBank(ProjectBank b);
	public List<ProjectBank> getAllProjectBankById(int projectid);
	public void deleteProjectBank(int projectbankid);
}
