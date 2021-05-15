package com.douglas.api.jointly;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class Utils {

	public final static int join = 0;
	public final static int done = 1;
	
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
}
