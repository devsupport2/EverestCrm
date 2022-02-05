package com.ui.dao;

import java.util.List;

import com.ui.model.FinancialYear;

public interface FinancialYearDAO 
{
	public List<FinancialYear> getAllFinancialYears();
	public String addFinancialYear(FinancialYear f);
	public String editFinancialYear(FinancialYear f);
	public void deleteFinancialYear(int financialyearid);
	public List<FinancialYear> getAllFinancialYearsByPage(int pagesize, int startindex);
	public List<FinancialYear> searchFinancialYears(String keyword);
	public void disableDefaultFinancialYear(FinancialYear f);
	public void addDefaultFinancialYear(FinancialYear f);
	public int getCurrentFinancialYearId();
	public String getCurrentFinancialYearCode();
}
