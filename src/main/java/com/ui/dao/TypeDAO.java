package com.ui.dao;

import java.util.List;

import com.ui.model.Realestate;

public interface TypeDAO {	
	
	public List<Realestate> getType();
	
	public List<Realestate> getTypeByPage(int pagesize, int startindex);

	public List<Realestate> searchType(String keyword);

	public String addRealestate(Realestate s);

	public String editRealestate(Realestate c);

	public void deleteType(int id);	

	public Realestate getTypeDetailById(int id);
}
