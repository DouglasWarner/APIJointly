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
	public List<Map<String, Object>> getListByUser(String userEmail) {
		List<Map<String, Object>> list = userReviewUserDAO.getListByUser(userEmail);
		return list;
	}

	@Override
	public int insert(UserReviewUser userReviewUser) {
		return userReviewUserDAO.insert(userReviewUser);
	}

	@Override
	public int delete(String userEmail, String userReviewEmail, String date) {
		return userReviewUserDAO.delete(userEmail, userReviewEmail, date);
	}

	@Override
	public UserReviewUser getReview(String userEmail, String userReviewEmail, String date) {
		return userReviewUserDAO.getReview(userEmail, userReviewEmail, date);
	}

	@Override
	public List<Map<String, Object>> getListReviews() {
		return userReviewUserDAO.getListReviews();
	}

	@Override
	public int updateSync(UserReviewUser reviewUser) {
		return userReviewUserDAO.updateSync(reviewUser);
	}
	
}
