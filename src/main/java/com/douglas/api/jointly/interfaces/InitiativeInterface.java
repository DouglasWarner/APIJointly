package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.Initiative;

public interface InitiativeInterface {
	public List<Map<String, Object>> getList();
	public List<Map<String, Object>> getListByName(String name);
	public Initiative getInitiativeById(long id);
	public long insert(String name, String createdAt, String targetDate, String description, String targetArea, String location, byte[] imagen, int targetAmount, String status, String createdBy, String refcode);
	public int update(String name, String targetDate, String description, String targetArea, String location, byte[] imagen, int targetAmount, String status, long id);
	public int delete(long id);
}
