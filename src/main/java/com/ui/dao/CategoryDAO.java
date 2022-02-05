package com.ui.dao;

import java.util.List;

import com.ui.model.RealestateType;

public interface CategoryDAO {
	public List<RealestateType> getCategory();
	
	public List<RealestateType> getCategoryByPage(int pagesize, int startindex);

	public List<RealestateType> searchCategory(String keyword);

	public String addRealestateType(RealestateType c);

	public String editRealestateType(RealestateType c);

	public void deleteCategory(int id);	

	public RealestateType getCategoryDetailById(int id);
}
