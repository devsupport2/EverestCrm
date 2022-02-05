package com.ui.dao;

import java.util.List;

import com.ui.model.Location;

public interface LocationDAO {
	public List<Location> getAllLocations();
	
	public List<Location> getAllLocationsByPage(int pagesize, int startindex);

	public List<Location> searchLocations(String keyword);

	public Location getLocationDetailById(int locationid);

	public String addLocation(Location s);

	public String editLocation(Location s);

	public void deleteLocation(int locationid);
	
	public List<Location> getLocationName();
}
