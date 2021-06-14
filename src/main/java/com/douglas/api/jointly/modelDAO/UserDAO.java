package com.douglas.api.jointly.modelDAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.Utils;
import com.douglas.api.jointly.interfaces.UserInterface;
import com.douglas.api.jointly.model.User;

@Repository
public class UserDAO implements UserInterface {
	
	@Autowired
	private JdbcTemplate template;
	
	private String qryGetList = "SELECT * FROM user";
	private String qryGetUser = "SELECT * FROM user WHERE email=?";
	private String qryInsert = "INSERT INTO user (email, password, name, phone, imagen, location, description, created_at) "
												+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private String qryUpdate = "UPDATE user SET password=?, name=?, phone=?, imagen=?, location=?, description=? WHERE id=?";
	private String qryDelete = "DELETE FROM user WHERE id=?";
	private String qryGetListInitiativeCreatedByUser = "SELECT * FROM initiative WHERE created_by=?";
	private String qryUpdateToSync = "UPDATE user SET name=?, phone=?, imagen=?, location=?, description=?, is_sync=? WHERE id=?";
	
	@Override
	public List<Map<String, Object>> getList() {
		List<Map<String, Object>> lista = template.queryForList(qryGetList);
		return lista;
	}

	@Override
	public User getUser(String email) {
		User u = template.queryForObject(qryGetUser, new BeanPropertyRowMapper<User>(User.class), email);
		u.setCreatedAt(Utils.getFormatStringDate(u.getCreatedAt()));
		
		return u;
	}
	
	@Override
	public int insert(String email, String password, String name, String phone, byte[] imagen, String location,
			String description, String created_at) {
		return template.update(qryInsert,
				email, password, name, phone, imagen, location, description, created_at);
	}

	@Override
	public int update(String email, String password, String name, String phone, byte[] imagen, String location,
			String description, int id) {
		return template.update(qryUpdate,
				email, password, name, phone, imagen, location, description, id);
	}

	@Override
	public int delete(int idUser) {
		return template.update(qryDelete, idUser);
	}

	@Override
	public List<Map<String, Object>> getListInitiativeCreatedByUser(String email) {
		List<Map<String, Object>> lista = template.queryForList(qryGetListInitiativeCreatedByUser, email);
		return lista;
	}

	@Override
	public int updateToSync(User user) {
		return template.update(qryUpdate, 
				user.getName(), user.getPhone(), user.getImagen(), user.getLocation(), user.getDescription(), true, user.getId());
	}

}
