package com.bus.dao.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.DaoFactory;
import com.bus.dao.UserDao;
import com.bus.dao.model.Line;
import com.bus.dao.model.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class UserDaoImpl implements UserDao {
	 
	@Override
	public String findUserPasswordByName(String userName) {
		DaoFactory fac = new DaoFactory() ;	 
		try {
		      Connection connect = (Connection) fac.getConnect();
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from sysuser");
		                                                              //user 为你表的名称	      
		while (rs.next()) {
			if(rs.getString("loginName").equals(userName))
			return rs.getString("password");
		      }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		 
	      return null;
	}

	@Override
	public List<String> findAuthorityListByName(String userName) {
		DaoFactory fac = new DaoFactory() ;	 
		List<String> AuthorityList = new ArrayList<>();
		try {
		      Connection connect = (Connection) fac.getConnect();
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from v_permission");
		                                                           
		while (rs.next()) {
			if(rs.getString("loginName").equals(userName)) {
				AuthorityList.add(rs.getString("permissionName"));
			}
			 
		      }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		return AuthorityList;
	}

	@Override
	public List<User> find(User condition) {
		List<User> users = new ArrayList<User>();
		try {
			  DaoFactory fac = new DaoFactory() ;	
		      Connection connect = (Connection) fac.getConnect();
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from sysuser");
		                                                                  
		while (rs.next()) {
			System.out.println(condition);
			if( condition.getName().equals("")||rs.getString("loginName").equals(condition.getName()) ) {		 
			User user = new User();
			user.setCode(rs.getString("code"));
			user.setDriving(rs.getInt("driving"));
			user.setIdCard(rs.getString("idCard"));
			user.setLoginName(rs.getString("loginName"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setPhone(rs.getString("phone"));
			user.setRole(rs.getString("role"));
			user.setStatus(rs.getString("status"));
			users.add(user);
			}
		      }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		return users;
	}

	@Override
	public void changePass(String userCode, String pass) {
		//1.获取数据库连接
				DaoFactory fac = new DaoFactory() ;	
				Connection connect = (Connection) fac.getConnect();
			    
			  //3、准备SQl语句
				System.out.println(userCode);
				String SQL1 = "update sysuser  "  + " set password = '"+ pass +"' where code = "+ userCode;
				 
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
	public void save(User user) {
		try {
			 //1.获取数据库连接
			DaoFactory fac = new DaoFactory() ;	
			Connection connect = (Connection) fac.getConnect();
		    
		  //3、准备SQl语句
			System.out.println(user);
			
			 
		  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
		      Statement stmt = (Statement) connect.createStatement();
		      System.out.println("-----------------");
		      ResultSet rs = stmt.executeQuery("select * from sysuser");
	          int t = 1;
				while (rs.next()) {
					t ++;
				}
				
		  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
		      int ttt = 1;
		      String u = user.getRole();
				if(u.equals("调度员[1]")) {
					ttt = 2;
				}
				else if(u.equals("驾驶员[2]")){
					ttt = 3;
				}
		      String SQL = "insert into sysuser values (" + t +  ", '" + user.getLoginName()+ "','"  
						+ user.getPassword()  + "','"+ user.getName()
			 			+ "','" + user.getPhone() 
			 			+ "','" + user.getIdCard()
			 			+ "','" + ttt
			 			+ "','" + user.getDriving()
			 			+ "','" + user.getStatus()+"')";
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

	@Override
	public void update(User user) {
		//1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
	    
	  //3、准备SQl语句
		System.out.println(user);
		String SQL1 = "update sysuser  "  + " set loginName = '"+ user.getLoginName() +"' where code = "+ user.getCode();
		 
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
	public void dropUser(User user){
		 //1.获取数据库连接
		DaoFactory fac = new DaoFactory() ;	
		Connection connect = (Connection) fac.getConnect();
		try {
	  //3、准备SQl语句
		System.out.println(user);
		String SQL = "delete from sysuser  where code = " + user.getCode();
		 
	  //4.1 获取用于执行SQl语句的对象statement 调用connection的creatStatement（）
	      Statement stmt = (Statement) connect.createStatement();
	  //4.2 调用Statement对象的executeUpdate（sql）执行SQL语句进行插入
	     	 
				  int n = stmt.executeUpdate(SQL);
				  System.out.println(n);
			    
	   //   }
	     
	      
	  //2.关闭数据库连接
	      connect.close();
		 }
	    catch (Exception e) {
	      System.out.print("get data error!");
	      e.printStackTrace();
	    }
	}
}
