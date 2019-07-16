package com.bus.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.DaoFactory;
import com.bus.dao.RoleDao;
import com.bus.dao.model.Role;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class RoleDaoImpl implements RoleDao{

	@Override
	public List<Role> findAllRole() {
		DaoFactory fac = new DaoFactory() ;	
		List<Role> allRole = new ArrayList<Role>();
		try {
		      Connection connect = (Connection) fac.getConnect();
		      Statement stmt = (Statement) connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from role");
		                                                                  
		while (rs.next()) {
			 
			 Role role = new Role();
			 role.setRoleCode(rs.getString("roleCode")); 
			 role.setRoleName(rs.getString("RoleName"));
			 allRole.add(role);
		      }
		    }
		    catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		    }
		 
	      
		return allRole;
	}
	

}
