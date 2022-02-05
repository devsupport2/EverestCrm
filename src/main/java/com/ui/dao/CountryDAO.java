package com.ui.dao;

import java.util.List;

import com.ui.model.Country;

public interface CountryDAO {
	public List<Country> getAllCountries();

	public String addCountry(Country c);

	public String editCountry(Country c);

	public void deleteCountry(int countryid);

	public List<Country> getAllCountriesByPage(int pagesize, int startindex);

	public List<Country> searchCountries(String keyword);

	public Country getCountryDetailById(int countryid);
}
