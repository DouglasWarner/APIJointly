package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.UserJoinInitiative;

public interface UserJoinInitiativeInterface {
	public List<Map<String, Object>> getUsersJoined();
	public List<Map<String, Object>> getUsersJoinedByInitiative(long idInitiative);
	public long insert(long idInitiative, String userEmail, int type);
	public int update(long idInitiative, String userEmail, int type);
	public int delete(long idInitiative, String userEmail);
	public List<Map<String, Object>> getInitiativeJoinedByUser(String userEmail, int type);
	public UserJoinInitiative getUserJoinInitiative(long idInitiative, String userEmail);
}
