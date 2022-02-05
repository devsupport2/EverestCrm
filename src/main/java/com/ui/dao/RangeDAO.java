package com.ui.dao;

import java.util.List;

import com.ui.model.RangeMaster;

public interface RangeDAO {
    public String addRange(RangeMaster r);
	public List<RangeMaster> getRangeByPage(int pagesize, int startindex);
	public List<RangeMaster> getAllRanges();
	public String editRange(RangeMaster r);
	public RangeMaster getRangeDetailById(int rangeid);
	public void deleteRange(int rangeid);
}
