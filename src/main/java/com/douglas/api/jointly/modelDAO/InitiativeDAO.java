package com.douglas.api.jointly.modelDAO;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.Utils;
import com.douglas.api.jointly.interfaces.InitiativeInterface;
import com.douglas.api.jointly.model.Initiative;

@Repository
public class InitiativeDAO implements InitiativeInterface {
	
	@Autowired
	private JdbcTemplate template;
	
	private String qryGetList = "SELECT * FROM initiative";
	private String qryGetListByName = "SELECT * FROM initiative WHERE name=?";
	private String qryGetInitiative = "SELECT * FROM initiative WHERE id=?";
	private String qryInsertInitiative = "INSERT INTO initiative (name, created_at, target_date, description, target_area, location, imagen, target_amount, status, created_by, ref_code) "
														+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String qryUpdateInitiative = "UPDATE initiative SET name=?, target_date=?, description=?, location=?, imagen=?, target_amount=?, status=? where id=?";
	private String qryDelete = "DELETE FROM initiative WHERE id=?";
	private String qryGetInitiativeToSync = "SELECT * FROM initiative WHERE id=? AND created_by=?";
	private String qryUpdateInitiativeToSync = "UPDATE initiative SET name=?, target_date=?, description=?, location=?, imagen=?, target_amount=?, status=?, is_sync=? where id=?";
	
	private int[] getListParamsInsert() {
		int[] list = new int[11];
		
		list[0]= Types.VARCHAR;
		list[1]= Types.VARCHAR;
		list[2]= Types.VARCHAR;
		list[3]= Types.VARCHAR;
		list[4]= Types.VARCHAR;
		list[5]= Types.VARCHAR;
		list[6]= Types.BLOB;
		list[7]= Types.INTEGER;
		list[8]= Types.VARCHAR;
		list[9]= Types.VARCHAR;
		list[10]= Types.VARCHAR;
		
		return list;
	}
	
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
	public Initiative getInitiativeById(long id) {
		Initiative initiative = template.queryForObject(qryGetInitiative, new BeanPropertyRowMapper<Initiative>(Initiative.class), id);
		
		initiative.setCreatedAt(Utils.getFormatStringDate(initiative.getCreatedAt()));
		initiative.setTargetDate(Utils.getFormatStringDate(initiative.getTargetDate()));
		
		return initiative;
	}

	@Override
	public Initiative getInitiativeToSync(long id, String createdBy) {
		Initiative initiative = template.queryForObject(qryGetInitiativeToSync, new BeanPropertyRowMapper<Initiative>(Initiative.class), id, createdBy);
		
		initiative.setCreatedAt(Utils.getFormatStringDate(initiative.getCreatedAt()));
		initiative.setTargetDate(Utils.getFormatStringDate(initiative.getTargetDate()));
		
		return initiative;
	}

	@Override
	public long insert(Initiative initiative) {
		
		PreparedStatementCreatorFactory creatorFactory = new PreparedStatementCreatorFactory(qryInsertInitiative, getListParamsInsert());
		creatorFactory.setReturnGeneratedKeys(true);
		PreparedStatementCreator creator = creatorFactory.newPreparedStatementCreator(new Object[] {initiative.getName(), initiative.getCreatedAt(), initiative.getTargetDate(), 
				initiative.getDescription(), initiative.getTargetArea(), initiative.getLocation(), initiative.getImagen(), 
				initiative.getTargetAmount(), initiative.getStatus(), initiative.getCreatedBy(), initiative.getRefCode()});
		KeyHolder holder = new GeneratedKeyHolder();
		
		template.update(creator, holder);
		
		return holder.getKey().longValue();
	}

	@Override
	public int update(String name, String targetDate,
							String description, String targetArea,
							String location, byte[] imagen,
							int targetAmount, String status, long id) {
		return template.update(qryUpdateInitiative,
				name, targetDate, description, location, imagen, targetAmount, status, id);
	}

	@Override
	public int delete(long id) {
		return template.update(qryDelete, id);
	}

	@Override
	public int updateSync(Initiative initiative) {
		initiative.setIs_sync(true);
		return template.update(qryUpdateInitiativeToSync,
				initiative.getName(), initiative.getTargetDate(), initiative.getDescription(), 
				initiative.getLocation(), initiative.getImagen(), initiative.getTargetAmount(), 
				initiative.getStatus(), initiative.isIs_sync(), initiative.getId());
		
	}
}
