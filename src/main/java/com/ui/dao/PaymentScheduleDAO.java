package com.ui.dao;

import java.util.List;

import com.ui.model.Payment;

public interface PaymentScheduleDAO {

  public List<Payment> getAllPayments();
  public String addPayment(Payment p);
  public List<Payment> getAllPaymentByPage(int pagesize, int startindex);
  public List<Payment> getPaymentDetailById(int projectid);
  public Payment getProjectIdById(int projectid);
  public String editPayment(Payment c);
  public void deletePayment(int paymentid); 
  public List<Payment> searchPayments(String keyword);
 
}
