package com.bus.view.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.bus.dao.model.Bus;
import com.bus.dao.model.Line;
import com.bus.dao.model.Station;
import com.bus.dao.model.User;
import com.bus.enums.ModuleEnum;

public class CustomerTableModelListener implements TableModelListener {

	private ModuleEnum currentModel;

	private Map<String, List<Object>> changeMapList;

	private JTable table;

	public CustomerTableModelListener(ModuleEnum currentModel, JTable table) {
		super();
		this.currentModel = currentModel;
		this.table = table;
		changeMapList = new HashMap<String, List<Object>>();
		changeMapList.put("UPDATE", new ArrayList<>());
		changeMapList.put("DELETE", new ArrayList<>());
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = 0;
		row = e.getFirstRow();
		if (row >=0) {
			if (TableModelEvent.UPDATE == e.getType()) {
				switch (currentModel) {
				case BUS:
					if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
						String busCode = String.valueOf(table.getValueAt(row, 0));
						List<Object> updateList = changeMapList.get("UPDATE");
						Bus bus = new Bus();
						bus.setBusCode(String.valueOf(table.getValueAt(row, 0)));
						bus.setBusLicense(String.valueOf(table.getValueAt(row, 1)));
						bus.setBusType(String.valueOf(table.getValueAt(row, 2)));
						if(String.valueOf(table.getValueAt(row, 3)) == null) {
							bus.setBusStatus(null);
						}else {
							String busStatus = String.valueOf(table.getValueAt(row, 3));
							if("启用".equals(busStatus)) {
								bus.setBusStatus("1");
							}else {
								bus.setBusStatus("0");
							}
						}
						bus.setStartTime(String.valueOf(table.getValueAt(row, 4)));
						
						boolean isSet = false;
						for (int i = 0; i < updateList.size(); i++) {
							Bus updateBus = (Bus) updateList.get(i);
							if (updateBus.getBusCode().equals(busCode)) {
								updateList.set(i, bus);
								isSet = true;
								break;
							}
						}
						if (!isSet) {
							updateList.add(bus);
						}
					}
					break;
				case USER:
					if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
						String userCode = String.valueOf(table.getValueAt(row, 0));
						List<Object> updateList = changeMapList.get("UPDATE");
						User user = new User();
						user.setCode(String.valueOf(table.getValueAt(row, 0)));
						user.setLoginName(String.valueOf(table.getValueAt(row, 1)));
						user.setName(String.valueOf(table.getValueAt(row, 2)));
						user.setPassword(String.valueOf(table.getValueAt(row, 8)));
						user.setPhone(String.valueOf(table.getValueAt(row, 3)));
						user.setIdCard(String.valueOf(table.getValueAt(row, 4)));
						if(table.getValueAt(row, 5) == null ||String.valueOf(table.getValueAt(row, 5)).length()==0) {
							user.setRole(null);
						}else {
							String roleText = String.valueOf(table.getValueAt(row, 5));
							roleText = roleText.substring(roleText.indexOf("[")+1,roleText.indexOf("]"));
							user.setRole(roleText);
						}
						user.setDriving(Integer.parseInt(String.valueOf(table.getValueAt(row, 6))));
						if("启用".equals(String.valueOf(table.getValueAt(row, 7)))) {
							user.setStatus("1");
						}else {
							user.setStatus("0");
						}
						boolean isSet = false;
						for (int i = 0; i < updateList.size(); i++) {
							User updateUser = (User) updateList.get(i);
							if (updateUser.getCode().equals(userCode)) {
								updateList.set(i, user);
								isSet = true;
								break;
							}
						}
						if (!isSet) {
							updateList.add(user);
						}

					}
					break;
				case LINE:
					if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
						String LineCode = String.valueOf(table.getValueAt(row, 0));
						List<Object> updateList = changeMapList.get("UPDATE");
						Line line = new Line();
						line.setLineCode(String.valueOf(table.getValueAt(row, 0)));
						line.setLineName(String.valueOf(table.getValueAt(row, 1)));
						if(String.valueOf(table.getValueAt(row, 2)) == null) {
							line.setStatus(null);
						}else {
							String busStatus = String.valueOf(table.getValueAt(row, 2));
							if("启用".equals(busStatus)) {
								line.setStatus("1");
							}else {
								line.setStatus("0");
							}
						}
						line.setStartLineTime(String.valueOf(table.getValueAt(row, 3)));
						line.setDirection(String.valueOf(table.getValueAt(row, 4)));
						if("上行".equals(line.getDirection())) {
							line.setDirection("1");
						}else {
							line.setDirection("0");
						}
						String stations = String.valueOf(String.valueOf(table.getValueAt(row, 5)));
						String stationCodes="";
						boolean isFirst=true;
						for(String station : stations.split(",")) {
							if(isFirst) {
								stationCodes = station.substring(station.indexOf("[")+1,station.indexOf("]"));
								isFirst = false;
							}else {
								stationCodes += ","+station.substring(station.indexOf("[")+1,station.indexOf("]"));
							}
							
						}
						line.setAllStation(stationCodes);
						
						boolean isSet = false;
						for (int i = 0; i < updateList.size(); i++) {
							Line updateLine = (Line) updateList.get(i);
							if (updateLine.getLineCode().equals(LineCode)) {
								updateList.set(i, line);
								isSet = true;
								break;
							}
						}
						if (!isSet) {
							updateList.add(line);
						}
					}
					
					break;
				case STATION:
					if (table.getValueAt(row, 0) != null && String.valueOf(table.getValueAt(row, 0)).length() > 0) {
						String StationCode = String.valueOf(table.getValueAt(row, 0));
						List<Object> updateList = changeMapList.get("UPDATE");
						Station station = new Station();
						station.setStationCode(String.valueOf(table.getValueAt(row, 0)));
						station.setStationName(String.valueOf(table.getValueAt(row, 1)));
						station.setLongitude(String.valueOf(table.getValueAt(row, 2)));
						station.setLatitude(String.valueOf(table.getValueAt(row, 3)));
						station.setRegion(String.valueOf(table.getValueAt(row, 4)));
						station.setStreet(String.valueOf(table.getValueAt(row, 4)));
						

						boolean isSet = false;
						for (int i = 0; i < updateList.size(); i++) {
							Station updateStation = (Station) updateList.get(i);
							if (updateStation.getStationCode().equals(StationCode)) {
								updateList.set(i, station);
								isSet = true;
								break;
							}
						}
						if (!isSet) {
							updateList.add(station);
						}
					}
					break;
				default:
					break;
				}
			}
		}
	}

	public void resetMap() {
		changeMapList.get("DELETE").clear();
		changeMapList.get("UPDATE").clear();
	}

	public Map<String, List<Object>> getChangeMapList() {
		return changeMapList;
	}
}
