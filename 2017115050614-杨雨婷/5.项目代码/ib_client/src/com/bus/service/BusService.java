package com.bus.service;

import java.util.List;
import java.util.Map;

import com.bus.dao.model.Bus;

public interface BusService {
	public String save(Map<String,List<Bus>> dataMapList);
	
	public List<Bus> find(Bus condition);
}
