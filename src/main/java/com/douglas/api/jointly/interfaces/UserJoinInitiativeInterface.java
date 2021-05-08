package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

public interface UserJoinInitiativeInterface {
	public List<Map<String, Object>> getUsersJoined(int idInitiative);
	public int insert(String date, int idInitiative, String userEmail, int type);
	public int update(String date, int idInitiative, String userEmail, int type);
	public void delete(String date, int idInitiative, String userEmail);
	public List<Map<String, Object>> getInitiativeJoinedByUser(String userEmail);
}
