package com.ui.dao;

import java.util.List;

import com.ui.model.Currency;

public interface CurrencyDAO 
{
	public List<Currency> getAllCurrencies();
	public String addCurrency(Currency c);
	public String editCurrency(Currency c);
	public void deleteCurrency(int currencyid);
	public List<Currency> getAllCurrenciesByPage(int pagesize, int startindex);
	public List<Currency> searchCurrencies(String keyword);
	
}
