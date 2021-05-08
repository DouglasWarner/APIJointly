package com.douglas.api.jointly.interfaces;

import java.util.List;
import java.util.Map;

import com.douglas.api.jointly.model.User;

public interface UserInterface {
	public List<Map<String, Object>> getList();
	public User getUser(String email);
	public int insert(String email, String password, String name, 
						String phone, byte[] imagen, String location, 
						String description);
	public int update(String email, String password, String name, 
			String phone, byte[] imagen, String location, 
			String description, int id);
	public void delete(int idUser);
	public List<Map<String, Object>> getInitiativeCreated(String email);
}
