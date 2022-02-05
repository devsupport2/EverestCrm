package com.ui.dao;

import java.util.List;

import com.ui.model.Tax;

public interface TaxDAO {
	public List<Tax> getAllTaxes();

	public String addTax(Tax t);

	public String editTax(Tax t);

	public void deleteTax(int taxid);

	public List<Tax> getAllTaxesByPage(int pagesize, int startindex);

	public List<Tax> searchTaxes(String keyword);
}
