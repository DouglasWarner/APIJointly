package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.Initiative;

public interface InitiativeInterface {
	public List<Map<String, Object>> getList();
	public List<Map<String, Object>> getListByName(String name);
	public Initiative getInitiativeById(long id);
	public long insert(Initiative initiative);
	public int update(String name, String targetDate, String description, String targetArea, String location, byte[] imagen, int targetAmount, String status, long id);
	public int delete(long id);
	public Initiative getInitiativeToSync(long id, String createdBy);
	public int updateSync(Initiative initiative);
}
