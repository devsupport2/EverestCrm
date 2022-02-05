package com.ui.dao;

import java.util.List;

import com.ui.model.RealestateSubcategory;

public interface SubcategoryDAO {
	public List<RealestateSubcategory> getSubcategory();

	public List<RealestateSubcategory> getSubcategoryByPage(int pagesize, int startindex);

	public List<RealestateSubcategory> searchSubcategory(String keyword);

	public String addRealestateSubcategoryType(RealestateSubcategory r);

	public String editRealestateSubcategoryType(RealestateSubcategory r);

	public void deleteSubcategory(int id);

	public RealestateSubcategory getSubcategoryDetailById(int id);
}
