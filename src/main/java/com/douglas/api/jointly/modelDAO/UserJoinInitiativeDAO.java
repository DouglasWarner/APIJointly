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
	
	private String qryGetUsersJoined = "SELECT * FROM user WHERE email in (SELECT userEmail FROM user_join_initiative WHERE idInitiative=?)";
	private String qryInsert = "INSERT INTO user_join_initiative(date, idInitiative, idUser, type) "
								+ "VALUES (?,?,?,?)";
	private String qryUpdate = "UPDATE user_join_initiative SET type=? WHERE date=? AND idInitiative=? AND idUser=?";
	private String qryDelete = "DELETE user_join_initiative WHERE date=? AND idInitiative=? AND userEmail=?";
	private String qryInitiativeJoinedByUser = "SELECT * FROM initiative WHERE id in (SELECT idInitiative FROM user_join_initiative WHERE userEmail=?)";

	@Override
	public List<Map<String, Object>> getUsersJoined(int idInitiative) {
		List<Map<String, Object>> list = template.queryForList(qryGetUsersJoined, idInitiative);
		return list;
	}

	@Override
	public UserJoinInitiative insert(GregorianCalendar date, int idInitiative, String userEmail, int type) {
		UserJoinInitiative userJoinInitiative = template.queryForObject(qryInsert,
				UserJoinInitiative.class,
				date, idInitiative, userEmail, type);
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
