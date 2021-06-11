package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.UserReviewUser;

public interface UserReviewUserInterface {
	public List<Map<String, Object>> getListReviews();
	public List<Map<String, Object>> getListByUser(String userEmail);
	public int insert(UserReviewUser userReviewUser);
	public int delete(String userEmail, String userReviewEmail, String date);
	public UserReviewUser getReview(String userEmail, String userReviewEmail, String date);
	public int updateSync(UserReviewUser reviewUser);
}
