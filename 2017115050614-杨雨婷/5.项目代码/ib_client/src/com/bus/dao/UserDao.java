package com.bus.dao;

import java.util.List;

import com.bus.dao.model.User;

public interface UserDao {
   //public User  findUserByName(String userName);
   
   public String findUserPasswordByName(String userName);
   
   public List<String> findAuthorityListByName(String userName);
   
   public List<User> find(User Condition);
   
   public void changePass(String userCode, String pass);
   
   public void save(User user);
   
   public void update(User user);
   
   public void dropUser(User user);
}
