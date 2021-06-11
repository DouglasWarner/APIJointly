package com.douglas.api.jointly.modelDAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.interfaces.CountriesInterface;

@Repository
public class CountriesDAO implements CountriesInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetListCountries = "SELECT * FROM countries";

	@Override
	public List<Map<String, Object>> getList() {
		return template.queryForList(qryGetListCountries);
	}	
}
