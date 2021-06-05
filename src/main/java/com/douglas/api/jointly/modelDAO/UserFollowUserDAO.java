package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.UserFollowUserInterface;
import com.douglas.api.jointly.model.UserFollowUser;

@Repository
public class UserFollowUserDAO implements UserFollowUserInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetListFollows = "SELECT * FROM user_follow_user";
	private String qryGetListFollowed = "SELECT * FROM user WHERE email in (SELECT user_follow FROM user_follow_user WHERE user=?)";
	private String qryGetListFollowers = "SELECT * FROM user WHERE email in (SELECT user FROM user_follow_user WHERE user_follow=?)";
	private String qryGetCountFollowed = "SELECT COUNT(*) FROM user_follow_user WHERE user=?";
	private String qryGetCountFollowers = "SELECT COUNT(*) FROM user_follow_user WHERE user_follow=?";
	private String qryInsert = "INSERT INTO user_follow_user VALUES (?,?)";
	private String qryDelete = "DELETE FROM user_follow_user WHERE user=? AND user_follow=?";
	private String qryGetUserFollowUser = "SELECT * FROM user_follow_user WHERE user=? AND user_follow=?";
	
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
	public int insert(String userEmail, String userFollowEmail) {
		return template.update(qryInsert,
				userEmail, userFollowEmail);
	}

	@Override
	public int delete(String userEmail, String userFollowEmail) {
		return template.update(qryDelete, userEmail, userFollowEmail);
	}

	@Override
	public UserFollowUser getUserFollowUser(String userEmail, String userFollowEmail) {
		return template.queryForObject(qryGetUserFollowUser, 
				new BeanPropertyRowMapper<>(UserFollowUser.class, true) {
			
					@Override
					public UserFollowUser mapRow(ResultSet rs, int rowNumber) throws SQLException {
						UserFollowUser followUser = new UserFollowUser();
						followUser.setUser(rs.getString(1));
						followUser.setUserFollow(rs.getString(2));
						
						return followUser;
					}
			
				} , userEmail, userFollowEmail);
	}

	@Override
	public List<Map<String, Object>> getListFollows() {
		return template.queryForList(qryGetListFollows);
	}
}