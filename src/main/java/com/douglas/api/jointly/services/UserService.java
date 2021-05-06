package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.UserInterface;
import com.douglas.api.jointly.model.User;
import com.douglas.api.jointly.modelDAO.UserDAO;

@Service
public class UserService implements UserInterface {

	@Autowired
	private UserDAO userDao;

	@Override
	public List<Map<String, Object>> getList() {
		List<Map<String, Object>> list = userDao.getList();
		return list;
	}

	@Override
	public User getUser(String email) {
		User user = userDao.getUser(email);
		return user;
	}

	@Override
	public User insert(String email, String password, String name, String phone, byte[] imagen, String location,
			String description) {
		User user = userDao.insert(email, password, name, phone, imagen, location, description);
		return user;
	}

	@Override
	public User update(String email, String password, String name, String phone, byte[] imagen, String location,
			String description, int id) {
		User user = userDao.update(email, password, name, phone, imagen, location, description, id);
		return user;
	}

	@Override
	public void delete(int idUser) {
		userDao.delete(idUser);
	}

	@Override
	public List<Map<String, Object>> getInitiativeCreated(String email) {
		List<Map<String, Object>> list = userDao.getInitiativeCreated(email);
		return list;
	} 
}
