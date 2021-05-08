package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.InitiativeInterface;
import com.douglas.api.jointly.model.Initiative;

@Repository
public class InitiativeDAO implements InitiativeInterface {
	
	@Autowired
	private JdbcTemplate template;
	
	private String qryGetList = "SELECT * FROM initiative";
	private String qryGetListByName = "SELECT * FROM initiative WHERE name=?";
	private String qryGetInitiative = "SELECT * FROM initiative WHERE id=?";
	private String qryInsertInitiative = "INSERT INTO initiative (name, target_date, description, target_area, location, imagen, target_amount, status, created_by, ref_code) "
														+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String qryUpdateInitiative = "UPDATE initiative SET name=?, target_date=?, description=?, location=?, imagen=?, target_amount=?, status=? where id=?";
	private String qryDelete = "DELETE initiative WHERE id=?";
	
	@Override
	public List<Map<String, Object>> getList() {		
		List<Map<String, Object>> list = template.queryForList(qryGetList);		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getListByName(String name) {		
		List<Map<String, Object>> list = template.queryForList(qryGetListByName, name);
		return list;
	}
	
	@Override
	public Initiative getInitiativeById(int id) {
		Initiative initiative = template.queryForObject(qryGetInitiative, new BeanPropertyRowMapper<Initiative>(Initiative.class), id);
		return initiative;
	}

	@Override
	public int insert(String name, String targetDate,
							String description, String targetArea,
							String location, byte[] imagen,
							int targetAmount, String status,
							String createdBy, String refcode) {		
		return template.update(qryInsertInitiative,
				name, targetDate, description, targetArea, location, imagen, targetAmount, status, createdBy, refcode);
	}

	@Override
	public int update(String name, String targetDate,
							String description, String targetArea,
							String location, byte[] imagen,
							int targetAmount, String status, int id) {		
		return template.update(qryUpdateInitiative,
				name, targetDate, description, location, imagen, targetAmount, status, id);
	}

	@Override
	public void delete(int id) {
		template.query(qryDelete,
				(ResultSet rs) -> {
					rs.deleteRow();
				}, id);
	}
}
