package com.douglas.api.jointly.interfaces;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.UserJoinInitiative;

public interface UserJoinInitiativeInterface {
	public List<Map<String, Object>> getUsersJoined(int idInitiative);
	public UserJoinInitiative insert(GregorianCalendar date, int idInitiative, String userEmail, int type);
	public UserJoinInitiative update(GregorianCalendar date, int idInitiative, String userEmail, int type);
	public void delete(GregorianCalendar date, int idInitiative, String userEmail);
	public List<Map<String, Object>> getInitiativeJoinedByUser(String userEmail);
}
