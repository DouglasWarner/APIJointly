package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.UserReviewUser;

public interface UserReviewUserInterface {
	public List<Map<String, Object>> getListReviews();
	public List<Map<String, Object>> getList(String userEmail);
	public int insert(String userEmail, String userReviewEmail, String date, String review, int stars);
	public int delete(String userEmail, String userReviewEmail);
	public UserReviewUser getReview(String userEmail, String userReviewEmail);
}
