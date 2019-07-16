package com.bus.dao;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.model.Station;

public interface StationDao {
	
	List<Station> find(Station condition);
	
	void updateStation(Station station) throws SQLException;
	
	void dropStation(Station station) throws SQLException;
	
	void saveStation(Station station) throws SQLException;
}
