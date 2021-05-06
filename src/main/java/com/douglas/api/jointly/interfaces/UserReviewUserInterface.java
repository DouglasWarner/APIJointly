package com.douglas.api.jointly.interfaces;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.UserReviewUser;

public interface UserReviewUserInterface {
	public List<Map<String, Object>> getList(String userEmail);
	public UserReviewUser insert(GregorianCalendar date, String userEmail, String userReviewEmail, String review, int stars);
	public void delete(GregorianCalendar date, String userEmail, String userReviewEmail);
}
