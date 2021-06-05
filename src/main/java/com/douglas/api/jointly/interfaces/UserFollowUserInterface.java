package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.UserFollowUser;

public interface UserFollowUserInterface {
	public List<Map<String, Object>> getListFollows();
	public List<Map<String, Object>> getListFollowed(String userEmail);
	public List<Map<String, Object>> getListFollowers(String userEmail);
	public int getCountFollowed(String userEmail);
	public int getCountFollowers(String userEmail);
	public int insert(String userEmail, String userFollowEmail);
	public int delete(String userEmail, String userFollowEmail);
	public UserFollowUser getUserFollowUser(String userEmail, String userFollowEmail);
}
