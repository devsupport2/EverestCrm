package com.ui.dao;

import java.util.List;

import com.ui.model.PriceLabel;
public interface PriceLabelDAO {
  
  public List<PriceLabel> getAllPrices();
  public List<PriceLabel> getPricesByPage(int pagesize, int startindex);
  public String addPriceLabel(PriceLabel t);
  public String editPriceLabel(PriceLabel t);
  public void deletePrice(int priceid);
  public List<PriceLabel> searchPrices(String keyword);
}
