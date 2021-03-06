package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.UserJoinInitiativeInterface;
import com.douglas.api.jointly.model.UserJoinInitiative;
import com.douglas.api.jointly.modelDAO.UserJoinInitiativeDAO;

@Service
public class UserJoinInitiativeService implements UserJoinInitiativeInterface {

	@Autowired
	private UserJoinInitiativeDAO userJoinInitiativeDAO;

	@Override
	public List<Map<String, Object>> getUsersJoined() {
		return userJoinInitiativeDAO.getUsersJoined();
	}
	
	@Override
	public List<Map<String, Object>> getUsersJoinedByInitiative(long idInitiative) {
		return userJoinInitiativeDAO.getUsersJoinedByInitiative(idInitiative);
	}

	@Override
	public long insert(long idInitiative, String userEmail, int type, String date) {
		return userJoinInitiativeDAO.insert(idInitiative, userEmail, type, date);
	}

	@Override
	public int update(long idInitiative, String userEmail, int type) {
		return userJoinInitiativeDAO.update(idInitiative, userEmail, type);
	}

	@Override
	public int delete(long idInitiative, String userEmail) {
		return userJoinInitiativeDAO.delete(idInitiative, userEmail);
	}

	@Override
	public List<Map<String, Object>> getListInitiativeJoinedByUser(String userEmail, int type) {
		return userJoinInitiativeDAO.getListInitiativeJoinedByUser(userEmail, type);
	}

	@Override
	public UserJoinInitiative getUserJoinInitiative(long idInitiative, String userEmail) {
		return userJoinInitiativeDAO.getUserJoinInitiative(idInitiative, userEmail);
	}	
}
