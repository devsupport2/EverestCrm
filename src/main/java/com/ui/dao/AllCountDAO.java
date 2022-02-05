package com.ui.dao;
import javax.servlet.http.HttpSession;

import com.ui.model.AllCount;

public interface AllCountDAO {
	public AllCount getAllCounts(HttpSession session);	
}
