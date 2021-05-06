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
	private String qryGetInitiative = "SELECT * FROM initiative WHERE id=%s";
	private String qryGetListByName = "SELECT * FROM initiative WHERE name=?";
	private String qryInsertInitiative = "INSERT INTO initiative (name, targetDate, description, targetArea, location, imagen, targetAmount, status, createBy, refCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String qryUpdateInitiative = "UPDATE initiative SET name=?, targetDate=?, description=?, location=?, imagen=?, targetAmount=?, status=? where id=?";
	private String qryDelete = "DELETE initiative WHERE id=%d";
	
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
		Initiative initiative = template.queryForObject(String.format(qryGetInitiative, id), Initiative.class);
		return initiative;
	}

	@Override
	public Initiative insert(String name, GregorianCalendar targetDate,
							String description, String targetArea,
							String location, byte[] imagen,
							int targetAmount, String status,
							String createBy, String refcode) {
		Initiative initiative = template.queryForObject(qryInsertInitiative,
				Initiative.class,
				name, description, targetDate, location, imagen, targetAmount, status, createBy, refcode);
		
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
		template.query(String.format(qryDelete, id),
				(ResultSet rs) -> {
					rs.deleteRow();
				}
			);
	}
}
