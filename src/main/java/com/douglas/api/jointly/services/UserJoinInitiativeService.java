package com.douglas.api.jointly.services;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglas.api.jointly.interfaces.UserJoinInitiativeInterface;
import com.douglas.api.jointly.model.UserJoinInitiative;
import com.douglas.api.jointly.modelDAO.UserJoinInitiativeDAO;

public class UserJoinInitiativeService implements UserJoinInitiativeInterface {

	@Autowired
	private UserJoinInitiativeDAO userJoinInitiativeDAO;

	@Override
	public List<Map<String, Object>> getUsersJoined(int idInitiative) {
		return userJoinInitiativeDAO.getUsersJoined(idInitiative);
	}

	@Override
	public UserJoinInitiative insert(int idInitiative, String userEmail, int type) {
		UserJoinInitiative joinInitiative = userJoinInitiativeDAO.insert(idInitiative, userEmail, type);
		return joinInitiative;
	}

	@Override
	public UserJoinInitiative update(GregorianCalendar date, int idInitiative, String userEmail, int type) {
		UserJoinInitiative joinInitiative = userJoinInitiativeDAO.update(date, idInitiative, userEmail, type);
		return joinInitiative;
	}

	@Override
	public void delete(GregorianCalendar date, int idInitiative, String userEmail) {
		userJoinInitiativeDAO.delete(date, idInitiative, userEmail);
	}

	@Override
	public List<Map<String, Object>> getInitiativeJoinedByUser(String userEmail) {
		List<Map<String, Object>> list = userJoinInitiativeDAO.getInitiativeJoinedByUser(userEmail);
		return list;
	}
	
	
}
