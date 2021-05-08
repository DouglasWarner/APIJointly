package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.UserReviewUserInterface;

@Repository
public class UserReviewUserDAO implements UserReviewUserInterface{

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetList = "SELECT * FROM user_review_user WHERE user_review=?";
	private String qryInsert = "INSERT INTO user_review_user (date, user, user_review, review, stars) VALUES (?,?,?,?,?)";
	private String qryDelete = "DELETE user_review_user WHERE date=? AND user=? AND user_review=?";

	@Override
	public List<Map<String, Object>> getList(String userEmail) {
		List<Map<String, Object>> list = template.queryForList(qryGetList, userEmail);
		return list;
	}

	@Override
	public int insert(String date, String userEmail, String userReviewEmail, String review, int stars) {
		return template.update(qryInsert,
				date, userEmail, userReviewEmail, review, stars);
	}

	@Override
	public void delete(String date, String userEmail, String userReviewEmail) {
		template.query(qryDelete,
				(ResultSet rs) -> {
					rs.deleteRow();
				}, date, userEmail, userReviewEmail);
	}

	
}
