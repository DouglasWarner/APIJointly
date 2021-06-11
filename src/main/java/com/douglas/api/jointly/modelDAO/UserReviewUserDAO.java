package com.douglas.api.jointly.modelDAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.Utils;
import com.douglas.api.jointly.interfaces.UserReviewUserInterface;
import com.douglas.api.jointly.model.UserReviewUser;

@Repository
public class UserReviewUserDAO implements UserReviewUserInterface{

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetListReview = "SELECT * FROM user_review_user";
	private String qryGetList = "SELECT * FROM user_review_user WHERE user_review=?";
	private String qryInsert = "INSERT INTO user_review_user (user, user_review, date, review, stars) VALUES (?,?,?,?,?)";
	private String qryDelete = "DELETE FROM user_review_user WHERE user=? AND user_review=?";
	private String qryGetReview = "SELECT * FROM user_review_user WHERE user=? AND user_review=? AND date=?";

	@Override
	public List<Map<String, Object>> getListByUser(String userEmail) {
		List<Map<String, Object>> list = template.queryForList(qryGetList, userEmail);
		return list;
	}

	@Override
	public int insert(UserReviewUser userReviewUser) {
		return template.update(qryInsert,
				userReviewUser.getUser(), userReviewUser.getUserReview(), userReviewUser.getDate(), 
				userReviewUser.getReview(), userReviewUser.getStars());
	}

	@Override
	public int delete(String userEmail, String userReviewEmail) {
		return template.update(qryDelete, userEmail, userReviewEmail);
	}

	@Override
	public UserReviewUser getReview(String userEmail, String userReviewEmail, String date) {
		UserReviewUser reviewUser = template.queryForObject(qryGetReview, new BeanPropertyRowMapper<UserReviewUser>(UserReviewUser.class), userEmail, userReviewEmail, date);
		
		reviewUser.setDate(Utils.getFormatStringDate(reviewUser.getDate()));
		
		return reviewUser;
	}

	@Override
	public List<Map<String, Object>> getListReviews() {
		return template.queryForList(qryGetListReview);
	}

	
}
