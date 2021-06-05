package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.UserReviewUserInterface;
import com.douglas.api.jointly.model.UserReviewUser;
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
	public int insert(String userEmail, String userReviewEmail, String date, String review, int stars) {
		return userReviewUserDAO.insert(userEmail, userReviewEmail, date, review, stars);
	}

	@Override
	public int delete(String userEmail, String userReviewEmail) {
		return userReviewUserDAO.delete(userEmail, userReviewEmail);
	}

	@Override
	public UserReviewUser getReview(String userEmail, String userReviewEmail) {
		return userReviewUserDAO.getReview(userEmail, userReviewEmail);
	}

	@Override
	public List<Map<String, Object>> getListReviews() {
		return userReviewUserDAO.getListReviews();
	}
	
}
