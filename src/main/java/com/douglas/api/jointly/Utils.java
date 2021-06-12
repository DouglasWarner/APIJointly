package com.douglas.api.jointly;

import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class Utils {

	public final static int JOIN = 0;
	public final static int DONE = 1;
	
	public final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
	public final static DateTimeFormatter FORMAT2 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	

	public static final String PATH_IMAGES = "src//main//resources//static/images";
	
	/**
	 * Execute any insert query and return the auto generated key
	 * @param template
	 * @param query
	 * @param params
	 * @param objects
	 * @return
	 */
	public static long executedInsert(JdbcTemplate template, String query, int[] params, Object...objects)
	{
		PreparedStatementCreatorFactory creatorFactory = new PreparedStatementCreatorFactory(query, params);
		creatorFactory.setReturnGeneratedKeys(true);
		PreparedStatementCreator creator = creatorFactory.newPreparedStatementCreator(objects);
		KeyHolder holder = new GeneratedKeyHolder();
		
		template.update(creator, holder);
		
		return holder.getKey().longValue();
	}
	
	public static String getFormatStringDate(String date) {
		return date.replace('-', '/');
	}

}
