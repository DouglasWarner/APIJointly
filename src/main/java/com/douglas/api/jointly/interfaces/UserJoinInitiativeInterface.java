package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.UserJoinInitiative;

public interface UserJoinInitiativeInterface {
	public List<Map<String, Object>> getListUsersJoined();
	public List<Map<String, Object>> getListUsersJoinedByInitiative(long idInitiative);
	public long insert(long idInitiative, String userEmail, int type, String date);
	public long insert(UserJoinInitiative joinInitiative);
	public int update(long idInitiative, String userEmail, int type);
	public int updateSync(UserJoinInitiative joinInitiative);
	public int delete(long idInitiative, String userEmail);
	public List<Map<String, Object>> getListInitiativeJoinedByUser(String userEmail, int type);
	public UserJoinInitiative getUserJoinInitiative(long idInitiative, String userEmail);
}
