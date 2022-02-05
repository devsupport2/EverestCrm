package com.ui.dao;

import java.util.List;

import com.ui.model.District;

public interface DistrictDAO {
	public List<District> getAllDistricts();
	
	public List<District> getAllDistrictsByPage(int pagesize, int startindex);

	public List<District> searchDistricts(String keyword);

	public District getDistrictDetailById(int districtid);

	public String addDistrict(District s);

	public String editDistrict(District s);

	public void deleteDistrict(int districtid);
	
	public List<District> getDistrictByStateId(int stateid);
}
