package com.bus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.DaoFactory;
import com.bus.dao.SchedulingDao;
import com.bus.dao.model.Scheduling;
import com.bus.dao.model.Station;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SchedulingDaoImpl implements SchedulingDao {

	@Override
	public List<Scheduling> find(Scheduling condition) {
		DaoFactory fac = new DaoFactory() ;	
		List<Scheduling> schedulings = new ArrayList<Scheduling>();
		try {
			 
		      Connection connect = (Connection) fac.getConnect();
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from scheduling");
		                                                                  
		while (rs.next()) {
			System.out.println(condition);
			if((condition.getStartStation().equals("")|| rs.getString("startStation").equals(condition.getStartStation()))) {
				System.out.println("add to schLis");
				Scheduling scheduling = new Scheduling();
				scheduling.setBusCode(rs.getString("busLicense"));
				scheduling.setCode(rs.getString("code"));
				scheduling.setEndStation(rs.getString("endStation"));
				scheduling.setLineCode(rs.getString("lineCode"));
				
				//scheduling.setLineName("1");
				//	scheduling.setLineName(rs.getString("lineName"));
				
				scheduling.setStartStation(rs.getString("startStation"));
				scheduling.setTcNumber(rs.getString("tcNumber"));
				scheduling.setTcTime(rs.getString("tcTime"));
				scheduling.setUserCode(rs.getString("userCode"));
				
				//scheduling.setUserName(rs.getString("admin"));
				//	scheduling.setUserName(rs.getString("userName"));
				schedulings.add(scheduling);
			}
				}
			
		 
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		 
		return schedulings;
	}

	@Override
	public void updateScheduling(Scheduling scheduling) throws SQLException{
		//1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(scheduling);
		String SQL1 = "update scheduling  "  + " set endStation = '"+ scheduling.getEndStation()  +"' where code = "+ scheduling.getCode();
		 
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
	public void dropScheduling(Scheduling scheduling) throws SQLException{
		 //1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(scheduling);
		String SQL = "delete from line  where code = " + scheduling.getCode();
		 
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
	public void saveScheduling(Scheduling scheduling) throws SQLException{
		try {
			 //1.获取数据库连接
			DaoFactory fac = new DaoFactory() ;	
			Connection connect = (Connection) fac.getConnect();
		    
		  //3、准备SQl语句
			System.out.println(scheduling);
			
			 
		  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from scheduling");
	          int t = 1;
				while (rs.next()) {
					t ++;
				}
		  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
		       
		      String SQL = "insert into scheduling values (" + t +  ", '"
						+ scheduling.getLineCode()  + "','"+ scheduling.getTcNumber()
			 			+ "','" +scheduling.getTcTime() 
			 			+ "','" +scheduling.getUserCode()
			 			+ "','" +scheduling.getStartStation()
			 			+ "','" +scheduling.getEndStation()
			 			+ "','" +scheduling.getBusCode() +"')";
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
