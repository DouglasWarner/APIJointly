package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

public interface UserReviewUserInterface {
	public List<Map<String, Object>> getList(String userEmail);
	public int insert(String date, String userEmail, String userReviewEmail, String review, int stars);
	public void delete(String date, String userEmail, String userReviewEmail);
}
