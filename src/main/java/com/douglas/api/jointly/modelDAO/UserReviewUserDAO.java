package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.UserReviewUserInterface;
import com.douglas.api.jointly.model.UserReviewUser;

@Repository
public class UserReviewUserDAO implements UserReviewUserInterface{

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetList = "SELECT * FROM userReviewUser WHERE userEmail=%s";
	private String qryInsert = "INSERT INTO user_review_user (user, userReview, review, stars) "
								+ "VALUES (?,?,?,?)";
	private String qryDelete = "DELETE user_review_user WHERE date=%s AND user=%s AND userReview=%s";

	@Override
	public List<Map<String, Object>> getList(String userEmail) {
		List<Map<String, Object>> list = template.queryForList(qryGetList, userEmail);
		return list;
	}

	@Override
	public UserReviewUser insert(String userEmail, String userReviewEmail, String review, int stars) {
		UserReviewUser userJoinInitiative = template.queryForObject(qryInsert,
				UserReviewUser.class,
				userEmail, userReviewEmail, review, stars);
		return userJoinInitiative;
	}

	@Override
	public void delete(GregorianCalendar date, String userEmail, String userReviewEmail) {
		template.query(String.format(qryDelete, date, userEmail, userReviewEmail),
				(ResultSet rs) -> {
					rs.deleteRow();
				}
			);
	}

	
}
