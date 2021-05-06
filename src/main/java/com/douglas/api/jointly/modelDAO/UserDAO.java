package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.UserInterface;
import com.douglas.api.jointly.model.User;

@Repository
public class UserDAO implements UserInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetList = "SELECT * FROM user";
	private String qryGetUser = "SELECT * FROM user WHERE email=?";
	private String qryInsert = "INSERT INTO user (email, password, name, phone, imagen, location, description) "
												+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private String qryUpdate = "UPDATE user SET email=?, password=?, name=?, phone=?, imagen=?, location=?, description=? WHERE id=?";
	private String qryDelete = "DELETE user WHERE id=?";
	private String qryGetInitiativeCreated = "SELECT * FROM initiative WHERE createdBy=?";
	
	@Override
	public List<Map<String, Object>> getList() {
		List<Map<String, Object>> lista = template.queryForList(qryGetList);
		return lista;
	}

	@Override
	public User getUser(String email) {
		User user = template.queryForObject(qryGetUser, User.class, email);
		return user;
	}
	
	@Override
	public User insert(String email, String password, String name, String phone, byte[] imagen, String location,
			String description) {
		User user = template.queryForObject(qryInsert,
				User.class,
				email, password, name, phone, imagen, location, description);
		return user;
	}

	@Override
	public User update(String email, String password, String name, String phone, byte[] imagen, String location,
			String description, int id) {
		User user = template.queryForObject(qryUpdate,
				User.class,
				email, password, name, phone, imagen, location, description, id);
		return user;
	}

	@Override
	public void delete(int idUser) {
		template.query(qryDelete,
				(ResultSet rs) -> {
					rs.deleteRow();
				}, idUser);
	}

	@Override
	public List<Map<String, Object>> getInitiativeCreated(String email) {
		List<Map<String, Object>> lista = template.queryForList(qryGetInitiativeCreated, email);
		return lista;
	}

}
