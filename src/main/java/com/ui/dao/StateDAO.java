package com.ui.dao;

import java.util.List;

import com.ui.model.State;

public interface StateDAO {
	public List<State> getAllStates();

	public List<State> getStateByCountryId(int countryId);

	public void addState(State s);

	public void editState(State s);

	public void deleteState(int stateid);

	public List<State> getAllStatesByPage(int pagesize, int startindex);

	public List<State> searchStates(String keyword);

	public State getStateDetailById(int stateid);
	
	public State getStateDetailByName(String statename);
}
