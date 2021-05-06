package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.UserFollowUserInterface;
import com.douglas.api.jointly.model.UserFollowUser;
import com.douglas.api.jointly.modelDAO.UserFollowUserDAO;

@Service
public class UserFollowUserService implements UserFollowUserInterface{

	@Autowired
	private UserFollowUserDAO UserFollowUserDAO;
	
	@Override
	public List<Map<String, Object>> getListFollowed(String email) {
		return UserFollowUserDAO.getListFollowed(email);
	}
	
	@Override
	public List<Map<String, Object>> getListFollowers(String userEmail) {
		return UserFollowUserDAO.getListFollowers(userEmail);
	}

	@Override
	public int getCountFollowed(String email) {
		return UserFollowUserDAO.getCountFollowed(email);
	}

	@Override
	public int getCountFollowers(String email) {
		return UserFollowUserDAO.getCountFollowers(email);
	}
	
	@Override
	public UserFollowUser insert(String userEmail, String userFollowEmail) {
		UserFollowUser followUser = UserFollowUserDAO.insert(userEmail, userFollowEmail);
		return followUser;
	}

	@Override
	public void delete(String userEmail, String userFollowEmail) {
		UserFollowUserDAO.delete(userEmail, userFollowEmail);
	}

}
