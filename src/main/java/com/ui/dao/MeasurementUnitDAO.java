package com.ui.dao;

import java.util.List;

import com.ui.model.MeasurementUnit;

public interface MeasurementUnitDAO {
	public List<MeasurementUnit> getAllMeasurementUnits();

	public void addMeasurementUnit(MeasurementUnit m);

	public void editMeasurementUnit(MeasurementUnit m);

	public void deleteMeasurementUnit(int measurementunitid);

	public List<MeasurementUnit> getAllMeasurementUnitsByPage(int pagesize, int startindex);

	public List<MeasurementUnit> searchMeasurementUnits(String keyword);
	
	public MeasurementUnit getUnitDetailById(int unitid);
}
