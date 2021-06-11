package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.ChatInterface;
import com.douglas.api.jointly.model.Chat;
import com.douglas.api.jointly.modelDAO.ChatDAO;

@Service
public class ChatService implements ChatInterface {

	@Autowired
	private ChatDAO chatDAO;
	
	@Override
	public List<Map<String, Object>> getList() {
		return chatDAO.getList();
	}

	@Override
	public List<Map<String, Object>> getListByInitiative(long id_initiative) {
		return chatDAO.getListByInitiative(id_initiative);
	}

	@Override
	public long insert(Chat chat) {
		return chatDAO.insert(chat);
	}

	@Override
	public Chat getChatMessage(long id_initiative, String date, String user_email) {
		return chatDAO.getChatMessage(id_initiative, date, user_email);
	}

}
