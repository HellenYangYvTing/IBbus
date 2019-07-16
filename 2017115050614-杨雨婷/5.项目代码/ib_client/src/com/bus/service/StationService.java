package com.bus.service;

import java.util.List;
import java.util.Map;

import com.bus.dao.model.Station;

public interface StationService {
	
	public String save(Map<String,List<Station>> dataMapList);

	public List<Station> find(Station condition);
}
