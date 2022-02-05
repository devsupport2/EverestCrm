package com.ui.dao;

import java.util.List;

import com.ui.model.Occupation;

public interface OccupationDao {

	public String addOccupation(Occupation o);
	public List<Occupation> getOccupationsByPage(int pagesize, int startindex);
	public Occupation getOccupationDetailById(int occupationid);
	public String editOccupation(Occupation o);
	public void deleteOccupation(int occupationid);
	public List<Occupation> getAllOccupation();
		
}
