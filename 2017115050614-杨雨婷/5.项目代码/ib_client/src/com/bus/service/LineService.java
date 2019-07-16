package com.bus.service;

import java.util.List;
import java.util.Map;

import com.bus.dao.model.Line;

public interface LineService {

	public String save(Map<String,List<Line>> dataMapList);

	public List<Line> find(Line condition);
}
