package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	private String qryGetReview = "SELECT * FROM user_review_user WHERE user=? AND user_review=?";

	@Override
	public List<Map<String, Object>> getList(String userEmail) {
		List<Map<String, Object>> list = template.queryForList(qryGetList, userEmail);
		return list;
	}

	@Override
	public int insert(String userEmail, String userReviewEmail, String date, String review, int stars) {
		return template.update(qryInsert,
				userEmail, userReviewEmail, date, review, stars);
	}

	@Override
	public int delete(String userEmail, String userReviewEmail) {
		return template.update(qryDelete, userEmail, userReviewEmail);
	}

	@Override
	public UserReviewUser getReview(String userEmail, String userReviewEmail) {
		return template.queryForObject(qryGetReview, new BeanPropertyRowMapper<>(UserReviewUser.class, true) {

			@Override
			public UserReviewUser mapRow(ResultSet rs, int rowNumber) throws SQLException {
				UserReviewUser reviewUser = new UserReviewUser();
				reviewUser.setUser(rs.getString(1));
				reviewUser.setUserReview(rs.getString(2));
				reviewUser.setDate(Utils.getFormatStringDate(rs.getString(3)));
				reviewUser.setReview(rs.getString(4));
				reviewUser.setStars(rs.getInt(5));
				
				return reviewUser;
			}
			
		}, userEmail, userReviewEmail);
	}

	@Override
	public List<Map<String, Object>> getListReviews() {
		return template.queryForList(qryGetListReview);
	}

	
}
