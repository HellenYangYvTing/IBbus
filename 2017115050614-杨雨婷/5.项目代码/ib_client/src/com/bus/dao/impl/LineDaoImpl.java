package com.bus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.DaoFactory;
import com.bus.dao.LineDao;
import com.bus.dao.model.Line;
import com.bus.dao.model.Station;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class LineDaoImpl implements LineDao{

	@Override
	public List<Line> find(Line condition) {
		List<Line> lines = new ArrayList<Line>();
		try {
			  DaoFactory fac = new DaoFactory() ;	
		      Connection connect = (Connection) fac.getConnect();
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from line");
		                                                                  
		while (rs.next()) {
			if(rs.getString("lineName").equals(condition.getLineName()) || condition.getLineName().equals("")) {
			Line line = new Line();
			line.setLineCode(rs.getString("lineCode"));
			line.setLineName(rs.getString("lineName"));
			line.setStatus(rs.getString("status"));
			line.setStartLineTime(rs.getString("startLineTime"));
			line.setDirection(rs.getString("direction"));
			lines.add(line);
			}
		      }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		 return lines;
	      
		 
	}

	@Override
	public void updateLine(Line line) throws SQLException{
		//1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(line);
		String SQL1 = "update line  "  + " set lineName = '"+ line.getLineName()  +"' where busCode = "+ line.getLineCode();
		 
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
	public int dropLine(Line line) throws SQLException {
		 //1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(line);
		String SQL = "delete from line  where lineCode = " + line.getLineCode() ;
		 
	  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
	      Statement stmt = (Statement) connect.createStatement();
	  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
	     	 
				  int n = stmt.executeUpdate(SQL);
				  System.out.println(n);
			    
	   //   }
	     
	      
	  //2.关闭数据库连接
	      connect.close();
		return 1; 
	}

	@Override
	public void saveLine(Line line) throws SQLException{
		try {
			 //1.获取数据库连接
			DaoFactory fac = new DaoFactory() ;	
			Connection connect = (Connection) fac.getConnect();
		    
		  //3、准备SQl语句
			System.out.println(line);
			
			 
		  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from line");
	          int t = 1;
				while (rs.next()) {
					t ++;
				}
		  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
		       
		      String SQL = "insert into line values (" + t +  ", '" + line.getLineName() + "','"  
						+ line.getStatus()  + "','"+ line.getStartLineTime()
			 			 + "','" +line.getDirection() +"')";
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
