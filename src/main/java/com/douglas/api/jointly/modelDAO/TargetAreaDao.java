package com.douglas.api.jointly.modelDAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.TargetAreaInterface;

@Repository
public class TargetAreaDao implements TargetAreaInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetListTargetArea = "SELECT * FROM target_area";

	@Override
	public List<Map<String, Object>> getList() {
		return template.queryForList(qryGetListTargetArea);
	}	
}
