package com.bus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.DaoFactory;
import com.bus.dao.StationDao;
import com.bus.dao.model.Role;
import com.bus.dao.model.Station;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class StationDaoImpl implements StationDao {

	@Override
	public List<Station> find(Station condition) {
	    DaoFactory fac = new DaoFactory() ;	
		List<Station> stations = new ArrayList<Station>();
		try {
			 
		      Connection connect = (Connection) fac.getConnect();
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from station");
		                                                                  
		while (rs.next()) {
			if(condition.getStationName().equals("")  ||(rs.getString("stationName").equals(condition.getStationName()))
			  ) {
				 Station station = new Station();
				 station.setLatitude(rs.getString("latitude"));
				 station.setLongitude(rs.getString("longitude"));
				 station.setRegion(rs.getString("region"));
				 station.setStationCode(rs.getString("stationCode"));
				 station.setStationName(rs.getString("stationName"));
				 station.setStreet(rs.getString("street"));
				 stations.add(station);
			}
			
		     }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		 return stations;
	      
	}

	@Override
	public void updateStation(Station station) throws SQLException{
		//1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(station);
		String SQL1 = "update station  "  + " set stationName = '"+ station.getStationName()  +"' where stationCode = "+ station.getStationCode();
		 
		 
		try { 
			
	  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
	      Statement stmt = (Statement) connect.createStatement();
	      
	  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
	     System.out.println(SQL1);
	   
	     
	     stmt.execute(SQL1);
	    /* stmt.executeQuery(SQL2);
	     stmt.executeQuery(SQL3);
	     stmt.executeQuery(SQL4);*/
	 
	    
	      
	  //2.关闭数据库连接
	      connect.close();
		  }
	    catch (Exception e) {
	      System.out.print("get data error!");
	      e.printStackTrace();
	    }
	}

	@Override
	public void dropStation(Station station) throws SQLException{
		 //1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(station);
		String SQL = "delete from line  where stationCode = " + station.getStationCode();
		 
	  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
	      Statement stmt = (Statement) connect.createStatement();
	  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
	     	 
				  int n = stmt.executeUpdate(SQL);
				  System.out.println(n);
			    
	   //   }
	     
	      
	  //2.关闭数据库连接
	      connect.close();
	}

	@Override
	public void saveStation(Station station) throws SQLException{
		try {
			 //1.获取数据库连接
			DaoFactory fac = new DaoFactory() ;	
			Connection connect = (Connection) fac.getConnect();
		    
		  //3、准备SQl语句
			System.out.println(station);
			
			 
		  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from station");
	          int t = 1;
				while (rs.next()) {
					t ++;
				}
		  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
		       
		      String SQL = "insert into station values (" + t +  ", '" + station.getStationName() + "','"  
						+ station.getLongitude() + "','"+ station.getLatitude()
						+ "','" + station.getRegion() + "','" + station.getStreet() +"')";
		      System.out.println(SQL);
		      int n = stmt.executeUpdate(SQL);
		      System.out.println(n);
		      
		  //2.关闭数据库连接
		      connect.close();
			}
			   catch (Exception e) {
				      System.out.print("save data error!");
				      e.printStackTrace();
				    }
		      
	}
}
