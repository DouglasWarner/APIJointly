package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.UserJoinInitiativeInterface;
import com.douglas.api.jointly.model.UserJoinInitiative;

@Repository
public class UserJoinInitiativeDAO implements UserJoinInitiativeInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String getUsersJoined = "SELECT * FROM user_join_initiative";
	private String getUsersJoinedByInitiative = "SELECT * FROM user WHERE email in (SELECT user_email FROM user_join_initiative WHERE id_initiative=?)";
	private String qryInsert = "INSERT INTO user_join_initiative(id_initiative, user_email, type) "
								+ "VALUES (?,?,?)";
	private String qryUpdate = "UPDATE user_join_initiative SET type=? WHERE id_initiative=? AND user_email=?";
	private String qryDelete = "DELETE FROM user_join_initiative WHERE id_initiative=? AND user_email=?";
	private String qryInitiativeJoinedByUser = "SELECT * FROM initiative WHERE id in (SELECT id_initiative FROM user_join_initiative WHERE user_email=? AND type=?)";
	private String qryGetUserJoinInitiative = "SELECT * FROM user_join_initiative WHERE id_initiative=? AND user_email=?";

	@Override
	public List<Map<String, Object>> getUsersJoined() {
		List<Map<String, Object>> list = template.queryForList(getUsersJoined);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getUsersJoinedByInitiative(long idInitiative) {
		List<Map<String, Object>> list = template.queryForList(getUsersJoinedByInitiative, idInitiative);
		return list;
	}

	@Override
	public long insert(long idInitiative, String userEmail, int type) {
		return template.update(qryInsert,
				idInitiative, userEmail, type);
	}

	@Override
	public int update(long idInitiative, String userEmail, int type) {
		return template.update(qryUpdate,
				type, idInitiative, userEmail);
	}

	@Override
	public int delete(long idInitiative, String userEmail) {
		return template.update(qryDelete, idInitiative, userEmail);
	}

	@Override
	public List<Map<String, Object>> getInitiativeJoinedByUser(String userEmail, int type) {
		List<Map<String, Object>> list = template.queryForList(qryInitiativeJoinedByUser, userEmail, type);
		return list;
	}

	@Override
	public UserJoinInitiative getUserJoinInitiative(long idInitiative, String userEmail) {
		return template.queryForObject(qryGetUserJoinInitiative, 
				new BeanPropertyRowMapper<>(UserJoinInitiative.class, true) {
			
					@Override
					public UserJoinInitiative mapRow(ResultSet rs, int rowNumber) throws SQLException {
						UserJoinInitiative initiative = new UserJoinInitiative ();
						initiative.setIdInitiative(rs.getLong(1));
						initiative.setIdUser(rs.getString(2));
						initiative.setDate(rs.getString(3));
						initiative.setType(rs.getInt(4));
						
						return initiative;
					}					
					
				}, idInitiative, userEmail);
	}
	
	class RowMapper extends BeanPropertyRowMapper<UserJoinInitiative>
	{
		
	}
}
