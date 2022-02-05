package com.ui.dao;

import java.util.List;
import com.ui.model.Designation;

public interface DesignationDAO {
	public String addDesignation(Designation d);
	public List<Designation> getDesignationsByPage(int pagesize, int startindex);
	public List<Designation> searchDesignations(String keyword); 
	public Designation getDesignationDetailById(int designationid);
	public String editDesignation(Designation d);
	public void deleteDesignation(int Designationid);
	public List<Designation> getAllDesignation();
}
