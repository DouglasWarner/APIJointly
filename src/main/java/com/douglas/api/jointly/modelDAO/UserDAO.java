package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
	private String qryGetInitiativeCreated = "SELECT * FROM initiative WHERE created_by=?";
	
	@Override
	public List<Map<String, Object>> getList() {
		List<Map<String, Object>> lista = template.queryForList(qryGetList);
		return lista;
	}

	@Override
	public User getUser(String email) {
		return template.queryForObject(qryGetUser, new BeanPropertyRowMapper<User>(User.class), email);
	}
	
	@Override
	public int insert(String email, String password, String name, String phone, byte[] imagen, String location,
			String description) {
		return template.update(qryInsert,
				email, password, name, phone, imagen, location, description);
	}

	@Override
	public int update(String email, String password, String name, String phone, byte[] imagen, String location,
			String description, int id) {
		return template.update(qryUpdate,
				email, password, name, phone, imagen, location, description, id);
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
