package com.douglas.api.jointly.modelDAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.douglas.api.jointly.Utils;
import com.douglas.api.jointly.interfaces.ChatInterface;
import com.douglas.api.jointly.model.Chat;

@Repository
public class ChatDAO implements ChatInterface {

	@Autowired
	private JdbcTemplate template;
	
	private String qryGetList = "SELECT * FROM chat";
	private String qryGetListByInitiative = "SELECT * FROM chat WHERE id_initiative=?";
	private String qryInsertChat = "INSERT INTO chat (date, id_initiative, email_user, message, read, is_sync) "
			+ "VALUES (?,?,?,?,?,?)";
	private String qryGetChatMessage = "SELECT * FROM chat WHERE id_initiative=? AND email_user=? AND date=?";
	

	@Override
	public List<Map<String, Object>> getList() {
		return template.queryForList(qryGetList);
	}


	@Override
	public List<Map<String, Object>> getListByInitiative(long id_initiative) {
		List<Map<String, Object>> list = template.queryForList(qryGetListByInitiative, id_initiative);
		return list;
	}


	@Override
	public long insert(Chat chat) {
		return template.update(qryInsertChat,
				chat.getDate(), chat.getIdInitiative(), chat.getEmailUser(),
				chat.getMessage(), chat.isRead(), chat.isIs_sync());
	}

	@Override
	public Chat getChatMessage(long id_initiative, String date, String user_email) {
		Chat chat = template.queryForObject(qryGetChatMessage, new BeanPropertyRowMapper<>(Chat.class),
				id_initiative, date, user_email);
		
		chat.setDate(Utils.getFormatStringDate(chat.getDate()));
		
		return chat;
	}
}
