package com.douglas.api.jointly.services;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglas.api.jointly.interfaces.UserReviewUserInterface;
import com.douglas.api.jointly.model.UserReviewUser;
import com.douglas.api.jointly.modelDAO.UserReviewUserDAO;

public class UserReviewUserService implements UserReviewUserInterface {

	@Autowired
	private UserReviewUserDAO userReviewUserDAO;

	@Override
	public List<Map<String, Object>> getList(String userEmail) {
		List<Map<String, Object>> list = userReviewUserDAO.getList(userEmail);
		return list;
	}

	@Override
	public UserReviewUser insert(String userEmail, String userReviewEmail, String review, int stars) {
		UserReviewUser reviewUser = userReviewUserDAO.insert(userEmail, userReviewEmail, review, stars);
		return reviewUser;
	}

	@Override
	public void delete(GregorianCalendar date, String userEmail, String userReviewEmail) {
		userReviewUserDAO.delete(date, userEmail, userReviewEmail);
	}
	
}
