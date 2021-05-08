package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.UserReviewUserInterface;
import com.douglas.api.jointly.modelDAO.UserReviewUserDAO;

@Service
public class UserReviewUserService implements UserReviewUserInterface {

	@Autowired
	private UserReviewUserDAO userReviewUserDAO;

	@Override
	public List<Map<String, Object>> getList(String userEmail) {
		List<Map<String, Object>> list = userReviewUserDAO.getList(userEmail);
		return list;
	}

	@Override
	public int insert(String date, String userEmail, String userReviewEmail, String review, int stars) {
		return userReviewUserDAO.insert(date, userEmail, userReviewEmail, review, stars);
	}

	@Override
	public void delete(String date, String userEmail, String userReviewEmail) {
		userReviewUserDAO.delete(date, userEmail, userReviewEmail);
	}
	
}
