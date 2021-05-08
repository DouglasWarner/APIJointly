package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.UserJoinInitiativeInterface;
import com.douglas.api.jointly.modelDAO.UserJoinInitiativeDAO;

@Service
public class UserJoinInitiativeService implements UserJoinInitiativeInterface {

	@Autowired
	private UserJoinInitiativeDAO userJoinInitiativeDAO;

	@Override
	public List<Map<String, Object>> getUsersJoined(int idInitiative) {
		return userJoinInitiativeDAO.getUsersJoined(idInitiative);
	}

	@Override
	public int insert(String date, int idInitiative, String userEmail, int type) {
		return userJoinInitiativeDAO.insert(date, idInitiative, userEmail, type);
	}

	@Override
	public int update(String date, int idInitiative, String userEmail, int type) {
		return userJoinInitiativeDAO.update(date, idInitiative, userEmail, type);
	}

	@Override
	public void delete(String date, int idInitiative, String userEmail) {
		userJoinInitiativeDAO.delete(date, idInitiative, userEmail);
	}

	@Override
	public List<Map<String, Object>> getInitiativeJoinedByUser(String userEmail) {
		List<Map<String, Object>> list = userJoinInitiativeDAO.getInitiativeJoinedByUser(userEmail);
		return list;
	}
	
	
}
