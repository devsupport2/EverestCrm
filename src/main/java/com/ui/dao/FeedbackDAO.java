package com.ui.dao;

import java.util.List;

import com.ui.model.Feedback;

public interface FeedbackDAO {
  
	public List<Feedback> getAllFeedback();
	public List<Feedback> searchFeedback(String keyword);
    public List<Feedback> getFeedbackByPage(int pagesize, int startindex);
    public String addFeedback(Feedback c);
    public String editFeedback(Feedback c);
    public Feedback getFeedbackById(int feedbackid);
    public void deleteFeedback(int feedbackid);
}
