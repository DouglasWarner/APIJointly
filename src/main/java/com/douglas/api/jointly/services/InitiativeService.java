package com.douglas.api.jointly.services;

import java.util.GregorianCalendar;
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
	public Initiative getInitiativeById(int id) {
		return initiativeDAO.getInitiativeById(id);
	}

	@Override
	public Initiative insert(String name, GregorianCalendar targetDate, String description, String targetArea, String location, byte[] imagen, int targetAmount, String status, String createdBy, String refcode) {
		Initiative initiative = initiativeDAO.insert(name, targetDate, description, targetArea, location, imagen, targetAmount, status, createdBy, refcode);
		return initiative;
	}

	@Override
	public Initiative update(String name, GregorianCalendar targetDate, String description, String targetArea, String location, byte[] imagen, int targetAmount, String status, int id) {
		Initiative initiative = initiativeDAO.update(name, targetDate, description, targetArea, location, imagen, targetAmount, status, id);
		return initiative;
	}

	@Override
	public void delete(int id) {
		initiativeDAO.delete(id);		
	}
}
