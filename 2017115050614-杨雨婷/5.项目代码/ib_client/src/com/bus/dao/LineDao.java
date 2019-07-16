package com.bus.dao;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.model.Line;

public interface LineDao {
	
	List<Line> find(Line condition);
	
	void updateLine(Line line) throws SQLException;
	
	int dropLine(Line line) throws SQLException;
	
	void saveLine(Line line) throws SQLException;
}
