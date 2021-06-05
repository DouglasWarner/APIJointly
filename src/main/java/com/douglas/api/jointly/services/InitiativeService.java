package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.InitiativeInterface;
import com.douglas.api.jointly.model.Initiative;
import com.douglas.api.jointly.modelDAO.InitiativeDAO;

@Service
public class InitiativeService implements InitiativeInterface {

	@Autowired
	private InitiativeDAO initiativeDAO;
	
	@Override
	public List<Map<String, Object>> getList() {
		return initiativeDAO.getList();
	}

	@Override
	public List<Map<String, Object>> getListByName(String name) {
		return initiativeDAO.getListByName(name);
	}
	
	@Override
	public Initiative getInitiativeById(long id) {
		return initiativeDAO.getInitiativeById(id);
	}

	@Override
	public long insert(String name, String createdAt, String targetDate, String description, String targetArea, String location, byte[] imagen, int targetAmount, String status, String createdBy, String refcode) {
		return initiativeDAO.insert(name, createdAt, targetDate, description, targetArea, location, imagen, targetAmount, status, createdBy, refcode);
	}

	@Override
	public int update(String name, String targetDate, String description, String targetArea, String location, byte[] imagen, int targetAmount, String status, long id) {
		return initiativeDAO.update(name, targetDate, description, targetArea, location, imagen, targetAmount, status, id);
	}

	@Override
	public int delete(long id) {
		return initiativeDAO.delete(id);		
	}
}
