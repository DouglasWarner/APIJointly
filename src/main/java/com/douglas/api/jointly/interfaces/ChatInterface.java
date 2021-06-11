package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.Chat;

public interface ChatInterface {
	public List<Map<String, Object>> getList();
	public List<Map<String, Object>> getListByInitiative(long id_initiative);
	public long insert(Chat chat);
	public Chat getChatMessage(long id_initiative, String date, String user_email);
}
