package com.douglas.api.jointly.modelDAO;

import java.sql.ResultSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	private String qryInsertInitiative = "INSERT INTO initiative (name, targetDate, description, targetArea, location, imagen, targetAmount, status, createdBy, refCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String qryUpdateInitiative = "UPDATE initiative SET name=?, targetDate=?, description=?, location=?, imagen=?, targetAmount=?, status=? where id=?";
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
		Initiative initiative = template.queryForObject(qryGetInitiative, Initiative.class, id);
		return initiative;
	}

	@Override
	public Initiative insert(String name, GregorianCalendar targetDate,
							String description, String targetArea,
							String location, byte[] imagen,
							int targetAmount, String status,
							String createdBy, String refcode) {
		Initiative initiative = template.queryForObject(qryInsertInitiative,
				Initiative.class,
				name, description, targetDate, location, imagen, targetAmount, status, createdBy, refcode);
		
		return initiative;
	}

	@Override
	public Initiative update(String name, GregorianCalendar targetDate,
							String description, String targetArea,
							String location, byte[] imagen,
							int targetAmount, String status, int id) {
		Initiative initiative = template.queryForObject(qryUpdateInitiative,
				Initiative.class,
				name, targetDate, description, location, imagen, targetAmount, status, id);
		
		return initiative;
	}

	@Override
	public void delete(int id) {
		template.query(qryDelete,
				(ResultSet rs) -> {
					rs.deleteRow();
				}, id);
	}
}
