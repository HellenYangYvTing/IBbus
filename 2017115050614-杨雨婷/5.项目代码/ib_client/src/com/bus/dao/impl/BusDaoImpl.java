package com.bus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.BusDao;
import com.bus.dao.DaoFactory;
import com.bus.dao.model.Bus;
import com.bus.dao.model.Role;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BusDaoImpl implements BusDao {

	@Override
	public List<Bus> find(Bus condition) {
		
		DaoFactory fac = new DaoFactory() ;	
		List<Bus> allBus = new ArrayList<Bus>();
		try {
		      Connection connect = (Connection) fac.getConnect();
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from bus");
		                                                                  
		while (rs.next()) {
		  	 
			 if(rs.getString("busLicense").equals(condition.getBusLicense()) || condition.getBusLicense().equals("")) {
			 Bus buss = new Bus();
			 buss.setBusCode(rs.getString("busCode"));
			 buss.setBusLicense(rs.getString("busLicense"));
			 buss.setBusStatus(rs.getString("busStatus"));
			 buss.setBusType(rs.getString("busType"));
			 buss.setStartTime(rs.getString("startTime"));
			 allBus.add(buss);
		      }
		}
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		 
		return allBus;
		
	}

	@Override
	public void updateBus(Bus bus) throws SQLException{
		  //1.获取数据库连接
			DaoFactory fac = new DaoFactory() ;	
			Connection connect = (Connection) fac.getConnect();
		    
		  //3、准备SQl语句
			System.out.println(bus);
			String SQL1 = "update bus  "  + " set busLicense = '"+ bus.getBusLicense()  +"' where busCode = "+ bus.getBusCode();
			String SQL2 = " update bus  " +"  set  busStatus = '" + bus.getBusStatus()  +"' where busCode = "+ bus.getBusCode();
			String SQL3 = " update bus  "+ "set busType ='" + bus.getBusType() +"' where busCode = "+ bus.getBusCode();
			String SQL4 = " update bus  "+ " set startTime ='" + bus.getStartTime() +"' where busCode = "+ bus.getBusCode();
			 
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
	public void dropBus(Bus bus) throws SQLException{
		 //1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(bus);
		String SQL = "delete from bus  where busCode = " + bus.getBusCode() ;
		 
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
	public void saveBus(Bus bus) throws SQLException{
		try {
		 //1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(bus);
		
		 
	  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
	      Statement stmt = (Statement) connect.createStatement();
	      ResultSet rs = stmt.executeQuery("select * from bus");
          int t = 1;
			while (rs.next()) {
				t ++;
			}
	  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
	       
	      String SQL = "insert into bus values (" + t +  ", '" + bus.getBusLicense() + "','"  
					+ bus.getBusType()  + "','"+ bus.getBusStatus()
		 			 + "','" + bus.getStartTime() +"')";
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
