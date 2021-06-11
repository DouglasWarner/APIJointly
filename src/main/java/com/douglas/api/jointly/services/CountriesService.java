package com.douglas.api.jointly.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.api.jointly.interfaces.CountriesInterface;
import com.douglas.api.jointly.modelDAO.CountriesDAO;

@Service
public class CountriesService implements CountriesInterface {

	@Autowired
	private CountriesDAO CountriesDAO;
	
	@Override
	public List<Map<String, Object>> getList() {
		return CountriesDAO.getList();
	}

}
