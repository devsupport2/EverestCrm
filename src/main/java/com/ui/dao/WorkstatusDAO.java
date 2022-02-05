package com.ui.dao;

import java.util.List;

import com.ui.model.Workstatus;
import com.ui.model.WorkstatusImage;

public interface WorkstatusDAO {

  public String addWorkStatus(Workstatus w);
  public String editWorkStatus(Workstatus w);
  public int getLastWorkstatusId();
  public String addImage(WorkstatusImage wi);
  public List<Workstatus> getAllWorkstatus();
  public List<Workstatus> getWorkstatusByPage(int pagesize, int startindex);
  public Workstatus getWorkstatusDetailsById(int id);
  public List<WorkstatusImage> getAllImageById(int id);
  public void deleteWorkstatus(int id);
  public void deleteWorkstatusImage(int workstatusImageId);
}
