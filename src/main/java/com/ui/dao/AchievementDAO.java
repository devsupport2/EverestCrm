package com.ui.dao;

import java.util.List;

import com.ui.model.Achievement;

public interface AchievementDAO {
  
  public String addAchivement(Achievement a);
  public List<Achievement> getAchievement();
  public List<Achievement> getAchievementByPage(int pagesize, int startindex);
  public Achievement getAchievementDetailById(int id);
  public String editAchivement(Achievement a);
  public void deleteAchievement(int id);
  public List<Achievement> searchAchievement(String keyword);

}
