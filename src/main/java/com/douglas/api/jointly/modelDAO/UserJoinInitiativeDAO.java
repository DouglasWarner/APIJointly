package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.UserJoinInitiativeInterface;
import com.douglas.api.jointly.model.UserJoinInitiative;

@Repository
public class UserJoinInitiativeDAO implements UserJoinInitiativeInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetUsersJoined = "SELECT * FROM user WHERE email in (SELECT userEmail FROM userJoinInitiative WHERE idInitiative=%d)";
	private String qryInitiativeJoinedByUser = "SELECT * FROM initiative WHERE id in (SELECT idInitiative FROM userJoinInitiative WHERE userEmail=%s)";
	private String qryInsert = "INSERT INTO user_join_initiative(idInitiative, idUser, type) "
								+ "VALUES (?,?,?)";
	private String qryUpdate = "UPDATE user_join_initiative SET type=? WHERE date=? AND idInitiative=? AND idUser=?";
	private String qryDelete = "DELETE user_join_initiative WHERE date=%s AND idInitiative=%d AND userEmail=%s";

	@Override
	public List<Map<String, Object>> getUsersJoined(int idInitiative) {
		List<Map<String, Object>> list = template.queryForList(qryGetUsersJoined, idInitiative);
		return list;
	}

	@Override
	public UserJoinInitiative insert(int idInitiative, String userEmail, int type) {
		UserJoinInitiative userJoinInitiative = template.queryForObject(qryInsert,
				UserJoinInitiative.class,
				idInitiative, userEmail, type);
		return userJoinInitiative;
	}

	@Override
	public UserJoinInitiative update(GregorianCalendar date, int idInitiative, String userEmail, int type) {
		UserJoinInitiative userJoinInitiative = template.queryForObject(qryUpdate,
				UserJoinInitiative.class,
				type, date, idInitiative, userEmail);
		return userJoinInitiative;
	}

	@Override
	public void delete(GregorianCalendar date, int idInitiative, String userEmail) {
		template.query(String.format(qryDelete, date, idInitiative, userEmail),
				(ResultSet rs) -> {
					rs.deleteRow();
				}
			);
	}

	@Override
	public List<Map<String, Object>> getInitiativeJoinedByUser(String userEmail) {
		List<Map<String, Object>> list = template.queryForList(qryInitiativeJoinedByUser, userEmail);
		return list;
	}
}
