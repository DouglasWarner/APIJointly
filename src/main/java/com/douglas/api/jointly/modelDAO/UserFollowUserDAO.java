package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.UserFollowUserInterface;
import com.douglas.api.jointly.model.UserFollowUser;

@Repository
public class UserFollowUserDAO implements UserFollowUserInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetListFollowed = "SELECT * FROM user WHERE email in (SELECT userFollow FROM user_follow_user WHERE user=?)";
	private String qryGetListFollowers = "SELECT * FROM user WHERE email in (SELECT user FROM user_follow_user WHERE userFollow=?)";
	private String qryGetCountFollowed = "SELECT COUNT(*) FROM user_follow_user WHERE user=?";
	private String qryGetCountFollowers = "SELECT COUNT(*) FROM user_follow_user WHERE userFollow=?";
	private String qryInsert = "INSERT INTO user_follow_user VALUES (?,?)";
	private String qryDelete = "DELETE user_follow_user WHERE user=? AND userFollow=?";
	
	@Override
	public List<Map<String, Object>> getListFollowed(String email) {
		List<Map<String, Object>> list = template.queryForList(qryGetListFollowed, email);
		return list;
	}

	@Override
	public List<Map<String, Object>> getListFollowers(String userEmail) {
		List<Map<String, Object>> list = template.queryForList(qryGetListFollowers, userEmail);
		return list;
	}
	
	@Override
	public int getCountFollowed(String email) {
		int count = template.queryForObject(qryGetCountFollowed, Integer.class, email);
		return count;
	}

	@Override
	public int getCountFollowers(String email) {
		int count = template.queryForObject(qryGetCountFollowers, Integer.class, email);
		return count;
	}
	
	@Override
	public UserFollowUser insert(String userEmail, String userFollowEmail) {
		UserFollowUser userFollowUser = template.queryForObject(qryInsert,
				UserFollowUser.class,
				userEmail, userFollowEmail);
		return userFollowUser;
	}

	@Override
	public void delete(String userEmail, String userFollowEmail) {
		template.query(qryDelete,
				(ResultSet rs) -> {
					rs.deleteRow();
				}, userEmail, userFollowEmail);
	}
}