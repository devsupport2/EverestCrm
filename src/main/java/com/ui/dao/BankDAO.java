package com.ui.dao;

import java.util.List;

import com.ui.model.Bank;

public interface BankDAO {
  public String addBank(Bank c);
  public String editBank(Bank c);
  public List<Bank> getAllBank();
  public List<Bank> getBankByPage(int pagesize, int startindex);
  public List<Bank> searchBank(String keyword);
  public Bank getBankDetailById(int id);
  public void deleteBank(int id);

}
