/**  
 * @Title: TableModelUtil.java
 * @Package com.bus.utils
 * @author zhangjingming
 * @date 2017年11月30日
 * @version V1.0  
 */
package com.bus.utils;

import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;

import com.bus.constants.Constants;
import com.bus.dao.model.Bus;
import com.bus.dao.model.Line;
import com.bus.dao.model.Scheduling;
import com.bus.dao.model.Station;
import com.bus.dao.model.User;
import com.bus.enums.ModuleEnum;
import com.bus.view.dto.ComboBoxDto;
import com.bus.view.listener.CustomerTableModelListener;
import com.github.lgooddatepicker.tableeditors.DateTableEditor;

/**
 * @ClassName: TableModelUtil
 * @Description: 表格模型工具类
 * @author zhangjingming
 * @date 2017年11月30日
 * @since JDK 1.8
 */
public class TableModelUtil {
	
	/**
	 * @Title: getTableModel
	 * @Description: 根据业务模块及数据创建TableModel并返回JTable对象
	 * @param moduleEnum 模块
	 * @param data 业务数据集合
	 * @param focusListener 焦点变化监听
	 * @return 创建好的业务表格
	 */
	public static JTable getTableByModule(ModuleEnum moduleEnum, List<?> data, FocusListener focusListener,List<ComboBoxDto> roleComboboxList) {
		DefaultTableModel tableModel = new DefaultTableModel() {
			/**
			 * @Fields serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				}
				return true;
			}
			
		};
		// 初始化table
		final JTable table = new JTable(tableModel);
		tableModel.addTableModelListener(new CustomerTableModelListener(moduleEnum,table));
		// 设置表头不可移动
		table.getTableHeader().setReorderingAllowed(false);
		// 设置行的高度
		table.setRowHeight(23);
		table.setAutoscrolls(true);
		table.setFillsViewportHeight(true);
		final Vector<Object> dataVector = new Vector<>();
		// 日期控件
		DateTableEditor dateEdit = new DateTableEditor();
		dateEdit.getDatePickerSettings().setTranslationClear("清空");
		dateEdit.getDatePickerSettings().setTranslationToday("今天");
		switch (moduleEnum) {
		case BUS:
			for (int i = 0; i < data.size(); i++) {
				Vector<Object> buses = new Vector<>();
				Bus bus = (Bus) data.get(i);
				buses.add(bus.getBusCode());
				buses.add(bus.getBusLicense());
				buses.add(bus.getBusType());
				buses.add(bus.getBusStatus());
				buses.add(bus.getStartTime());
				dataVector.add(buses);
			}
			// 设置车辆状态下拉列表
			JComboBox<ComboBoxDto> busCbx = new JComboBox<>();
			busCbx.addItem(new ComboBoxDto("1", "启用"));
			busCbx.addItem(new ComboBoxDto("0", "禁用"));;
			tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
			// 创建编辑器
			TableCellEditor busTce = new DefaultCellEditor(busCbx);
			// 车辆状态,设置下拉
			table.getColumnModel().getColumn(Constants.BUSSTATUS_COLUMN_SHOW).setCellEditor(busTce);
			// 时间列添加时间可选
			table.getColumnModel().getColumn(Constants.BUS_START_DATE_SHOW).setCellEditor(dateEdit);
			break;
		case USER:
			for (int i = 0; i < data.size(); i++) {
				Vector<Object> users = new Vector<>();
				User user = (User) data.get(i);
				users.add(user.getCode());
				users.add(user.getLoginName());
				users.add(user.getName());
				users.add(user.getPhone());
				users.add(user.getIdCard());
				users.add(user.getRole());
				users.add(user.getDriving());
				users.add(user.getStatus());
				users.add(user.getPassword());
				dataVector.add(users);
			}
			//标题
			tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
			// 设置用户状态下拉列表
			JComboBox<String> cbx = new JComboBox<>();
			cbx.addItem("启用");
			cbx.addItem("禁用");
			//  创建编辑器
			TableCellEditor tce = new DefaultCellEditor(cbx);
			// 下表为第7列的是用户状态,设置下拉
			table.getColumnModel().getColumn(Constants.USERSTATUS_COLUMN_SHOW).setCellEditor(tce);
			
			// 设置角色下拉列表
			JComboBox<String> roleCombobox = new JComboBox<>();
			for(ComboBoxDto cbd : roleComboboxList) {
				if(!"-1".equals(cbd.getKey())) {
					roleCombobox.addItem(cbd.getValue()+"["+cbd.getKey()+"]");
				}
			}
			
			//  创建编辑器
			TableCellEditor roleTce = new DefaultCellEditor(roleCombobox);
			// 下表为第7列的是用户状态,设置下拉
			table.getColumnModel().getColumn(Constants.ROLE_COLUMN_SHOW).setCellEditor(roleTce);
			
			// 设置驾龄下拉列表
			JComboBox<String> drvingCombobox = new JComboBox<>();
			for(int i = 0; i < 61; i++) {
				drvingCombobox.addItem(i+"");
			}
			//  创建编辑器
			TableCellEditor drvingTce = new DefaultCellEditor(drvingCombobox);
			table.getColumnModel().getColumn(6).setCellEditor(drvingTce);
			
			// 隐藏密码
			table.getColumnModel().getColumn(8).setMaxWidth(0);
			table.getColumnModel().getColumn(8).setMinWidth(0);
			break;
		case LINE:
			for (int i = 0; i < data.size(); i++) {
				Vector<Object> lines = new Vector<>();
				Line line = (Line) data.get(i);
				lines.add(line.getLineCode());
				lines.add(line.getLineName());
				lines.add(line.getStatus());
				lines.add(line.getStartLineTime());
				lines.add(line.getDirection());
				lines.add(line.getAllStation());
				dataVector.add(lines);
			}
			// 设置线路状态下拉列表
			JComboBox<String> lineCbx = new JComboBox<>();
			lineCbx.addItem("启用");
			lineCbx.addItem("禁用");
			tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
			//  创建编辑器
			TableCellEditor lineTce = new DefaultCellEditor(lineCbx);
			// 线路状态,设置下拉
			table.getColumnModel().getColumn(Constants.LINESTATUS_COLUMN_SHOW).setCellEditor(lineTce);
			
			// 设置线路方向下拉列表
			JComboBox<String> directionCbx = new JComboBox<>();
			directionCbx.addItem(Constants.CHOOSE);
			directionCbx.addItem("上行");
			directionCbx.addItem("下行");
			//  创建编辑器
			TableCellEditor directionTce = new DefaultCellEditor(directionCbx);
			// 线路方向,设置下拉
			table.getColumnModel().getColumn(Constants.LINEDIR_COLUMN_SHOW).setCellEditor(directionTce);
			
			// 包含站点编辑器
			JTextField containStationsField = new JTextField();
			table.getColumnModel().getColumn(Constants.LINESTATIONS_COLUMN_SHOW).setCellEditor(new DefaultCellEditor(containStationsField));
			
			table.getColumnModel().getColumn(Constants.LINE_START_DATE_SHOW).setCellEditor(dateEdit);
			
			containStationsField.addFocusListener(focusListener);
			break;
		case STATION:
			for (int i = 0; i < data.size(); i++) {
				Vector<Object> stations = new Vector<>();
				Station station = (Station) data.get(i);
				stations.add(station.getStationCode());
				stations.add(station.getStationName());
				stations.add(station.getLongitude());
				stations.add(station.getLatitude());
				stations.add(station.getRegion());
				stations.add(station.getStreet());
				dataVector.add(stations);
			}
			tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
			break;
		case SCHEDULING:
			for (int i = 0; i < data.size(); i++) {
				Vector<Object> schedulings = new Vector<>();
				Scheduling scheduling = (Scheduling) data.get(i);
				
				schedulings.add(scheduling.getCode());
				schedulings.add(scheduling.getLineCode());
				schedulings.add(scheduling.getTcNumber());
				schedulings.add(scheduling.getTcTime());
				schedulings.add(scheduling.getUserCode());
				schedulings.add(scheduling.getStartStation());
				schedulings.add(scheduling.getEndStation());
				schedulings.add(scheduling.getBusCode());
			 
				dataVector.add(schedulings);
			}
			tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
			break;
		default:
			break;
		}
		// 设置列宽
		setColumnPreferredWidth(table, moduleEnum);
		
		// 表格排序
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		table.setRowSorter(sorter);
		
		return table;
	}
	
	/**
	 * @Title: setColumnPreferredWidth
	 * @Description: 设置列宽
	 * @param table 表格对象
	 * @param moduleEnum 模块枚举,用于获取表格列宽
	 */
	private static void setColumnPreferredWidth(JTable table, ModuleEnum moduleEnum) {
		// 如果相关功能的表格列宽不为空,则设置对应列宽,否则用默认列宽
		if (moduleEnum.getPreferredWidth() != null) {
			for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(moduleEnum.getPreferredWidth().get(i));
			}
		}
	}
	
	/**
	 * @Title: getTableDataListByModule
	 * @Description: 根据模块获取List<Model>类型的表格数据
	 * @param moduleEnum 模块
	 * @param data Vector类型的表格数据
	 * @return List<Model>类型的表格数据
	 */
	@SuppressWarnings("rawtypes")
	public static List<Object> getTableDataListByModule(ModuleEnum moduleEnum, Vector<Object> data) {
		List<Object> datas = new ArrayList<>();
		Vector obj = new Vector<>();
		for (Object t : data) {
			switch (moduleEnum) {
			case BUS:
				obj = (Vector) t;
				Bus bus = new Bus();
				bus.setBusCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
				bus.setBusLicense(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
				bus.setBusType(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
				bus.setBusStatus(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
				bus.setStartTime(obj.get(4) == null ? null : String.valueOf(obj.get(4)));
				datas.add(bus);
				break;
			case USER:
				obj = (Vector) t;
				User user = new User();
				user.setCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
				user.setLoginName(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
				user.setName(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
				user.setPhone(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
				user.setIdCard(obj.get(4) == null ? null : String.valueOf(obj.get(4)));
				if(obj.get(5) == null ||String.valueOf(obj.get(5)).length()==0) {
					user.setRole(null);
				}else {
					String roleText = String.valueOf(obj.get(5));
				//	roleText = roleText.substring(roleText.indexOf("[")+1,roleText.indexOf("]"));
					user.setRole(roleText);
				}
				user.setDriving(obj.get(6) == null ? 0 : Integer.parseInt(String.valueOf(obj.get(6))));
				if("启用".equals(String.valueOf(obj.get(7)))) {
					user.setStatus("1");
				}else {
					user.setStatus("0");
				}
				user.setPassword(MD5Util.md5Encode("123456"));
				datas.add(user);
				break;
			case LINE:
				obj = (Vector) t;
				Line line = new Line();
				line.setLineCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
				line.setLineName(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
				line.setStatus(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
				line.setStartLineTime(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
				line.setDirection(obj.get(4) == null ? null : String.valueOf(obj.get(4)));
				
				if("启用".equals(line.getStatus())) {
					line.setStatus("1");
				}else {
					line.setStatus("0");
				}
				if("上行".equals(line.getDirection())) {
					line.setDirection("1");
				}else {
					line.setDirection("0");
				}
				
				if(obj.get(5) == null) {
					line.setAllStation(null);
				}else {
					String allStation = String.valueOf(obj.get(5));
					String[] allStationSplit = allStation.split(",");
					String stationCodes = "";
					for (String station : allStationSplit) {
						String code = station.substring(station.indexOf("[")+1, station.lastIndexOf("]"));
						if("".equals(stationCodes)) {
							stationCodes += code;
						}else {
							stationCodes += "," + code;
						}
					}
					line.setAllStation(stationCodes);
				}
				datas.add(line);
				break;
			case STATION:
				obj = (Vector) t;
				Station station = new Station();
				station.setStationCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
				station.setStationName(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
				station.setLongitude(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
				station.setLatitude(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
				station.setRegion(obj.get(4) == null ? null : String.valueOf(obj.get(4)));
				station.setStreet(obj.get(5) == null ? null : String.valueOf(obj.get(5)));
				datas.add(station);
				break;
			case SCHEDULING:
				obj = (Vector) t;
				Scheduling scheduling = new Scheduling();
				/*schedulings.add(scheduling.getCode());
				schedulings.add(scheduling.getLineCode());
				schedulings.add(scheduling.getTcNumber());
				schedulings.add(scheduling.getTcTime());
				schedulings.add(scheduling.getUserCode());
				schedulings.add(scheduling.getStartStation());
				schedulings.add(scheduling.getEndStation());
				schedulings.add(scheduling.getBusCode());*/
				scheduling.setCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
				scheduling.setLineCode(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
				scheduling.setTcNumber(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
				scheduling.setTcTime(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
				scheduling.setUserCode(obj.get(4) == null ? null : String.valueOf(obj.get(4)));
				scheduling.setStartStation(obj.get(5) == null ? null : String.valueOf(obj.get(5)));
				scheduling.setEndStation(obj.get(6) == null ? null : String.valueOf(obj.get(6)));
				scheduling.setBusCode(obj.get(7) == null ? null : String.valueOf(obj.get(7)));
				//scheduling.setLineCode(obj.get(8) == null ? null : String.valueOf(obj.get(8)));
				//scheduling.setUserCode(obj.get(9) == null ? null : String.valueOf(obj.get(9)));
				//String start = scheduling.getStartStation();
				//String end = scheduling.getEndStation();
				//start = start.substring(start.indexOf("[")+1, start.lastIndexOf("]"));
				//end = end.substring(end.indexOf("[")+1, end.lastIndexOf("]"));
				//scheduling.setStartStation(start);
				//scheduling.setEndStation(end);
				datas.add(scheduling);
				break;

			default:
				break;
			}
		}
		return datas;
	}
	
}
