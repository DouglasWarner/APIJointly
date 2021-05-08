package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.UserJoinInitiativeInterface;

@Repository
public class UserJoinInitiativeDAO implements UserJoinInitiativeInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetUsersJoined = "SELECT * FROM user WHERE email in (SELECT user_email FROM user_join_initiative WHERE id_initiative=?)";
	private String qryInsert = "INSERT INTO user_join_initiative(date, id_initiative, user_email, type) "
								+ "VALUES (?,?,?,?)";
	private String qryUpdate = "UPDATE user_join_initiative SET type=? WHERE date=? AND id_initiative=? AND user_email=?";
	private String qryDelete = "DELETE user_join_initiative WHERE date=? AND id_initiative=? AND user_email=?";
	private String qryInitiativeJoinedByUser = "SELECT * FROM initiative WHERE id in (SELECT id_initiative FROM user_join_initiative WHERE user_email=?)";

	@Override
	public List<Map<String, Object>> getUsersJoined(int idInitiative) {
		List<Map<String, Object>> list = template.queryForList(qryGetUsersJoined, idInitiative);
		return list;
	}

	@Override
	public int insert(String date, int idInitiative, String userEmail, int type) {
		return template.update(qryInsert,
				date, idInitiative, userEmail, type);
	}

	@Override
	public int update(String date, int idInitiative, String userEmail, int type) {
		return template.update(qryUpdate,
				type, date, idInitiative, userEmail);
	}

	@Override
	public void delete(String date, int idInitiative, String userEmail) {
		template.query(qryDelete,
				(ResultSet rs) -> {
					rs.deleteRow();
				}, date, idInitiative, userEmail);
	}

	@Override
	public List<Map<String, Object>> getInitiativeJoinedByUser(String userEmail) {
		List<Map<String, Object>> list = template.queryForList(qryInitiativeJoinedByUser, userEmail);
		return list;
	}
}
