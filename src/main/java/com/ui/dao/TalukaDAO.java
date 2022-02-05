package com.ui.dao;

import java.util.List;

import com.ui.model.Taluka;

public interface TalukaDAO {
	public List<Taluka> getAllTalukas();
	
	public List<Taluka> getAllTalukasByPage(int pagesize, int startindex);

	public List<Taluka> searchTalukas(String keyword);

	public Taluka getTalukaDetailById(int talukaid);

	public String addTaluka(Taluka s);

	public String editTaluka(Taluka s);

	public void deleteTaluka(int talukaid);
	
	public List<Taluka> getTalukaByDistrictId(int districtid);
}
