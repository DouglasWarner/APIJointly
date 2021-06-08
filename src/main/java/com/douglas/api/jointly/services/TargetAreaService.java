package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglas.api.jointly.interfaces.TargetAreaInterface;
import com.douglas.api.jointly.modelDAO.TargetAreaDao;

public class TargetAreaService implements TargetAreaInterface {

	@Autowired
	private TargetAreaDao targetAreaDAO;
	
	@Override
	public List<Map<String, Object>> getList() {
		return targetAreaDAO.getList();
	}

}
